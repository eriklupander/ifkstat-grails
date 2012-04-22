package se.ifkgoteborg.stat.model

import org.springframework.dao.DataIntegrityViolationException

class TournamentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tournamentInstanceList: Tournament.list(params), tournamentInstanceTotal: Tournament.count()]
    }

    def create() {
        [tournamentInstance: new Tournament(params)]
    }

    def save() {
        def tournamentInstance = new Tournament(params)
        if (!tournamentInstance.save(flush: true)) {
            render(view: "create", model: [tournamentInstance: tournamentInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'tournament.label', default: 'Tournament'), tournamentInstance.id])
        redirect(action: "show", id: tournamentInstance.id)
    }

    def show() {
        def tournamentInstance = Tournament.get(params.id)
        if (!tournamentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tournament.label', default: 'Tournament'), params.id])
            redirect(action: "list")
            return
        }

        [tournamentInstance: tournamentInstance]
    }

    def edit() {
        def tournamentInstance = Tournament.get(params.id)
        if (!tournamentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tournament.label', default: 'Tournament'), params.id])
            redirect(action: "list")
            return
        }

        [tournamentInstance: tournamentInstance]
    }

    def update() {
        def tournamentInstance = Tournament.get(params.id)
        if (!tournamentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tournament.label', default: 'Tournament'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (tournamentInstance.version > version) {
                tournamentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tournament.label', default: 'Tournament')] as Object[],
                          "Another user has updated this Tournament while you were editing")
                render(view: "edit", model: [tournamentInstance: tournamentInstance])
                return
            }
        }

        tournamentInstance.properties = params

        if (!tournamentInstance.save(flush: true)) {
            render(view: "edit", model: [tournamentInstance: tournamentInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'tournament.label', default: 'Tournament'), tournamentInstance.id])
        redirect(action: "show", id: tournamentInstance.id)
    }

    def delete() {
        def tournamentInstance = Tournament.get(params.id)
        if (!tournamentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tournament.label', default: 'Tournament'), params.id])
            redirect(action: "list")
            return
        }

        try {
            tournamentInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'tournament.label', default: 'Tournament'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tournament.label', default: 'Tournament'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
