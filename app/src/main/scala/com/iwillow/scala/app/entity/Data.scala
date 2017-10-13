package com.iwillow.scala.app.entity

import spray.json.DefaultJsonProtocol

/**
  * Created by iwillow on 2017/10/12.
  */
object Data {

  /**
    * 评分
    */
  case class Rating(max: Int, average: Float, stars: String, min: Int) extends java.io.Serializable


  /**
    * 图片
    */
  case class Avatars(small: String, large: String, medium: String) extends java.io.Serializable


  /**
    * 人
    */
  case class Person(alt: String, avatars: Avatars, name: String, id: String) extends java.io.Serializable

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
                    ) extends java.io.Serializable

  /**
    * Top250
    **/
  case class Top250(count: Int, start: Int, total: Int, subjects: List[Subject], title: String) extends java.io.Serializable


  object ResultJsonProtocol extends DefaultJsonProtocol {
    implicit val ratingFormat = jsonFormat(Rating, "max", "average", "stars", "min")
    implicit val avatarsFormat = jsonFormat(Avatars, "small", "large", "medium")
    implicit val personFormat = jsonFormat(Person, "alt", "avatars", "name", "id")
    implicit val subjectFormat = jsonFormat(Subject, "rating", "genres", "title", "casts", "collect_count", "original_title", "subtype", "directors", "year", "images", "alt", "id")
    implicit val resultFormat = jsonFormat(Top250, "count", "start", "total", "subjects", "title")

  }


  case class MovieInfo(
                        reviews_count: Int,
                        wish_count: Int,
                        douban_site: String,
                        mobile_url: String,
                        do_count: Int,
                        share_url: String,
                        seasons_count: Int,
                        schedule_url: String,
                        episodes_count: Int,
                        countries: List[String],
                        collect_count: Int,
                        casts: List[Person],
                        current_season: Int,
                        original_title: String,
                        summary: String,
                        subtype: String,
                        comments_count: Int,
                        ratings_count: Int,
                        aka: List[String]) extends java.io.Serializable


  object MovieInfoProtocol extends DefaultJsonProtocol {

    implicit val avatarsFormat = jsonFormat(Avatars, "small", "large", "medium")
    implicit val personFormat = jsonFormat(Person, "alt", "avatars", "name", "id")
    implicit val movieInfoFormat = jsonFormat(MovieInfo,
      "reviews_count", "wish_count",
      "douban_site", "mobile_url",
      "do_count", "share_url",
      "seasons_count", "schedule_url",
      "episodes_count", "countries",
      "collect_count", "casts",
      "current_season", "original_title",
      "summary", "subtype",
      "comments_count", "ratings_count",
      "aka")
  }


}
