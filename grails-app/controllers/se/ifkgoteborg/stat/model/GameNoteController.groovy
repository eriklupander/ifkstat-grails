package se.ifkgoteborg.stat.model

import org.springframework.dao.DataIntegrityViolationException

class GameNoteController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [gameNoteInstanceList: GameNote.list(params), gameNoteInstanceTotal: GameNote.count()]
    }

    def create() {
        [gameNoteInstance: new GameNote(params)]
    }

    def save() {
        def gameNoteInstance = new GameNote(params)
        if (!gameNoteInstance.save(flush: true)) {
            render(view: "create", model: [gameNoteInstance: gameNoteInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'gameNote.label', default: 'GameNote'), gameNoteInstance.id])
        redirect(action: "show", id: gameNoteInstance.id)
    }

    def show() {
        def gameNoteInstance = GameNote.get(params.id)
        if (!gameNoteInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'gameNote.label', default: 'GameNote'), params.id])
            redirect(action: "list")
            return
        }

        [gameNoteInstance: gameNoteInstance]
    }

    def edit() {
        def gameNoteInstance = GameNote.get(params.id)
        if (!gameNoteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'gameNote.label', default: 'GameNote'), params.id])
            redirect(action: "list")
            return
        }

        [gameNoteInstance: gameNoteInstance]
    }

    def update() {
        def gameNoteInstance = GameNote.get(params.id)
        if (!gameNoteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'gameNote.label', default: 'GameNote'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (gameNoteInstance.version > version) {
                gameNoteInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'gameNote.label', default: 'GameNote')] as Object[],
                          "Another user has updated this GameNote while you were editing")
                render(view: "edit", model: [gameNoteInstance: gameNoteInstance])
                return
            }
        }

        gameNoteInstance.properties = params

        if (!gameNoteInstance.save(flush: true)) {
            render(view: "edit", model: [gameNoteInstance: gameNoteInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'gameNote.label', default: 'GameNote'), gameNoteInstance.id])
        redirect(action: "show", id: gameNoteInstance.id)
    }

    def delete() {
        def gameNoteInstance = GameNote.get(params.id)
        if (!gameNoteInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'gameNote.label', default: 'GameNote'), params.id])
            redirect(action: "list")
            return
        }

        try {
            gameNoteInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'gameNote.label', default: 'GameNote'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'gameNote.label', default: 'GameNote'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
