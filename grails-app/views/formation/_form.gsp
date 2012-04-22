<%@ page import="se.ifkgoteborg.stat.model.Formation" %>



<div class="fieldcontain ${hasErrors(bean: formationInstance, field: 'id', 'error')} ">
	<label for="id">
		<g:message code="formation.id.label" default="Id" />
		
	</label>
	<g:field type="number" name="id" value="${fieldValue(bean: formationInstance, field: 'id')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: formationInstance, field: 'formationPositions', 'error')} ">
	<label for="formationPositions">
		<g:message code="formation.formationPositions.label" default="Formation Positions" />
		
	</label>
	<g:select name="formationPositions" from="${se.ifkgoteborg.stat.model.FormationPosition.list()}" multiple="multiple" optionKey="id" size="5" required="" value="${formationInstance?.formationPositions*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: formationInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="formation.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${formationInstance?.name}" />
</div>

