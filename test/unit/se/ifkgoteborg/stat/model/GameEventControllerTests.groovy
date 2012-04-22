package se.ifkgoteborg.stat.model



import org.junit.*
import grails.test.mixin.*

@TestFor(GameEventController)
@Mock(GameEvent)
class GameEventControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/gameEvent/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.gameEventInstanceList.size() == 0
        assert model.gameEventInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.gameEventInstance != null
    }

    void testSave() {
        controller.save()

        assert model.gameEventInstance != null
        assert view == '/gameEvent/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/gameEvent/show/1'
        assert controller.flash.message != null
        assert GameEvent.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/gameEvent/list'


        populateValidParams(params)
        def gameEvent = new GameEvent(params)

        assert gameEvent.save() != null

        params.id = gameEvent.id

        def model = controller.show()

        assert model.gameEventInstance == gameEvent
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/gameEvent/list'


        populateValidParams(params)
        def gameEvent = new GameEvent(params)

        assert gameEvent.save() != null

        params.id = gameEvent.id

        def model = controller.edit()

        assert model.gameEventInstance == gameEvent
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/gameEvent/list'

        response.reset()


        populateValidParams(params)
        def gameEvent = new GameEvent(params)

        assert gameEvent.save() != null

        // test invalid parameters in update
        params.id = gameEvent.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/gameEvent/edit"
        assert model.gameEventInstance != null

        gameEvent.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/gameEvent/show/$gameEvent.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        gameEvent.clearErrors()

        populateValidParams(params)
        params.id = gameEvent.id
        params.version = -1
        controller.update()

        assert view == "/gameEvent/edit"
        assert model.gameEventInstance != null
        assert model.gameEventInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/gameEvent/list'

        response.reset()

        populateValidParams(params)
        def gameEvent = new GameEvent(params)

        assert gameEvent.save() != null
        assert GameEvent.count() == 1

        params.id = gameEvent.id

        controller.delete()

        assert GameEvent.count() == 0
        assert GameEvent.get(gameEvent.id) == null
        assert response.redirectedUrl == '/gameEvent/list'
    }
}
