
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
    <h1>User Page</h1>


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
        """),_display_(/*44.10*/recommendedHotelCluster),format.raw/*44.33*/("""
    """),format.raw/*45.5*/("""</div>
</div>

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
        """),_display_(/*71.10*/for(index <- 0 until listOfRecos.size) yield /*71.48*/{_display_(Seq[Any](format.raw/*71.49*/("""
        """),_display_(/*72.10*/if(index % 3 == 0)/*72.28*/{_display_(Seq[Any](format.raw/*72.29*/("""
        """),format.raw/*73.9*/("""<ul>
            """)))}),format.raw/*74.14*/("""

            """),format.raw/*76.13*/("""<li>"""),_display_(/*76.18*/listOfRecos(index)),format.raw/*76.36*/("""</li>

            """),_display_(/*78.14*/if(index % 3 == 2 || index == (listOfRecos.size - 1))/*78.67*/{_display_(Seq[Any](format.raw/*78.68*/("""
        """),format.raw/*79.9*/("""</ul>
        """)))}),format.raw/*80.10*/("""
        """)))}),format.raw/*81.10*/("""</div>
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
                  SOURCE: G:/7200/Scala-Final-Project/FinalProjectCode/HotelRecommend/app/views/Home/HomePage.scala.html
                  HASH: 60f503ecb02cc588b25e79bb56180000f3e13123
                  MATRIX: 750->1|903->59|931->62|974->97|1012->98|1048->108|3094->2127|3138->2150|3171->2156|4006->2964|4060->3002|4099->3003|4137->3014|4164->3032|4203->3033|4240->3043|4290->3062|4334->3078|4366->3083|4405->3101|4454->3123|4516->3176|4555->3177|4592->3187|4639->3203|4681->3214
                  LINES: 21->1|26->1|27->2|27->2|27->2|30->5|69->44|69->44|70->45|96->71|96->71|96->71|97->72|97->72|97->72|98->73|99->74|101->76|101->76|101->76|103->78|103->78|103->78|104->79|105->80|106->81
                  -- GENERATED --
              */
          