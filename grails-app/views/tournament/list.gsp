
<%@ page import="se.ifkgoteborg.stat.model.Tournament" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tournament.label', default: 'Tournament')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tournament" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tournament" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'tournament.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="yearOverlapping" title="${message(code: 'tournament.yearOverlapping.label', default: 'Year Overlapping')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tournamentInstanceList}" status="i" var="tournamentInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tournamentInstance.id}">${fieldValue(bean: tournamentInstance, field: "name")}</g:link></td>
					
						<td><g:formatBoolean boolean="${tournamentInstance.yearOverlapping}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tournamentInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
