
<%@ page import="se.ifkgoteborg.stat.model.GameParticipation" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gameParticipation.label', default: 'GameParticipation')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-gameParticipation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-gameParticipation" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list gameParticipation">
			
				<g:if test="${gameParticipationInstance?.enterMinute}">
				<li class="fieldcontain">
					<span id="enterMinute-label" class="property-label"><g:message code="gameParticipation.enterMinute.label" default="Enter Minute" /></span>
					
						<span class="property-value" aria-labelledby="enterMinute-label"><g:fieldValue bean="${gameParticipationInstance}" field="enterMinute"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameParticipationInstance?.formationPosition}">
				<li class="fieldcontain">
					<span id="formationPosition-label" class="property-label"><g:message code="gameParticipation.formationPosition.label" default="Formation Position" /></span>
					
						<span class="property-value" aria-labelledby="formationPosition-label"><g:link controller="formationPosition" action="show" id="${gameParticipationInstance?.formationPosition?.id}">${gameParticipationInstance?.formationPosition?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameParticipationInstance?.game}">
				<li class="fieldcontain">
					<span id="game-label" class="property-label"><g:message code="gameParticipation.game.label" default="Game" /></span>
					
						<span class="property-value" aria-labelledby="game-label"><g:link controller="game" action="show" id="${gameParticipationInstance?.game?.id}">${gameParticipationInstance?.game?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameParticipationInstance?.participationType}">
				<li class="fieldcontain">
					<span id="participationType-label" class="property-label"><g:message code="gameParticipation.participationType.label" default="Participation Type" /></span>
					
						<span class="property-value" aria-labelledby="participationType-label"><g:fieldValue bean="${gameParticipationInstance}" field="participationType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameParticipationInstance?.player}">
				<li class="fieldcontain">
					<span id="player-label" class="property-label"><g:message code="gameParticipation.player.label" default="Player" /></span>
					
						<span class="property-value" aria-labelledby="player-label"><g:link controller="player" action="show" id="${gameParticipationInstance?.player?.id}">${gameParticipationInstance?.player?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameParticipationInstance?.positionId}">
				<li class="fieldcontain">
					<span id="positionId-label" class="property-label"><g:message code="gameParticipation.positionId.label" default="Position Id" /></span>
					
						<span class="property-value" aria-labelledby="positionId-label"><g:fieldValue bean="${gameParticipationInstance}" field="positionId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameParticipationInstance?.substitutedMinute}">
				<li class="fieldcontain">
					<span id="substitutedMinute-label" class="property-label"><g:message code="gameParticipation.substitutedMinute.label" default="Substituted Minute" /></span>
					
						<span class="property-value" aria-labelledby="substitutedMinute-label"><g:fieldValue bean="${gameParticipationInstance}" field="substitutedMinute"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${gameParticipationInstance?.id}" />
					<g:link class="edit" action="edit" id="${gameParticipationInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
