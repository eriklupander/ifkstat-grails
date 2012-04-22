package se.ifkgoteborg.stat.model



import org.junit.*
import grails.test.mixin.*

@TestFor(TournamentSeasonController)
@Mock(TournamentSeason)
class TournamentSeasonControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/tournamentSeason/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.tournamentSeasonInstanceList.size() == 0
        assert model.tournamentSeasonInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.tournamentSeasonInstance != null
    }

    void testSave() {
        controller.save()

        assert model.tournamentSeasonInstance != null
        assert view == '/tournamentSeason/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/tournamentSeason/show/1'
        assert controller.flash.message != null
        assert TournamentSeason.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/tournamentSeason/list'


        populateValidParams(params)
        def tournamentSeason = new TournamentSeason(params)

        assert tournamentSeason.save() != null

        params.id = tournamentSeason.id

        def model = controller.show()

        assert model.tournamentSeasonInstance == tournamentSeason
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/tournamentSeason/list'


        populateValidParams(params)
        def tournamentSeason = new TournamentSeason(params)

        assert tournamentSeason.save() != null

        params.id = tournamentSeason.id

        def model = controller.edit()

        assert model.tournamentSeasonInstance == tournamentSeason
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/tournamentSeason/list'

        response.reset()


        populateValidParams(params)
        def tournamentSeason = new TournamentSeason(params)

        assert tournamentSeason.save() != null

        // test invalid parameters in update
        params.id = tournamentSeason.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/tournamentSeason/edit"
        assert model.tournamentSeasonInstance != null

        tournamentSeason.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/tournamentSeason/show/$tournamentSeason.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        tournamentSeason.clearErrors()

        populateValidParams(params)
        params.id = tournamentSeason.id
        params.version = -1
        controller.update()

        assert view == "/tournamentSeason/edit"
        assert model.tournamentSeasonInstance != null
        assert model.tournamentSeasonInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/tournamentSeason/list'

        response.reset()

        populateValidParams(params)
        def tournamentSeason = new TournamentSeason(params)

        assert tournamentSeason.save() != null
        assert TournamentSeason.count() == 1

        params.id = tournamentSeason.id

        controller.delete()

        assert TournamentSeason.count() == 0
        assert TournamentSeason.get(tournamentSeason.id) == null
        assert response.redirectedUrl == '/tournamentSeason/list'
    }
}
