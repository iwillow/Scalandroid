package com.iwillow.scala.app.util


import rx.lang.scala.Observable
import rx.lang.scala.schedulers.NewThreadScheduler

/**
  * Created by iwillow on 2017/10/11.
  */
object RxHttp {
  def get(url: String): Observable[String] = {
    Observable[String] { subscriber =>
      try {
        val rep = HttpClient.get(url)
        subscriber.onNext(rep)
        subscriber.onCompleted()
      } catch {
        case e: Exception => subscriber.onError(e)
      }
    }
      .subscribeOn(NewThreadScheduler())
      .observeOn(AndroidMainThread())
  }

  def post(url: String, parameters: Map[String, String]): Observable[String] = {
    Observable[String] { subscriber =>
      try {
        val rep = HttpClient post(url, parameters)
        subscriber.onNext(rep)
        subscriber.onCompleted()
      } catch {
        case e: Exception => subscriber.onError(e)
      }
    }
      .subscribeOn(NewThreadScheduler())
      .observeOn(AndroidMainThread())
  }
}
