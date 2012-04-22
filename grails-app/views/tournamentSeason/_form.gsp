<%@ page import="se.ifkgoteborg.stat.model.TournamentSeason" %>



<div class="fieldcontain ${hasErrors(bean: tournamentSeasonInstance, field: 'id', 'error')} ">
	<label for="id">
		<g:message code="tournamentSeason.id.label" default="Id" />
		
	</label>
	<g:field type="number" name="id" value="${fieldValue(bean: tournamentSeasonInstance, field: 'id')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: tournamentSeasonInstance, field: 'games', 'error')} ">
	<label for="games">
		<g:message code="tournamentSeason.games.label" default="Games" />
		
	</label>
	<g:select name="games" from="${se.ifkgoteborg.stat.model.Game.list()}" multiple="multiple" optionKey="id" size="5" required="" value="${tournamentSeasonInstance?.games*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tournamentSeasonInstance, field: 'season', 'error')} ">
	<label for="season">
		<g:message code="tournamentSeason.season.label" default="Season" />
		
	</label>
	<g:select id="season" name="season.id" from="${se.ifkgoteborg.stat.model.SquadSeason.list()}" optionKey="id" required="" value="${tournamentSeasonInstance?.season?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tournamentSeasonInstance, field: 'seasonName', 'error')} ">
	<label for="seasonName">
		<g:message code="tournamentSeason.seasonName.label" default="Season Name" />
		
	</label>
	<g:textField name="seasonName" value="${tournamentSeasonInstance?.seasonName}" />
</div>

<div class="fieldcontain ${hasErrors(bean: tournamentSeasonInstance, field: 'start', 'error')} ">
	<label for="start">
		<g:message code="tournamentSeason.start.label" default="Start" />
		
	</label>
	<g:datePicker name="start" precision="day" value="${tournamentSeasonInstance?.start}" />
</div>

<div class="fieldcontain ${hasErrors(bean: tournamentSeasonInstance, field: 'tournament', 'error')} ">
	<label for="tournament">
		<g:message code="tournamentSeason.tournament.label" default="Tournament" />
		
	</label>
	<g:select id="tournament" name="tournament.id" from="${se.ifkgoteborg.stat.model.Tournament.list()}" optionKey="id" required="" value="${tournamentSeasonInstance?.tournament?.id}" class="many-to-one"/>
</div>

