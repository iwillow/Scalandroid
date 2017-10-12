package com.iwillow.scala.app.entity

import spray.json.DefaultJsonProtocol

/**
  * Created by iwillow on 2017/10/12.
  */
object Data {

  /**
    * 评分
    */
  case class Rating(max: Int, average: Float, stars: String, min: Int)

  /**
    * 图片
    */
  case class Avatars(small: String, large: String, medium: String)

  /**
    * 人
    */
  case class Person(alt: String, avatars: Avatars, name: String, id: String)

  /**
    * 电影主题
    */
  case class Subject(
                      rating: Rating,
                      genres: List[String],
                      title: String,
                      casts: List[Person],
                      collect_count: Int,
                      original_title: String,
                      subtype: String,
                      directors: List[Person],
                      year: String,
                      images: Avatars,
                      alt: String,
                      id: String
                    )

  /**
    * Top250
    **/
  case class Top250(count: Int, start: Int, total: Int, subjects: List[Subject], title: String)


  object ResultJsonProtocol extends DefaultJsonProtocol {
    implicit val ratingFormat = jsonFormat(Rating, "max", "average", "stars", "min")
    implicit val avatarsFormat = jsonFormat(Avatars, "small", "large", "medium")
    implicit val personFormat = jsonFormat(Person, "alt", "avatars", "name", "id")
    implicit val subjectFormat = jsonFormat(Subject, "rating", "genres", "title", "casts", "collect_count", "original_title", "subtype", "directors", "year", "images", "alt", "id")
    implicit val resultFormat = jsonFormat(Top250, "count", "start", "total", "subjects", "title")

  }

}
