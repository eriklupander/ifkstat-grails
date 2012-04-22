
<%@ page import="se.ifkgoteborg.stat.model.FormationPosition" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'formationPosition.label', default: 'FormationPosition')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-formationPosition" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-formationPosition" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list formationPosition">
			
				<g:if test="${formationPositionInstance?.formation}">
				<li class="fieldcontain">
					<span id="formation-label" class="property-label"><g:message code="formationPosition.formation.label" default="Formation" /></span>
					
						<span class="property-value" aria-labelledby="formation-label"><g:link controller="formation" action="show" id="${formationPositionInstance?.formation?.id}">${formationPositionInstance?.formation?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${formationPositionInstance?.index}">
				<li class="fieldcontain">
					<span id="index-label" class="property-label"><g:message code="formationPosition.index.label" default="Index" /></span>
					
						<span class="property-value" aria-labelledby="index-label"><g:fieldValue bean="${formationPositionInstance}" field="index"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${formationPositionInstance?.position}">
				<li class="fieldcontain">
					<span id="position-label" class="property-label"><g:message code="formationPosition.position.label" default="Position" /></span>
					
						<span class="property-value" aria-labelledby="position-label"><g:link controller="position" action="show" id="${formationPositionInstance?.position?.id}">${formationPositionInstance?.position?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${formationPositionInstance?.id}" />
					<g:link class="edit" action="edit" id="${formationPositionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
