
<%@ page import="se.ifkgoteborg.stat.model.Country" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'country.label', default: 'Country')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-country" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-country" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="code" title="${message(code: 'country.code.label', default: 'Code')}" />
					
						<g:sortableColumn property="isoCode" title="${message(code: 'country.isoCode.label', default: 'Iso Code')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'country.name.label', default: 'Name')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${countryInstanceList}" status="i" var="countryInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${countryInstance.id}">${fieldValue(bean: countryInstance, field: "code")}</g:link></td>
					
						<td>${fieldValue(bean: countryInstance, field: "isoCode")}</td>
					
						<td>${fieldValue(bean: countryInstance, field: "name")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${countryInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
