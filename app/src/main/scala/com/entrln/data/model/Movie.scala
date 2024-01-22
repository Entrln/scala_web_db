package com.entrln.data.model

import spray.json._

/**
 *
 * @param id       : .
 * @param title    : .
 * @param director : .
 */
final case class Movie(id: Long, title: String, director: String)

object JsonFormats extends DefaultJsonProtocol {
  implicit val basicBlockFormat: RootJsonFormat[Movie] = jsonFormat3(Movie.apply)
}