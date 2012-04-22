package se.ifkgoteborg.stat.model



import org.junit.*
import grails.test.mixin.*

@TestFor(RefereeController)
@Mock(Referee)
class RefereeControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/referee/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.refereeInstanceList.size() == 0
        assert model.refereeInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.refereeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.refereeInstance != null
        assert view == '/referee/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/referee/show/1'
        assert controller.flash.message != null
        assert Referee.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/referee/list'


        populateValidParams(params)
        def referee = new Referee(params)

        assert referee.save() != null

        params.id = referee.id

        def model = controller.show()

        assert model.refereeInstance == referee
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/referee/list'


        populateValidParams(params)
        def referee = new Referee(params)

        assert referee.save() != null

        params.id = referee.id

        def model = controller.edit()

        assert model.refereeInstance == referee
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/referee/list'

        response.reset()


        populateValidParams(params)
        def referee = new Referee(params)

        assert referee.save() != null

        // test invalid parameters in update
        params.id = referee.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/referee/edit"
        assert model.refereeInstance != null

        referee.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/referee/show/$referee.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        referee.clearErrors()

        populateValidParams(params)
        params.id = referee.id
        params.version = -1
        controller.update()

        assert view == "/referee/edit"
        assert model.refereeInstance != null
        assert model.refereeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/referee/list'

        response.reset()

        populateValidParams(params)
        def referee = new Referee(params)

        assert referee.save() != null
        assert Referee.count() == 1

        params.id = referee.id

        controller.delete()

        assert Referee.count() == 0
        assert Referee.get(referee.id) == null
        assert response.redirectedUrl == '/referee/list'
    }
}
