
<%@ page import="se.ifkgoteborg.stat.model.Position" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'position.label', default: 'Position')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-position" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-position" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list position">
			
				<g:if test="${positionInstance?.code}">
				<li class="fieldcontain">
					<span id="code-label" class="property-label"><g:message code="position.code.label" default="Code" /></span>
					
						<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${positionInstance}" field="code"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${positionInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="position.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${positionInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${positionInstance?.positionType}">
				<li class="fieldcontain">
					<span id="positionType-label" class="property-label"><g:message code="position.positionType.label" default="Position Type" /></span>
					
						<span class="property-value" aria-labelledby="positionType-label"><g:link controller="positionType" action="show" id="${positionInstance?.positionType?.id}">${positionInstance?.positionType?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${positionInstance?.side}">
				<li class="fieldcontain">
					<span id="side-label" class="property-label"><g:message code="position.side.label" default="Side" /></span>
					
						<span class="property-value" aria-labelledby="side-label"><g:fieldValue bean="${positionInstance}" field="side"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${positionInstance?.id}" />
					<g:link class="edit" action="edit" id="${positionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
