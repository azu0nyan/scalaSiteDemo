
package my.site.server.pages

import my.site.db.Hooman
import my.site.server.{Helpers, PageTemplate}
import org.slf4j.LoggerFactory
import scalatags.Text.all._
import spark.{Request, Response, Spark}

object LogIn {
  val log = LoggerFactory.getLogger(this.getClass)

  def makeEndpoints(): Unit = {
    Spark.get("/login", (request: Request, response: Response) =>
      if( Helpers.isUserLoggedIn(request)){
        response.redirect("/user")
        ""
      } else PageTemplate.genPage(html, false))

    Spark.post("/loginReq", (request: Request, response: Response) => {
      val name = request.queryParams("name")
      val passwordToCheck = request.queryParams("password")

      val user = Hooman.reqHooman(name)
      user match {
        case Some(Hooman(id, name, password)) if password == passwordToCheck =>
          log.info(s"New User logged in $name")
          response.cookie("userName", name)
          response.redirect("/user")
        case Some(Hooman(id, name, password)) =>
          log.info(s"User cant log in, wrong password")
          response.redirect("/login")
        case None =>
          log.info(s"User not found : $name")
          response.redirect("/login")
      }
      ""
    })
  }

  def html: String = form(method := "post", action := "/loginReq")(
    label(`for` := "name")("Имя"),
    input(`type` := "text", id := "name", name := "name"),
    br(),
    label(`for` := "password")("Пароль"),
    input(`type` := "text", id := "password", name := "password"),
    input(`type` := "submit")
  ).render

}

