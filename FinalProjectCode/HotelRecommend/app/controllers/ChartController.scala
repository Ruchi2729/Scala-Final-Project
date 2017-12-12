package controllers

import javax.inject.{Inject, Singleton}

import play.api.mvc.{AbstractController, ControllerComponents}
import services.{ChartModel, Counter}



@Singleton
class ChartController @Inject() (cc: ControllerComponents,
                                 chartModel: ChartModel) extends AbstractController(cc) {

  /**
    * Create an action that responds with the [[Counter]]'s current
    * count. The result is plain text. This `Action` is mapped to
    * `GET /count` requests by an entry in the `routes` config file.
    */
  //def count = Action { Ok(counter.nextCount().toString) }

  def home() = Action {request=>
    Ok(views.html.Home.charts.render("none",List("none")))
  }

}