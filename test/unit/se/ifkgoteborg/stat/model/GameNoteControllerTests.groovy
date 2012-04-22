package se.ifkgoteborg.stat.model



import org.junit.*
import grails.test.mixin.*

@TestFor(GameNoteController)
@Mock(GameNote)
class GameNoteControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/gameNote/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.gameNoteInstanceList.size() == 0
        assert model.gameNoteInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.gameNoteInstance != null
    }

    void testSave() {
        controller.save()

        assert model.gameNoteInstance != null
        assert view == '/gameNote/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/gameNote/show/1'
        assert controller.flash.message != null
        assert GameNote.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/gameNote/list'


        populateValidParams(params)
        def gameNote = new GameNote(params)

        assert gameNote.save() != null

        params.id = gameNote.id

        def model = controller.show()

        assert model.gameNoteInstance == gameNote
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/gameNote/list'


        populateValidParams(params)
        def gameNote = new GameNote(params)

        assert gameNote.save() != null

        params.id = gameNote.id

        def model = controller.edit()

        assert model.gameNoteInstance == gameNote
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/gameNote/list'

        response.reset()


        populateValidParams(params)
        def gameNote = new GameNote(params)

        assert gameNote.save() != null

        // test invalid parameters in update
        params.id = gameNote.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/gameNote/edit"
        assert model.gameNoteInstance != null

        gameNote.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/gameNote/show/$gameNote.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        gameNote.clearErrors()

        populateValidParams(params)
        params.id = gameNote.id
        params.version = -1
        controller.update()

        assert view == "/gameNote/edit"
        assert model.gameNoteInstance != null
        assert model.gameNoteInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/gameNote/list'

        response.reset()

        populateValidParams(params)
        def gameNote = new GameNote(params)

        assert gameNote.save() != null
        assert GameNote.count() == 1

        params.id = gameNote.id

        controller.delete()

        assert GameNote.count() == 0
        assert GameNote.get(gameNote.id) == null
        assert response.redirectedUrl == '/gameNote/list'
    }
}
