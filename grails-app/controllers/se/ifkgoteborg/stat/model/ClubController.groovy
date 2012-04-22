package se.ifkgoteborg.stat.model

import org.springframework.dao.DataIntegrityViolationException

class ClubController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [clubInstanceList: Club.list(params), clubInstanceTotal: Club.count()]
    }

    def create() {
        [clubInstance: new Club(params)]
    }

    def save() {
        def clubInstance = new Club(params)
        if (!clubInstance.save(flush: true)) {
            render(view: "create", model: [clubInstance: clubInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'club.label', default: 'Club'), clubInstance.id])
        redirect(action: "show", id: clubInstance.id)
    }

    def show() {
        def clubInstance = Club.get(params.id)
        if (!clubInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'club.label', default: 'Club'), params.id])
            redirect(action: "list")
            return
        }

        [clubInstance: clubInstance]
    }

    def edit() {
        def clubInstance = Club.get(params.id)
        if (!clubInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'club.label', default: 'Club'), params.id])
            redirect(action: "list")
            return
        }

        [clubInstance: clubInstance]
    }

    def update() {
        def clubInstance = Club.get(params.id)
        if (!clubInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'club.label', default: 'Club'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (clubInstance.version > version) {
                clubInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'club.label', default: 'Club')] as Object[],
                          "Another user has updated this Club while you were editing")
                render(view: "edit", model: [clubInstance: clubInstance])
                return
            }
        }

        clubInstance.properties = params

        if (!clubInstance.save(flush: true)) {
            render(view: "edit", model: [clubInstance: clubInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'club.label', default: 'Club'), clubInstance.id])
        redirect(action: "show", id: clubInstance.id)
    }

    def delete() {
        def clubInstance = Club.get(params.id)
        if (!clubInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'club.label', default: 'Club'), params.id])
            redirect(action: "list")
            return
        }

        try {
            clubInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'club.label', default: 'Club'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'club.label', default: 'Club'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
