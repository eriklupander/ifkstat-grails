package se.ifkgoteborg.stat.model



import org.junit.*
import grails.test.mixin.*

@TestFor(TournamentController)
@Mock(Tournament)
class TournamentControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/tournament/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.tournamentInstanceList.size() == 0
        assert model.tournamentInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.tournamentInstance != null
    }

    void testSave() {
        controller.save()

        assert model.tournamentInstance != null
        assert view == '/tournament/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/tournament/show/1'
        assert controller.flash.message != null
        assert Tournament.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/tournament/list'


        populateValidParams(params)
        def tournament = new Tournament(params)

        assert tournament.save() != null

        params.id = tournament.id

        def model = controller.show()

        assert model.tournamentInstance == tournament
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/tournament/list'


        populateValidParams(params)
        def tournament = new Tournament(params)

        assert tournament.save() != null

        params.id = tournament.id

        def model = controller.edit()

        assert model.tournamentInstance == tournament
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/tournament/list'

        response.reset()


        populateValidParams(params)
        def tournament = new Tournament(params)

        assert tournament.save() != null

        // test invalid parameters in update
        params.id = tournament.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/tournament/edit"
        assert model.tournamentInstance != null

        tournament.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/tournament/show/$tournament.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        tournament.clearErrors()

        populateValidParams(params)
        params.id = tournament.id
        params.version = -1
        controller.update()

        assert view == "/tournament/edit"
        assert model.tournamentInstance != null
        assert model.tournamentInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/tournament/list'

        response.reset()

        populateValidParams(params)
        def tournament = new Tournament(params)

        assert tournament.save() != null
        assert Tournament.count() == 1

        params.id = tournament.id

        controller.delete()

        assert Tournament.count() == 0
        assert Tournament.get(tournament.id) == null
        assert response.redirectedUrl == '/tournament/list'
    }
}
