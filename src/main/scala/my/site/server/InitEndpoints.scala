package my.site.server

import my.site.server.pages._
import org.slf4j.LoggerFactory
import scalatags.Text.all.s
import spark.{Request, Response, Spark}

object InitEndpoints {
  def init():Unit = {
    val log = LoggerFactory.getLogger(this.getClass)
    log.info(s"Starting application....")

    Spark.externalStaticFileLocation("serverData")
    Spark.ipAddress("0.0.0.0")
    Spark.port(8080)
    log.info(s"Setting up endpoints...")

    Main.makeEndpoints()
    Registration.makeEndpoints()
    LogIn.makeEndpoints()
    User.makeEndpoints()
    LogOut.makeEndpoints()
    DogeSearch.makeEndpoints()
  }


}
