package services

trait ChartModel {
  def nextCount(): Int
}

/**
  * This class is a concrete implementation of the [[ChartModel]] trait.
  * It is configured for Guice dependency injection in the [[play.api.inject.Module]]
  * class.
  *
  * This class has a `Singleton` annotation because we need to make
  * sure we only use one counter per application. Without this
  * annotation we would get a new instance every time a [[ChartModel]] is
  * injected.
  */

class ChartModel1 extends ChartModel {
 // private val atomicCounter = new AtomicInteger()
  //override def nextCount(): Int = atomicCounter.getAndIncrement()
  override def nextCount() = 5
}
