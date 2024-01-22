package com.entrln.actor

import akka.actor.{Actor, ActorLogging}
import akka.pattern.pipe
import com.entrln.data.model.Movie
import com.entrln.message.TableRead
import slick.dbio.Effect
import slick.jdbc.PostgresProfile.api._
import slick.sql.FixedSqlStreamingAction
import scala.concurrent.Future
import scala.language.postfixOps

/**
 *
 */
class MoviesDataActor extends Actor with DatabaseActor with ActorLogging {
  /**
   *
   * @return
   */
  override def receive: Receive = {
    case read: TableRead =>
      val query: FixedSqlStreamingAction[Seq[Movie], Movie, Effect.Read] = movies.take(read.limit).result
      val runFuture: Future[Seq[Movie]] = db.run(query)
      runFuture.pipeTo(sender())
  }
}