<%@ page import="se.ifkgoteborg.stat.model.GameParticipation" %>



<div class="fieldcontain ${hasErrors(bean: gameParticipationInstance, field: 'id', 'error')} ">
	<label for="id">
		<g:message code="gameParticipation.id.label" default="Id" />
		
	</label>
	<g:field type="number" name="id" value="${fieldValue(bean: gameParticipationInstance, field: 'id')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: gameParticipationInstance, field: 'enterMinute', 'error')} ">
	<label for="enterMinute">
		<g:message code="gameParticipation.enterMinute.label" default="Enter Minute" />
		
	</label>
	<g:field type="number" name="enterMinute" value="${fieldValue(bean: gameParticipationInstance, field: 'enterMinute')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: gameParticipationInstance, field: 'formationPosition', 'error')} ">
	<label for="formationPosition">
		<g:message code="gameParticipation.formationPosition.label" default="Formation Position" />
		
	</label>
	<g:select id="formationPosition" name="formationPosition.id" from="${se.ifkgoteborg.stat.model.FormationPosition.list()}" optionKey="id" required="" value="${gameParticipationInstance?.formationPosition?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameParticipationInstance, field: 'game', 'error')} ">
	<label for="game">
		<g:message code="gameParticipation.game.label" default="Game" />
		
	</label>
	<g:select id="game" name="game.id" from="${se.ifkgoteborg.stat.model.Game.list()}" optionKey="id" required="" value="${gameParticipationInstance?.game?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameParticipationInstance, field: 'participationType', 'error')} ">
	<label for="participationType">
		<g:message code="gameParticipation.participationType.label" default="Participation Type" />
		
	</label>
	<g:select name="participationType" from="${se.ifkgoteborg.stat.model.enums.ParticipationType?.values()}" keys="${se.ifkgoteborg.stat.model.enums.ParticipationType.values()*.name()}" required="" value="${gameParticipationInstance?.participationType?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameParticipationInstance, field: 'player', 'error')} ">
	<label for="player">
		<g:message code="gameParticipation.player.label" default="Player" />
		
	</label>
	<g:select id="player" name="player.id" from="${se.ifkgoteborg.stat.model.Player.list()}" optionKey="id" required="" value="${gameParticipationInstance?.player?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameParticipationInstance, field: 'positionId', 'error')} ">
	<label for="positionId">
		<g:message code="gameParticipation.positionId.label" default="Position Id" />
		
	</label>
	<g:field type="number" name="positionId" value="${fieldValue(bean: gameParticipationInstance, field: 'positionId')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: gameParticipationInstance, field: 'substitutedMinute', 'error')} ">
	<label for="substitutedMinute">
		<g:message code="gameParticipation.substitutedMinute.label" default="Substituted Minute" />
		
	</label>
	<g:field type="number" name="substitutedMinute" value="${fieldValue(bean: gameParticipationInstance, field: 'substitutedMinute')}" />
</div>

