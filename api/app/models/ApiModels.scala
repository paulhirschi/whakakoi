package models

import play.api.libs.json._

/**
 * 
 * API Response Models 
 * 
 */
case class WelcomeMessage(message: String)
object WelcomeMessage {
  implicit val jsonWriter = Json.writes[WelcomeMessage] // Json.toJson(obj): JsValue
}

case class BasicMessage(message: String)
object BasicMessage {
  implicit val jsonWriter = Json.writes[BasicMessage] // Json.toJson(obj): JsValue
  implicit val jsonReader = Json.reads[BasicMessage] // Json.fromJson[T](jsval): JsResult[T] .asOpt Option[T]
  def toJsArray(objs: List[BasicMessage]): JsArray = JsArray(objs.map(Json.toJson(_)).toSeq)
}

case class ResultMessage(success: Boolean, message: String, data: JsValue)
object ResultMessage {
  implicit val jsonWriter = Json.writes[ResultMessage] // Json.toJson(obj): JsValue
  implicit val jsonReader = Json.reads[ResultMessage] // Json.fromJson[T](jsval): JsResult[T] .asOpt Option[T]
  def toJsArray(objs: List[ResultMessage]): JsArray = JsArray(objs.map(Json.toJson(_)).toSeq)
}

/**
 * 
 * API DB Models
 * 
 */

case class CompleteWods(wod: String, date: String, score: String)
object CompleteWods {
  implicit val jsonWriter = Json.writes[CompleteWods] // Json.toJson(obj): JsValue
  implicit val jsonReader = Json.reads[CompleteWods] // Json.fromJson[T](jsval): JsResult[T] .asOpt Option[T]
  def toJsArray(objs: List[CompleteWods]): JsArray = JsArray(objs.map(Json.toJson(_)).toSeq)
}

case class CustomWods(name: String, score: String, description: String, createdBy: String)
object CustomWods {
  implicit val jsonWriter = Json.writes[CustomWods] // Json.toJson(obj): JsValue
  implicit val jsonReader = Json.reads[CustomWods] // Json.fromJson[T](jsval): JsResult[T] .asOpt Option[T]
  def toJsArray(objs: List[CustomWods]): JsArray = JsArray(objs.map(Json.toJson(_)).toSeq)
}

case class NamedWods(name: String, score: String, description: String, createdBy: String)
object NamedWods {
  implicit val jsonWriter = Json.writes[NamedWods] // Json.toJson(obj): JsValue
  implicit val jsonReader = Json.reads[NamedWods] // Json.fromJson[T](jsval): JsResult[T] .asOpt Option[T]
  def toJsArray(objs: List[NamedWods]): JsArray = JsArray(objs.map(Json.toJson(_)).toSeq)
}

case class RecordWeight(date: Double, weight: Double)
object RecordWeight {
  implicit val jsonWriter = Json.writes[RecordWeight] // Json.toJson(obj): JsValue
  implicit val jsonReader = Json.reads[RecordWeight] // Json.fromJson[T](jsval): JsResult[T] .asOpt Option[T]
  def toJsArray(objs: List[RecordWeight]): JsArray = JsArray(objs.map(Json.toJson(_)).toSeq)
} 

case class MaxLiftStats(
  cleanJerk: RecordWeight,
  snatch: RecordWeight,
  deadlift: RecordWeight,
  frontSquat: RecordWeight,
  backSquat: RecordWeight,
  benchPress: RecordWeight,
  bodyWeight: RecordWeight)
object MaxLiftStats {
  implicit val jsonWriter = Json.writes[MaxLiftStats] // Json.toJson(obj): JsValue
  implicit val jsonReader = Json.reads[MaxLiftStats] // Json.fromJson[T](jsval): JsResult[T] .asOpt Option[T]
  def toJsArray(objs: List[MaxLiftStats]): JsArray = JsArray(objs.map(Json.toJson(_)).toSeq)
  def empty: MaxLiftStats = MaxLiftStats(RecordWeight(0, 0),RecordWeight(0, 0),RecordWeight(0, 0),RecordWeight(0, 0),RecordWeight(0, 0),RecordWeight(0, 0),RecordWeight(0, 0))
}

case class User(
  firstName: String,
  lastName: String,
  email: String,
  id: Double,
  auth: String,
  customWods: List[CustomWods],
  completeWods: List[CompleteWods],
  stats: MaxLiftStats) 
object User {
  implicit val jsonWriter = Json.writes[User] // Json.toJson(obj): JsValue
  implicit val jsonReader = Json.reads[User] // Json.fromJson[T](jsval): JsResult[T] .asOpt Option[T]
  implicit val formatter = Json.format[User]
  def toJsArray(objs: List[User]): JsArray = JsArray(objs.map(Json.toJson(_)).toSeq)
}

case class Invitation(
  firstName: String,
  lastName: String,
  email: String, 
  password: String)
object Invitation {
  implicit val jsonWriter = Json.writes[Invitation] // Json.toJson(obj): JsValue
  implicit val jsonReader = Json.reads[Invitation] // Json.fromJson[T](jsval): JsResult[T] .asOpt Option[T]
  implicit val formatter = Json.format[Invitation]
  def toJsArray(objs: List[Invitation]): JsArray = JsArray(objs.map(Json.toJson(_)).toSeq)
}
