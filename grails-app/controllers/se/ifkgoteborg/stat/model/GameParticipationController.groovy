package se.ifkgoteborg.stat.model

import org.springframework.dao.DataIntegrityViolationException

class GameParticipationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [gameParticipationInstanceList: GameParticipation.list(params), gameParticipationInstanceTotal: GameParticipation.count()]
    }

    def create() {
        [gameParticipationInstance: new GameParticipation(params)]
    }

    def save() {
        def gameParticipationInstance = new GameParticipation(params)
        if (!gameParticipationInstance.save(flush: true)) {
            render(view: "create", model: [gameParticipationInstance: gameParticipationInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'gameParticipation.label', default: 'GameParticipation'), gameParticipationInstance.id])
        redirect(action: "show", id: gameParticipationInstance.id)
    }

    def show() {
        def gameParticipationInstance = GameParticipation.get(params.id)
        if (!gameParticipationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'gameParticipation.label', default: 'GameParticipation'), params.id])
            redirect(action: "list")
            return
        }

        [gameParticipationInstance: gameParticipationInstance]
    }

    def edit() {
        def gameParticipationInstance = GameParticipation.get(params.id)
        if (!gameParticipationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'gameParticipation.label', default: 'GameParticipation'), params.id])
            redirect(action: "list")
            return
        }

        [gameParticipationInstance: gameParticipationInstance]
    }

    def update() {
        def gameParticipationInstance = GameParticipation.get(params.id)
        if (!gameParticipationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'gameParticipation.label', default: 'GameParticipation'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (gameParticipationInstance.version > version) {
                gameParticipationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'gameParticipation.label', default: 'GameParticipation')] as Object[],
                          "Another user has updated this GameParticipation while you were editing")
                render(view: "edit", model: [gameParticipationInstance: gameParticipationInstance])
                return
            }
        }

        gameParticipationInstance.properties = params

        if (!gameParticipationInstance.save(flush: true)) {
            render(view: "edit", model: [gameParticipationInstance: gameParticipationInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'gameParticipation.label', default: 'GameParticipation'), gameParticipationInstance.id])
        redirect(action: "show", id: gameParticipationInstance.id)
    }

    def delete() {
        def gameParticipationInstance = GameParticipation.get(params.id)
        if (!gameParticipationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'gameParticipation.label', default: 'GameParticipation'), params.id])
            redirect(action: "list")
            return
        }

        try {
            gameParticipationInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'gameParticipation.label', default: 'GameParticipation'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'gameParticipation.label', default: 'GameParticipation'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
