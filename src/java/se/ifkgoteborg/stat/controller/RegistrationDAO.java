package se.ifkgoteborg.stat.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

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

public interface RegistrationDAO {

	public Ground getOrCreateGround(String name);

	public Club getOrCreateClub(String name);

	public Player getOrCreatePlayer(String name, Date dateOfBirth);

	public List<Player> getAllPlayers();

	public SquadSeason importPlayers(List<SquadPlayer> players, String season);

	public void persist(Object o);

	public Club getDefaultClub();

	public Map<Integer, Player> loadSquad(String season);

	public List<Game> getGames(int year);

	public Game getGame(Long id);

	public FormationPosition getFormationPosition(String formationName,
			int positionId);

	public List<Tournament> getTournaments();

	public List<TournamentSeason> getTournamentSeasons(Long tournamentId);

	public TournamentSeason getTournamentSeasonByName(String tournamentName,
			SquadSeason squadSeason);

	public Tournament getOrCreateTournamentByName(String tournamentName);

	public List<Game> getGames(Long tournamentId, String season);

	public Formation getFormationByName(String formation);

	public void saveGame(Game g);

	public EntityManager getPersistenceUnit();

	public List<Formation> getFormations();

	public List<Club> getClubs();

	public List<Ground> getGrounds();

	public List<Referee> getReferees();

	public Game updateGame(Game detachedGame);

	public void savePlayer(Player bean);

	public Player updatePlayer(Player bean);

	public List<Position> getPositions();

	public List<PositionType> getPositionTypes();

	public List<SquadSeason> getSeasons();

	public void removePlayedForClub(PlayedForClub selectedItem);

	public void updatePlayerSeason(PlayedForClub bean);
	
	public void createPlayerSeason(PlayedForClub pfc);

	public List<Player> getAllPlayersShallow();

	public SquadSeason getSquadSeason(Long id);

	public void updateGameWithNote(Date date, String note);

	public List<Country> getCountries();

	public void updateGround(Ground ground);

	public SquadSeason getSquadForSeason(Long seasonId);

	public TournamentSeason getTournamentSeason(Long tournamentId, String seasonStr);

	public Tournament createTournament(String tournamentName);

	public List<TournamentSeason> getTournamentSeasonsOfSeason(Long squadSeasonId);

	public TournamentSeason saveTournamentSeason(TournamentSeason tournamentSeason);

	public void removeTournamentSeasonFromSeason(TournamentSeason ts);

}