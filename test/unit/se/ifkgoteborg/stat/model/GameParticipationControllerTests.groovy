package se.ifkgoteborg.stat.model



import org.junit.*
import grails.test.mixin.*

@TestFor(GameParticipationController)
@Mock(GameParticipation)
class GameParticipationControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/gameParticipation/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.gameParticipationInstanceList.size() == 0
        assert model.gameParticipationInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.gameParticipationInstance != null
    }

    void testSave() {
        controller.save()

        assert model.gameParticipationInstance != null
        assert view == '/gameParticipation/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/gameParticipation/show/1'
        assert controller.flash.message != null
        assert GameParticipation.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/gameParticipation/list'


        populateValidParams(params)
        def gameParticipation = new GameParticipation(params)

        assert gameParticipation.save() != null

        params.id = gameParticipation.id

        def model = controller.show()

        assert model.gameParticipationInstance == gameParticipation
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/gameParticipation/list'


        populateValidParams(params)
        def gameParticipation = new GameParticipation(params)

        assert gameParticipation.save() != null

        params.id = gameParticipation.id

        def model = controller.edit()

        assert model.gameParticipationInstance == gameParticipation
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/gameParticipation/list'

        response.reset()


        populateValidParams(params)
        def gameParticipation = new GameParticipation(params)

        assert gameParticipation.save() != null

        // test invalid parameters in update
        params.id = gameParticipation.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/gameParticipation/edit"
        assert model.gameParticipationInstance != null

        gameParticipation.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/gameParticipation/show/$gameParticipation.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        gameParticipation.clearErrors()

        populateValidParams(params)
        params.id = gameParticipation.id
        params.version = -1
        controller.update()

        assert view == "/gameParticipation/edit"
        assert model.gameParticipationInstance != null
        assert model.gameParticipationInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/gameParticipation/list'

        response.reset()

        populateValidParams(params)
        def gameParticipation = new GameParticipation(params)

        assert gameParticipation.save() != null
        assert GameParticipation.count() == 1

        params.id = gameParticipation.id

        controller.delete()

        assert GameParticipation.count() == 0
        assert GameParticipation.get(gameParticipation.id) == null
        assert response.redirectedUrl == '/gameParticipation/list'
    }
}
