package se.ifkgoteborg.stat.model

import org.springframework.dao.DataIntegrityViolationException

class GameEventController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [gameEventInstanceList: GameEvent.list(params), gameEventInstanceTotal: GameEvent.count()]
    }

    def create() {
        [gameEventInstance: new GameEvent(params)]
    }

    def save() {
        def gameEventInstance = new GameEvent(params)
        if (!gameEventInstance.save(flush: true)) {
            render(view: "create", model: [gameEventInstance: gameEventInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'gameEvent.label', default: 'GameEvent'), gameEventInstance.id])
        redirect(action: "show", id: gameEventInstance.id)
    }

    def show() {
        def gameEventInstance = GameEvent.get(params.id)
        if (!gameEventInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'gameEvent.label', default: 'GameEvent'), params.id])
            redirect(action: "list")
            return
        }

        [gameEventInstance: gameEventInstance]
    }

    def edit() {
        def gameEventInstance = GameEvent.get(params.id)
        if (!gameEventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'gameEvent.label', default: 'GameEvent'), params.id])
            redirect(action: "list")
            return
        }

        [gameEventInstance: gameEventInstance]
    }

    def update() {
        def gameEventInstance = GameEvent.get(params.id)
        if (!gameEventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'gameEvent.label', default: 'GameEvent'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (gameEventInstance.version > version) {
                gameEventInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'gameEvent.label', default: 'GameEvent')] as Object[],
                          "Another user has updated this GameEvent while you were editing")
                render(view: "edit", model: [gameEventInstance: gameEventInstance])
                return
            }
        }

        gameEventInstance.properties = params

        if (!gameEventInstance.save(flush: true)) {
            render(view: "edit", model: [gameEventInstance: gameEventInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'gameEvent.label', default: 'GameEvent'), gameEventInstance.id])
        redirect(action: "show", id: gameEventInstance.id)
    }

    def delete() {
        def gameEventInstance = GameEvent.get(params.id)
        if (!gameEventInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'gameEvent.label', default: 'GameEvent'), params.id])
            redirect(action: "list")
            return
        }

        try {
            gameEventInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'gameEvent.label', default: 'GameEvent'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'gameEvent.label', default: 'GameEvent'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
