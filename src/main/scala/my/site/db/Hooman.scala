package my.site.db


import org.slf4j.LoggerFactory
import scalikejdbc._

object Hooman {
  val log = LoggerFactory.getLogger(this.getClass)

  def hasDoge(name: String): Boolean = {
    implicit val session = AutoSession
    sql"""
         SELECT *
         FROM Hoomans
         WHERE name = $name AND EXISTS (
            SELECT * FROM Relations WHERE HoomanId = Hoomans.id
         )
       """.map(x => x).single().apply().nonEmpty
  }

  def getId(name: String): Option[Int] = { //None Some(4)
    implicit val session = AutoSession
    sql"""
         SELECT *
         FROM Hoomans
         WHERE name = $name
         """.map(rs => rs.int("id")).single().apply()
  }


  def addHooman(name: String, password: String): Unit = {
    implicit val session = AutoSession
    sql"""insert into Hoomans(name, password) VALUES($name, $password)""".update().apply()
  }

  def reqHooman(name: String): Option[Hooman] = {
    implicit val session = AutoSession
    sql"""select * from Hoomans WHERE name = $name"""
      .map(rs => Hooman(rs.int("id"), rs.string("name"), rs.string("password"))).single().apply()
  }

  def reqHoomans(): Seq[Hooman] = {
    implicit val session = AutoSession
    sql"""select * from Hoomans"""
      .map(rs => Hooman(rs.int("id"), rs.string("name"), rs.string("password"))).list().apply()
  }
}


case class Hooman(id: Int, name: String, password: String) {
  def myDogs: Seq[Doge] = {
    implicit val session = AutoSession
    sql"""SELECT * FROM Doges
      WHERE EXISTS (
            SELECT * FROM Relations WHERE HoomanId = $id AND Doges.id = DogeId
         )""".map(rs => Doge(rs.int("id"), rs.string("name")))
      .list().apply()
  }
}