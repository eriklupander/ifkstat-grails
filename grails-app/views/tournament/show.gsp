
<%@ page import="se.ifkgoteborg.stat.model.Tournament" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tournament.label', default: 'Tournament')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tournament" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tournament" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tournament">
			
				<g:if test="${tournamentInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="tournament.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${tournamentInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tournamentInstance?.yearOverlapping}">
				<li class="fieldcontain">
					<span id="yearOverlapping-label" class="property-label"><g:message code="tournament.yearOverlapping.label" default="Year Overlapping" /></span>
					
						<span class="property-value" aria-labelledby="yearOverlapping-label"><g:formatBoolean boolean="${tournamentInstance?.yearOverlapping}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${tournamentInstance?.id}" />
					<g:link class="edit" action="edit" id="${tournamentInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
