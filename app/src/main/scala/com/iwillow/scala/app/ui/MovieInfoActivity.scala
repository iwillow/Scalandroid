package com.iwillow.scala.app.ui


import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.widget.NestedScrollView
import android.support.v4.widget.NestedScrollView.OnScrollChangeListener
import android.support.v7.app.{ActionBar, AppCompatActivity}
import android.support.v7.widget.Toolbar
import android.view.{View, ViewGroup}
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.iwillow.scala.app.R
import com.iwillow.scala.app.entity.Data.Subject
import com.iwillow.scala.app.util.{DimUtil, StatusBarUtils}
import jp.wasabeef.glide.transformations.BlurTransformation


/**
  * Created by iwillow on 2017/10/13.
  */
object MovieInfoActivity {
  def startMovieInfoActivity(@NonNull context: Activity, subject: Subject, sharedView: View): Unit = {
    val intent: Intent = new Intent(context, classOf[MovieInfoActivity])
    intent.putExtra("subject", subject)
    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context, sharedView, context.getString(R.string.transition_movie_img)); //与xml文件对应
    context.startActivity(intent, options.toBundle)
  }

}


class MovieInfoActivity extends AppCompatActivity {

  private var bgToolBarImageView: ImageView = _
  private var toolBar: Toolbar = _
  private var imageBgHeight: Int = 0
  private var slidingDistance: Int = 0

  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    val subject: Subject = getIntent.getSerializableExtra("subject").asInstanceOf[Subject]
    StatusBarUtils.setFullScreen(this)
    setContentView(R.layout.activity_movie_info)
    toolBar = findViewById(R.id.toolbar).asInstanceOf[Toolbar]
    bgToolBarImageView = findViewById(R.id.iv_toolbar_bg).asInstanceOf[ImageView]
    initStatusBar()
    Glide.`with`(this)
      .load(subject.images.large)
      .crossFade()
      .bitmapTransform(new BlurTransformation(this, 30, 3))
      .listener(new RequestListener[String, GlideDrawable]() {
        override def onException(e: Exception, model: String, target: Target[GlideDrawable], isFirstResource: Boolean): Boolean = false

        override def onResourceReady(resource: GlideDrawable, model: String, target: Target[GlideDrawable], isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean = {
          toolBar.setBackgroundColor(Color.TRANSPARENT)
          bgToolBarImageView.setImageAlpha(0)
          false
        }
      })
      .into(bgToolBarImageView)

    setSupportActionBar(toolBar)

    val actionBar: ActionBar = getSupportActionBar
    if (actionBar != null) {
      actionBar.setDisplayShowTitleEnabled(false)
      actionBar.setDisplayHomeAsUpEnabled(true)
    }



    val imageView: ImageView = findViewById(R.id.iv_cover).asInstanceOf[ImageView]
    Glide.`with`(this)
      .load(subject.images.large)
      .placeholder(R.drawable.img_default_movie)
      .error(R.drawable.img_default_movie)
      .crossFade()
      .centerCrop()
      .into(imageView)

    val bgImageView: ImageView = findViewById(R.id.iv_background).asInstanceOf[ImageView]
    Glide.`with`(this)
      .load(subject.images.large)
      .crossFade()
      .bitmapTransform(new BlurTransformation(this, 30, 3))
      .into(bgImageView)
  }


  private def scrollChangeHeader(scrolledY: Int): Unit = {
    if (scrolledY < 0) return
    val alpha: Float = Math.abs(scrolledY) * 1.0f / slidingDistance
    val drawable: Drawable = bgToolBarImageView.getDrawable
    if (drawable == null) return
    if (scrolledY <= slidingDistance) {
      // title部分的渐变
      drawable.mutate.setAlpha((alpha * 255).toInt)
      bgToolBarImageView.setImageDrawable(drawable)
    }
    else {
      drawable.mutate.setAlpha(255)
      bgToolBarImageView.setImageDrawable(drawable)
    }
  }

  private def initStatusBar(): Unit = {
    val toolbarHeight: Int = toolBar.getLayoutParams.height
    val headerBgHeight: Int = toolbarHeight + StatusBarUtils.getStatusBarHeight(this)
    // 使背景图向上移动到图片的最低端，保留（titlebar+statusbar）的高度
    val params: ViewGroup.LayoutParams = bgToolBarImageView.getLayoutParams
    val ivTitleHeadBgParams: ViewGroup.MarginLayoutParams = bgToolBarImageView.getLayoutParams.asInstanceOf[ViewGroup.MarginLayoutParams]
    val marginTop: Int = params.height - headerBgHeight
    ivTitleHeadBgParams.setMargins(0, -marginTop, 0, 0)

    bgToolBarImageView.setImageAlpha(0)
    StatusBarUtils.setTranslucentImageHeader(this, 0, toolBar)

    // 上移背景图片，使空白状态栏消失(这样下方就空了状态栏的高度)
    if (bgToolBarImageView != null) {
      val layoutParams: ViewGroup.MarginLayoutParams = bgToolBarImageView.getLayoutParams.asInstanceOf[ViewGroup.MarginLayoutParams]
      layoutParams.setMargins(0, -StatusBarUtils.getStatusBarHeight(this), 0, 0)
      val imgItemBgparams: ViewGroup.LayoutParams = bgToolBarImageView.getLayoutParams
      // 获得高斯图背景的高度
      imageBgHeight = imgItemBgparams.height
    }
    val titleBarAndStatusHeight: Int = DimUtil.dp2px(56, this).toInt + StatusBarUtils.getStatusBarHeight(this)
    // 减掉后，没到顶部就不透明了
    slidingDistance = imageBgHeight - titleBarAndStatusHeight - DimUtil.dp2px(45, this).toInt


    findViewById(R.id.nested_scroll_view).asInstanceOf[NestedScrollView].setOnScrollChangeListener(new OnScrollChangeListener() {
      override def onScrollChange(v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int): Unit = {
        scrollChangeHeader(scrollY)
      }
    })
  }
}
