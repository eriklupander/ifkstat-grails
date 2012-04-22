<%@ page import="se.ifkgoteborg.stat.model.Country" %>



<div class="fieldcontain ${hasErrors(bean: countryInstance, field: 'id', 'error')} ">
	<label for="id">
		<g:message code="country.id.label" default="Id" />
		
	</label>
	<g:field type="number" name="id" value="${fieldValue(bean: countryInstance, field: 'id')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: countryInstance, field: 'code', 'error')} ">
	<label for="code">
		<g:message code="country.code.label" default="Code" />
		
	</label>
	<g:textField name="code" value="${countryInstance?.code}" />
</div>

<div class="fieldcontain ${hasErrors(bean: countryInstance, field: 'isoCode', 'error')} ">
	<label for="isoCode">
		<g:message code="country.isoCode.label" default="Iso Code" />
		
	</label>
	<g:textField name="isoCode" value="${countryInstance?.isoCode}" />
</div>

<div class="fieldcontain ${hasErrors(bean: countryInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="country.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${countryInstance?.name}" />
</div>

