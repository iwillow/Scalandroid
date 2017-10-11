package com.iwillow.scala.app.util

import android.content.Context
import android.content.pm.ApplicationInfo
import android.support.annotation.NonNull

/**
  * Created by iwillow on 2017/10/11.
  */
object SystemUtil {

  def isDebugMode(@NonNull context: Context): Boolean = {
    try {
      val info = context.getApplicationInfo()
      return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
    false
  }

}
