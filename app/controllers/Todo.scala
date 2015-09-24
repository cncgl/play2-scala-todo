package controllers

import play.api.Play.current
import anorm._
import play.api.db._
import play.api.mvc._
import play.api.libs.json._

/**
 * Created by shigeru on 15/09/24.
 */
class Todo extends Controller {

  def index = Action {
    DB.withConnection { implicit c =>
      val records = SQL("select id,status,title from todos order by id")().map {
        row => (row[Int]("id").toString,
          Map(
            "id"     -> JsNumber(row[Int]("id")),
            "status" -> JsBoolean(row[Boolean]("status")),
            "title"  -> JsString(row[String]("title"))
          ))
      }.toMap

      Ok(Json.toJson(records))
    }
  }

}
