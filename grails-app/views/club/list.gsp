
<%@ page import="se.ifkgoteborg.stat.model.Club" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'club.label', default: 'Club')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-club" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-club" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'club.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="city" title="${message(code: 'club.city.label', default: 'City')}" />
					
						<th><g:message code="club.country.label" default="Country" /></th>
					
						<g:sortableColumn property="defaultClub" title="${message(code: 'club.defaultClub.label', default: 'Default Club')}" />
					
						<g:sortableColumn property="foundedDate" title="${message(code: 'club.foundedDate.label', default: 'Founded Date')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${clubInstanceList}" status="i" var="clubInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${clubInstance.id}">${fieldValue(bean: clubInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: clubInstance, field: "city")}</td>
					
						<td>${fieldValue(bean: clubInstance, field: "country")}</td>
					
						<td><g:formatBoolean boolean="${clubInstance.defaultClub}" /></td>
					
						<td><g:formatDate date="${clubInstance.foundedDate}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${clubInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
