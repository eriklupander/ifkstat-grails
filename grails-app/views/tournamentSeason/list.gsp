
<%@ page import="se.ifkgoteborg.stat.model.TournamentSeason" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tournamentSeason.label', default: 'TournamentSeason')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tournamentSeason" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tournamentSeason" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="tournamentSeason.season.label" default="Season" /></th>
					
						<g:sortableColumn property="seasonName" title="${message(code: 'tournamentSeason.seasonName.label', default: 'Season Name')}" />
					
						<g:sortableColumn property="start" title="${message(code: 'tournamentSeason.start.label', default: 'Start')}" />
					
						<th><g:message code="tournamentSeason.tournament.label" default="Tournament" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tournamentSeasonInstanceList}" status="i" var="tournamentSeasonInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tournamentSeasonInstance.id}">${fieldValue(bean: tournamentSeasonInstance, field: "season")}</g:link></td>
					
						<td>${fieldValue(bean: tournamentSeasonInstance, field: "seasonName")}</td>
					
						<td><g:formatDate date="${tournamentSeasonInstance.start}" /></td>
					
						<td>${fieldValue(bean: tournamentSeasonInstance, field: "tournament")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tournamentSeasonInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
