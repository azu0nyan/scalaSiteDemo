package my.site.server.pages

import my.site.db.Hooman
import my.site.server.{Helpers, PageTemplate}
import org.slf4j.LoggerFactory
import scalatags.Text.all._
import spark.{Request, Response, Spark}

object Registration {
  val log = LoggerFactory.getLogger(this.getClass)

  def makeEndpoints(): Unit = {
    Spark.get("/register", (request: Request, response: Response) =>
      if( Helpers.isUserLoggedIn(request)){
        response.redirect("/user")
        ""
      } else  PageTemplate.genPage(html, false))

    Spark.post("/registerNew", (request: Request, response: Response) => {
      val name = request.queryParams("name")
      val password = request.queryParams("password")
      val captcha = request.queryParams("captcha")
      if (captcha != "WOW") {
        log.info(s"Wrong captcha: $captcha")
        response.redirect("/register")
        ""
      } else if (Hooman.reqHooman(name).nonEmpty){
        log.info(s"User already exists: $captcha")
        response.redirect("/register")
        ""
      }else if(name.length < 3 && name.forall(_.isLetterOrDigit)) {
        log.info(s"Name is too short: $captcha")
        response.redirect("/register")
        ""
      }else {
        log.info(s"Registering Hooman name: $name password: $password captcha: $captcha")
        Hooman.addHooman(name, password)
        response.redirect("/login")
        ""
      }
    })
  }

  def html: String = form(method := "post", action := "/registerNew")(
    label(`for` := "name")("Имя"),
    input(`type` := "text", id := "name", name := "name"),
    br(),
    label(`for` := "password")("Пароль"),
    input(`type` := "text", id := "password", name := "password"),
    br(),
    label(`for` := "captcha")("Введите WOW(защита от ботов)"),
    input(`type` := "text", id := "captcha", name := "captcha"),
    input(`type` := "submit")
  ).render

}
