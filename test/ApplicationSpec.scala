import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._

class ApplicationSpec extends PlaySpec with OneAppPerTest {

  "Routes" should {

    "send 404 on a bad request" in {
      route(app, FakeRequest(GET, "/boum")).map(status(_)) mustBe Some(NOT_FOUND)
    }

  }

  "PlantUmlController" should {

    "creates a PNG image" in {
      var text = """
        @startuml
        Bob -> Alice : hello
        @enduml
        """
      val home = route(app, FakeRequest(POST, "/plantuml/png").withTextBody(text)).get

      status(home) mustBe OK
      contentType(home) mustBe Some("image/png")
    }

  }

}
