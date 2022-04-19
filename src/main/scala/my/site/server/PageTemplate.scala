package my.site.server


object PageTemplate {
  def genPage(content: String, userLoggedIn: Boolean): String =
    s"""
       |<!DOCTYPE html>
       |<html lang="en">
       |<head>
       |    <meta charset="UTF-8">
       |    <title>Doge and HOOMAN</title>
       |    <link rel="stylesheet" href="styles.css">
       |    <script src="qwewe.js"></script>
       |</head>
       |<body>
       |<div class="container">
       |    <header class="header">
       |        <h1>WOW SHUCH HEADER</h1>
       |    </header>
       |    <article class="content">
       |    $content
       |
       |    </article>
       |    <div class="menu">
       |       ${genMenu(userLoggedIn)}
       |    </div>
       |    <div class="ad">
       |        <h2><a href="http://google.com/?q=do%20a%20barrel%20roll">BUY DOGECOIN</a></h2>
       |    </div>
       |    <footer class="footer">
       |        <i>BIG DATE</i>
       |        <b>SO 2049-ALOT</b>
       |    </footer>
       |</div>
       |</body>
       |</html>
       |""".stripMargin

  def genMenu(userLoggedIn: Boolean): String =
    if (userLoggedIn)
      s"""
         | <ul>
         |            <li><a href="/"> Главная </a></li>
         |            <li><a href="/user"> Личный кабинет</a></li>
         |            <li><a href="/search"> DOGE-поиск</a></li>
         |            <li><a href="/logout"> Выйти</a></li>
         |        </ul>""".stripMargin
    else
      s"""
         | <ul>
         |             <li><a href="/"> Главная </a></li>
         |            <li><a href="/register"> Регистрация </a></li>
         |            <li><a href="/login"> Войти </a></li>
         |        </ul>""".stripMargin
}
