package se.ifkgoteborg.stat.model



import org.junit.*
import grails.test.mixin.*

@TestFor(PositionController)
@Mock(Position)
class PositionControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/position/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.positionInstanceList.size() == 0
        assert model.positionInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.positionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.positionInstance != null
        assert view == '/position/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/position/show/1'
        assert controller.flash.message != null
        assert Position.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/position/list'


        populateValidParams(params)
        def position = new Position(params)

        assert position.save() != null

        params.id = position.id

        def model = controller.show()

        assert model.positionInstance == position
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/position/list'


        populateValidParams(params)
        def position = new Position(params)

        assert position.save() != null

        params.id = position.id

        def model = controller.edit()

        assert model.positionInstance == position
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/position/list'

        response.reset()


        populateValidParams(params)
        def position = new Position(params)

        assert position.save() != null

        // test invalid parameters in update
        params.id = position.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/position/edit"
        assert model.positionInstance != null

        position.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/position/show/$position.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        position.clearErrors()

        populateValidParams(params)
        params.id = position.id
        params.version = -1
        controller.update()

        assert view == "/position/edit"
        assert model.positionInstance != null
        assert model.positionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/position/list'

        response.reset()

        populateValidParams(params)
        def position = new Position(params)

        assert position.save() != null
        assert Position.count() == 1

        params.id = position.id

        controller.delete()

        assert Position.count() == 0
        assert Position.get(position.id) == null
        assert response.redirectedUrl == '/position/list'
    }
}
