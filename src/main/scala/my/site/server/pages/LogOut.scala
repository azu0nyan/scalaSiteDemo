package my.site.server.pages

import my.site.server.{Helpers, PageTemplate}
import my.site.server.pages.LogIn.html
import spark.{Request, Response, Spark}

object LogOut {
  def makeEndpoints(): Unit = {
    Spark.get("/logout", (request: Request, response: Response) =>
      if (Helpers.isUserLoggedIn(request)) {
        response.removeCookie("userName")
        response.redirect("/")
        ""
      } else {
        response.redirect("/")
        ""
      })
  }
}
