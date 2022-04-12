package my.site.server

import my.site.db.Hooman
import spark.Request


object Helpers {
  def isUserLoggedIn(request: Request): Boolean = {
    val userName: Option[String] = Option(request.cookie("userName"))
    userName.nonEmpty && Hooman.reqHooman(userName.get).nonEmpty
  }

}
