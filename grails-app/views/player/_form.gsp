<%@ page import="se.ifkgoteborg.stat.model.Player" %>



<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'id', 'error')} required">
	<label for="id">
		<g:message code="player.id.label" default="Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="id" required="" value="${fieldValue(bean: playerInstance, field: 'id')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="player.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${playerInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'biography', 'error')} ">
	<label for="biography">
		<g:message code="player.biography.label" default="Biography" />
		
	</label>
	<g:textField name="biography" value="${playerInstance?.biography}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'clubs', 'error')} required">
	<label for="clubs">
		<g:message code="player.clubs.label" default="Clubs" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="clubs" from="${se.ifkgoteborg.stat.model.PlayedForClub.list()}" multiple="multiple" optionKey="id" size="5" required="" value="${playerInstance?.clubs*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'dateOfBirth', 'error')} required">
	<label for="dateOfBirth">
		<g:message code="player.dateOfBirth.label" default="Date Of Birth" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateOfBirth" precision="day"  value="${playerInstance?.dateOfBirth}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'fullName', 'error')} ">
	<label for="fullName">
		<g:message code="player.fullName.label" default="Full Name" />
		
	</label>
	<g:textField name="fullName" value="${playerInstance?.fullName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'games', 'error')} required">
	<label for="games">
		<g:message code="player.games.label" default="Games" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="games" from="${se.ifkgoteborg.stat.model.GameParticipation.list()}" multiple="multiple" optionKey="id" size="5" required="" value="${playerInstance?.games*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'imageUrl', 'error')} ">
	<label for="imageUrl">
		<g:message code="player.imageUrl.label" default="Image Url" />
		
	</label>
	<g:textField name="imageUrl" value="${playerInstance?.imageUrl}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'length', 'error')} required">
	<label for="length">
		<g:message code="player.length.label" default="Length" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="length" required="" value="${fieldValue(bean: playerInstance, field: 'length')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'motherClub', 'error')} ">
	<label for="motherClub">
		<g:message code="player.motherClub.label" default="Mother Club" />
		
	</label>
	<g:textField name="motherClub" value="${playerInstance?.motherClub}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'nationality', 'error')} required">
	<label for="nationality">
		<g:message code="player.nationality.label" default="Nationality" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="nationality" name="nationality.id" from="${se.ifkgoteborg.stat.model.Country.list()}" optionKey="id" required="" value="${playerInstance?.nationality?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'otherCompetitiveGames', 'error')} required">
	<label for="otherCompetitiveGames">
		<g:message code="player.otherCompetitiveGames.label" default="Other Competitive Games" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="otherCompetitiveGames" required="" value="${fieldValue(bean: playerInstance, field: 'otherCompetitiveGames')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'otherPracticeGames', 'error')} required">
	<label for="otherPracticeGames">
		<g:message code="player.otherPracticeGames.label" default="Other Practice Games" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="otherPracticeGames" required="" value="${fieldValue(bean: playerInstance, field: 'otherPracticeGames')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'playedForClubs', 'error')} ">
	<label for="playedForClubs">
		<g:message code="player.playedForClubs.label" default="Played For Clubs" />
		
	</label>
	<g:textField name="playedForClubs" value="${playerInstance?.playedForClubs}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'positionType', 'error')} required">
	<label for="positionType">
		<g:message code="player.positionType.label" default="Position Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="positionType" name="positionType.id" from="${se.ifkgoteborg.stat.model.PositionType.list()}" optionKey="id" required="" value="${playerInstance?.positionType?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'squadNumber', 'error')} required">
	<label for="squadNumber">
		<g:message code="player.squadNumber.label" default="Squad Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="squadNumber" required="" value="${fieldValue(bean: playerInstance, field: 'squadNumber')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'weight', 'error')} required">
	<label for="weight">
		<g:message code="player.weight.label" default="Weight" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="weight" required="" value="${fieldValue(bean: playerInstance, field: 'weight')}"/>
</div>

