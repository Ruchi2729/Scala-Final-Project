package controllers

import javax.inject._

import models.{DetailUser, User3, UserHiistoryHotels}
import play.api.mvc._
import services.MLModels
import views.html.index
import views.html.Home.HomePage
import play.api.data._
import play.api.data.Forms._
import views.html.helper.form


case class UserData(userid: String)
//case class appUser(adultCount: Int,childrenCount:Int,roomCount:Int,hotelContinent:Int,hotelCountry:Int,hotelMarket:Int,hotelCluster:Int)

//, adultCount: Int,childrenCount:Int,roomCount:Int,hotelContinent:Int,hotelCountry:Int,hotelMarket:Int
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents,mlModel: MLModels) extends AbstractController(cc)   {

  val userForm = Form(
    mapping(
      "userid" -> text

//      "roomCount" -> number,
//      "hotelContinent" -> number,
//      "hotelCountry" -> number,
//      "hotelMarket" -> number
    )(UserData.apply)(UserData.unapply)
  )

  val userForm1 = Form(
    mapping(
            "adultCount" -> number,
            "childrenCount" -> number,
            "roomCount" -> number,
            "hotelContinent" -> number,
            "hotelCountry" -> number,
            "hotelMarket" -> number,
            "hotelCluster"->number
    )(User3.apply)(User3.unapply)
  )

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok("Your new application is ready.")
  }

  def home() = Action {request=>
    Ok(views.html.Home.HomePage.render("none",List("none")))
  }

def recos()=Action { implicit request=>
    //val UserData(id,adultCount,childrenCount,roomCount,hotelContinent,hotelCountry,hotelMarket) = userForm.bindFromRequest.ge

    val UserData(userid)=userForm.bindFromRequest.get
  val x="none"
    val recommendedHotelCluster:List[String] = UserHiistoryHotels.getHistoricalBookingsOfUser(userid)
    Ok(views.html.Home.HomePage.render(x,recommendedHotelCluster))
}

  def recos2()=Action {  implicit request=>
    val x:String=mlModel.getRecosUsingDecisionTree(userForm1.bindFromRequest.get)
    Ok(views.html.Home.HomePage.render(x,List("1","2","3")))


  }


}
