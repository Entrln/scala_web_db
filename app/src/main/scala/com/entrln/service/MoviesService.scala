package com.entrln.service

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshalling.{Marshaller, ToEntityMarshaller}
import akka.http.scaladsl.model.{HttpEntity, MediaTypes, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{PathMatcher, Route}
import akka.pattern.ask
import akka.util.Timeout
import com.entrln.actor.MoviesDataActor
import com.entrln.data.model.{JsonFormats, Movie}
import com.entrln.message.TableRead
import spray.json._
import scala.concurrent.duration.DurationInt
import scala.concurrent.{ExecutionContext, Future}
import scala.io.StdIn
import scala.language.postfixOps
import scala.util.{Failure, Success}


/**
 *
 * @param executionContext   : .
 * @param actorSystem        : .
 * @param defaultReadTimeout : .
 */
class MoviesService(implicit executionContext: ExecutionContext, actorSystem: ActorSystem, defaultReadTimeout: Timeout) extends Service {

  import JsonFormats._

  implicit val movieMarshaller: ToEntityMarshaller[Seq[Movie]] = Marshaller.opaque { movie => HttpEntity(MediaTypes.`application/json`, movie.toJson.toString()) }

  /**
   *
   */
  private val patchMatcherBase: PathMatcher[Unit] = "data" / "movies"

  /**
   *
   */
  private val read: Route = {
    path(patchMatcherBase / "read") {
      get {
        parameters('limit.as[Int].?) {
          maybeLimit => {
            val limit = maybeLimit.getOrElse(Int.MaxValue)
            val moviesActor: ActorRef = actorSystem.actorOf(Props(new MoviesDataActor))
            onComplete((moviesActor ? TableRead(limit = limit)).mapTo[Seq[Movie]]) {
              case Success(results) => complete(StatusCodes.OK, results)
              case Failure(exception) => complete(StatusCodes.InternalServerError, s"An error occurred: ${exception.getMessage}")
            }
          }
        }
      }
    }
  }

  private val insert: Route = {
    implicit val readTimeout: Timeout = Timeout(10 seconds)
    path(patchMatcherBase / "insert") {
      post {

        null
      }
    }
  }


  private val remove: Route = {
    implicit val readTimeout: Timeout = Timeout(10 seconds)
    path(patchMatcherBase / "delete") {
      delete {

        null
      }
    }
  }

  private val update: Route = {
    implicit val readTimeout: Timeout = Timeout(10 seconds)
    path(patchMatcherBase / "update") {
      put {

        null
      }
    }
  }

  /**
   *
   */
  private val routes: Route = read ~ insert ~ remove ~ update

  /**
   *
   */
  override def start(): Unit = {
    val bindingFuture: Future[Http.ServerBinding] = Http().newServerAt("localhost", 8080).bind(routes)
    println(s"Service running\nPress RETURN to stop...")
    // exit on return.
    StdIn.readLine()
    bindingFuture.flatMap(_.unbind()).onComplete(_ => actorSystem.terminate())
  }
}