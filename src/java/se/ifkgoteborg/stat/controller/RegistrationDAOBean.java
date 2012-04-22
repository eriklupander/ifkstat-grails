package se.ifkgoteborg.stat.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import se.ifkgoteborg.stat.controller.adapter.SquadPlayer;
import se.ifkgoteborg.stat.model.Club;
import se.ifkgoteborg.stat.model.Country;
import se.ifkgoteborg.stat.model.Formation;
import se.ifkgoteborg.stat.model.FormationPosition;
import se.ifkgoteborg.stat.model.Game;
import se.ifkgoteborg.stat.model.Ground;
import se.ifkgoteborg.stat.model.PlayedForClub;
import se.ifkgoteborg.stat.model.Player;
import se.ifkgoteborg.stat.model.Position;
import se.ifkgoteborg.stat.model.PositionType;
import se.ifkgoteborg.stat.model.Referee;
import se.ifkgoteborg.stat.model.SquadSeason;
import se.ifkgoteborg.stat.model.Tournament;
import se.ifkgoteborg.stat.model.TournamentSeason;
import se.ifkgoteborg.stat.util.DateFactory;


public class RegistrationDAOBean implements RegistrationDAO {

	EntityManager em;
	
	/* (non-Javadoc)
	 * @see se.ifkgoteborg.stat.controller.RegistrationDAO#getOrCreateGround(java.lang.String)
	 */
	@Override
	public Ground getOrCreateGround(String name) {
		
		try {
			return (Ground) em.createQuery("select g from Ground g WHERE g.name=:name").setParameter("name", name).getSingleResult();
		} catch(NoResultException nre) {
			Ground g = new Ground();
			g.setName(name);
			return em.merge(g);
		}
	}
	
		
	/* (non-Javadoc)
	 * @see se.ifkgoteborg.stat.controller.RegistrationDAO#getOrCreateClub(java.lang.String)
	 */
	@Override
	public Club getOrCreateClub(String name) {
		
		try {
			return (Club) em.createQuery("select g from Club g WHERE lower(g.name)=:name").setParameter("name", name.trim().toLowerCase()).getSingleResult();
		} catch(NoResultException nre) {
			Club g = new Club();
			g.setName(name.trim());
			Club c = em.merge(g);
			em.flush();
			return c;
		}
	}
	
	/* (non-Javadoc)
	 * @see se.ifkgoteborg.stat.controller.RegistrationDAO#getOrCreatePlayer(java.lang.String, java.util.Date)
	 */
	@Override
	public Player getOrCreatePlayer(String name, Date dateOfBirth) {
		
		try {
			Player p = (Player) em.createQuery("select p from Player p WHERE lower(p.name)=:name")
					.setParameter("name", name.trim().toString())
					.getSingleResult();
			p.getGames().size();
			return p;
		} catch(NoResultException nre) {
			Player g = new Player();
			g.setName(name);
			g.setDateOfBirth(dateOfBirth);
			return em.merge(g);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Player> getAllPlayers() {
		System.out.println("Is EM null?: " + (em == null));
		Query q = em.createQuery("select p from Player p");
		List<Player> l = q.getResultList();
		for(Player p : l) {
			p.getGames().size();
			p.getClubs().size();
			
		}
		System.out.println("Returning " + l.size() + " players");
		return l;
	}


	@Override
	public SquadSeason importPlayers(List<SquadPlayer> players, String season) {
		
		SquadSeason s = getOrCreateSquadSeason(season.trim());
		
		Club club = getDefaultClub();
		
		for(SquadPlayer sp : players) {
			Player p = null;
			try {
				p = (Player) em.createQuery("select g from Player g WHERE lower(g.name)=:name")
				.setParameter("name", sp.name.trim().toLowerCase())
				.getSingleResult();
				
			} catch (NoResultException e) {
				// If not found, create...
				p = new Player();
				p.setName(sp.name);
				p.setSquadNumber(sp.nr);
				p = em.merge(p);
				
				
			}
			PlayedForClub pfc = new PlayedForClub();
			pfc.setClub(club);
			pfc.setPlayer(p);
			pfc.setSeason(s);
			pfc.setSquadNr(sp.nr);
			pfc.setImportIndex(sp.index);
			
			pfc = em.merge(pfc);
			
			p.getClubs().add(pfc);
			
			System.out.println("Created player " + sp.name);
			em.flush();
		}
		return s;
	}


	@Override
	public void persist(Object t) {
		em.persist(t);
		em.flush();
	}


	@Override
	public Club getDefaultClub() {
		return (Club) em.createQuery("select c from Club c WHERE c.defaultClub=:defaultClub").setParameter("defaultClub", true).getSingleResult();
	}


	@Override
	public Map<Integer, Player> loadSquad(String season) {
		SquadSeason s = getOrCreateSquadSeason(season);
		
		Date fromDate = s.getStartYear();
		Date toDate = s.getEndYear();
		
		List<Object[]> resultList = em.createQuery("select p, pfc.importIndex from PlayedForClub pfc JOIN pfc.player p WHERE pfc.season.id = :s")
			.setParameter("s", s.getId())			
			.getResultList();
		
		Map<Integer, Player> map = new HashMap<Integer, Player>();

		for(Object[] o : resultList) {
			Player p = (Player) o[0];
			Integer index = (Integer) o[1];
			map.put(index, p);
		}
		System.out.println("Loaded squad for season: " + season + ". Got " + map.size() + " players");
		return map;
	}


	@Override
	public List<Game> getGames(int year) {
		Date fromDate = DateFactory.get(year, 0, 1);
		Date toDate = DateFactory.get(year+1, 0, 1);
		
		List<Game> resultList = em.createQuery("select g from Game g").getResultList(); // WHERE g.dateOfGame >= :fromDate AND g.dateOfGame < :toDate")
//			.setParameter("fromDate", fromDate)
//			.setParameter("toDate", toDate)
//			.getResultList();
		
		for(Game g : resultList) {
			g.getEvents().size();
			g.getGameParticipation().size();
		}
		return resultList;
	}
	
	@Override
	public List<Game> getGames(Long tournamentId, String season) {
		SquadSeason s = getOrCreateSquadSeason(season);
		Date start = s.getStartYear();
		
		return em.createQuery("select g from Game g WHERE g.tournamentSeason.start = :start AND g.tournamentSeason.tournament.id = :tournamentId")
			.setParameter("start", start)
			.setParameter("tournamentId", tournamentId)
			.getResultList();
	}


	@Override
	public Game getGame(Long id) {
		Game g = (Game) em.createQuery("select g from Game g WHERE g.id=:id")
				.setParameter("id", id)
				.getSingleResult();
		g.getEvents().size();
		g.getGameParticipation().size();
		g.getFormation().getFormationPositions().size();
		return g;
	}


	@Override
	public FormationPosition getFormationPosition(String formationName,
			int positionId) {
		try {
			return (FormationPosition) em.createQuery("select fp from FormationPosition fp where fp.formation.name=:formationName AND fp.index = :positionId")
				.setParameter("formationName", formationName)
				.setParameter("positionId", positionId)
				.getSingleResult();
		} catch (Exception e) {
			System.out.println("Error, could not get FormationPosition: " + e.getMessage() + " Formation name: " + formationName + " positionId: " + positionId);
			return new FormationPosition();
		}
	}


	@Override
	public List<Tournament> getTournaments() {
		return em.createQuery("select t from Tournament t ORDER BY t.name").getResultList();
	}
	
	@Override
	public List<TournamentSeason> getTournamentSeasons(Long tournamentId) {
		System.out.println("ENTER - getTournamentSeasons(" + tournamentId  + ")");
		return em.createQuery("select ts from TournamentSeason ts WHERE ts.tournament.id=:tournamentId ORDER BY ts.start DESC")
				.setParameter("tournamentId", tournamentId)
				.getResultList();
	}


	@Override
	public TournamentSeason getTournamentSeasonByName(String tournamentName,
			SquadSeason season) {
		System.out.println("ENTER - getTournamentSeasonByName(" + tournamentName + ", " + season.getName() + ")");
		
		
		
		TournamentSeason ts;
		try {
			ts = (TournamentSeason) em.createQuery("select ts from TournamentSeason ts WHERE lower(ts.tournament.name)=:tname AND ts.season.id=:seasonId")
				.setParameter("tname", tournamentName.toLowerCase())
				.setParameter("seasonId", season.getId())
				.getSingleResult();
		} catch (NoResultException e) {
			
			Tournament t = getOrCreateTournamentByName(tournamentName);
			ts = new TournamentSeason(t, season.getStartYear(), season.getEndYear());
			
			ts.setSeason(season);
			ts.setStart(season.getStartYear());
			ts = em.merge(ts);
		}
		
		
		ts.getGames().size();
		
		return ts;
	}

	private SquadSeason getOrCreateSquadSeason(String season) {
		try {
			return (SquadSeason) em.createQuery("select s from SquadSeason s WHERE lower(s.name) = :sname").
					setParameter("sname", season.toLowerCase().trim()).
					getSingleResult();
		} catch (NoResultException e) {
			// Create new tournament with this name			
			return em.merge(createSeasonFromString(season)); 
		}
	}
	
	private SquadSeason getOrCreateTournamentSeason(String season) {
		try {
			return (SquadSeason) em.createQuery("select s from TournamentSeason s WHERE lower(s.name) = :sname").
					setParameter("sname", season.toLowerCase().trim()).
					getSingleResult();
		} catch (NoResultException e) {
			// Create new tournament with this name			
			return em.merge(createSeasonFromString(season)); 
		}
	}
	
	SquadSeason createSeasonFromString(String seasonName) {
		int startYear;
		int endYear;
		if(seasonName.trim().indexOf("/") > -1) {
			String[] parts = seasonName.trim().split("/");
			if(parts.length > 0)
				if(parts[0].trim().length() == 2) {
					parts[0] = "19" + parts[0].trim();
				}
				
				startYear = Integer.parseInt(parts[0].trim());
			if(parts.length > 1) {
				if(parts[1].trim().length() < 3) {
					endYear = Integer.parseInt(parts[0].substring(0, 2) + "" + parts[1].trim());
				} else {
					endYear = Integer.parseInt(parts[1].trim());
				}
			} else {
				endYear = startYear;
			}
		} else {
			startYear = Integer.parseInt(seasonName.trim());
			endYear = startYear;
		}
		
		return new SquadSeason(seasonName, startYear, endYear);
	}


	@Override
	public Tournament getOrCreateTournamentByName(String tournamentName) {
		try {
			return (Tournament) em.createQuery("select t from Tournament t WHERE lower(t.name) = :tname").
					setParameter("tname", tournamentName.toLowerCase().trim()).
					getSingleResult();
		} catch (NoResultException e) {
			// Create new tournament with this name
			return em.merge(new Tournament(tournamentName, false));
		}
	}


	@Override
	public Formation getFormationByName(String formation) {
		try {
			Formation f = (Formation) em.createQuery("select f from Formation f WHERE f.name=:formationName")
				.setParameter("formationName", formation)
				.getSingleResult();
			//f.getUsedInGames().size();
			return f;
		} catch (NoResultException e) {
			Formation f = em.merge(new Formation(formation));
			//f.getUsedInGames().size();
			return f;
		}
	}


	@Override
	public void saveGame(Game g) {
		if(g.getFormation() != null && g.getFormation().getId() != null) {
			Formation f = em.find(Formation.class, g.getFormation().getId());
			g.setFormation(f);
		} else {
			g.setFormation(getFormationByName(g.getFormation().getName()));
		}
		
		// Check if game exists (by date)
		try {
			Game dbG = (Game) em.createQuery("select g from Game g WHERE g.dateOfGame=:date")
				.setParameter("date", g.getDateOfGame())
				.getSingleResult();
			
			// OOPS! Game already exists. Skip.
		} catch (NoResultException e) {
			// No previous game that day. Persist
			em.persist(g);
		}
		
		
	}
	
	@Override
	public Game updateGame(Game detachedGame) {
		
		if(detachedGame.getReferee() != null && detachedGame.getReferee().getId() == null) {
			Referee ref = em.merge(detachedGame.getReferee());
			detachedGame.setReferee(ref);
		}
		
		return em.merge(detachedGame);
	}


	@Override
	public EntityManager getPersistenceUnit() {		
		return em;
	}


	@Override
	public List<Formation> getFormations() {
		return em.createQuery("select f from Formation f").getResultList();
	}


	@Override
	public List<Club> getClubs() {
		return em.createQuery("select c from Club c ORDER BY c.name").getResultList();
	}
	
	@Override
	public List<Ground> getGrounds() {
		return em.createQuery("select g from Ground g ORDER BY g.name").getResultList();
	}
	
	@Override
	public List<Referee> getReferees() {
		return em.createQuery("select r from Referee r ORDER BY r.name").getResultList();
	}


	@Override
	public void savePlayer(Player player) {
		em.persist(player);
	}


	@Override
	public Player updatePlayer(Player player) {
		return em.merge(player);
	}


	@Override
	public List<Position> getPositions() {
		return em.createQuery("select p from Position p ORDER BY p.name").getResultList();
	}


	@Override
	public List<PositionType> getPositionTypes() {
		return em.createQuery("select p from PositionType p ORDER BY p.name").getResultList();
	}


	@Override
	public List<SquadSeason> getSeasons() {
		List<SquadSeason> resultList = em.createQuery("select s from SquadSeason s ORDER BY s.endYear DESC").getResultList();
		
		for(SquadSeason s : resultList) {
			s.getSquad().size();
		}
		return resultList;
	}


	@Override
	public void removePlayedForClub(PlayedForClub selectedItem) {
		if(selectedItem == null || selectedItem.getId() == null) {
			return;
		}
		PlayedForClub pfc = em.find(PlayedForClub.class, selectedItem.getId());
		em.remove(pfc);
		em.flush();
	}


	@Override
	public void updatePlayerSeason(PlayedForClub pfc) {
		em.merge(pfc);
	}
	
	@Override
	public void createPlayerSeason(PlayedForClub pfc) {
		em.persist(pfc);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Player> getAllPlayersShallow() {
		//return em.createQuery("select p from Player p ORDER BY p.name").getResultList();
		return em.createNamedQuery("allPlayers").getResultList();
	}


	@Override
	public SquadSeason getSquadSeason(Long id) {
		SquadSeason ss = em.find(SquadSeason.class, id);
		ss.getSquad().size();
		return ss;
	}


	@Override
	public void updateGameWithNote(Date date, String note) {
		try {
			Game g = (Game) em.createQuery("select g from Game g WHERE g.dateOfGame = :date").setParameter("date", date).getSingleResult();
			g.setGameSummary(note);
			System.out.println("Saved note: " + note + " for game on date " + DateFactory.format(date));
		} catch (NoResultException e) {
			System.out.println("Could not find a game for date: " + DateFactory.format(date));
		}
	}


	@Override
	public List<Country> getCountries() {
		return em.createQuery("select c from Country c ORDER BY c.name").getResultList();
	}


	@Override
	public void updateGround(Ground ground) {
		em.merge(ground);
	}


	@Override
	public SquadSeason getSquadForSeason(Long seasonId) {
		SquadSeason ss = em.find(SquadSeason.class, seasonId);
		ss.getSquad().size();
		return ss;
	}


	@Override
	public TournamentSeason getTournamentSeason(Long tournamentId,
			String seasonStr) {
		try {
			return (TournamentSeason) em.createQuery("select ts from TournamentSeason ts where ts.tournament.id=:tournamentId AND ts.season.name=:seasonStr")
				.setParameter("tournamentId", tournamentId)
				.setParameter("seasonStr", seasonStr)
				.getSingleResult();
		} catch (Exception e) {
			System.err.println("Could not find TournamentSeason by: getTournamentSeason(" + tournamentId + "," + seasonStr + ")");
			return null;
		}
	}


	@Override
	public Tournament createTournament(String tournamentName) {
		Tournament t = new Tournament(tournamentName, false);
		return em.merge(t);
	}


	@Override
	public List<TournamentSeason> getTournamentSeasonsOfSeason(
			Long squadSeasonId) {
		return em.createQuery("select ts from TournamentSeason ts WHERE ts.season.id=:id").setParameter("id", squadSeasonId).getResultList();
		
	}


	@Override
	public TournamentSeason saveTournamentSeason(TournamentSeason tournamentSeason) {
		return em.merge(tournamentSeason);
	}


	@Override
	public void removeTournamentSeasonFromSeason(TournamentSeason ts) {
		em.remove(ts);
	}

	
}
