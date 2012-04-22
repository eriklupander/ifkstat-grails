package se.ifkgoteborg.stat.model



import org.junit.*
import grails.test.mixin.*

@TestFor(FormationController)
@Mock(Formation)
class FormationControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/formation/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.formationInstanceList.size() == 0
        assert model.formationInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.formationInstance != null
    }

    void testSave() {
        controller.save()

        assert model.formationInstance != null
        assert view == '/formation/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/formation/show/1'
        assert controller.flash.message != null
        assert Formation.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/formation/list'


        populateValidParams(params)
        def formation = new Formation(params)

        assert formation.save() != null

        params.id = formation.id

        def model = controller.show()

        assert model.formationInstance == formation
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/formation/list'


        populateValidParams(params)
        def formation = new Formation(params)

        assert formation.save() != null

        params.id = formation.id

        def model = controller.edit()

        assert model.formationInstance == formation
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/formation/list'

        response.reset()


        populateValidParams(params)
        def formation = new Formation(params)

        assert formation.save() != null

        // test invalid parameters in update
        params.id = formation.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/formation/edit"
        assert model.formationInstance != null

        formation.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/formation/show/$formation.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        formation.clearErrors()

        populateValidParams(params)
        params.id = formation.id
        params.version = -1
        controller.update()

        assert view == "/formation/edit"
        assert model.formationInstance != null
        assert model.formationInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/formation/list'

        response.reset()

        populateValidParams(params)
        def formation = new Formation(params)

        assert formation.save() != null
        assert Formation.count() == 1

        params.id = formation.id

        controller.delete()

        assert Formation.count() == 0
        assert Formation.get(formation.id) == null
        assert response.redirectedUrl == '/formation/list'
    }
}
