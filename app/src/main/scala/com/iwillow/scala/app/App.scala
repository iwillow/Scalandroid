package com.iwillow.scala.app

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.os.StrictMode
import android.os.StrictMode.{ThreadPolicy, VmPolicy}
import android.support.multidex.MultiDex
import com.iwillow.scala.app.util.{LogExt, SystemUtil}

/**
  * Created by iwillow on 2017/10/11.
  */

object App {
  private[App] var INSTANCE: App = _

  def getInstance(): App = INSTANCE
}

class App extends Application {

  val TAG = getClass.getSimpleName

  override def attachBaseContext(base: Context): Unit = {
    super.attachBaseContext(base)
    MultiDex.install(this)
  }


  override def onCreate(): Unit = {
    super.onCreate()
    try {
      LogExt.init(getPackageName, getPackageManager.getPackageInfo(getPackageName, 0).versionName, !SystemUtil.isDebugMode(this))
      if (SystemUtil.isDebugMode(this)) {
        LogExt.i(TAG, " debug mode")
        StrictMode.setThreadPolicy(new ThreadPolicy.Builder().detectAll.penaltyLog.build)
        StrictMode.setVmPolicy(new VmPolicy.Builder().detectAll.penaltyLog.build)
      }
      else LogExt.i(TAG, " release mode")
    } catch {
      case e: PackageManager.NameNotFoundException =>
        e.printStackTrace()
    }
  }
}
