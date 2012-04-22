package se.ifkgoteborg.stat.model

import org.springframework.dao.DataIntegrityViolationException

class GroundController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [groundInstanceList: Ground.list(params), groundInstanceTotal: Ground.count()]
    }

    def create() {
        [groundInstance: new Ground(params)]
    }

    def save() {
        def groundInstance = new Ground(params)
        if (!groundInstance.save(flush: true)) {
            render(view: "create", model: [groundInstance: groundInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'ground.label', default: 'Ground'), groundInstance.id])
        redirect(action: "show", id: groundInstance.id)
    }

    def show() {
        def groundInstance = Ground.get(params.id)
        if (!groundInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'ground.label', default: 'Ground'), params.id])
            redirect(action: "list")
            return
        }

        [groundInstance: groundInstance]
    }

    def edit() {
        def groundInstance = Ground.get(params.id)
        if (!groundInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ground.label', default: 'Ground'), params.id])
            redirect(action: "list")
            return
        }

        [groundInstance: groundInstance]
    }

    def update() {
        def groundInstance = Ground.get(params.id)
        if (!groundInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ground.label', default: 'Ground'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (groundInstance.version > version) {
                groundInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'ground.label', default: 'Ground')] as Object[],
                          "Another user has updated this Ground while you were editing")
                render(view: "edit", model: [groundInstance: groundInstance])
                return
            }
        }

        groundInstance.properties = params

        if (!groundInstance.save(flush: true)) {
            render(view: "edit", model: [groundInstance: groundInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'ground.label', default: 'Ground'), groundInstance.id])
        redirect(action: "show", id: groundInstance.id)
    }

    def delete() {
        def groundInstance = Ground.get(params.id)
        if (!groundInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'ground.label', default: 'Ground'), params.id])
            redirect(action: "list")
            return
        }

        try {
            groundInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'ground.label', default: 'Ground'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ground.label', default: 'Ground'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
