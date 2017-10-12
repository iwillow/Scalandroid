package com.iwillow.scala.app.adapter

import android.support.annotation.LayoutRes
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.{BaseQuickAdapter, BaseViewHolder}
import com.iwillow.scala.app.R
import com.iwillow.scala.app.entity.Data.Subject

import scala.collection.mutable.ListBuffer

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
      .load(item.images.medium)
      .centerCrop()
      .into(helper.getView[ImageView](R.id.iv_cover))

  }
}
