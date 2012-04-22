package se.ifkgoteborg.stat.importer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import se.ifkgoteborg.stat.controller.RegistrationDAO;
import se.ifkgoteborg.stat.model.Club;
import se.ifkgoteborg.stat.model.Formation;
import se.ifkgoteborg.stat.model.FormationPosition;
import se.ifkgoteborg.stat.model.Game;
import se.ifkgoteborg.stat.model.GameEvent;
import se.ifkgoteborg.stat.model.GameEvent.EventType;
import se.ifkgoteborg.stat.model.GameNote;
import se.ifkgoteborg.stat.model.GameParticipation;
import se.ifkgoteborg.stat.model.Player;
import se.ifkgoteborg.stat.model.SquadSeason;
import se.ifkgoteborg.stat.model.TournamentSeason;
import se.ifkgoteborg.stat.model.enums.ParticipationType;
import se.ifkgoteborg.stat.util.StringUtil;



public class GameImporter {
	
	public static int goalsImported = 0;
	public static int subInImported = 0;
	public static int subOutImported = 0;


	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy/dd/MM");

	private static final SimpleDateFormat sdf2 = new SimpleDateFormat(
			"yyyy-MM-dd");

	private static final String GOAL_TOKEN = "•";
	private static final String SUBST_TOKEN = "[";
	private static final String SUBST_TOKEN_ALT = "(";

	private static final int OFFSET = 8;

	RegistrationDAO dao;

	public GameImporter(RegistrationDAO dao) {
		this.dao = dao;
	}

	/**
	 * 
	 * @param data
	 * @param year
	 * @param players
	 *            Integer is INDEX during this season.
	 * @param tournamentName 
	 * @param squadSeason 
	 */
	public void importTournamentSeason(String data, String season, Map<Integer, Player> players, String tournamentName, SquadSeason squadSeason) {
		System.out.println("ENTER - importTournamentSeason: Season: " + season + ", Tournament name: " +  tournamentName);
		
		String[] games = data.split("\n");
		TournamentSeason ts = dao.getTournamentSeasonByName(tournamentName, squadSeason);
		int offset = 0;
		Date dateOfLastGame = null;
		for (String game : games) {
			dateOfLastGame = importGame(game, players, ts, dateOfLastGame);
		}
	}

	private Date importGame(String dataRow, Map<Integer, Player> players, TournamentSeason ts, Date dateOfLastGame) {
		
		Integer year = null;
		if(dateOfLastGame == null) {
			year = ts.getStart().getYear()+1900;
		} else {
			 year = dateOfLastGame.getYear()+1900;
		}
		
		System.out.println("Import game of season: " + year);

		String[] cells = dataRow.split("\t");

		Game g = new Game();
		g.setTournamentSeason(ts);
		ts.getGames().add(g);
		
		Club opponentClub = null;
		boolean homegame = true;

		List<Integer> substitutedPositions = new ArrayList();
		
		for (int a = 0; a < cells.length; a++) {
			//System.out.println("INDEX: " + a + " DATA: " + cells[a]);
			
			switch (a) {

			// Date
			case 0:
				try {
					Date parsedDate = sdf.parse(year + "/" + cells[a].trim().replaceAll("\\.", "/"));
					
					if(dateOfLastGame != null) {
						// Check if month of this game is < than last game. Then we have switched year
						if(parsedDate.getMonth() < dateOfLastGame.getMonth()) {
							parsedDate.setYear(parsedDate.getYear()+1);
						}
					}
					
					g.setDateOfGame(parsedDate);
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
				break;

			// Opponent
			case 1:
				// Create opponent club if not exists
				opponentClub = dao.getOrCreateClub(cells[a].trim());
				break;

			// Home or Away (H/B) N=Neutral
			case 2:
				homegame = "H".equals(cells[a].trim());
				if("N".equals(cells[a].trim())) {
					homegame = true;
					g.getGameNotes().add(new GameNote(g, "Neutral plan"));
				}
				break;
			// Arena / ground
			case 3:
				g.setGround(dao.getOrCreateGround(cells[a].trim()));
				break;

			// Result
			case 4:
				String result = cells[a];
				if(result == null || result.trim().length() == 0 || result.trim().toLowerCase().startsWith("w")) {
					break;
				}
				
				result = result.replaceAll("[^\\d]", " ");
				String[] parts = result.split(" ");
				
				// IFK:s mål står alltid först, vänd ifall ifk har bortamatch.
				if(homegame) {
					g.setHomeGoals(Integer.parseInt(parts[0]));
					g.setAwayGoals(Integer.parseInt(parts[1]));
				} else {
					g.setAwayGoals(Integer.parseInt(parts[0]));
					g.setHomeGoals(Integer.parseInt(parts[1]));
				}
				break;
			// Halftime result
			case 5:
				String htResult = cells[a];
				if(htResult == null || htResult.trim().length() == 0) {
					break;
				}
				htResult = htResult.replaceAll("[^\\d]", " ");
				String[] htParts = htResult.trim().split(" ");
				g.setHomeGoalsHalftime(htParts[0] != null ? Integer.parseInt(htParts[0].replaceAll("[^\\d]",
						"")) : 0);
				g.setAwayGoalsHalftime(htParts[1] != null ?Integer.parseInt(htParts[1].replaceAll("[^\\d]",
						"")) : 0);
				break;
			// attendance
			case 6:
				g.setAttendance(Integer.parseInt(cells[a].replaceAll("[^\\d]",
						"")));
				break;

			// default team formation
			case 7:
				String formation = cells[a].trim();
				Formation f = dao.getFormationByName(formation);				
				g.setFormation(f);
				//f.getUsedInGames().add(g);
				
				break;

			// The rest are always player position in game
			default:
				// If empty, player at index (a) did not participate in game
				if (cells[a] == null || cells[a].trim().length() == 0) {
					break;
				}
				// OK, player did participate. Get player of current index,
				// start building GameParticipation list.
				String cleanedCellData = cells[a].trim().replaceAll("[^\\d]", "");
				String rawData = cells[a].trim();
				if(cleanedCellData.trim().length() == 0) {
					System.out.println("WARNING: unknown cell data: " + cells[a]);
					break;
				}
				int positionId = Integer.parseInt(cleanedCellData);
				int goalsScored = 0;
				
				// Some years use a "raised" integer after the number instead of the * to mark goals.
				if(positionId > 11) {
					String s = new String("" + positionId);
					goalsScored = Integer.parseInt(s.substring(s.length() - 1));
					
					
					positionId = Integer.parseInt(s.substring(0, s.length() - 1));
				}

				Player player = players.get(new Integer(a - OFFSET));
				
				// Add a participation
				GameParticipation gp = new GameParticipation();
				gp.setPlayer(player);
				gp.setGame(g);
				gp.setPositionId(positionId);
				//gp.setPlayerNumber(player.getSquadNumber());
				gp.setFormationPosition(getFormationPosition(g.getFormation().getName(), positionId));
				g.getGameParticipation().add(gp);
				
				// Check for special characters
				if (rawData.indexOf(GOAL_TOKEN) > -1) {
					// How many?
					int numberOfGoals = StringUtil.countOccurrences(rawData,
							GOAL_TOKEN);
					for (int i = 0; i < numberOfGoals; i++) {
						// Add a GameEvent instance for each goal.
						GameEvent event = new GameEvent();
						event.setEventType(EventType.GOAL);
						event.setPlayer(player);
						event.setGame(g);
						g.getEvents().add(event);
						goalsImported++;
					}
				} else if(goalsScored > 0) {
					for (int i = 0; i < goalsScored; i++) {
						// Add a GameEvent instance for each goal.
						GameEvent event = new GameEvent();
						event.setEventType(EventType.GOAL);
						event.setPlayer(player);
						event.setGame(g);
						g.getEvents().add(event);
						goalsImported++;
					}
				}
				if (rawData.indexOf(SUBST_TOKEN) > -1 || rawData.indexOf(SUBST_TOKEN_ALT) > -1) {
					// For a subst, check which POSITION the number refers to
					// and add a subst IN event
					GameEvent event = new GameEvent();
					event.setPlayer(player);
					event.setEventType(EventType.SUBSTITUTION_IN);
					event.setGame(g);
					g.getEvents().add(event);
					
					gp.setParticipationType(ParticipationType.SUBSTITUTE_IN);
					subInImported++;
					// Add ID to list so we can post-process and set the SUBST_OUT properly
					substitutedPositions.add(positionId);
					
				} else {
					gp.setParticipationType(ParticipationType.STARTER);
				}
				break;

			}
			

		}

		if (homegame) {
			g.setAwayTeam(opponentClub);
			g.setHomeTeam(dao.getDefaultClub());
		} else {
			g.setAwayTeam(dao.getDefaultClub());
			g.setHomeTeam(opponentClub);
		}
		
		// Find the players that were substituted out
		for(Integer posId : substitutedPositions) {
			for(GameParticipation gp : g.getGameParticipation()) {
				if(gp.getPositionId().equals(posId) && gp.getParticipationType() == ParticipationType.STARTER) {
					gp.setParticipationType(ParticipationType.SUBSTITUTE_OUT);
					GameEvent ge = new GameEvent();
					ge.setEventType(EventType.SUBSTITUTION_OUT);
					ge.setPlayer(gp.getPlayer());
					ge.setGame(g);
					g.getEvents().add(ge);
					subOutImported++;
					break;
				}
			}
		}
		
		dao.saveGame(g);
		

		System.out.println("Imported game vs " + opponentClub.getName());
		
		return g.getDateOfGame();
	}

	private FormationPosition getFormationPosition(String formationName, int positionId) {
		return dao.getFormationPosition(formationName, positionId);
	}

	class PGame {
		public int index;
		public int positionIndex;
	}

}
