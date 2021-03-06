
<%@ page import="se.ifkgoteborg.stat.model.Ground" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ground.label', default: 'Ground')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-ground" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-ground" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="address" title="${message(code: 'ground.address.label', default: 'Address')}" />
					
						<g:sortableColumn property="city" title="${message(code: 'ground.city.label', default: 'City')}" />
					
						<th><g:message code="ground.country.label" default="Country" /></th>
					
						<g:sortableColumn property="dateOfConstruction" title="${message(code: 'ground.dateOfConstruction.label', default: 'Date Of Construction')}" />
					
						<g:sortableColumn property="maxCapacity" title="${message(code: 'ground.maxCapacity.label', default: 'Max Capacity')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'ground.name.label', default: 'Name')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${groundInstanceList}" status="i" var="groundInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${groundInstance.id}">${fieldValue(bean: groundInstance, field: "address")}</g:link></td>
					
						<td>${fieldValue(bean: groundInstance, field: "city")}</td>
					
						<td>${fieldValue(bean: groundInstance, field: "country")}</td>
					
						<td><g:formatDate date="${groundInstance.dateOfConstruction}" /></td>
					
						<td>${fieldValue(bean: groundInstance, field: "maxCapacity")}</td>
					
						<td>${fieldValue(bean: groundInstance, field: "name")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${groundInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
