<%@ page import="se.ifkgoteborg.stat.model.Tournament" %>



<div class="fieldcontain ${hasErrors(bean: tournamentInstance, field: 'id', 'error')} ">
	<label for="id">
		<g:message code="tournament.id.label" default="Id" />
		
	</label>
	<g:field type="number" name="id" value="${fieldValue(bean: tournamentInstance, field: 'id')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: tournamentInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="tournament.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${tournamentInstance?.name}" />
</div>

<div class="fieldcontain ${hasErrors(bean: tournamentInstance, field: 'yearOverlapping', 'error')} ">
	<label for="yearOverlapping">
		<g:message code="tournament.yearOverlapping.label" default="Year Overlapping" />
		
	</label>
	<g:checkBox name="yearOverlapping" value="${tournamentInstance?.yearOverlapping}" />
</div>

