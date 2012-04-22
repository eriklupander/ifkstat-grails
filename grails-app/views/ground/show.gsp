
<%@ page import="se.ifkgoteborg.stat.model.Ground" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ground.label', default: 'Ground')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-ground" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-ground" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list ground">
			
				<g:if test="${groundInstance?.address}">
				<li class="fieldcontain">
					<span id="address-label" class="property-label"><g:message code="ground.address.label" default="Address" /></span>
					
						<span class="property-value" aria-labelledby="address-label"><g:fieldValue bean="${groundInstance}" field="address"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${groundInstance?.city}">
				<li class="fieldcontain">
					<span id="city-label" class="property-label"><g:message code="ground.city.label" default="City" /></span>
					
						<span class="property-value" aria-labelledby="city-label"><g:fieldValue bean="${groundInstance}" field="city"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${groundInstance?.country}">
				<li class="fieldcontain">
					<span id="country-label" class="property-label"><g:message code="ground.country.label" default="Country" /></span>
					
						<span class="property-value" aria-labelledby="country-label"><g:link controller="country" action="show" id="${groundInstance?.country?.id}">${groundInstance?.country?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${groundInstance?.dateOfConstruction}">
				<li class="fieldcontain">
					<span id="dateOfConstruction-label" class="property-label"><g:message code="ground.dateOfConstruction.label" default="Date Of Construction" /></span>
					
						<span class="property-value" aria-labelledby="dateOfConstruction-label"><g:formatDate date="${groundInstance?.dateOfConstruction}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${groundInstance?.maxCapacity}">
				<li class="fieldcontain">
					<span id="maxCapacity-label" class="property-label"><g:message code="ground.maxCapacity.label" default="Max Capacity" /></span>
					
						<span class="property-value" aria-labelledby="maxCapacity-label"><g:fieldValue bean="${groundInstance}" field="maxCapacity"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${groundInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="ground.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${groundInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${groundInstance?.id}" />
					<g:link class="edit" action="edit" id="${groundInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
