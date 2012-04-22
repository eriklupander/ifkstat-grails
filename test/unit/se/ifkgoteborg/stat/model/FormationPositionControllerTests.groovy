package se.ifkgoteborg.stat.model



import org.junit.*
import grails.test.mixin.*

@TestFor(FormationPositionController)
@Mock(FormationPosition)
class FormationPositionControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/formationPosition/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.formationPositionInstanceList.size() == 0
        assert model.formationPositionInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.formationPositionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.formationPositionInstance != null
        assert view == '/formationPosition/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/formationPosition/show/1'
        assert controller.flash.message != null
        assert FormationPosition.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/formationPosition/list'


        populateValidParams(params)
        def formationPosition = new FormationPosition(params)

        assert formationPosition.save() != null

        params.id = formationPosition.id

        def model = controller.show()

        assert model.formationPositionInstance == formationPosition
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/formationPosition/list'


        populateValidParams(params)
        def formationPosition = new FormationPosition(params)

        assert formationPosition.save() != null

        params.id = formationPosition.id

        def model = controller.edit()

        assert model.formationPositionInstance == formationPosition
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/formationPosition/list'

        response.reset()


        populateValidParams(params)
        def formationPosition = new FormationPosition(params)

        assert formationPosition.save() != null

        // test invalid parameters in update
        params.id = formationPosition.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/formationPosition/edit"
        assert model.formationPositionInstance != null

        formationPosition.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/formationPosition/show/$formationPosition.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        formationPosition.clearErrors()

        populateValidParams(params)
        params.id = formationPosition.id
        params.version = -1
        controller.update()

        assert view == "/formationPosition/edit"
        assert model.formationPositionInstance != null
        assert model.formationPositionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/formationPosition/list'

        response.reset()

        populateValidParams(params)
        def formationPosition = new FormationPosition(params)

        assert formationPosition.save() != null
        assert FormationPosition.count() == 1

        params.id = formationPosition.id

        controller.delete()

        assert FormationPosition.count() == 0
        assert FormationPosition.get(formationPosition.id) == null
        assert response.redirectedUrl == '/formationPosition/list'
    }
}
