package services

import akka.actor.ActorSystem
import net.sourceforge.plantuml.SourceStringReader

import scala.concurrent.{ExecutionContext, blocking}
import scala.concurrent.Future
import java.io.ByteArrayOutputStream
import java.io.IOException
import javax.inject._

trait PlantUml {
  def createImage(text: String): Future[Array[Byte]]
}

@Singleton
class PlantUmlCreator @Inject() (actorSystem: ActorSystem) extends PlantUml {

  private val myExecutionContext: ExecutionContext = actorSystem.dispatchers.lookup("contexts.plantuml")
  def createImage(text: String): Future[Array[Byte]] = {
    Future {
      blocking {
        val reader = new SourceStringReader(text, "UTF8")
        val baos = new ByteArrayOutputStream()
        val image = reader.generateImage(baos)
        try {
          baos.toByteArray()
        } finally {
          try {
            if (baos != null) { // scalastyle:ignore
              baos.close();
            }
          } catch {
            case e: IOException => // ¯\_(ツ)_/¯
          }
        }
      }
    }(myExecutionContext)
  }
}
