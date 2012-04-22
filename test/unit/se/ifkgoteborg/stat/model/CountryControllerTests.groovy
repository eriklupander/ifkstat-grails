package se.ifkgoteborg.stat.model



import org.junit.*
import grails.test.mixin.*

@TestFor(CountryController)
@Mock(Country)
class CountryControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/country/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.countryInstanceList.size() == 0
        assert model.countryInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.countryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.countryInstance != null
        assert view == '/country/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/country/show/1'
        assert controller.flash.message != null
        assert Country.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/country/list'


        populateValidParams(params)
        def country = new Country(params)

        assert country.save() != null

        params.id = country.id

        def model = controller.show()

        assert model.countryInstance == country
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/country/list'


        populateValidParams(params)
        def country = new Country(params)

        assert country.save() != null

        params.id = country.id

        def model = controller.edit()

        assert model.countryInstance == country
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/country/list'

        response.reset()


        populateValidParams(params)
        def country = new Country(params)

        assert country.save() != null

        // test invalid parameters in update
        params.id = country.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/country/edit"
        assert model.countryInstance != null

        country.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/country/show/$country.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        country.clearErrors()

        populateValidParams(params)
        params.id = country.id
        params.version = -1
        controller.update()

        assert view == "/country/edit"
        assert model.countryInstance != null
        assert model.countryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/country/list'

        response.reset()

        populateValidParams(params)
        def country = new Country(params)

        assert country.save() != null
        assert Country.count() == 1

        params.id = country.id

        controller.delete()

        assert Country.count() == 0
        assert Country.get(country.id) == null
        assert response.redirectedUrl == '/country/list'
    }
}
