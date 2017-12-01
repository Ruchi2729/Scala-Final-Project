
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

object HomePage extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](_display_(/*1.2*/layout("7250-Music Recommend")/*1.32*/{_display_(Seq[Any](format.raw/*1.33*/("""
    """),format.raw/*2.5*/("""<p>
       Hotel Recommend
    </p>
    <form name="input" action="" method="get">
        Username: <input type="text" name="user">
        Username: <input type="text" name="user">
        Username: <input type="text" name="user">
        Username: <input type="text" name="user">
        Username: <input type="text" name="user">
        Username: <input type="text" name="user">
        <input type="submit" value="Submit">
    </form>

""")))}))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Thu Nov 30 16:38:28 EST 2017
                  SOURCE: C:/Mycode/Scala/Scala-Final-Project/FinalProjectCode/HotelRecommend/app/views/Home/HomePage.scala.html
                  HASH: 0aaf105e4db513944df22b2f4fde55413b499c48
                  MATRIX: 819->1|857->31|895->32|927->38
                  LINES: 26->1|26->1|26->1|27->2
                  -- GENERATED --
              */
          