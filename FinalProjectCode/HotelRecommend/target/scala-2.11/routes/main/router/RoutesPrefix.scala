
// @GENERATOR:play-routes-compiler
// @SOURCE:G:/7200/Scala-Final-Project/FinalProjectCode/HotelRecommend/conf/routes
// @DATE:Tue Dec 05 02:40:14 EST 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
