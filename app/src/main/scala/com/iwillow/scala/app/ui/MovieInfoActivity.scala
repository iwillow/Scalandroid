package com.iwillow.scala.app.ui


import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.{Build, Bundle}
import android.support.annotation.{IdRes, NonNull}
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.{ActionBar, AppCompatActivity}
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.{ImageView, TextView}
import com.bumptech.glide.Glide
import com.iwillow.scala.app.R
import com.iwillow.scala.app.entity.Data.Subject
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


  private var item: Subject = _

  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    item = getIntent.getSerializableExtra("subject").asInstanceOf[Subject]
    if (Build.VERSION.SDK_INT >= 21) {
      val decorView: View = getWindow().getDecorView()
      val option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
      decorView.setSystemUiVisibility(option)
      getWindow().setNavigationBarColor(Color.TRANSPARENT)
      getWindow().setStatusBarColor(Color.TRANSPARENT)
    }
    setContentView(R.layout.activity_movie_info)
    initView()


  }

  private[this] def initView(): Unit = {
    val toolBar: Toolbar = findViewById(R.id.toolbar).asInstanceOf[Toolbar]
    toolBar.setTitle(item.title)
    setSupportActionBar(toolBar)
    val actionBar: ActionBar = getSupportActionBar
    if (actionBar != null) {
      //去除默认Title显示
      actionBar.setDisplayShowTitleEnabled(false)
      actionBar.setDisplayHomeAsUpEnabled(true)
    }

    val imageView: ImageView = findViewById(R.id.iv_cover).asInstanceOf[ImageView]
    Glide.`with`(this)
      .load(item.images.large)
      .placeholder(R.drawable.img_default_movie)
      .error(R.drawable.img_default_movie)
      .crossFade()
      .centerCrop()
      .into(imageView)

    val bgImageView: ImageView = findViewById(R.id.iv_background).asInstanceOf[ImageView]
    Glide.`with`(this)
      .load(item.images.large)
      .crossFade()
      .bitmapTransform(new BlurTransformation(this, 30, 3))
      .into(bgImageView)
    setText(R.id.toolbar_title, item.title)
    setText(R.id.tv_title, item.title)
    setText(R.id.tv_rate, item.rating.average.toString)
    setText(R.id.tv_director, item.directors.mapConserve(p => p.name).mkString("/"))
    setText(R.id.tv_casts, item.casts.mapConserve(p => p.name).mkString("/"))
    setText(R.id.tv_genres, item.genres.mkString("/"))
  }


  private def setText(@IdRes resId: Int, text: String): Unit = {
    val textView: TextView = findViewById(resId).asInstanceOf[TextView]
    if (textView != null) {
      textView.setText(text)
    }
  }


}