package com.iwillow.scala.app.adapter

import android.app.Activity
import android.support.annotation.LayoutRes
import android.view.View
import android.view.View.OnClickListener
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.{BaseQuickAdapter, BaseViewHolder}
import com.iwillow.scala.app.R
import com.iwillow.scala.app.entity.Data.Subject
import com.iwillow.scala.app.ui.MovieInfoActivity
import com.nineoldandroids.view.{ViewHelper, ViewPropertyAnimator}

/**
  * Created by iwillow on 2017/10/12.
  */
class MovieAdapter(@LayoutRes layoutResId: Int, data: java.util.List[Subject]) extends BaseQuickAdapter[Subject, BaseViewHolder](layoutResId, data) {


  override def convert(helper: BaseViewHolder, item: Subject): Unit = {

    helper.setText(R.id.tv_title, item.title)
      .setText(R.id.tv_rate, item.rating.average.toString)
      .setText(R.id.tv_director, item.directors.mapConserve(p => p.name).mkString("/"))
      .setText(R.id.tv_casts, item.casts.mapConserve(p => p.name).mkString("/"))
      .setText(R.id.tv_genres, item.genres.mkString("/"))

    Glide.`with`(helper.itemView.getContext)
      .load(item.images.large)
      .placeholder(R.drawable.img_default_movie)
      .error(R.drawable.img_default_movie)
      .crossFade()
      .into(helper.getView[ImageView](R.id.iv_cover))
    //add scale animation
    ViewHelper.setScaleX(helper.itemView, 0.8f)
    ViewHelper.setScaleY(helper.itemView, 0.8f)
    ViewPropertyAnimator.animate(helper.itemView).scaleX(1).setDuration(350).setInterpolator(new OvershootInterpolator).start()
    ViewPropertyAnimator.animate(helper.itemView).scaleY(1).setDuration(350).setInterpolator(new OvershootInterpolator).start()
    helper.itemView.setOnClickListener(new OnClickListener {
      override def onClick(v: View): Unit = {
        MovieInfoActivity.startMovieInfoActivity(
          v.getContext.asInstanceOf[Activity],
          item,
          helper.getView(R.id.iv_cover))

      }
    })
  }

}
