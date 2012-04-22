package se.ifkgoteborg.stat.model

import org.springframework.dao.DataIntegrityViolationException

class FormationPositionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [formationPositionInstanceList: FormationPosition.list(params), formationPositionInstanceTotal: FormationPosition.count()]
    }

    def create() {
        [formationPositionInstance: new FormationPosition(params)]
    }

    def save() {
        def formationPositionInstance = new FormationPosition(params)
        if (!formationPositionInstance.save(flush: true)) {
            render(view: "create", model: [formationPositionInstance: formationPositionInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'formationPosition.label', default: 'FormationPosition'), formationPositionInstance.id])
        redirect(action: "show", id: formationPositionInstance.id)
    }

    def show() {
        def formationPositionInstance = FormationPosition.get(params.id)
        if (!formationPositionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'formationPosition.label', default: 'FormationPosition'), params.id])
            redirect(action: "list")
            return
        }

        [formationPositionInstance: formationPositionInstance]
    }

    def edit() {
        def formationPositionInstance = FormationPosition.get(params.id)
        if (!formationPositionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'formationPosition.label', default: 'FormationPosition'), params.id])
            redirect(action: "list")
            return
        }

        [formationPositionInstance: formationPositionInstance]
    }

    def update() {
        def formationPositionInstance = FormationPosition.get(params.id)
        if (!formationPositionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'formationPosition.label', default: 'FormationPosition'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (formationPositionInstance.version > version) {
                formationPositionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'formationPosition.label', default: 'FormationPosition')] as Object[],
                          "Another user has updated this FormationPosition while you were editing")
                render(view: "edit", model: [formationPositionInstance: formationPositionInstance])
                return
            }
        }

        formationPositionInstance.properties = params

        if (!formationPositionInstance.save(flush: true)) {
            render(view: "edit", model: [formationPositionInstance: formationPositionInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'formationPosition.label', default: 'FormationPosition'), formationPositionInstance.id])
        redirect(action: "show", id: formationPositionInstance.id)
    }

    def delete() {
        def formationPositionInstance = FormationPosition.get(params.id)
        if (!formationPositionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'formationPosition.label', default: 'FormationPosition'), params.id])
            redirect(action: "list")
            return
        }

        try {
            formationPositionInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'formationPosition.label', default: 'FormationPosition'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'formationPosition.label', default: 'FormationPosition'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
