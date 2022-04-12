package my.site

import my.site.db.InitDb
import my.site.server.{InitEndpoints, PageTemplate}
import org.slf4j.LoggerFactory
import spark.{Request, Response, Route, Spark}

import scala.util.Random
import scalatags.Text.all._


object Main {
  def main(args: Array[String]): Unit = {
    InitDb.init()
    InitEndpoints.init()

  }
}
