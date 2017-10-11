package com.iwillow.scala.app.util

/**
  * Created by iwillow on 2017/10/11.
  */

import java.io.IOException
import java.util.concurrent.TimeUnit

import okhttp3._

object HttpClient {
  val TAG: String = getClass.getName
  val CONNECT_TIME_OUT: Int = 25
  val okHttpClient: OkHttpClient = new OkHttpClient.Builder()
    .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
    .addInterceptor(new LoggingInterceptor())
    .build()


  @throws[IOException]
  def get(url: String): String = {
    val request = new Request.Builder().url(url).build
    val response = okHttpClient.newCall(request).execute
    if (response.isSuccessful) {
      val rep = response.body.string
      rep
    }
    else throw new IOException("Unexpected IOException:" + request)
  }

  @throws[IOException]
  def get(url: String, parameters: Map[String, String]): String = {
    var realUrl: String = null
    if (parameters.nonEmpty) {
      val sb = new java.lang.StringBuilder()
      parameters.foreach({ case (key: String, value: String) =>
        sb.append(key).append("=").append(value).append("&")
      })
      //  parameters.foreach((e: (String, String)) =>  sb.append(e._1 ).append("=").append( e._2).append("&"))
      realUrl = url + "?" + sb.toString.substring(0, sb.lastIndexOf("&"))
    } else realUrl = url
    val request = new Request.Builder().url(realUrl).build
    val response = okHttpClient.newCall(request).execute
    if (response.isSuccessful) {
      val rep = response.body.string
      LogExt.d(TAG, "get response:\n" + rep)
      rep
    }
    else throw new IOException("Unexpected IOException: " + response)
  }

  @throws[IOException]
  def post(url: String, parameters: Map[String, String]): String = {
    val formBodyBuilder = new FormBody.Builder()
    if (parameters != null) {
      parameters.foreach({ case (key, value) => formBodyBuilder.add(key, value) })
    }
    val request = new Request.Builder().url(url).post(formBodyBuilder.build).build
    val response = okHttpClient.newCall(request).execute
    if (response.isSuccessful) {
      response.body.string
      //LogExt.d(TAG, "post response:\n" + rep)
      //rep
    }
    else throw new IOException("Unexpected IOException: " + response)
  }


}


private class LoggingInterceptor extends Interceptor {
  val TAG = getClass.getSimpleName

  @throws[IOException]
  override def intercept(chain: Interceptor.Chain): Response = {
    val request = chain.request
    val t1 = System.nanoTime
    LogExt.d(TAG, String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()))
    val response = chain.proceed(request)
    val t2 = System.nanoTime
    LogExt.d(TAG, "duration:" + (t2 - t1) + "nanoseconds")
    response
  }
}