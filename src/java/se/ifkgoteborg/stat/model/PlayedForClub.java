package se.ifkgoteborg.stat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="player_club")
public class PlayedForClub {
	
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Player player;
	
	@ManyToOne
	private Club club;

	@ManyToOne
	private SquadSeason season;
	
	private Integer squadNr;
	private Integer importIndex;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Club getClub() {
		return club;
	}
	public void setClub(Club club) {
		this.club = club;
	}

	public SquadSeason getSeason() {
		return season;
	}

	public void setSeason(SquadSeason season) {
		this.season = season;
	}

	public Integer getSquadNr() {
		return squadNr;
	}

	public void setSquadNr(Integer squadNr) {
		this.squadNr = squadNr;
	}

	public Integer getImportIndex() {
		return importIndex;
	}

	public void setImportIndex(Integer importIndex) {
		this.importIndex = importIndex;
	}
	
	
}
