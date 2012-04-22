
<%@ page import="se.ifkgoteborg.stat.model.TournamentSeason" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tournamentSeason.label', default: 'TournamentSeason')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tournamentSeason" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tournamentSeason" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tournamentSeason">
			
				<g:if test="${tournamentSeasonInstance?.games}">
				<li class="fieldcontain">
					<span id="games-label" class="property-label"><g:message code="tournamentSeason.games.label" default="Games" /></span>
					
						<g:each in="${tournamentSeasonInstance.games}" var="g">
						<span class="property-value" aria-labelledby="games-label"><g:link controller="game" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${tournamentSeasonInstance?.season}">
				<li class="fieldcontain">
					<span id="season-label" class="property-label"><g:message code="tournamentSeason.season.label" default="Season" /></span>
					
						<span class="property-value" aria-labelledby="season-label"><g:link controller="squadSeason" action="show" id="${tournamentSeasonInstance?.season?.id}">${tournamentSeasonInstance?.season?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${tournamentSeasonInstance?.seasonName}">
				<li class="fieldcontain">
					<span id="seasonName-label" class="property-label"><g:message code="tournamentSeason.seasonName.label" default="Season Name" /></span>
					
						<span class="property-value" aria-labelledby="seasonName-label"><g:fieldValue bean="${tournamentSeasonInstance}" field="seasonName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tournamentSeasonInstance?.start}">
				<li class="fieldcontain">
					<span id="start-label" class="property-label"><g:message code="tournamentSeason.start.label" default="Start" /></span>
					
						<span class="property-value" aria-labelledby="start-label"><g:formatDate date="${tournamentSeasonInstance?.start}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${tournamentSeasonInstance?.tournament}">
				<li class="fieldcontain">
					<span id="tournament-label" class="property-label"><g:message code="tournamentSeason.tournament.label" default="Tournament" /></span>
					
						<span class="property-value" aria-labelledby="tournament-label"><g:link controller="tournament" action="show" id="${tournamentSeasonInstance?.tournament?.id}">${tournamentSeasonInstance?.tournament?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${tournamentSeasonInstance?.id}" />
					<g:link class="edit" action="edit" id="${tournamentSeasonInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
