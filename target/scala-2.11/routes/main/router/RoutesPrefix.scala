
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/shigeru/test/play2-scala-todo/conf/routes
// @DATE:Thu Sep 24 19:57:56 JST 2015


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
