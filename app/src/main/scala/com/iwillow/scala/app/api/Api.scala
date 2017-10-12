package com.iwillow.scala.app

import android.support.annotation.NonNull
import com.iwillow.scala.app.entity.Data.Top250

/**
  * Created by iwillow on 2017/10/11.
  */
package object Api {
  val HOST = "https://api.douban.com"
  val URL_TOP250 = HOST + "/v2/movie/top250"


  object DoubanParser {

    def parseTop250(@NonNull json: String): Top250 = {
      import spray.json._
      import com.iwillow.scala.app.entity.Data.ResultJsonProtocol._
      json.parseJson.convertTo[Top250]
    }
  }

}
