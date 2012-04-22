<%@ page import="se.ifkgoteborg.stat.model.Referee" %>



<div class="fieldcontain ${hasErrors(bean: refereeInstance, field: 'id', 'error')} ">
	<label for="id">
		<g:message code="referee.id.label" default="Id" />
		
	</label>
	<g:field type="number" name="id" value="${fieldValue(bean: refereeInstance, field: 'id')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: refereeInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="referee.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${refereeInstance?.name}" />
</div>

