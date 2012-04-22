
<%@ page import="se.ifkgoteborg.stat.model.Game" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-game" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-game" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list game">
			
				<g:if test="${gameInstance?.attendance}">
				<li class="fieldcontain">
					<span id="attendance-label" class="property-label"><g:message code="game.attendance.label" default="Attendance" /></span>
					
						<span class="property-value" aria-labelledby="attendance-label"><g:fieldValue bean="${gameInstance}" field="attendance"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.awayCorners}">
				<li class="fieldcontain">
					<span id="awayCorners-label" class="property-label"><g:message code="game.awayCorners.label" default="Away Corners" /></span>
					
						<span class="property-value" aria-labelledby="awayCorners-label"><g:fieldValue bean="${gameInstance}" field="awayCorners"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.awayFreekicks}">
				<li class="fieldcontain">
					<span id="awayFreekicks-label" class="property-label"><g:message code="game.awayFreekicks.label" default="Away Freekicks" /></span>
					
						<span class="property-value" aria-labelledby="awayFreekicks-label"><g:fieldValue bean="${gameInstance}" field="awayFreekicks"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.awayGoals}">
				<li class="fieldcontain">
					<span id="awayGoals-label" class="property-label"><g:message code="game.awayGoals.label" default="Away Goals" /></span>
					
						<span class="property-value" aria-labelledby="awayGoals-label"><g:fieldValue bean="${gameInstance}" field="awayGoals"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.awayGoalsHalftime}">
				<li class="fieldcontain">
					<span id="awayGoalsHalftime-label" class="property-label"><g:message code="game.awayGoalsHalftime.label" default="Away Goals Halftime" /></span>
					
						<span class="property-value" aria-labelledby="awayGoalsHalftime-label"><g:fieldValue bean="${gameInstance}" field="awayGoalsHalftime"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.awayTeam}">
				<li class="fieldcontain">
					<span id="awayTeam-label" class="property-label"><g:message code="game.awayTeam.label" default="Away Team" /></span>
					
						<span class="property-value" aria-labelledby="awayTeam-label"><g:link controller="club" action="show" id="${gameInstance?.awayTeam?.id}">${gameInstance?.awayTeam?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.dateOfGame}">
				<li class="fieldcontain">
					<span id="dateOfGame-label" class="property-label"><g:message code="game.dateOfGame.label" default="Date Of Game" /></span>
					
						<span class="property-value" aria-labelledby="dateOfGame-label"><g:formatDate date="${gameInstance?.dateOfGame}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.events}">
				<li class="fieldcontain">
					<span id="events-label" class="property-label"><g:message code="game.events.label" default="Events" /></span>
					
						<g:each in="${gameInstance.events}" var="e">
						<span class="property-value" aria-labelledby="events-label"><g:link controller="gameEvent" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.formation}">
				<li class="fieldcontain">
					<span id="formation-label" class="property-label"><g:message code="game.formation.label" default="Formation" /></span>
					
						<span class="property-value" aria-labelledby="formation-label"><g:link controller="formation" action="show" id="${gameInstance?.formation?.id}">${gameInstance?.formation?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.gameNotes}">
				<li class="fieldcontain">
					<span id="gameNotes-label" class="property-label"><g:message code="game.gameNotes.label" default="Game Notes" /></span>
					
						<g:each in="${gameInstance.gameNotes}" var="g">
						<span class="property-value" aria-labelledby="gameNotes-label"><g:link controller="gameNote" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.gameParticipation}">
				<li class="fieldcontain">
					<span id="gameParticipation-label" class="property-label"><g:message code="game.gameParticipation.label" default="Game Participation" /></span>
					
						<g:each in="${gameInstance.gameParticipation}" var="g">
						<span class="property-value" aria-labelledby="gameParticipation-label"><g:link controller="gameParticipation" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.gameSummary}">
				<li class="fieldcontain">
					<span id="gameSummary-label" class="property-label"><g:message code="game.gameSummary.label" default="Game Summary" /></span>
					
						<span class="property-value" aria-labelledby="gameSummary-label"><g:fieldValue bean="${gameInstance}" field="gameSummary"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.ground}">
				<li class="fieldcontain">
					<span id="ground-label" class="property-label"><g:message code="game.ground.label" default="Ground" /></span>
					
						<span class="property-value" aria-labelledby="ground-label"><g:link controller="ground" action="show" id="${gameInstance?.ground?.id}">${gameInstance?.ground?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.homeCorners}">
				<li class="fieldcontain">
					<span id="homeCorners-label" class="property-label"><g:message code="game.homeCorners.label" default="Home Corners" /></span>
					
						<span class="property-value" aria-labelledby="homeCorners-label"><g:fieldValue bean="${gameInstance}" field="homeCorners"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.homeFreekicks}">
				<li class="fieldcontain">
					<span id="homeFreekicks-label" class="property-label"><g:message code="game.homeFreekicks.label" default="Home Freekicks" /></span>
					
						<span class="property-value" aria-labelledby="homeFreekicks-label"><g:fieldValue bean="${gameInstance}" field="homeFreekicks"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.homeGoals}">
				<li class="fieldcontain">
					<span id="homeGoals-label" class="property-label"><g:message code="game.homeGoals.label" default="Home Goals" /></span>
					
						<span class="property-value" aria-labelledby="homeGoals-label"><g:fieldValue bean="${gameInstance}" field="homeGoals"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.homeGoalsHalftime}">
				<li class="fieldcontain">
					<span id="homeGoalsHalftime-label" class="property-label"><g:message code="game.homeGoalsHalftime.label" default="Home Goals Halftime" /></span>
					
						<span class="property-value" aria-labelledby="homeGoalsHalftime-label"><g:fieldValue bean="${gameInstance}" field="homeGoalsHalftime"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.homeTeam}">
				<li class="fieldcontain">
					<span id="homeTeam-label" class="property-label"><g:message code="game.homeTeam.label" default="Home Team" /></span>
					
						<span class="property-value" aria-labelledby="homeTeam-label"><g:link controller="club" action="show" id="${gameInstance?.homeTeam?.id}">${gameInstance?.homeTeam?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.referee}">
				<li class="fieldcontain">
					<span id="referee-label" class="property-label"><g:message code="game.referee.label" default="Referee" /></span>
					
						<span class="property-value" aria-labelledby="referee-label"><g:link controller="referee" action="show" id="${gameInstance?.referee?.id}">${gameInstance?.referee?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.tournamentSeason}">
				<li class="fieldcontain">
					<span id="tournamentSeason-label" class="property-label"><g:message code="game.tournamentSeason.label" default="Tournament Season" /></span>
					
						<span class="property-value" aria-labelledby="tournamentSeason-label"><g:link controller="tournamentSeason" action="show" id="${gameInstance?.tournamentSeason?.id}">${gameInstance?.tournamentSeason?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${gameInstance?.id}" />
					<g:link class="edit" action="edit" id="${gameInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
