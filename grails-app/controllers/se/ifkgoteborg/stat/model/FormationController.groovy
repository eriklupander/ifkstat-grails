package se.ifkgoteborg.stat.model

import org.springframework.dao.DataIntegrityViolationException

class FormationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [formationInstanceList: Formation.list(params), formationInstanceTotal: Formation.count()]
    }

    def create() {
        [formationInstance: new Formation(params)]
    }

    def save() {
        def formationInstance = new Formation(params)
        if (!formationInstance.save(flush: true)) {
            render(view: "create", model: [formationInstance: formationInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'formation.label', default: 'Formation'), formationInstance.id])
        redirect(action: "show", id: formationInstance.id)
    }

    def show() {
        def formationInstance = Formation.get(params.id)
        if (!formationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'formation.label', default: 'Formation'), params.id])
            redirect(action: "list")
            return
        }

        [formationInstance: formationInstance]
    }

    def edit() {
        def formationInstance = Formation.get(params.id)
        if (!formationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'formation.label', default: 'Formation'), params.id])
            redirect(action: "list")
            return
        }

        [formationInstance: formationInstance]
    }

    def update() {
        def formationInstance = Formation.get(params.id)
        if (!formationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'formation.label', default: 'Formation'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (formationInstance.version > version) {
                formationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'formation.label', default: 'Formation')] as Object[],
                          "Another user has updated this Formation while you were editing")
                render(view: "edit", model: [formationInstance: formationInstance])
                return
            }
        }

        formationInstance.properties = params

        if (!formationInstance.save(flush: true)) {
            render(view: "edit", model: [formationInstance: formationInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'formation.label', default: 'Formation'), formationInstance.id])
        redirect(action: "show", id: formationInstance.id)
    }

    def delete() {
        def formationInstance = Formation.get(params.id)
        if (!formationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'formation.label', default: 'Formation'), params.id])
            redirect(action: "list")
            return
        }

        try {
            formationInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'formation.label', default: 'Formation'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'formation.label', default: 'Formation'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
