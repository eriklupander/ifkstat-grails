<%@ page import="se.ifkgoteborg.stat.model.FormationPosition" %>



<div class="fieldcontain ${hasErrors(bean: formationPositionInstance, field: 'id', 'error')} ">
	<label for="id">
		<g:message code="formationPosition.id.label" default="Id" />
		
	</label>
	<g:field type="number" name="id" value="${fieldValue(bean: formationPositionInstance, field: 'id')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: formationPositionInstance, field: 'formation', 'error')} ">
	<label for="formation">
		<g:message code="formationPosition.formation.label" default="Formation" />
		
	</label>
	<g:select id="formation" name="formation.id" from="${se.ifkgoteborg.stat.model.Formation.list()}" optionKey="id" required="" value="${formationPositionInstance?.formation?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: formationPositionInstance, field: 'index', 'error')} ">
	<label for="index">
		<g:message code="formationPosition.index.label" default="Index" />
		
	</label>
	<g:field type="number" name="index" value="${fieldValue(bean: formationPositionInstance, field: 'index')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: formationPositionInstance, field: 'position', 'error')} ">
	<label for="position">
		<g:message code="formationPosition.position.label" default="Position" />
		
	</label>
	<g:select id="position" name="position.id" from="${se.ifkgoteborg.stat.model.Position.list()}" optionKey="id" required="" value="${formationPositionInstance?.position?.id}" class="many-to-one"/>
</div>

