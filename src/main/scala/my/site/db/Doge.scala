package my.site.db

import scalikejdbc._

object Doge {
  def addDoge(name:String) :Unit = {
    implicit val session = AutoSession
    sql"""insert into Doges(name) VALUES($name)""".update().apply
  }

  def getId(name:String):Option[Int] = {
    implicit val session = AutoSession
    sql"""
         SELECT *
         FROM Doges
         WHERE name = $name
         """.map(rs => rs.int("id")).single().apply()
  }

  def allDoges(): Seq[Doge] = {
    implicit val session = AutoSession
    sql"""select * from Doges"""
      .map(rs => Doge(rs.int("id"), rs.string("name"))).list().apply()
  }

}

case class Doge(id:Int, name:String)
