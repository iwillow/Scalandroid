package com.iwillow.scala.app.util

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.view.{View, ViewGroup, Window, WindowManager}
import android.widget.LinearLayout

/**
  * Created by https://github.com/youlookwhat/CloudReader
  */
object StatusBarUtils {

  def setFullScreen(activity: Activity): Unit = {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      val window: Window = activity.getWindow
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
      window.getDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
      window.setStatusBarColor(Color.TRANSPARENT)
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      // 设置透明状态栏,这样才能让 ContentView 向上
      activity.getWindow.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

  }

  def setTranslucentImageHeader(activity: Activity, alpha: Int, needOffsetView: View) {
    setFullScreen(activity)
    //获取windowphone下的decorView
    val decorView: ViewGroup = activity.getWindow.getDecorView.asInstanceOf[ViewGroup]
    val count: Int = decorView.getChildCount
    //判断是否已经添加了statusBarView
    if (count > 0 && decorView.getChildAt(count - 1).isInstanceOf[StatusBarUtils.StatusBarView]) {
      decorView.getChildAt(count - 1).setBackgroundColor(Color.argb(alpha, 0, 0, 0))
    } else {
      //新建一个和状态栏高宽的view
      val statusView: StatusBarUtils.StatusBarView = createTranslucentStatusBarView(activity, alpha)
      decorView.addView(statusView)
    }
    if (needOffsetView != null) {
      val layoutParams: ViewGroup.MarginLayoutParams = needOffsetView.getLayoutParams.asInstanceOf[ViewGroup.MarginLayoutParams]
      layoutParams.setMargins(0, getStatusBarHeight(activity), 0, 0)
    }
  }


  private def createTranslucentStatusBarView(activity: Activity, alpha: Int): StatusBarUtils.StatusBarView = {
    // 绘制一个和状态栏一样高的矩形
    val statusBarView: StatusBarView = new StatusBarUtils.StatusBarView(activity)
    val params: LinearLayout.LayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity))
    statusBarView.setLayoutParams(params)
    statusBarView.setBackgroundColor(Color.argb(alpha, 0, 0, 0))
    statusBarView
  }

  def getStatusBarHeight(context: Context): Int = {
    val resourceId: Int = context.getResources.getIdentifier("status_bar_height", "dimen", "android")
    context.getResources.getDimensionPixelSize(resourceId)
  }


  class StatusBarView(val context: Context, @Nullable val attrs: AttributeSet, val defStyleAttr: Int) extends View(context, attrs, defStyleAttr) {

    def this(context: Context) {
      this(context, null, 0)

    }

    def this(context: Context, @Nullable attrs: AttributeSet) {
      this(context, attrs, 0)
    }

  }

}
