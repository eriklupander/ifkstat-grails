package se.ifkgoteborg.stat.importer;

import java.util.ArrayList;
import java.util.List;

import se.ifkgoteborg.stat.controller.adapter.SquadPlayer;

public class PlayerImporter {

	public List<SquadPlayer> importPlayers(String squadData) {
		List<SquadPlayer> players = new ArrayList<SquadPlayer>();
		

		// 1.1 First row are numbers, second is player names
		String numbers = squadData.substring(0, squadData.indexOf("\n"));
		String names = squadData.substring(squadData.indexOf("\n") + 1);
		String[] numArr = numbers.split("\t");
		String[] namesArr = names.split("\t");
		for (int a = 0; a < namesArr.length; a++) {
			try {
				if (namesArr[a] == null || namesArr[a].trim().length() == 0) {
					continue;
				}
				SquadPlayer sp = new SquadPlayer();
				
				if(numArr.length > a && numArr[a] != null && numArr[a].trim().length() > 0) {
					sp.nr = Integer.parseInt(numArr[a].trim());
				} else {
					sp.nr = -1;
				}
				
				sp.name = namesArr[a].trim();
				sp.index = a;
				players.add(sp);
			} catch (Exception e) {
				System.err.println("An exception occured importing player ID: "  + a + " Message: " + e.getMessage());
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}

		System.out.println("Read " + players.size() + " players");

		return players;
	}

}
