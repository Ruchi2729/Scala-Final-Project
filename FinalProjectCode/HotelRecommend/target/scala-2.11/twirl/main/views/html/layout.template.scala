
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
        <script type="text/javascript" src="""),_display_(/*11.45*/routes/*11.51*/.Assets.versioned("js/d3.v3.min.js").url),format.raw/*11.91*/("""></script>
        <!--<script type="text/javascript" src="""),_display_(/*12.49*/routes/*12.55*/.Assets.versioned("js/bar2.js").url),format.raw/*12.90*/("""></script>-->

        <!--<script-->
                <!--type="text/javascript"-->
                <!--src="""),_display_(/*16.26*/routes/*16.32*/.Assets.versioned("lib/requirejs/require.js").url),format.raw/*16.81*/("""-->
                <!--data-main="""),_display_(/*17.32*/routes/*17.38*/.Assets.versioned("js/bar2.js").url),format.raw/*17.73*/(""">-->
        <!--</script>-->

        <script
                type="text/javascript"
                src="""),_display_(/*22.22*/routes/*22.28*/.Assets.versioned("lib/requirejs/require.js").url),format.raw/*22.77*/("""
                """),format.raw/*23.17*/("""data-main="""),_display_(/*23.28*/routes/*23.34*/.Assets.versioned("js/main.js").url),format.raw/*23.69*/(""">
        </script>
        <!--<script-->
                <!--type="text/javascript"-->
                <!--src="""),_display_(/*27.26*/routes/*27.32*/.Assets.versioned("lib/requirejs/require.js").url),format.raw/*27.81*/("""-->
                <!--data-main="""),_display_(/*28.32*/routes/*28.38*/.Assets.versioned("js/bar3.js").url),format.raw/*28.73*/(""">-->
        <!--</script>-->


        <!-- Latest compiled and minified CSS -->

    </head>
    <body>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Expedia Hotel Recommendation Site</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="/">Home</a></li>
                <li><a href="/">User's Page</a></li>
                <li><a href="/charts">Analytics Dashboard</a></li>
                <li><a href="#">Page 3</a></li>
            </ul>
        </div>
    </nav>

        <div class = "container">
          """),_display_(/*51.12*/content),format.raw/*51.19*/("""
        """),format.raw/*52.9*/("""</div>

        <!--<script src=""""),_display_(/*54.27*/routes/*54.33*/.Assets.versioned("js/costum.js")),format.raw/*54.66*/("""" type="text/javascript"></script>-->
        <!--<script src=""""),_display_(/*55.27*/routes/*55.33*/.Assets.versioned("js/bootstrap.min.js")),format.raw/*55.73*/("""" type="text/javascript"></script>-->
        <!--<script src=""""),_display_(/*56.27*/routes/*56.33*/.Assets.versioned("js/jquery-3.2.1.min.js")),format.raw/*56.76*/("""" type="text/javascript"></script>-->
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
                  DATE: Wed Dec 13 16:30:50 EST 2017
                  SOURCE: G:/7200/Scala-Final-Project/FinalProjectCode/HotelRecommend/app/views/layout.scala.html
                  HASH: 3fabdad98b323833c5d4223076bc56de47a7717f
                  MATRIX: 735->1|860->31|888->33|977->96|1002->101|1091->164|1105->170|1167->211|1250->268|1264->274|1335->324|1418->381|1432->387|1509->443|1597->505|1611->511|1671->550|1755->607|1770->613|1834->655|1909->703|1924->709|1985->749|2072->809|2087->815|2143->850|2283->963|2298->969|2368->1018|2431->1054|2446->1060|2502->1095|2641->1207|2656->1213|2726->1262|2772->1280|2810->1291|2825->1297|2881->1332|3026->1450|3041->1456|3111->1505|3174->1541|3189->1547|3245->1582|3984->2294|4012->2301|4049->2311|4112->2347|4127->2353|4181->2386|4273->2451|4288->2457|4349->2497|4441->2562|4456->2568|4520->2611
                  LINES: 21->1|26->1|27->2|30->5|30->5|31->6|31->6|31->6|32->7|32->7|32->7|33->8|33->8|33->8|34->9|34->9|34->9|35->10|35->10|35->10|36->11|36->11|36->11|37->12|37->12|37->12|41->16|41->16|41->16|42->17|42->17|42->17|47->22|47->22|47->22|48->23|48->23|48->23|48->23|52->27|52->27|52->27|53->28|53->28|53->28|76->51|76->51|77->52|79->54|79->54|79->54|80->55|80->55|80->55|81->56|81->56|81->56
                  -- GENERATED --
              */
          