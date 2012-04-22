package se.ifkgoteborg.stat.model



import org.junit.*
import grails.test.mixin.*

@TestFor(GameController)
@Mock(Game)
class GameControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/game/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.gameInstanceList.size() == 0
        assert model.gameInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.gameInstance != null
    }

    void testSave() {
        controller.save()

        assert model.gameInstance != null
        assert view == '/game/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/game/show/1'
        assert controller.flash.message != null
        assert Game.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/game/list'


        populateValidParams(params)
        def game = new Game(params)

        assert game.save() != null

        params.id = game.id

        def model = controller.show()

        assert model.gameInstance == game
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/game/list'


        populateValidParams(params)
        def game = new Game(params)

        assert game.save() != null

        params.id = game.id

        def model = controller.edit()

        assert model.gameInstance == game
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/game/list'

        response.reset()


        populateValidParams(params)
        def game = new Game(params)

        assert game.save() != null

        // test invalid parameters in update
        params.id = game.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/game/edit"
        assert model.gameInstance != null

        game.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/game/show/$game.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        game.clearErrors()

        populateValidParams(params)
        params.id = game.id
        params.version = -1
        controller.update()

        assert view == "/game/edit"
        assert model.gameInstance != null
        assert model.gameInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/game/list'

        response.reset()

        populateValidParams(params)
        def game = new Game(params)

        assert game.save() != null
        assert Game.count() == 1

        params.id = game.id

        controller.delete()

        assert Game.count() == 0
        assert Game.get(game.id) == null
        assert response.redirectedUrl == '/game/list'
    }
}
