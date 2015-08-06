
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/shigeru/test/play2-2/app/conf/routes
// @DATE:Thu Aug 06 16:02:30 JST 2015


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
