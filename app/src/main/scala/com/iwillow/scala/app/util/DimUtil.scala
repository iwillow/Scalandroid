package com.iwillow.scala.app.util

import android.content.Context
import android.support.annotation.NonNull
import android.util.TypedValue

/**
  * Created by iwillow on 2017/10/13.
  */
object DimUtil {

  def sp2px(spSize: Int, @NonNull context: Context): Float = {
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spSize, context.getApplicationContext.getResources().getDisplayMetrics())
  }

  def dp2px(dpSize: Int, @NonNull context: Context): Float = {
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpSize, context.getApplicationContext.getResources().getDisplayMetrics())
  }

}
