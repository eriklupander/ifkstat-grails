<%@ page import="se.ifkgoteborg.stat.model.GameNote" %>



<div class="fieldcontain ${hasErrors(bean: gameNoteInstance, field: 'id', 'error')} ">
	<label for="id">
		<g:message code="gameNote.id.label" default="Id" />
		
	</label>
	<g:field type="number" name="id" value="${fieldValue(bean: gameNoteInstance, field: 'id')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: gameNoteInstance, field: 'game', 'error')} ">
	<label for="game">
		<g:message code="gameNote.game.label" default="Game" />
		
	</label>
	<g:select id="game" name="game.id" from="${se.ifkgoteborg.stat.model.Game.list()}" optionKey="id" required="" value="${gameNoteInstance?.game?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameNoteInstance, field: 'text', 'error')} ">
	<label for="text">
		<g:message code="gameNote.text.label" default="Text" />
		
	</label>
	<g:textField name="text" value="${gameNoteInstance?.text}" />
</div>

