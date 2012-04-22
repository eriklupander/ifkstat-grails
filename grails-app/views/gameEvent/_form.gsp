<%@ page import="se.ifkgoteborg.stat.model.GameEvent" %>



<div class="fieldcontain ${hasErrors(bean: gameEventInstance, field: 'id', 'error')} ">
	<label for="id">
		<g:message code="gameEvent.id.label" default="Id" />
		
	</label>
	<g:field type="number" name="id" value="${fieldValue(bean: gameEventInstance, field: 'id')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: gameEventInstance, field: 'eventType', 'error')} ">
	<label for="eventType">
		<g:message code="gameEvent.eventType.label" default="Event Type" />
		
	</label>
	<g:select name="eventType" from="${se.ifkgoteborg.stat.model.GameEvent$EventType?.values()}" keys="${se.ifkgoteborg.stat.model.GameEvent$EventType.values()*.name()}" required="" value="${gameEventInstance?.eventType?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameEventInstance, field: 'game', 'error')} ">
	<label for="game">
		<g:message code="gameEvent.game.label" default="Game" />
		
	</label>
	<g:select id="game" name="game.id" from="${se.ifkgoteborg.stat.model.Game.list()}" optionKey="id" required="" value="${gameEventInstance?.game?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameEventInstance, field: 'gameMinute', 'error')} ">
	<label for="gameMinute">
		<g:message code="gameEvent.gameMinute.label" default="Game Minute" />
		
	</label>
	<g:field type="number" name="gameMinute" value="${fieldValue(bean: gameEventInstance, field: 'gameMinute')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: gameEventInstance, field: 'player', 'error')} ">
	<label for="player">
		<g:message code="gameEvent.player.label" default="Player" />
		
	</label>
	<g:select id="player" name="player.id" from="${se.ifkgoteborg.stat.model.Player.list()}" optionKey="id" required="" value="${gameEventInstance?.player?.id}" class="many-to-one"/>
</div>

