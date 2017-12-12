
// @GENERATOR:play-routes-compiler
// @SOURCE:G:/7200/Scala-Final-Project/FinalProjectCode/HotelRecommend/conf/routes
// @DATE:Tue Dec 12 13:29:43 EST 2017

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:6
package controllers {

  // @LINE:12
  class ReverseChartController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:12
    def home(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "charts")
    }
  
  }

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:10
    def recos(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "rec")
    }
  
    // @LINE:6
    def home(): Call = {
      
      Call("GET", _prefix)
    }
  
    // @LINE:11
    def recos2(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "rec2")
    }
  
  }

  // @LINE:8
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
