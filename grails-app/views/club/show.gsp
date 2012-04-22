
<%@ page import="se.ifkgoteborg.stat.model.Club" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'club.label', default: 'Club')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-club" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-club" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list club">
			
				<g:if test="${clubInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="club.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${clubInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${clubInstance?.city}">
				<li class="fieldcontain">
					<span id="city-label" class="property-label"><g:message code="club.city.label" default="City" /></span>
					
						<span class="property-value" aria-labelledby="city-label"><g:fieldValue bean="${clubInstance}" field="city"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${clubInstance?.country}">
				<li class="fieldcontain">
					<span id="country-label" class="property-label"><g:message code="club.country.label" default="Country" /></span>
					
						<span class="property-value" aria-labelledby="country-label"><g:link controller="country" action="show" id="${clubInstance?.country?.id}">${clubInstance?.country?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${clubInstance?.defaultClub}">
				<li class="fieldcontain">
					<span id="defaultClub-label" class="property-label"><g:message code="club.defaultClub.label" default="Default Club" /></span>
					
						<span class="property-value" aria-labelledby="defaultClub-label"><g:formatBoolean boolean="${clubInstance?.defaultClub}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${clubInstance?.foundedDate}">
				<li class="fieldcontain">
					<span id="foundedDate-label" class="property-label"><g:message code="club.foundedDate.label" default="Founded Date" /></span>
					
						<span class="property-value" aria-labelledby="foundedDate-label"><g:formatDate date="${clubInstance?.foundedDate}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${clubInstance?.id}" />
					<g:link class="edit" action="edit" id="${clubInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
