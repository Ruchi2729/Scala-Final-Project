
package views.html.Home

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

object charts extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,List[String],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(recommendedHotelCluster:String,listOfRecos: List[String]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.60*/("""
"""),_display_(/*2.2*/layout("7250-Hotel Recommendatins")/*2.37*/{_display_(Seq[Any](format.raw/*2.38*/("""


"""),format.raw/*5.1*/("""<h1></h1>Charts</h1>



<div class="container">
    <h1>Total Number Of bookings in every month of the year</h1>
    <div class="row" id="char1">

    </div><br>
    <h1>Total Number Of bookings for Every Country</h1>
    <div class="row" id="char2">

    </div>
    <h1>Total Number Of bookings by some users</h1>
    <div class="row" id="char3">

    </div>
</div>


""")))}))
      }
    }
  }

  def render(recommendedHotelCluster:String,listOfRecos:List[String]): play.twirl.api.HtmlFormat.Appendable = apply(recommendedHotelCluster,listOfRecos)

  def f:((String,List[String]) => play.twirl.api.HtmlFormat.Appendable) = (recommendedHotelCluster,listOfRecos) => apply(recommendedHotelCluster,listOfRecos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Wed Dec 13 16:30:50 EST 2017
                  SOURCE: G:/7200/Scala-Final-Project/FinalProjectCode/HotelRecommend/app/views/Home/charts.scala.html
                  HASH: 424d5dc6146d7d64fbb6e05049a38c8cc8af6d35
                  MATRIX: 748->1|901->59|929->62|972->97|1010->98|1042->104
                  LINES: 21->1|26->1|27->2|27->2|27->2|30->5
                  -- GENERATED --
              */
          