package domain.todo

/**
 * Created by shigeru on 15/09/24.
 */
case class Todo(val id: Option[Long], val status: Boolean, val title: String)

