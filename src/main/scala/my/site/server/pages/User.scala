package my.site.server.pages

import my.site.db.Hooman
import my.site.server.PageTemplate
import org.slf4j.LoggerFactory
import scalatags.Text.all._
import spark.{Request, Response, Spark}

object User {
  val log = LoggerFactory.getLogger(this.getClass)

  def makeEndpoints(): Unit = {
    Spark.get("/user", (request: Request, response: Response) => {
      val userName: Option[String] = Option(request.cookie("userName"))
      userName match {
        case Some(name) =>
          val user = Hooman.reqHooman(name)
          user match {
            case Some(user) =>
              PageTemplate.genPage(html(user), true)
            case None =>
              log.info(s"Accessing user page with unknown userName")
              response.redirect("/login")
              ""
          }

        case None =>
          log.info(s"Accessing user page without userName cookie")
          response.redirect("/login")
          ""
      }
    })
  }

  def html(h:Hooman): String =
    div(
      p(h.name),
      p(h.myDogs.toString()),
    ).render




}
