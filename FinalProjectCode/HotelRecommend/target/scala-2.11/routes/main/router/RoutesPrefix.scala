
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Mycode/Scala/Scala-Final-Project/FinalProjectCode/HotelRecommend/conf/routes
// @DATE:Thu Nov 30 16:05:37 EST 2017


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
