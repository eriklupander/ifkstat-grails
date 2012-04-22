
<%@ page import="se.ifkgoteborg.stat.model.Game" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-game" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-game" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="attendance" title="${message(code: 'game.attendance.label', default: 'Attendance')}" />
					
						<g:sortableColumn property="awayCorners" title="${message(code: 'game.awayCorners.label', default: 'Away Corners')}" />
					
						<g:sortableColumn property="awayFreekicks" title="${message(code: 'game.awayFreekicks.label', default: 'Away Freekicks')}" />
					
						<g:sortableColumn property="awayGoals" title="${message(code: 'game.awayGoals.label', default: 'Away Goals')}" />
					
						<g:sortableColumn property="awayGoalsHalftime" title="${message(code: 'game.awayGoalsHalftime.label', default: 'Away Goals Halftime')}" />
					
						<th><g:message code="game.awayTeam.label" default="Away Team" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${gameInstanceList}" status="i" var="gameInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${gameInstance.id}">${fieldValue(bean: gameInstance, field: "attendance")}</g:link></td>
					
						<td>${fieldValue(bean: gameInstance, field: "awayCorners")}</td>
					
						<td>${fieldValue(bean: gameInstance, field: "awayFreekicks")}</td>
					
						<td>${fieldValue(bean: gameInstance, field: "awayGoals")}</td>
					
						<td>${fieldValue(bean: gameInstance, field: "awayGoalsHalftime")}</td>
					
						<td>${fieldValue(bean: gameInstance, field: "awayTeam")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${gameInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
