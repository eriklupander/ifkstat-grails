package se.ifkgoteborg.stat.model



import org.junit.*
import grails.test.mixin.*

@TestFor(ClubController)
@Mock(Club)
class ClubControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/club/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.clubInstanceList.size() == 0
        assert model.clubInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.clubInstance != null
    }

    void testSave() {
        controller.save()

        assert model.clubInstance != null
        assert view == '/club/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/club/show/1'
        assert controller.flash.message != null
        assert Club.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/club/list'


        populateValidParams(params)
        def club = new Club(params)

        assert club.save() != null

        params.id = club.id

        def model = controller.show()

        assert model.clubInstance == club
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/club/list'


        populateValidParams(params)
        def club = new Club(params)

        assert club.save() != null

        params.id = club.id

        def model = controller.edit()

        assert model.clubInstance == club
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/club/list'

        response.reset()


        populateValidParams(params)
        def club = new Club(params)

        assert club.save() != null

        // test invalid parameters in update
        params.id = club.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/club/edit"
        assert model.clubInstance != null

        club.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/club/show/$club.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        club.clearErrors()

        populateValidParams(params)
        params.id = club.id
        params.version = -1
        controller.update()

        assert view == "/club/edit"
        assert model.clubInstance != null
        assert model.clubInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/club/list'

        response.reset()

        populateValidParams(params)
        def club = new Club(params)

        assert club.save() != null
        assert Club.count() == 1

        params.id = club.id

        controller.delete()

        assert Club.count() == 0
        assert Club.get(club.id) == null
        assert response.redirectedUrl == '/club/list'
    }
}
