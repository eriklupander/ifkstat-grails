<%@ page import="se.ifkgoteborg.stat.model.Club" %>



<div class="fieldcontain ${hasErrors(bean: clubInstance, field: 'id', 'error')} required">
	<label for="id">
		<g:message code="club.id.label" default="Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="id" required="" value="${fieldValue(bean: clubInstance, field: 'id')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clubInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="club.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${clubInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clubInstance, field: 'city', 'error')} ">
	<label for="city">
		<g:message code="club.city.label" default="City" />
		
	</label>
	<g:textField name="city" value="${clubInstance?.city}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clubInstance, field: 'country', 'error')} required">
	<label for="country">
		<g:message code="club.country.label" default="Country" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="country" name="country.id" from="${se.ifkgoteborg.stat.model.Country.list()}" optionKey="id" required="" value="${clubInstance?.country?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clubInstance, field: 'defaultClub', 'error')} ">
	<label for="defaultClub">
		<g:message code="club.defaultClub.label" default="Default Club" />
		
	</label>
	<g:checkBox name="defaultClub" value="${clubInstance?.defaultClub}" />
</div>

<div class="fieldcontain ${hasErrors(bean: clubInstance, field: 'foundedDate', 'error')} required">
	<label for="foundedDate">
		<g:message code="club.foundedDate.label" default="Founded Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="foundedDate" precision="day"  value="${clubInstance?.foundedDate}"  />
</div>

