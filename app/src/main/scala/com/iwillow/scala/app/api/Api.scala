package com.iwillow.scala.app

/**
  * Created by iwillow on 2017/10/11.
  */
package object api {
  val HOST = "https://api.douban.com"
  val API = "/v2/movie/top250"

  /**
    * 评分
    */
  case class Rating(
                     max: Int,
                     average: Float,
                     stars: String,
                     min: Int
                   )
  /**
    * 图片
    */
  case class Avatars(
                      small: String,
                      large: String,
                      medium: String
                    )
  /**
    * 人
    */
  case class Person(
                     alt: String,
                     avatars: Avatars,
                     name: String,
                     id: String
                   )

  case class Subject(
                      rating: Rating,
                      genres: List[String],
                      title: String,
                      casts: List[Person],
                      collect_count: Long,
                      original_title: String,
                      subtype: String,
                      directors: List[Person],
                      year: String,
                      images: List[Avatars],
                      alt: String,
                      id: String
                    )
  /**
  * Top250
  * */
  case class Result(
                     count: Int,
                     start: Int,
                     total: Int,
                     subjects: List[Subject],
                     title: String
                   )

   object Parser{
     def parseMovies(json:String):Unit={



     }
   }
}
