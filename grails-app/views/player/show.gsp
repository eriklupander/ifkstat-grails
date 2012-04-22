
<%@ page import="se.ifkgoteborg.stat.model.Player" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'player.label', default: 'Player')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-player" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-player" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list player">
			
				<g:if test="${playerInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="player.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${playerInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.biography}">
				<li class="fieldcontain">
					<span id="biography-label" class="property-label"><g:message code="player.biography.label" default="Biography" /></span>
					
						<span class="property-value" aria-labelledby="biography-label"><g:fieldValue bean="${playerInstance}" field="biography"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.clubs}">
				<li class="fieldcontain">
					<span id="clubs-label" class="property-label"><g:message code="player.clubs.label" default="Clubs" /></span>
					
						<g:each in="${playerInstance.clubs}" var="c">
						<span class="property-value" aria-labelledby="clubs-label"><g:link controller="playedForClub" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.dateOfBirth}">
				<li class="fieldcontain">
					<span id="dateOfBirth-label" class="property-label"><g:message code="player.dateOfBirth.label" default="Date Of Birth" /></span>
					
						<span class="property-value" aria-labelledby="dateOfBirth-label"><g:formatDate date="${playerInstance?.dateOfBirth}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.fullName}">
				<li class="fieldcontain">
					<span id="fullName-label" class="property-label"><g:message code="player.fullName.label" default="Full Name" /></span>
					
						<span class="property-value" aria-labelledby="fullName-label"><g:fieldValue bean="${playerInstance}" field="fullName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.games}">
				<li class="fieldcontain">
					<span id="games-label" class="property-label"><g:message code="player.games.label" default="Games" /></span>
					
						<g:each in="${playerInstance.games}" var="g">
						<span class="property-value" aria-labelledby="games-label"><g:link controller="gameParticipation" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.imageUrl}">
				<li class="fieldcontain">
					<span id="imageUrl-label" class="property-label"><g:message code="player.imageUrl.label" default="Image Url" /></span>
					
						<span class="property-value" aria-labelledby="imageUrl-label"><g:fieldValue bean="${playerInstance}" field="imageUrl"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.length}">
				<li class="fieldcontain">
					<span id="length-label" class="property-label"><g:message code="player.length.label" default="Length" /></span>
					
						<span class="property-value" aria-labelledby="length-label"><g:fieldValue bean="${playerInstance}" field="length"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.motherClub}">
				<li class="fieldcontain">
					<span id="motherClub-label" class="property-label"><g:message code="player.motherClub.label" default="Mother Club" /></span>
					
						<span class="property-value" aria-labelledby="motherClub-label"><g:fieldValue bean="${playerInstance}" field="motherClub"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.nationality}">
				<li class="fieldcontain">
					<span id="nationality-label" class="property-label"><g:message code="player.nationality.label" default="Nationality" /></span>
					
						<span class="property-value" aria-labelledby="nationality-label"><g:link controller="country" action="show" id="${playerInstance?.nationality?.id}">${playerInstance?.nationality?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.otherCompetitiveGames}">
				<li class="fieldcontain">
					<span id="otherCompetitiveGames-label" class="property-label"><g:message code="player.otherCompetitiveGames.label" default="Other Competitive Games" /></span>
					
						<span class="property-value" aria-labelledby="otherCompetitiveGames-label"><g:fieldValue bean="${playerInstance}" field="otherCompetitiveGames"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.otherPracticeGames}">
				<li class="fieldcontain">
					<span id="otherPracticeGames-label" class="property-label"><g:message code="player.otherPracticeGames.label" default="Other Practice Games" /></span>
					
						<span class="property-value" aria-labelledby="otherPracticeGames-label"><g:fieldValue bean="${playerInstance}" field="otherPracticeGames"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.playedForClubs}">
				<li class="fieldcontain">
					<span id="playedForClubs-label" class="property-label"><g:message code="player.playedForClubs.label" default="Played For Clubs" /></span>
					
						<span class="property-value" aria-labelledby="playedForClubs-label"><g:fieldValue bean="${playerInstance}" field="playedForClubs"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.positionType}">
				<li class="fieldcontain">
					<span id="positionType-label" class="property-label"><g:message code="player.positionType.label" default="Position Type" /></span>
					
						<span class="property-value" aria-labelledby="positionType-label"><g:link controller="positionType" action="show" id="${playerInstance?.positionType?.id}">${playerInstance?.positionType?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.squadNumber}">
				<li class="fieldcontain">
					<span id="squadNumber-label" class="property-label"><g:message code="player.squadNumber.label" default="Squad Number" /></span>
					
						<span class="property-value" aria-labelledby="squadNumber-label"><g:fieldValue bean="${playerInstance}" field="squadNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.weight}">
				<li class="fieldcontain">
					<span id="weight-label" class="property-label"><g:message code="player.weight.label" default="Weight" /></span>
					
						<span class="property-value" aria-labelledby="weight-label"><g:fieldValue bean="${playerInstance}" field="weight"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${playerInstance?.id}" />
					<g:link class="edit" action="edit" id="${playerInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
