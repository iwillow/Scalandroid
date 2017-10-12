package com.iwillow.scala.app.util

/**
  * Created by iwillow on 2017/10/11.
  */
object LogExt {
  private var a: MyLog = null
  private var b: String = null
  private var c: String = null
  private var d: Boolean = false

  def init(componentName: String, version: String, banAllTag: Boolean): Unit = {
    b = componentName
    c = version
    d = banAllTag
  }

  def d(tag: String, log: String): Unit = {
    a(b, c, d).d(tag, log)
  }

  private def a(var0: String, var1: String, var2: Boolean): MyLog = {
    synchronized {
      if (a == null) a = new MyLog(var0, var1, var2)
      a
    }
  }

  def d(tag: String, log: String, t: Throwable): Unit = {
    a(b, c, d).d(tag, log, t)
  }

  def i(tag: String, log: String): Unit = {
    a(b, c, d).i(tag, log)
  }

  def i(tag: String, log: String, t: Throwable): Unit = {
    a(b, c, d).i(tag, log, t)
  }

  def w(tag: String, log: String): Unit = {
    a(b, c, d).w(tag, log)
  }

  def w(tag: String, log: String, t: Throwable): Unit = {
    a(b, c, d).w(tag, log, t)
  }

  def e(tag: String, log: String): Unit = {
    a(b, c, d).e(tag, log)
  }

  def e(tag: String, log: String, t: Throwable): Unit = {
    a(b, c, d).e(tag, log, t)
  }

  def banTag(tag: String): Unit = {
    a(b, c, d).banTag(tag)
  }

  def unbanTag(tag: String): Unit = {
    a(b, c, d).unbanTag(tag)
  }
}
