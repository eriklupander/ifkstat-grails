package se.ifkgoteborg.stat.model

import org.springframework.dao.DataIntegrityViolationException

class RefereeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [refereeInstanceList: Referee.list(params), refereeInstanceTotal: Referee.count()]
    }

    def create() {
        [refereeInstance: new Referee(params)]
    }

    def save() {
        def refereeInstance = new Referee(params)
        if (!refereeInstance.save(flush: true)) {
            render(view: "create", model: [refereeInstance: refereeInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'referee.label', default: 'Referee'), refereeInstance.id])
        redirect(action: "show", id: refereeInstance.id)
    }

    def show() {
        def refereeInstance = Referee.get(params.id)
        if (!refereeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'referee.label', default: 'Referee'), params.id])
            redirect(action: "list")
            return
        }

        [refereeInstance: refereeInstance]
    }

    def edit() {
        def refereeInstance = Referee.get(params.id)
        if (!refereeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'referee.label', default: 'Referee'), params.id])
            redirect(action: "list")
            return
        }

        [refereeInstance: refereeInstance]
    }

    def update() {
        def refereeInstance = Referee.get(params.id)
        if (!refereeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'referee.label', default: 'Referee'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (refereeInstance.version > version) {
                refereeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'referee.label', default: 'Referee')] as Object[],
                          "Another user has updated this Referee while you were editing")
                render(view: "edit", model: [refereeInstance: refereeInstance])
                return
            }
        }

        refereeInstance.properties = params

        if (!refereeInstance.save(flush: true)) {
            render(view: "edit", model: [refereeInstance: refereeInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'referee.label', default: 'Referee'), refereeInstance.id])
        redirect(action: "show", id: refereeInstance.id)
    }

    def delete() {
        def refereeInstance = Referee.get(params.id)
        if (!refereeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'referee.label', default: 'Referee'), params.id])
            redirect(action: "list")
            return
        }

        try {
            refereeInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'referee.label', default: 'Referee'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'referee.label', default: 'Referee'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
