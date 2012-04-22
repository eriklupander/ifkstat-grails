<%@ page import="se.ifkgoteborg.stat.model.Game" %>



<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'id', 'error')} ">
	<label for="id">
		<g:message code="game.id.label" default="Id" />
		
	</label>
	<g:field type="number" name="id" value="${fieldValue(bean: gameInstance, field: 'id')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'homeTeam', 'error')} ">
	<label for="homeTeam">
		<g:message code="game.homeTeam.label" default="Home Team" />
		
	</label>
	<g:select id="homeTeam" name="homeTeam.id" from="${se.ifkgoteborg.stat.model.Club.list()}" optionKey="id" required="" value="${gameInstance?.homeTeam?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'awayTeam', 'error')} ">
	<label for="awayTeam">
		<g:message code="game.awayTeam.label" default="Away Team" />
		
	</label>
	<g:select id="awayTeam" name="awayTeam.id" from="${se.ifkgoteborg.stat.model.Club.list()}" optionKey="id" required="" value="${gameInstance?.awayTeam?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'attendance', 'error')} ">
	<label for="attendance">
		<g:message code="game.attendance.label" default="Attendance" />
		
	</label>
	<g:field type="number" name="attendance" value="${fieldValue(bean: gameInstance, field: 'attendance')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'awayCorners', 'error')} ">
	<label for="awayCorners">
		<g:message code="game.awayCorners.label" default="Away Corners" />
		
	</label>
	<g:field type="number" name="awayCorners" value="${fieldValue(bean: gameInstance, field: 'awayCorners')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'awayFreekicks', 'error')} ">
	<label for="awayFreekicks">
		<g:message code="game.awayFreekicks.label" default="Away Freekicks" />
		
	</label>
	<g:field type="number" name="awayFreekicks" value="${fieldValue(bean: gameInstance, field: 'awayFreekicks')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'awayGoals', 'error')} ">
	<label for="awayGoals">
		<g:message code="game.awayGoals.label" default="Away Goals" />
		
	</label>
	<g:field type="number" name="awayGoals" value="${fieldValue(bean: gameInstance, field: 'awayGoals')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'awayGoalsHalftime', 'error')} ">
	<label for="awayGoalsHalftime">
		<g:message code="game.awayGoalsHalftime.label" default="Away Goals Halftime" />
		
	</label>
	<g:field type="number" name="awayGoalsHalftime" value="${fieldValue(bean: gameInstance, field: 'awayGoalsHalftime')}" />
</div>



<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'dateOfGame', 'error')} ">
	<label for="dateOfGame">
		<g:message code="game.dateOfGame.label" default="Date Of Game" />
		
	</label>
	<g:datePicker name="dateOfGame" precision="day" value="${gameInstance?.dateOfGame}" />
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'events', 'error')} ">
	<label for="events">
		<g:message code="game.events.label" default="Events" />
		
	</label>
	<g:select name="events" from="${se.ifkgoteborg.stat.model.GameEvent.list()}" multiple="multiple" optionKey="id" size="5" required="" value="${gameInstance?.events*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'formation', 'error')} ">
	<label for="formation">
		<g:message code="game.formation.label" default="Formation" />
		
	</label>
	<g:select id="formation" name="formation.id" from="${se.ifkgoteborg.stat.model.Formation.list()}" optionKey="id" required="" value="${gameInstance?.formation?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'gameNotes', 'error')} ">
	<label for="gameNotes">
		<g:message code="game.gameNotes.label" default="Game Notes" />		
	</label>
	<g:select name="gameNotes" from="${se.ifkgoteborg.stat.model.GameNote.list()}" multiple="multiple" optionKey="id" size="5" required="" value="${gameInstance?.gameNotes*.id}" class="many-to-many"/>
	<g:createLink action="create" controller="GameEvent" params="[gameId: 1]" fragment="add"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'gameParticipation', 'error')} ">
	<label for="gameParticipation">
		<g:message code="game.gameParticipation.label" default="Game Participation" />
		
	</label>
	<g:select name="gameParticipation" from="${se.ifkgoteborg.stat.model.GameParticipation.list()}" multiple="multiple" optionKey="id" size="5" required="" value="${gameInstance?.gameParticipation*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'gameSummary', 'error')} ">
	<label for="gameSummary">
		<g:message code="game.gameSummary.label" default="Game Summary" />
		
	</label>
	<g:textField name="gameSummary" value="${gameInstance?.gameSummary}" />
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'ground', 'error')} ">
	<label for="ground">
		<g:message code="game.ground.label" default="Ground" />
		
	</label>
	<g:select id="ground" name="ground.id" from="${se.ifkgoteborg.stat.model.Ground.list()}" optionKey="id" required="" value="${gameInstance?.ground?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'homeCorners', 'error')} ">
	<label for="homeCorners">
		<g:message code="game.homeCorners.label" default="Home Corners" />
		
	</label>
	<g:field type="number" name="homeCorners" value="${fieldValue(bean: gameInstance, field: 'homeCorners')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'homeFreekicks', 'error')} ">
	<label for="homeFreekicks">
		<g:message code="game.homeFreekicks.label" default="Home Freekicks" />
		
	</label>
	<g:field type="number" name="homeFreekicks" value="${fieldValue(bean: gameInstance, field: 'homeFreekicks')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'homeGoals', 'error')} ">
	<label for="homeGoals">
		<g:message code="game.homeGoals.label" default="Home Goals" />
		
	</label>
	<g:field type="number" name="homeGoals" value="${fieldValue(bean: gameInstance, field: 'homeGoals')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'homeGoalsHalftime', 'error')} ">
	<label for="homeGoalsHalftime">
		<g:message code="game.homeGoalsHalftime.label" default="Home Goals Halftime" />
		
	</label>
	<g:field type="number" name="homeGoalsHalftime" value="${fieldValue(bean: gameInstance, field: 'homeGoalsHalftime')}" />
</div>



<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'referee', 'error')} ">
	<label for="referee">
		<g:message code="game.referee.label" default="Referee" />
		
	</label>
	<g:select id="referee" name="referee.id" from="${se.ifkgoteborg.stat.model.Referee.list()}" optionKey="id" required="" value="${gameInstance?.referee?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'tournamentSeason', 'error')} ">
	<label for="tournamentSeason">
		<g:message code="game.tournamentSeason.label" default="Tournament Season" />
		
	</label>
	<g:select id="tournamentSeason" name="tournamentSeason.id" from="${se.ifkgoteborg.stat.model.TournamentSeason.list()}" optionKey="id" required="" value="${gameInstance?.tournamentSeason?.id}" class="many-to-one"/>
</div>

