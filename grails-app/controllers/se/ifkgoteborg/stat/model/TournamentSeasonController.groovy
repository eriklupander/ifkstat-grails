package se.ifkgoteborg.stat.model

import org.springframework.dao.DataIntegrityViolationException

class TournamentSeasonController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tournamentSeasonInstanceList: TournamentSeason.list(params), tournamentSeasonInstanceTotal: TournamentSeason.count()]
    }

    def create() {
        [tournamentSeasonInstance: new TournamentSeason(params)]
    }

    def save() {
        def tournamentSeasonInstance = new TournamentSeason(params)
        if (!tournamentSeasonInstance.save(flush: true)) {
            render(view: "create", model: [tournamentSeasonInstance: tournamentSeasonInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'tournamentSeason.label', default: 'TournamentSeason'), tournamentSeasonInstance.id])
        redirect(action: "show", id: tournamentSeasonInstance.id)
    }

    def show() {
        def tournamentSeasonInstance = TournamentSeason.get(params.id)
        if (!tournamentSeasonInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tournamentSeason.label', default: 'TournamentSeason'), params.id])
            redirect(action: "list")
            return
        }

        [tournamentSeasonInstance: tournamentSeasonInstance]
    }

    def edit() {
        def tournamentSeasonInstance = TournamentSeason.get(params.id)
        if (!tournamentSeasonInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tournamentSeason.label', default: 'TournamentSeason'), params.id])
            redirect(action: "list")
            return
        }

        [tournamentSeasonInstance: tournamentSeasonInstance]
    }

    def update() {
        def tournamentSeasonInstance = TournamentSeason.get(params.id)
        if (!tournamentSeasonInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tournamentSeason.label', default: 'TournamentSeason'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (tournamentSeasonInstance.version > version) {
                tournamentSeasonInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tournamentSeason.label', default: 'TournamentSeason')] as Object[],
                          "Another user has updated this TournamentSeason while you were editing")
                render(view: "edit", model: [tournamentSeasonInstance: tournamentSeasonInstance])
                return
            }
        }

        tournamentSeasonInstance.properties = params

        if (!tournamentSeasonInstance.save(flush: true)) {
            render(view: "edit", model: [tournamentSeasonInstance: tournamentSeasonInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'tournamentSeason.label', default: 'TournamentSeason'), tournamentSeasonInstance.id])
        redirect(action: "show", id: tournamentSeasonInstance.id)
    }

    def delete() {
        def tournamentSeasonInstance = TournamentSeason.get(params.id)
        if (!tournamentSeasonInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tournamentSeason.label', default: 'TournamentSeason'), params.id])
            redirect(action: "list")
            return
        }

        try {
            tournamentSeasonInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'tournamentSeason.label', default: 'TournamentSeason'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tournamentSeason.label', default: 'TournamentSeason'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
