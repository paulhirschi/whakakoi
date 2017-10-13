package controllers

import models._
import com.phirschi.util.{EncryptionUtil, DatabaseUtil}

import javax.inject._
import play.api.mvc._
import play.api.Logger
import play.api.libs.json._
import scala.concurrent.ExecutionContext.Implicits.global

class AuthController @Inject()
(dbu: DatabaseUtil,
cc: ControllerComponents)
extends AbstractController(cc) {

  private val emptyJs: JsValue = Json.obj()

  def authenticate = Action(parse.tolerantJson) { implicit request =>
    // val usersCollection = dbu.databaseCollection(db, dbCollection)
    // Logger.info(s"+++ $usersCollection")

    val loginCreds: JsValue = request.body
    val email = (loginCreds \ "email").get.toString
    val password = (loginCreds \ "password").get.toString
    // val encryptPass = EncryptionUtil.encryptGetSalt(password)
    val obj = ResultMessage(true, "success", emptyJs)
    Ok(jsonResult(obj))
  }

  private def jsonBasic(obj: BasicMessage): JsValue = Json.toJson(obj)

  private def jsonResult(obj: ResultMessage): JsValue = Json.toJson(obj)

}