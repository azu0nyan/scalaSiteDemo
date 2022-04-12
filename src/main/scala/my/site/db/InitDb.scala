package my.site.db


import scalikejdbc._

object InitDb {


  def init(): Unit ={
    // initialize JDBC driver & connection pool
    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:file:~/testdb2:db", "user", "pass")
//    ConnectionPool.singleton("jdbc:h2:mem:db", "user", "pass")
    initTables()

  }


  def initTables():Unit = {
    implicit val session = AutoSession
    sql"""create table if not exists Hoomans(
          id serial not null primary key,
          name varchar(64) not null,
          password varchar(64) not null
          )
       """.execute().apply()

    sql"""
          create table if not exists Doges(
          id serial not null primary key,
          name varchar(64)
          )
       """.execute().apply()

    sql"""
         create  table if not exists Relations(
         id serial not null primary key,
         hoomanId int,
         dogeId int )
       """.execute().apply()
  }


}