package controllers

import javax.inject._
import play.api.mvc._
import play.api.Logger
import play.api.libs.ws._
import play.api.libs.json._
import play.api.libs.concurrent.Execution.Implicits._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

import models._
import com.phirschi.util._

@Singleton
class ApiController @Inject()
(config: play.api.Configuration,
WS: WSClient,
cc: ControllerComponents)
extends AbstractController(cc) {

  private val apiUrl: String = getConfigurationString("whakakoi.api.apiUrl")

  // val stats: MaxLiftStats = MaxLiftStats(RecordWeight(0, 0), RecordWeight(0,0), RecordWeight(0, 0), RecordWeight(0, 0), RecordWeight(0, 0), RecordWeight(0, 0), RecordWeight(0, 0))

  def api = Action { request =>
    val obj = ResultMessage(true, "success", jsonBasic(BasicMessage("Welcome to Whakakoi API")))
    Ok(jsonResult(obj))
      // .withSession(
      //   "user" -> "Paul Hirschi")
    // Ok("Welcome to Whakakoi API")
  }

  private def getConfigurationString(name: String): String =
    config.get[String](name)

  private def jsonBasic(obj: BasicMessage): JsValue = Json.toJson(obj)

  private def jsonResult(obj: ResultMessage): JsValue = Json.toJson(obj)

}
