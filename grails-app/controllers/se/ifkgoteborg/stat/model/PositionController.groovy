package se.ifkgoteborg.stat.model

import org.springframework.dao.DataIntegrityViolationException

class PositionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [positionInstanceList: Position.list(params), positionInstanceTotal: Position.count()]
    }

    def create() {
        [positionInstance: new Position(params)]
    }

    def save() {
        def positionInstance = new Position(params)
        if (!positionInstance.save(flush: true)) {
            render(view: "create", model: [positionInstance: positionInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'position.label', default: 'Position'), positionInstance.id])
        redirect(action: "show", id: positionInstance.id)
    }

    def show() {
        def positionInstance = Position.get(params.id)
        if (!positionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'position.label', default: 'Position'), params.id])
            redirect(action: "list")
            return
        }

        [positionInstance: positionInstance]
    }

    def edit() {
        def positionInstance = Position.get(params.id)
        if (!positionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'position.label', default: 'Position'), params.id])
            redirect(action: "list")
            return
        }

        [positionInstance: positionInstance]
    }

    def update() {
        def positionInstance = Position.get(params.id)
        if (!positionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'position.label', default: 'Position'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (positionInstance.version > version) {
                positionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'position.label', default: 'Position')] as Object[],
                          "Another user has updated this Position while you were editing")
                render(view: "edit", model: [positionInstance: positionInstance])
                return
            }
        }

        positionInstance.properties = params

        if (!positionInstance.save(flush: true)) {
            render(view: "edit", model: [positionInstance: positionInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'position.label', default: 'Position'), positionInstance.id])
        redirect(action: "show", id: positionInstance.id)
    }

    def delete() {
        def positionInstance = Position.get(params.id)
        if (!positionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'position.label', default: 'Position'), params.id])
            redirect(action: "list")
            return
        }

        try {
            positionInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'position.label', default: 'Position'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'position.label', default: 'Position'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
