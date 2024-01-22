package com.entrln.actor

import akka.util.Timeout
import com.entrln.data.tables.MoviesTable
import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

/**
 *
 */
trait DatabaseActor {
  val db: PostgresProfile.backend.Database = Database.forConfig("starterDB")
  val movies: TableQuery[MoviesTable] = TableQuery[MoviesTable]
  implicit val defaultTimeout: Timeout = Timeout(1 minute)
  implicit val executionContext: ExecutionContext = scala.concurrent.ExecutionContext.fromExecutor(
    java.util.concurrent.Executors.newFixedThreadPool(8)
  )
}

