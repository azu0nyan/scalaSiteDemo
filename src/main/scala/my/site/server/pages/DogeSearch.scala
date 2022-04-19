package my.site.server.pages

import my.site.db.Doge
import my.site.server.{Helpers, PageTemplate}
import org.slf4j.LoggerFactory
import scalatags.Text.all._
import spark.{Request, Response, Spark}

object DogeSearch {
  val log = LoggerFactory.getLogger(this.getClass)
  def makeEndpoints(): Unit = {
    Spark.get("/search", (request: Request, response: Response) =>
      if (Helpers.isUserLoggedIn(request)) {
        PageTemplate.genPage(html, false)
      } else {
        response.redirect("/login")
        ""
      })
    Spark.get(s"/searchReq", (request: Request, response: Response) =>{
      val q = request.queryParams("q")
      log.info(s"Requested info about: $q")
      //по хорошему такой filter нужно делать средствами DB
      val result: Seq[Doge] = Doge.allDoges().filter(_.name.toUpperCase.contains(q.toUpperCase))
      log.info(s"Found results $result")
      import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._
      result.asJson.noSpaces
    } )

  }


  def html: String = div(
    p("Введите имя DOGE чтобы найти:"),
    br,
    input(`type` := "text", placeholder := "Type here!", id := "searchWindow"),
    br,
    div(minHeight := "300px", id := "searchResult"),
    script(src :="search.js")
  ).render

}
