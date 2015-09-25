package controllers

import java.util.Date

import play.api.Play.current
import anorm._
import play.api.db._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._
//import domain.todo.Todo

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

  def show(id: Long) = Action {
    DB.withConnection { implicit c =>
      val record = SQL("select id,status,title from todos where id = {id}").on("id" -> id)().map {
        row => (row[Int]("id").toString,
          Map(
            "id"     -> JsNumber(row[Int]("id")),
            "status" -> JsBoolean(row[Boolean]("status")),
            "title"  -> JsString(row[String]("title"))
          ))
      }.toMap

      Ok(Json.toJson(record))
    }
  }

  def create = Action(parse.json) {
    request => unmarshalTodoResource( request, (resource: TodoResource) => {
      val todo = domain.todo.Todo(Option.empty, resource.status, resource.title)
      DB.withConnection { implicit c =>
        val id: Int = SQL("insert into todos (status, title, inserted_at, updated_at) values ({status}, {title}, {inserted_at}, {updated_at})").
        on("status" -> todo.status, "title" -> todo.title, "inserted_at" -> new Date(), "updated_at" -> new Date() ).
        executeUpdate()
      }

      Created
    })
  }

  def delete(id: Long) = Action {
    DB.withConnection { implicit c =>
      SQL("delete from todos where id = {id}").on("id" -> id).executeUpdate()
    }
    Ok(Json.toJson(Map("result" -> "ok")))
  }

  private def unmarshalTodoResource(request: Request[JsValue], block: (TodoResource) => Result): Result = {
    request.body.validate[TodoResource].fold(
      valid = block,
      invalid = (e => {
        val error = e.mkString
        BadRequest(error)
      })
    )
  }

}

case class TodoResource(status: Boolean, title: String)
object TodoResource {
  implicit val todoResourceFormat: Format[TodoResource] = (
    (__ \ "status").format[Boolean] and
    (__ \ "title").format[String]
  )(TodoResource.apply, unlift(TodoResource.unapply))
  /*
  implicit object TodoResourceFormat extends Format[TodoResource] {
    def reads(json: JsValue): TodoResource = TodoResource(
      (json \ "status").as[Boolean] ,
      (json \ "title").as[String]
    )
    def writes(t: TodoResource): JsValue = JsObject(Seq(
      "status" -> JsBoolean(t.status),
      "title"  -> JsString(t.title)
    ))
  }
  */
}
