
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

    </head>
    <body>
        <div class = "container">
          """),_display_(/*14.12*/content),format.raw/*14.19*/("""
        """),format.raw/*15.9*/("""</div>

        <script src=""""),_display_(/*17.23*/routes/*17.29*/.Assets.versioned("js/costum.js")),format.raw/*17.62*/("""" type="text/javascript"></script>
        <script src=""""),_display_(/*18.23*/routes/*18.29*/.Assets.versioned("js/bootstrap.min.js")),format.raw/*18.69*/("""" type="text/javascript"></script>
        <script src=""""),_display_(/*19.23*/routes/*19.29*/.Assets.versioned("js/jquery-3.2.1.min.js")),format.raw/*19.72*/("""" type="text/javascript"></script>
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
                  DATE: Thu Nov 30 16:05:37 EST 2017
                  SOURCE: C:/Mycode/Scala/Scala-Final-Project/FinalProjectCode/HotelRecommend/app/views/layout.scala.html
                  HASH: f30e5852c51fddb0d97c58f26d27ad7594a29f60
                  MATRIX: 735->1|860->31|887->32|973->92|998->97|1086->159|1100->165|1162->206|1244->262|1258->268|1329->318|1411->374|1425->380|1502->436|1589->497|1603->503|1663->542|1762->614|1790->621|1826->630|1883->660|1898->666|1952->699|2036->756|2051->762|2112->802|2196->859|2211->865|2275->908
                  LINES: 21->1|26->1|27->2|30->5|30->5|31->6|31->6|31->6|32->7|32->7|32->7|33->8|33->8|33->8|34->9|34->9|34->9|39->14|39->14|40->15|42->17|42->17|42->17|43->18|43->18|43->18|44->19|44->19|44->19
                  -- GENERATED --
              */
          