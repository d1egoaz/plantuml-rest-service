package controllers

import play.api._
import play.api.mvc._
import scala.concurrent.ExecutionContext
import services.PlantUml

import javax.inject._

@Singleton
class PlantUmlController @Inject() (plantuml: PlantUml)(implicit exec: ExecutionContext) extends Controller {

  def createImage: Action[String] = Action.async(parse.text) { request =>
    val text = request.body
    Logger.debug(s"Converting to PNG\n$text")
    plantuml.createImage(text).map(bytes => Ok(bytes).as("image/png"))
  }
}
