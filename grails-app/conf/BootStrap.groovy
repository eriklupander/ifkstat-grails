import se.ifkgoteborg.stat.model.Club
import se.ifkgoteborg.stat.model.Country
import se.ifkgoteborg.stat.model.Formation
import se.ifkgoteborg.stat.model.FormationPosition;
import se.ifkgoteborg.stat.model.Ground
import se.ifkgoteborg.stat.model.Player
import se.ifkgoteborg.stat.model.Position
import se.ifkgoteborg.stat.model.PositionType
import se.ifkgoteborg.stat.model.enums.Side


class BootStrap {

    def init = { servletContext ->
		if (!Country.count()) {
			Country sweden = new Country(name: "Sverige", code: "SE").save(failOnError: true)
			
			if (!Club.count()) {
				new Club(name: "IFK Göteborg", city: "Göteborg", defaultClub:true, country:sweden).save(failOnError: true)
			}
			
			if (!Ground.count()) {
				new Ground(name: "Gamla Ullevi", city:"Göteborg", maxCapacity:18800, address:"Ullevigatan 5", country:sweden).save(failOnError: true)
			}
		}
		
		if (!PositionType.count()) {
			PositionType gk = new PositionType(name:"Målvakt").save(failOnError: true)
			PositionType df = new PositionType(name:"Försvarare").save(failOnError: true)
			PositionType mid = new PositionType(name:"Mittfältare").save(failOnError: true)
			PositionType forw = new PositionType(name:"Anfallare").save(failOnError: true)	
			
			Position mv = new Position(name:"Målvakt", code: "MV", side: Side.CENTRAL, positionType: gk).save(failOnError: true)
			Position hb = new Position(name:"Högerback", code: "HB", side: Side.RIGHT, positionType: df).save(failOnError: true)
			Position mb1 = new Position(name:"Mittback", code: "MB", side: Side.RIGHT, positionType: df).save(failOnError: true)
			Position mb2 = new Position(name: "Mittback", code: "MB", side: Side.LEFT, positionType: df).save(failOnError: true)
			Position vb = new Position(name: "Vänsterback", code: "VB", side: Side.LEFT, positionType: df).save(failOnError: true)
			Position hy = new Position(name: "Högerytter", code: "HY", side: Side.RIGHT, positionType: mid).save(failOnError: true)
			Position im1 = new Position(name: "Innermitt", code: "IM", side: Side.RIGHT, positionType: mid).save(failOnError: true)
			Position im2 = new Position(name: "Innermitt", code: "IM", side: Side.LEFT, positionType: mid).save(failOnError: true)
			Position im1_c = new Position(name: "Innermitt", code: "IM", side: Side.CENTRAL, positionType: mid).save(failOnError: true)
			
			Position im1d_r = new Position(name: "Innermitt (def)", code: "IM (D)", side: Side.RIGHT, positionType: mid).save(failOnError: true)
			Position im2d_l = new Position(name: "Innermitt (def)", code: "IM (D)", side: Side.LEFT, positionType: mid).save(failOnError: true)
			Position im1d_c = new Position(name: "Innermitt (def)", code: "IM (D)", side: Side.CENTRAL, positionType: mid).save(failOnError: true)
			
			Position im1o = new Position(name: "Innermitt (off)", code: "IM (O)", side: Side.CENTRAL, positionType: mid).save(failOnError: true)
			
			Position vy = new Position(name: "Vänsterytter", code: "VY", side: Side.LEFT, positionType: mid).save(failOnError: true)
			
			Position fw1 = new Position(name: "Anfallare", code: "FW", side: Side.RIGHT, positionType: forw).save(failOnError: true)
			Position fw2 = new Position(name: "Anfallare", code: "FW", side: Side.LEFT, positionType: forw).save(failOnError: true)
			
			Position fw1_c = new Position(name: "Anfallare", code: "FW", side: Side.CENTRAL, positionType: forw).save(failOnError: true)
			//Position fw1_def = new Position(name: "Anfallare", "FW (D)", Side.CENTRAL, forw).save(failOnError: true)
			
			Position p12 = new Position(name: "Innermitt", code: "IM", side: Side.CENTRAL, positionType: mid).save(failOnError: true)
			
			
			Position hh = new Position(name: "Högerhalv", code: "HH", side: Side.RIGHT, positionType: mid).save(failOnError: true)
			Position ch = new Position(name: "Centerhalv", code: "CH", side: Side.CENTRAL, positionType: mid).save(failOnError: true)
			Position vh = new Position(name: "Vänsterhalv", code: "VH", side: Side.LEFT, positionType: mid).save(failOnError: true)
			
			Position hi = new Position(name: "Högerinner", code: "HI", side: Side.RIGHT, positionType: forw).save(failOnError: true)
			Position c = new Position(name: "Center", code: "CE", side: Side.CENTRAL, positionType: forw).save(failOnError: true)
			Position vi = new Position(name: "Vänsterinner", code: "VI", side: Side.LEFT, positionType: forw).save(failOnError: true)
					
			Formation f442 = new Formation(name:"4-4-2").save(failOnError: true)			
			Formation f4231 = new Formation(name:"4-2-3-1").save(failOnError: true)			
			Formation f4141 = new Formation(name:"4-1-4-1").save(failOnError: true)			
			Formation f451 = new Formation(name:"4-5-1").save(failOnError: true)			
			Formation f352 = new Formation(name:"3-5-2").save(failOnError: true)			
			Formation f235 = new Formation(name:"2-3-5").save(failOnError: true)			
			Formation f4411 = new Formation(name:"4-4-1-1").save(failOnError: true)			
			Formation f4132 = new Formation(name:"4-1-3-2").save(failOnError: true)
			
			new FormationPosition(index: 1, formation: f442, position: mv).save(failOnError: true)
			new FormationPosition(index: 2, formation: f442, position: hb).save(failOnError: true)
			new FormationPosition(index: 3, formation: f442, position: mb1).save(failOnError: true)
			new FormationPosition(index: 4, formation: f442, position: mb2).save(failOnError: true)
			new FormationPosition(index: 5, formation: f442, position: vb).save(failOnError: true)
			new FormationPosition(index: 6, formation: f442, position: hy).save(failOnError: true)
			new FormationPosition(index: 7, formation: f442, position: im1).save(failOnError: true)
			new FormationPosition(index: 8, formation: f442, position: im2).save(failOnError: true)
			new FormationPosition(index: 9, formation: f442, position: vy).save(failOnError: true)
			new FormationPosition(index: 10, formation: f442, position: fw1).save(failOnError: true)
			new FormationPosition(index: 11, formation: f442, position: fw2).save(failOnError: true)
			
			new FormationPosition(index: 1, formation: f4231, position: mv).save(failOnError: true)
			new FormationPosition(index: 2, formation: f4231, position: hb).save(failOnError: true)
			new FormationPosition(index: 3, formation: f4231, position: mb1).save(failOnError: true)
			new FormationPosition(index: 4, formation: f4231, position: mb2).save(failOnError: true)
			new FormationPosition(index: 5, formation: f4231, position: vb).save(failOnError: true)
			new FormationPosition(index: 6, formation: f4231, position: im1d_r).save(failOnError: true)
			new FormationPosition(index: 7, formation: f4231, position: im2d_l).save(failOnError: true)
			new FormationPosition(index: 8, formation: f4231, position: hy).save(failOnError: true)
			new FormationPosition(index: 9, formation: f4231, position: im1o).save(failOnError: true)
			new FormationPosition(index: 10, formation: f4231, position: vy).save(failOnError: true)
			new FormationPosition(index: 11, formation: f4231, position: fw1_c).save(failOnError: true)
			
			new FormationPosition(index: 1, formation: f4141, position: mv).save(failOnError: true)
			new FormationPosition(index: 2, formation: f4141, position: hb).save(failOnError: true)
			new FormationPosition(index: 3, formation: f4141, position: mb1).save(failOnError: true)
			new FormationPosition(index: 4, formation: f4141, position: mb2).save(failOnError: true)
			new FormationPosition(index: 5, formation: f4141, position: vb).save(failOnError: true)
			new FormationPosition(index: 6, formation: f4141, position: im1d_c).save(failOnError: true)
			new FormationPosition(index: 7, formation: f4141, position: hy).save(failOnError: true)
			new FormationPosition(index: 8, formation: f4141, position: im1).save(failOnError: true)
			new FormationPosition(index: 9, formation: f4141, position: vy).save(failOnError: true)
			new FormationPosition(index: 10, formation: f4141, position: im1o).save(failOnError: true)
			new FormationPosition(index: 11, formation: f4141, position: fw1_c).save(failOnError: true)
			
			new FormationPosition(index: 1, formation: f451, position: mv).save(failOnError: true)
			new FormationPosition(index: 2, formation: f451, position: hb).save(failOnError: true)
			new FormationPosition(index: 3, formation: f451, position: mb1).save(failOnError: true)
			new FormationPosition(index: 4, formation: f451, position: mb2).save(failOnError: true)
			new FormationPosition(index: 5, formation: f451, position: vb).save(failOnError: true)
			new FormationPosition(index: 6, formation: f451, position: hy).save(failOnError: true)
			new FormationPosition(index: 7, formation: f451, position: im1).save(failOnError: true)
			new FormationPosition(index: 8, formation: f451, position: im1_c).save(failOnError: true)
			new FormationPosition(index: 9, formation: f451, position: im2).save(failOnError: true)
			new FormationPosition(index: 10, formation: f451, position: vy).save(failOnError: true)
			new FormationPosition(index: 11, formation: f451, position: fw1_c).save(failOnError: true)
			
			new FormationPosition(index: 1, formation: f352, position: mv).save(failOnError: true)
			new FormationPosition(index: 2, formation: f352, position: hb).save(failOnError: true)
			new FormationPosition(index: 3, formation: f352, position: mb1).save(failOnError: true)
			new FormationPosition(index: 4, formation: f352, position: vb).save(failOnError: true)
			new FormationPosition(index: 5, formation: f352, position: hy).save(failOnError: true)
			new FormationPosition(index: 6, formation: f352, position: im1).save(failOnError: true)
			new FormationPosition(index: 7, formation: f352, position: im1_c).save(failOnError: true)
			new FormationPosition(index: 8, formation: f352, position: im2).save(failOnError: true)
			new FormationPosition(index: 9, formation: f352, position: vy).save(failOnError: true)
			new FormationPosition(index: 10, formation: f352, position: fw1).save(failOnError: true)
			new FormationPosition(index: 11, formation: f352, position: fw2).save(failOnError: true)
			
	
			new FormationPosition(index: 1, formation: f235, position: mv).save(failOnError: true)
			new FormationPosition(index: 2, formation: f235, position: hb).save(failOnError: true)
			new FormationPosition(index: 3, formation: f235, position: vb).save(failOnError: true)
			new FormationPosition(index: 4, formation: f235, position: hh).save(failOnError: true)
			new FormationPosition(index: 5, formation: f235, position: ch).save(failOnError: true)
			new FormationPosition(index: 6, formation: f235, position: vh).save(failOnError: true)
			new FormationPosition(index: 7, formation: f235, position: hy).save(failOnError: true)
			new FormationPosition(index: 8, formation: f235, position: hi).save(failOnError: true)
			new FormationPosition(index: 9, formation: f235, position: c).save(failOnError: true)
			new FormationPosition(index: 10, formation: f235, position: vi).save(failOnError: true)
			new FormationPosition(index: 11, formation: f235, position: vy).save(failOnError: true)
			
			new FormationPosition(index: 1, formation: f4411, position: mv).save(failOnError: true)
			new FormationPosition(index: 2, formation: f4411, position: hb).save(failOnError: true)
			new FormationPosition(index: 3, formation: f4411, position: mb1).save(failOnError: true)
			new FormationPosition(index: 4, formation: f4411, position: mb2).save(failOnError: true)
			new FormationPosition(index: 5, formation: f4411, position: vb).save(failOnError: true)
			new FormationPosition(index: 6, formation: f4411, position: hy).save(failOnError: true)
			new FormationPosition(index: 7, formation: f4411, position: im1).save(failOnError: true)
			new FormationPosition(index: 8, formation: f4411, position: im2).save(failOnError: true)
			new FormationPosition(index: 9, formation: f4411, position: vy).save(failOnError: true)
			new FormationPosition(index: 10, formation: f4411, position: im1o).save(failOnError: true)
			new FormationPosition(index: 11, formation: f4411, position: fw1_c).save(failOnError: true)
			
			
			new FormationPosition(index: 1, formation: f4132, position: mv).save(failOnError: true)
			new FormationPosition(index: 2, formation: f4132, position: hb).save(failOnError: true)
			new FormationPosition(index: 3, formation: f4132, position: mb1).save(failOnError: true)
			new FormationPosition(index: 4, formation: f4132, position: mb2).save(failOnError: true)
			new FormationPosition(index: 5, formation: f4132, position: vb).save(failOnError: true)
			new FormationPosition(index: 6, formation: f4132, position: im1d_c).save(failOnError: true)
			new FormationPosition(index: 7, formation: f4132, position: hy).save(failOnError: true)
			new FormationPosition(index: 8, formation: f4132, position: im1_c).save(failOnError: true)
			new FormationPosition(index: 9, formation: f4132, position: vy).save(failOnError: true)
			new FormationPosition(index: 10, formation: f4132, position: fw1).save(failOnError: true)
			new FormationPosition(index: 11, formation: f4132, position: fw2).save(failOnError: true)

		}
		
		if (!Player.count()) { 
			new Player(name: "Erik Lupander").save(failOnError: true) 
			new Player(name: "Theo Lupander").save(failOnError: true)
		}
    }
    def destroy = {
    }
	
	
}
