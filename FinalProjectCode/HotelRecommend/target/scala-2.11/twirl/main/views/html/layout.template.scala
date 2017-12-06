
package views.html

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

object layout extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.32*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html>
<html lang="en">
    <head>
        <title>"""),_display_(/*5.17*/title),format.raw/*5.22*/("""</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(/*6.54*/routes/*6.60*/.Assets.versioned("stylesheets/main.css")),format.raw/*6.101*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(/*7.54*/routes/*7.60*/.Assets.versioned("stylesheets/bootstrap.min.css")),format.raw/*7.110*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(/*8.54*/routes/*8.60*/.Assets.versioned("stylesheets/bootstrap-theme.min.css")),format.raw/*8.116*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*9.59*/routes/*9.65*/.Assets.versioned("images/favicon.png")),format.raw/*9.104*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(/*10.54*/routes/*10.60*/.Assets.versioned("stylesheets/style.css")),format.raw/*10.102*/("""">
        <!-- Latest compiled and minified CSS -->

    </head>
    <body>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Expedia Hotel Recommendation Site</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#">User's Page</a></li>
                <li><a href="#">Analytics Dashboard</a></li>
                <li><a href="#">Page 3</a></li>
            </ul>
        </div>
    </nav>

        <div class = "container">
          """),_display_(/*30.12*/content),format.raw/*30.19*/("""
        """),format.raw/*31.9*/("""</div>

        <!--<script src=""""),_display_(/*33.27*/routes/*33.33*/.Assets.versioned("js/costum.js")),format.raw/*33.66*/("""" type="text/javascript"></script>-->
        <!--<script src=""""),_display_(/*34.27*/routes/*34.33*/.Assets.versioned("js/bootstrap.min.js")),format.raw/*34.73*/("""" type="text/javascript"></script>-->
        <!--<script src=""""),_display_(/*35.27*/routes/*35.33*/.Assets.versioned("js/jquery-3.2.1.min.js")),format.raw/*35.76*/("""" type="text/javascript"></script>-->
    </body>
</html>
"""))
      }
    }
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Dec 04 21:31:07 EST 2017
                  SOURCE: G:/7200/Scala-Final-Project/FinalProjectCode/HotelRecommend/app/views/layout.scala.html
                  HASH: 629ed2ff8ccd9e7181c7b0ebfbad00032f745134
                  MATRIX: 735->1|860->31|888->33|977->96|1002->101|1091->164|1105->170|1167->211|1250->268|1264->274|1335->324|1418->381|1432->387|1509->443|1597->505|1611->511|1671->550|1755->607|1770->613|1834->655|2535->1329|2563->1336|2600->1346|2663->1382|2678->1388|2732->1421|2824->1486|2839->1492|2900->1532|2992->1597|3007->1603|3071->1646
                  LINES: 21->1|26->1|27->2|30->5|30->5|31->6|31->6|31->6|32->7|32->7|32->7|33->8|33->8|33->8|34->9|34->9|34->9|35->10|35->10|35->10|55->30|55->30|56->31|58->33|58->33|58->33|59->34|59->34|59->34|60->35|60->35|60->35
                  -- GENERATED --
              */
          