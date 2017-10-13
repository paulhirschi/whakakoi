package controllers

import javax.inject._
import play.api.mvc._
import play.api.Logger
import play.api.libs.json._
import play.api.libs.functional.syntax._

import models._
import com.phirschi.util.{DatabaseUtil, EncryptionUtil}
import reactivemongo.bson._
import reactivemongo.api.collections.bson.BSONCollection
// import play.modules.reactivemongo._
// import reactivemongo.api.ReadPreference
// import reactivemongo.play.json._
// import reactivemongo.play.json.collection._
// import utils.Errors

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserController @Inject()
(dbu: DatabaseUtil,
cc: ControllerComponents)(implicit exec: ExecutionContext)
extends AbstractController(cc) {

  def getUser(id: Long) = Action.async {
    // val user: User = User("Paul Hirschi", "hirschi.pt@gmail.com", 100, "auth", List(), List(), MaxLiftStats.empty)
    val user = dbu.getUserById(id)
    user.map { u =>
      u match {
        case Some(u) => {
          val obj = ResultMessage(true, "success", Json.toJson(u))
          Ok(jsonResult(obj))
        }
        case None => {
          val obj = ResultMessage(false, "Not Found", jsonBasic(BasicMessage("Unable to get a user by that id")))
          NotFound(jsonResult(obj))
        }
      }
    }
  }

  def createInvite = Action(parse.json).async { request: Request[JsValue] =>
    val invitation: JsResult[Invitation] = Json.fromJson[Invitation](request.body)
    invitation match {
      case JsSuccess(i: Invitation, path: JsPath) => {
        val password = EncryptionUtil.encrypt(i.password)
        val newInvite: Invitation = Invitation(i.firstName, i.lastName, i.email, password)
        val inviteUser = dbu.createInvitation(newInvite)
        inviteUser.map { invite =>
          val obj = ResultMessage(true, "Success", jsonBasic(BasicMessage("Invitation Saved")))
          Ok(Json.toJson(obj))
        }
      }
      case e: JsError => {
        val obj = ResultMessage(false, "Failed", jsonBasic(BasicMessage("Unable to save invitation")))
        Future(InternalServerError(Json.toJson(obj)))
      }
    }
  }

  // def findByName(name: String) = Action {
  //   for {
  //     db <- dbu.databaseCollection(db, dbCollection)
  //     res <- db.find(name).one[BSONDocument]
  //   } yield res
    // val usersCollection = dbu.databaseCollection(db, dbCollection)
    // val query = BSONDocument("name" -> name)
    // val result = usersCollection.find(query).one[BSONDocument]
    // Ok(s"$res")
  // }

  private def jsonBasic(obj: BasicMessage): JsValue = Json.toJson(obj)

  private def jsonResult(obj: ResultMessage): JsValue = Json.toJson(obj)

}
