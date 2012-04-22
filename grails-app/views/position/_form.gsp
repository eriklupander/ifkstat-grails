<%@ page import="se.ifkgoteborg.stat.model.Position" %>



<div class="fieldcontain ${hasErrors(bean: positionInstance, field: 'id', 'error')} ">
	<label for="id">
		<g:message code="position.id.label" default="Id" />
		
	</label>
	<g:field type="number" name="id" value="${fieldValue(bean: positionInstance, field: 'id')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: positionInstance, field: 'code', 'error')} ">
	<label for="code">
		<g:message code="position.code.label" default="Code" />
		
	</label>
	<g:textField name="code" value="${positionInstance?.code}" />
</div>

<div class="fieldcontain ${hasErrors(bean: positionInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="position.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${positionInstance?.name}" />
</div>

<div class="fieldcontain ${hasErrors(bean: positionInstance, field: 'positionType', 'error')} ">
	<label for="positionType">
		<g:message code="position.positionType.label" default="Position Type" />
		
	</label>
	<g:select id="positionType" name="positionType.id" from="${se.ifkgoteborg.stat.model.PositionType.list()}" optionKey="id" required="" value="${positionInstance?.positionType?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: positionInstance, field: 'side', 'error')} ">
	<label for="side">
		<g:message code="position.side.label" default="Side" />
		
	</label>
	<g:select name="side" from="${se.ifkgoteborg.stat.model.enums.Side?.values()}" keys="${se.ifkgoteborg.stat.model.enums.Side.values()*.name()}" required="" value="${positionInstance?.side?.name()}"/>
</div>

