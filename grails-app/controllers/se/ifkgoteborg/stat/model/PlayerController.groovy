package se.ifkgoteborg.stat.model

import org.springframework.dao.DataIntegrityViolationException

class PlayerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [playerInstanceList: Player.list(params), playerInstanceTotal: Player.count()]
    }

    def create() {
        [playerInstance: new Player(params)]
    }

    def save() {
        def playerInstance = new Player(params)
        if (!playerInstance.save(flush: true)) {
            render(view: "create", model: [playerInstance: playerInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'player.label', default: 'Player'), playerInstance.id])
        redirect(action: "show", id: playerInstance.id)
    }

    def show() {
        def playerInstance = Player.get(params.id)
        if (!playerInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'player.label', default: 'Player'), params.id])
            redirect(action: "list")
            return
        }

        [playerInstance: playerInstance]
    }

    def edit() {
        def playerInstance = Player.get(params.id)
        if (!playerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'player.label', default: 'Player'), params.id])
            redirect(action: "list")
            return
        }

        [playerInstance: playerInstance]
    }

    def update() {
        def playerInstance = Player.get(params.id)
        if (!playerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'player.label', default: 'Player'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (playerInstance.version > version) {
                playerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'player.label', default: 'Player')] as Object[],
                          "Another user has updated this Player while you were editing")
                render(view: "edit", model: [playerInstance: playerInstance])
                return
            }
        }

        playerInstance.properties = params

        if (!playerInstance.save(flush: true)) {
            render(view: "edit", model: [playerInstance: playerInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'player.label', default: 'Player'), playerInstance.id])
        redirect(action: "show", id: playerInstance.id)
    }

    def delete() {
        def playerInstance = Player.get(params.id)
        if (!playerInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'player.label', default: 'Player'), params.id])
            redirect(action: "list")
            return
        }

        try {
            playerInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'player.label', default: 'Player'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'player.label', default: 'Player'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
