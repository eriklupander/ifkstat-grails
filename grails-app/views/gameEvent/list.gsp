
<%@ page import="se.ifkgoteborg.stat.model.GameEvent" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gameEvent.label', default: 'GameEvent')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-gameEvent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-gameEvent" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="eventType" title="${message(code: 'gameEvent.eventType.label', default: 'Event Type')}" />
					
						<th><g:message code="gameEvent.game.label" default="Game" /></th>
					
						<g:sortableColumn property="gameMinute" title="${message(code: 'gameEvent.gameMinute.label', default: 'Game Minute')}" />
					
						<th><g:message code="gameEvent.player.label" default="Player" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${gameEventInstanceList}" status="i" var="gameEventInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${gameEventInstance.id}">${fieldValue(bean: gameEventInstance, field: "eventType")}</g:link></td>
					
						<td>${fieldValue(bean: gameEventInstance, field: "game")}</td>
					
						<td>${fieldValue(bean: gameEventInstance, field: "gameMinute")}</td>
					
						<td>${fieldValue(bean: gameEventInstance, field: "player")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${gameEventInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
