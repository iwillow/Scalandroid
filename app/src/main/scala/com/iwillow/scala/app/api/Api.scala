package com.iwillow.scala.app

import android.support.annotation.NonNull
import com.iwillow.scala.app.entity.Data.{MovieInfo, Top250}

/**
  * Created by iwillow on 2017/10/11.
  */
package object Api {
  val HOST = "https://api.douban.com"
  val URL_TOP250 = HOST + "/v2/movie/top250"
  val URL_MOVIE_INFO = HOST + "/v2/movie/subject/"

  object DoubanParser {

    def parseTop250(@NonNull json: String): Top250 = {
      import com.iwillow.scala.app.entity.Data.ResultJsonProtocol._
      import spray.json._
      json.parseJson.convertTo[Top250]
    }

    def parseMovieInfo(@NonNull json: String): MovieInfo = {
      import com.iwillow.scala.app.entity.Data.MovieInfoProtocol._
      import spray.json._
      json.parseJson.convertTo[MovieInfo]
    }

  }

}
