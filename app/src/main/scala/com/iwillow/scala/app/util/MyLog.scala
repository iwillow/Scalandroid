package com.iwillow.scala.app.util

/**
  * Created by iwillow on 2017/10/11.
  */
class MyLog {

  import android.util.Log
  import java.util

  private var a: String = null
  private var b: Boolean = false
  private val c: util.ArrayList[String] = new util.ArrayList[String]

  def this(componentName: String, version: String, banAllTag: Boolean) {
    this()
    a = "[" + (if (componentName == null) ""
    else componentName) + "][" + (if (version == null) ""
    else version) + "]"
    b = banAllTag
  }

  def d(tag: String, log: String): Unit = {
    if (a(tag)) Log.d(a, "[" + tag + "]" + log)
  }

  private def a(var1: String) = !b && !c.contains(var1)

  def d(tag: String, log: String, t: Throwable): Unit = {
    if (a(tag)) Log.d(a, "[" + tag + "]" + log, t)
  }

  def i(tag: String, log: String): Unit = {
    Log.i(a, "[" + tag + "]" + log)
  }

  def i(tag: String, log: String, t: Throwable): Unit = {
    Log.i(a, "[" + tag + "]" + log, t)
  }

  def w(tag: String, log: String): Unit = {
    Log.w(a, "[" + tag + "]" + log)
  }

  def w(tag: String, log: String, t: Throwable): Unit = {
    Log.w(a, "[" + tag + "]" + log, t)
  }

  def e(tag: String, log: String): Unit = {
    Log.e(a, "[" + tag + "]" + log)
  }

  def e(tag: String, log: String, t: Throwable): Unit = {
    Log.e(a, "[" + tag + "]" + log, t)
  }

  def banTag(tag: String): Unit = {
    c.add(tag)
  }

  def unbanTag(tag: String): Unit = {
    c.remove(tag)
  }
}
