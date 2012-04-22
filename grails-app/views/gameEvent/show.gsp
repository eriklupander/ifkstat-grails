
<%@ page import="se.ifkgoteborg.stat.model.GameEvent" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gameEvent.label', default: 'GameEvent')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-gameEvent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-gameEvent" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list gameEvent">
			
				<g:if test="${gameEventInstance?.eventType}">
				<li class="fieldcontain">
					<span id="eventType-label" class="property-label"><g:message code="gameEvent.eventType.label" default="Event Type" /></span>
					
						<span class="property-value" aria-labelledby="eventType-label"><g:fieldValue bean="${gameEventInstance}" field="eventType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameEventInstance?.game}">
				<li class="fieldcontain">
					<span id="game-label" class="property-label"><g:message code="gameEvent.game.label" default="Game" /></span>
					
						<span class="property-value" aria-labelledby="game-label"><g:link controller="game" action="show" id="${gameEventInstance?.game?.id}">${gameEventInstance?.game?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameEventInstance?.gameMinute}">
				<li class="fieldcontain">
					<span id="gameMinute-label" class="property-label"><g:message code="gameEvent.gameMinute.label" default="Game Minute" /></span>
					
						<span class="property-value" aria-labelledby="gameMinute-label"><g:fieldValue bean="${gameEventInstance}" field="gameMinute"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameEventInstance?.player}">
				<li class="fieldcontain">
					<span id="player-label" class="property-label"><g:message code="gameEvent.player.label" default="Player" /></span>
					
						<span class="property-value" aria-labelledby="player-label"><g:link controller="player" action="show" id="${gameEventInstance?.player?.id}">${gameEventInstance?.player?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${gameEventInstance?.id}" />
					<g:link class="edit" action="edit" id="${gameEventInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
