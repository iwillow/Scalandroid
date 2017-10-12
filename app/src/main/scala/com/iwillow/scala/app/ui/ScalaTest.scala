package com.iwillow.scala.app.ui

import com.iwillow.scala.app.entity.Data.{Top250}

/**
  * Created by iwillow on 2017/10/12.
  */
object ScalaTest {
  def main(args: Array[String]): Unit = {
    import spray.json._
    val jsonValue =
      """
        |{
        | "count": 20,
        | "start": 0,
        | "total": 250,
        |	"title": "豆瓣电影Top250",
        |	"subjects": [
        |	 {
        |            "rating": {
        |                "max": 10,
        |                "average": 9.6,
        |                "stars": "50",
        |                "min": 0
        |            },
        |            "genres": [
        |                "犯罪",
        |                "剧情"
        |            ],
        |            "title": "肖申克的救赎",
        |            "casts": [
        |                {
        |                    "alt": "https://movie.douban.com/celebrity/1054521/",
        |                    "avatars": {
        |                        "small": "https://img3.doubanio.com/img/celebrity/small/17525.jpg",
        |                        "large": "https://img3.doubanio.com/img/celebrity/large/17525.jpg",
        |                        "medium": "https://img3.doubanio.com/img/celebrity/medium/17525.jpg"
        |                    },
        |                    "name": "蒂姆·罗宾斯",
        |                    "id": "1054521"
        |                },
        |                {
        |                    "alt": "https://movie.douban.com/celebrity/1054534/",
        |                    "avatars": {
        |                        "small": "https://img3.doubanio.com/img/celebrity/small/34642.jpg",
        |                        "large": "https://img3.doubanio.com/img/celebrity/large/34642.jpg",
        |                        "medium": "https://img3.doubanio.com/img/celebrity/medium/34642.jpg"
        |                    },
        |                    "name": "摩根·弗里曼",
        |                    "id": "1054534"
        |                },
        |                {
        |                    "alt": "https://movie.douban.com/celebrity/1041179/",
        |                    "avatars": {
        |                        "small": "https://img1.doubanio.com/img/celebrity/small/5837.jpg",
        |                        "large": "https://img1.doubanio.com/img/celebrity/large/5837.jpg",
        |                        "medium": "https://img1.doubanio.com/img/celebrity/medium/5837.jpg"
        |                    },
        |                    "name": "鲍勃·冈顿",
        |                    "id": "1041179"
        |                }
        |            ],
        |            "collect_count": 1135009,
        |            "original_title": "The Shawshank Redemption",
        |            "subtype": "movie",
        |            "directors": [
        |                {
        |                    "alt": "https://movie.douban.com/celebrity/1047973/",
        |                    "avatars": {
        |                        "small": "https://img3.doubanio.com/img/celebrity/small/230.jpg",
        |                        "large": "https://img3.doubanio.com/img/celebrity/large/230.jpg",
        |                        "medium": "https://img3.doubanio.com/img/celebrity/medium/230.jpg"
        |                    },
        |                    "name": "弗兰克·德拉邦特",
        |                    "id": "1047973"
        |                }
        |            ],
        |            "year": "1994",
        |            "images": {
        |                "small": "https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p480747492.webp",
        |                "large": "https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p480747492.webp",
        |                "medium": "https://img3.doubanio.com/view/movie_poster_cover/spst/public/p480747492.webp"
        |            },
        |            "alt": "https://movie.douban.com/subject/1292052/",
        |            "id": "1292052"
        |        },
        |        {
        |            "rating": {
        |                "max": 10,
        |                "average": 9.5,
        |                "stars": "50",
        |                "min": 0
        |            },
        |            "genres": [
        |                "剧情",
        |                "爱情",
        |                "同性"
        |            ],
        |            "title": "霸王别姬",
        |            "casts": [
        |                {
        |                    "alt": "https://movie.douban.com/celebrity/1003494/",
        |                    "avatars": {
        |                        "small": "https://img1.doubanio.com/img/celebrity/small/67.jpg",
        |                        "large": "https://img1.doubanio.com/img/celebrity/large/67.jpg",
        |                        "medium": "https://img1.doubanio.com/img/celebrity/medium/67.jpg"
        |                    },
        |                    "name": "张国荣",
        |                    "id": "1003494"
        |                },
        |                {
        |                    "alt": "https://movie.douban.com/celebrity/1050265/",
        |                    "avatars": {
        |                        "small": "https://img3.doubanio.com/img/celebrity/small/46345.jpg",
        |                        "large": "https://img3.doubanio.com/img/celebrity/large/46345.jpg",
        |                        "medium": "https://img3.doubanio.com/img/celebrity/medium/46345.jpg"
        |                    },
        |                    "name": "张丰毅",
        |                    "id": "1050265"
        |                },
        |                {
        |                    "alt": "https://movie.douban.com/celebrity/1035641/",
        |                    "avatars": {
        |                        "small": "https://img1.doubanio.com/img/celebrity/small/1399268395.47.jpg",
        |                        "large": "https://img1.doubanio.com/img/celebrity/large/1399268395.47.jpg",
        |                        "medium": "https://img1.doubanio.com/img/celebrity/medium/1399268395.47.jpg"
        |                    },
        |                    "name": "巩俐",
        |                    "id": "1035641"
        |                }
        |            ],
        |            "collect_count": 810842,
        |            "original_title": "霸王别姬",
        |            "subtype": "movie",
        |            "directors": [
        |                {
        |                    "alt": "https://movie.douban.com/celebrity/1023040/",
        |                    "avatars": {
        |                        "small": "https://img3.doubanio.com/img/celebrity/small/750.jpg",
        |                        "large": "https://img3.doubanio.com/img/celebrity/large/750.jpg",
        |                        "medium": "https://img3.doubanio.com/img/celebrity/medium/750.jpg"
        |                    },
        |                    "name": "陈凯歌",
        |                    "id": "1023040"
        |                }
        |            ],
        |            "year": "1993",
        |            "images": {
        |                "small": "https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p1910813120.webp",
        |                "large": "https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p1910813120.webp",
        |                "medium": "https://img3.doubanio.com/view/movie_poster_cover/spst/public/p1910813120.webp"
        |            },
        |            "alt": "https://movie.douban.com/subject/1291546/",
        |            "id": "1291546"
        |        }
        |	]
        |}
      """.stripMargin.parseJson

    import com.iwillow.scala.app.entity.Data.ResultJsonProtocol._
    val top250 = jsonValue.convertTo[Top250]
    println("count:" + top250.count + ";start" + top250.start + ";start:" + top250.total + ";title:" + top250.title)
    top250.subjects.foreach(item => println(item.title))

  }
}
