package se.ifkgoteborg.stat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import se.ifkgoteborg.stat.model.enums.ParticipationType;

/**
 * Holds the relation between a player and a game, i.e. "Participation".
 * @author Erik
 *
 */
@Entity
@Table(name="player_game")
public class GameParticipation {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="position_id")
	private Integer positionId;
	
	@ManyToOne
	private FormationPosition formationPosition;
	
	private Integer enterMinute = 0;
	private Integer substitutedMinute;
	
	// Spelarens nummer i denna match
	//private Integer playerNumber;
	
	@ManyToOne
	private Game game;
	
	@ManyToOne
	private Player player;
	
	@Enumerated(value=EnumType.STRING)
	private ParticipationType participationType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public Integer getEnterMinute() {
		return enterMinute;
	}

	public void setEnterMinute(Integer enterMinute) {
		this.enterMinute = enterMinute;
	}

	public Integer getSubstitutedMinute() {
		return substitutedMinute;
	}

	public void setSubstitutedMinute(Integer substitutedMinute) {
		this.substitutedMinute = substitutedMinute;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
	
	public FormationPosition getFormationPosition() {
		return formationPosition;
	}

	public void setFormationPosition(FormationPosition formationPosition) {
		this.formationPosition = formationPosition;
	}
	
	

	public ParticipationType getParticipationType() {
		return participationType;
	}

	public void setParticipationType(ParticipationType participationType) {
		this.participationType = participationType;
	}
	
	

//	public Integer getPlayerNumber() {
//		return playerNumber;
//	}
//
//	public void setPlayerNumber(Integer playerNumber) {
//		this.playerNumber = playerNumber;
//	}

	public String toString() {
		return this.player.getName() + "(" + formationPosition.getPosition().getName() + ") " + (participationType == ParticipationType.SUBSTITUTE_IN ? "(inbytt)" : "");
	}
}
