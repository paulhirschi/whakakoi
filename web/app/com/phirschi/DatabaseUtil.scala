package com.phirschi.util

import javax.inject._
import scala.util.Try
import scala.concurrent.{ ExecutionContext, Future }
import play.api.Logger
import play.api.Configuration
import reactivemongo.api.{ MongoDriver, MongoConnection, DefaultDB }
import reactivemongo.bson.{ BSONDocumentWriter, BSONDocumentReader, Macros, document, BSONDocument }

import models._

@Singleton
class DatabaseUtil @Inject() (config: play.api.Configuration) {

  import ExecutionContext.Implicits.global

  // Database setup===============================
  private val dbDriver = new MongoDriver
  private val dbUri = MongoConnection.parseURI(getConfigurationString("whakakoi.api.dbUri"))
  private val dbName = getConfigurationString("whakakoi.api.db")
  private val dbConnection = dbUri.map(dbDriver.connection(_))
  private val futureDbConnection = Future.fromTry(dbConnection)

  private def db: Future[DefaultDB] = futureDbConnection.flatMap(_.database(dbName))

  // Database collections ========================
  private def invitationCollection = db.map(_.collection("invitations"))
  private def userCollection = db.map(_.collection("users"))
  private def customWodsCollection = db.map(_.collection("customwods"))
  private def namedWodsCollection = db.map(_.collection("customwods"))
  private def userIdCollection = db.map(_.collection("userid"))

  // Writers and Readers =========================
  implicit def invitationWriter: BSONDocumentWriter[Invitation] = Macros.writer[Invitation]
  implicit def userWriter: BSONDocumentWriter[User] = Macros.writer[User]
  implicit def customWodWriter: BSONDocumentWriter[CustomWods] = Macros.writer[CustomWods]
  implicit def completeWodWriter: BSONDocumentWriter[CompleteWods] = Macros.writer[CompleteWods]
  implicit def maxLiftStatsWriter: BSONDocumentWriter[MaxLiftStats] = Macros.writer[MaxLiftStats]
  implicit def recordWeightWriter: BSONDocumentWriter[RecordWeight] = Macros.writer[RecordWeight]

  implicit def invitationReader: BSONDocumentReader[Invitation] = Macros.reader[Invitation]
  implicit def userReader: BSONDocumentReader[User] = Macros.reader[User]
  implicit def customWodReader: BSONDocumentReader[CustomWods] = Macros.reader[CustomWods]
  implicit def completeWodReader: BSONDocumentReader[CompleteWods] = Macros.reader[CompleteWods]
  implicit def maxLiftStatsReader: BSONDocumentReader[MaxLiftStats] = Macros.reader[MaxLiftStats]
  implicit def recordWeightReader: BSONDocumentReader[RecordWeight] = Macros.reader[RecordWeight]


  // User DB actions =============================
  def createInvitation(invitation: Invitation): Future[Unit] =
    invitationCollection.flatMap(_.insert(invitation).map(_ => {}))

  def createUser(user: User): Future[Unit] =
    userCollection.flatMap(_.insert(user).map(_ => {}))

  def getUserById(id: Long): Future[Option[User]] =
    userCollection.flatMap(_.find(document("id" -> id)).
      one[User])

  // NamedWod DB actions =========================

  private def getConfigurationString(name: String): String =
    config.get[String](name)

}
