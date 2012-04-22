package se.ifkgoteborg.stat.model



import org.junit.*
import grails.test.mixin.*

@TestFor(GroundController)
@Mock(Ground)
class GroundControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/ground/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.groundInstanceList.size() == 0
        assert model.groundInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.groundInstance != null
    }

    void testSave() {
        controller.save()

        assert model.groundInstance != null
        assert view == '/ground/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/ground/show/1'
        assert controller.flash.message != null
        assert Ground.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/ground/list'


        populateValidParams(params)
        def ground = new Ground(params)

        assert ground.save() != null

        params.id = ground.id

        def model = controller.show()

        assert model.groundInstance == ground
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/ground/list'


        populateValidParams(params)
        def ground = new Ground(params)

        assert ground.save() != null

        params.id = ground.id

        def model = controller.edit()

        assert model.groundInstance == ground
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/ground/list'

        response.reset()


        populateValidParams(params)
        def ground = new Ground(params)

        assert ground.save() != null

        // test invalid parameters in update
        params.id = ground.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/ground/edit"
        assert model.groundInstance != null

        ground.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/ground/show/$ground.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        ground.clearErrors()

        populateValidParams(params)
        params.id = ground.id
        params.version = -1
        controller.update()

        assert view == "/ground/edit"
        assert model.groundInstance != null
        assert model.groundInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/ground/list'

        response.reset()

        populateValidParams(params)
        def ground = new Ground(params)

        assert ground.save() != null
        assert Ground.count() == 1

        params.id = ground.id

        controller.delete()

        assert Ground.count() == 0
        assert Ground.get(ground.id) == null
        assert response.redirectedUrl == '/ground/list'
    }
}
