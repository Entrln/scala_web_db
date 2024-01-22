package com.entrln.data.tables

import com.entrln.data.model.Movie
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape

/**
 *
 * @param tag : .
 */
class MoviesTable(tag: Tag) extends Table[Movie](tag, "movie") {
  /**
   *
   * @return
   */
  def id: Rep[Long] = column[Long]("movie_id", O.PrimaryKey, O.AutoInc)

  /**
   *
   * @return
   */
  def title: Rep[String] = column[String]("name")

  /**
   *
   * @return
   */
  def director: Rep[String] = column[String]("director")

  /**
   *
   * @return
   */
  override def * : ProvenShape[Movie] = (id, title, director) <> (Movie.tupled, Movie.unapply)
}
