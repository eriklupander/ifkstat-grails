
<%@ page import="se.ifkgoteborg.stat.model.GameNote" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gameNote.label', default: 'GameNote')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-gameNote" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-gameNote" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="gameNote.game.label" default="Game" /></th>
					
						<g:sortableColumn property="text" title="${message(code: 'gameNote.text.label', default: 'Text')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${gameNoteInstanceList}" status="i" var="gameNoteInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${gameNoteInstance.id}">${fieldValue(bean: gameNoteInstance, field: "game")}</g:link></td>
					
						<td>${fieldValue(bean: gameNoteInstance, field: "text")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${gameNoteInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
