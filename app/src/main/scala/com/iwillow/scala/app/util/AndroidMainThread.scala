package com.iwillow.scala.app.util

import rx.android.schedulers.AndroidSchedulers
import rx.lang.scala.Scheduler

/**
  * Created by iwillow on 2017/10/11.
  *
  * Hook 实现AndroidSchedulers.mainThread()
  */
object AndroidMainThread {
  def apply(): AndroidMainThread =
    new AndroidMainThread(AndroidSchedulers.mainThread())
}
class AndroidMainThread private[scala](val asJavaScheduler: rx.Scheduler) extends Scheduler {}
