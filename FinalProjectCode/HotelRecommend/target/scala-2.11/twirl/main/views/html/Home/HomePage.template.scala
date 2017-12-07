
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

object HomePage extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,List[String],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(recommendedHotelCluster:String,listOfRecos: List[String]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.60*/("""
"""),_display_(/*2.2*/layout("7250-Hotel Recommendatins")/*2.37*/{_display_(Seq[Any](format.raw/*2.38*/("""


    """),format.raw/*5.5*/("""<!--<p>-->
       <!--Hotel Recommend-->
    <!--</p>-->
    >

<div class="well well-lg">Select the User(Recommendation According to similar Users: Collaborative filtering)
    <form name="input" action="/rec" method="get" >

        <!--<select name="id">-->
            <!--<option value=1234>User1</option>-->
            <!--<option value=1234>User2</option>-->
            <!--<option value=1234>User3</option>-->
            <!--<option value=1234>User4</option>-->
        <!--</select>-->
        <!--<br><br>-->

        <div class="form-group">
            <label for="userid">User Id</label>
            <input type="number" class="form-control" name="userid" id="userid" placeholder="Enter User Id">
        </div>
        <input type="submit">





    </form>
    <div class="well well-sm" id="colabRecos">Recommendations:
        """),_display_(/*33.10*/for(index <- 0 until listOfRecos.size) yield /*33.48*/{_display_(Seq[Any](format.raw/*33.49*/("""
        """),_display_(/*34.10*/if(index % 3 == 0)/*34.28*/{_display_(Seq[Any](format.raw/*34.29*/("""
        """),format.raw/*35.9*/("""<ul>
            """)))}),format.raw/*36.14*/("""

            """),format.raw/*38.13*/("""<li>"""),_display_(/*38.18*/listOfRecos(index)),format.raw/*38.36*/("""</li>

            """),_display_(/*40.14*/if(index % 3 == 2 || index == (listOfRecos.size - 1))/*40.67*/{_display_(Seq[Any](format.raw/*40.68*/("""
        """),format.raw/*41.9*/("""</ul>
        """)))}),format.raw/*42.10*/("""
        """)))}),format.raw/*43.10*/("""</div>
</div>
<div class="well well-lg">Give User Input(Recommendations based on Decision Tree Predictive Model)
    <form action="/rec2" method="get" >
        <div class="form-group">
            <label for="HotelMarket">Hotel Market</label>
            <input type="text" class="form-control" id="HotelMarket" name="hotelMarket" placeholder="Enter Hotel Market">
        </div>
        <div class="form-group">
            <label for="HotelContinent">Hotel Continent</label>
            <input type="text" class="form-control" id="HotelContinent" name="hotelContinent" placeholder="Enter Hotel Continent">
        </div>
        <div class="form-group">
            <label for="HotelCountry">Hotel Country</label>
            <input type="text" class="form-control" id="HotelCountry" name="hotelCountry" placeholder="Enter Hotel Country">
        </div>
        <div class="form-group">
            <label for="AdultCount">Adult Count</label>
            <input type="text" class="form-control" id="AdultCount" name="adultCount" placeholder="Enter Adult Count">
        </div>

        <div class="form-group">
            <label for="ChildrenCount">Children Count</label>
            <input type="text" class="form-control" id="ChildrenCount" name="childrenCount" placeholder="Enter Children Count">
        </div>
        <div class="form-group">
            <label for="ChildrenCount">Children Count</label>
            <input type="text" class="form-control" id="roomCount" name="roomCount" placeholder="Enter Room Count">
        </div>

        <input type="hidden" class="form-control" id="hotelCluster" name="hotelCluster" value=0 placeholder="Enter Room Count">

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    <div class="well well-sm">Recommendations as per user input(the hotelClusterId recommended by predicting the one user is most likey to choose
        """),_display_(/*78.10*/recommendedHotelCluster),format.raw/*78.33*/("""
    """),format.raw/*79.5*/("""</div>
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
                  DATE: Thu Dec 07 04:03:56 EST 2017
                  SOURCE: G:/7200/Scala-Final-Project/FinalProjectCode/HotelRecommend/app/views/Home/HomePage.scala.html
                  HASH: 111397e2bb05bfa474b0d7051346995b2424b7dc
                  MATRIX: 750->1|903->59|931->62|974->97|1012->98|1048->108|1950->983|2004->1021|2043->1022|2081->1033|2108->1051|2147->1052|2184->1062|2234->1081|2278->1097|2310->1102|2349->1120|2398->1142|2460->1195|2499->1196|2536->1206|2583->1222|2625->1233|4599->3180|4643->3203|4676->3209
                  LINES: 21->1|26->1|27->2|27->2|27->2|30->5|58->33|58->33|58->33|59->34|59->34|59->34|60->35|61->36|63->38|63->38|63->38|65->40|65->40|65->40|66->41|67->42|68->43|103->78|103->78|104->79
                  -- GENERATED --
              */
          