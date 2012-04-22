package se.ifkgoteborg.stat.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="game_event")
public class GameEvent {

	public enum EventType {
		YELLOW_CARD, RED_CARD, SUBSTITUTION_IN, SUBSTITUTION_OUT, PENALTY_KICK, GOAL
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Enumerated(value=EnumType.STRING)
	private EventType eventType;
	private Integer gameMinute;
	
	@ManyToOne
	private Player player;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private Game game;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	public Integer getGameMinute() {
		return gameMinute;
	}
	public void setGameMinute(Integer gameMinute) {
		this.gameMinute = gameMinute;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
