<%@ page import="se.ifkgoteborg.stat.model.Ground" %>



<div class="fieldcontain ${hasErrors(bean: groundInstance, field: 'id', 'error')} ">
	<label for="id">
		<g:message code="ground.id.label" default="Id" />
		
	</label>
	<g:field type="number" name="id" value="${fieldValue(bean: groundInstance, field: 'id')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: groundInstance, field: 'address', 'error')} ">
	<label for="address">
		<g:message code="ground.address.label" default="Address" />
		
	</label>
	<g:textField name="address" value="${groundInstance?.address}" />
</div>

<div class="fieldcontain ${hasErrors(bean: groundInstance, field: 'city', 'error')} ">
	<label for="city">
		<g:message code="ground.city.label" default="City" />
		
	</label>
	<g:textField name="city" value="${groundInstance?.city}" />
</div>

<div class="fieldcontain ${hasErrors(bean: groundInstance, field: 'country', 'error')} ">
	<label for="country">
		<g:message code="ground.country.label" default="Country" />
		
	</label>
	<g:select id="country" name="country.id" from="${se.ifkgoteborg.stat.model.Country.list()}" optionKey="id" required="" value="${groundInstance?.country?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: groundInstance, field: 'dateOfConstruction', 'error')} ">
	<label for="dateOfConstruction">
		<g:message code="ground.dateOfConstruction.label" default="Date Of Construction" />
		
	</label>
	<g:datePicker name="dateOfConstruction" precision="day" value="${groundInstance?.dateOfConstruction}" />
</div>

<div class="fieldcontain ${hasErrors(bean: groundInstance, field: 'maxCapacity', 'error')} ">
	<label for="maxCapacity">
		<g:message code="ground.maxCapacity.label" default="Max Capacity" />
		
	</label>
	<g:field type="number" name="maxCapacity" value="${fieldValue(bean: groundInstance, field: 'maxCapacity')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: groundInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="ground.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${groundInstance?.name}" />
</div>

