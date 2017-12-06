
package services

import java.util.concurrent.atomic.AtomicInteger
import javax.inject._

import controllers.appUser

/**
  * This trait demonstrates how to create a component that is injected
  * into a controller. The trait represents a counter that returns a
  * incremented number each time it is called.
  */
trait MLModels {
  def getRecosUsingDecisionTree(get: appUser):String

  def getRecosUsingCollaborativeFiltering(userid: Int) :List[String]

}

/**
  * This class is a concrete implementation of the [[MLModels]] trait.
  * It is configured for Guice dependency injection in the [[play.api.inject.Module]]
  * class.
  *
  * This class has a `Singleton` annotation because we need to make
  * sure we only use one counter per application. Without this
  * annotation we would get a new instance every time a [[MLModels]] is
  * injected.
  */
@Singleton
class MLModels1 extends MLModels {
  override def getRecosUsingDecisionTree(get: appUser): String= "Ruchira"

  override def getRecosUsingCollaborativeFiltering(userid: Int):List[String] =List(userid.toString,"2","3")


}
