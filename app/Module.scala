import com.google.inject.AbstractModule
import services.{PlantUml, PlantUmlCreator}

class Module extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[PlantUml]).to(classOf[PlantUmlCreator])
  }
}
