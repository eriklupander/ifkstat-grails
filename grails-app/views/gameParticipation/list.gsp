
<%@ page import="se.ifkgoteborg.stat.model.GameParticipation" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gameParticipation.label', default: 'GameParticipation')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-gameParticipation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-gameParticipation" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="enterMinute" title="${message(code: 'gameParticipation.enterMinute.label', default: 'Enter Minute')}" />
					
						<th><g:message code="gameParticipation.formationPosition.label" default="Formation Position" /></th>
					
						<th><g:message code="gameParticipation.game.label" default="Game" /></th>
					
						<g:sortableColumn property="participationType" title="${message(code: 'gameParticipation.participationType.label', default: 'Participation Type')}" />
					
						<th><g:message code="gameParticipation.player.label" default="Player" /></th>
					
						<g:sortableColumn property="positionId" title="${message(code: 'gameParticipation.positionId.label', default: 'Position Id')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${gameParticipationInstanceList}" status="i" var="gameParticipationInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${gameParticipationInstance.id}">${fieldValue(bean: gameParticipationInstance, field: "enterMinute")}</g:link></td>
					
						<td>${fieldValue(bean: gameParticipationInstance, field: "formationPosition")}</td>
					
						<td>${fieldValue(bean: gameParticipationInstance, field: "game")}</td>
					
						<td>${fieldValue(bean: gameParticipationInstance, field: "participationType")}</td>
					
						<td>${fieldValue(bean: gameParticipationInstance, field: "player")}</td>
					
						<td>${fieldValue(bean: gameParticipationInstance, field: "positionId")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${gameParticipationInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
