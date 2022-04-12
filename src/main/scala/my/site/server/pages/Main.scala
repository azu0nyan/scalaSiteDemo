package my.site.server.pages

import my.site.server.{Helpers, PageTemplate}
import spark.{Request, Response, Spark}

object Main {
  def makeEndpoints():Unit =
    Spark.get("/", (request: Request, response: Response) =>
      PageTemplate.genPage("<h1>MAIN PAGE</h1>",  Helpers.isUserLoggedIn(request)))

}
