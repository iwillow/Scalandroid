package com.iwillow.scala.app.adapter

import android.support.annotation.LayoutRes
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.{BaseQuickAdapter, BaseViewHolder}
import com.iwillow.scala.app.R
import com.iwillow.scala.app.entity.Data.{Avatars, Star}

/**
  * Created by iwillow on 2017/10/15.
  */


class StarAdapter(@LayoutRes layoutResId: Int, data: java.util.List[Star]) extends BaseQuickAdapter[Star, BaseViewHolder](layoutResId, data) {
  override def convert(helper: BaseViewHolder, item: Star): Unit = {
    helper.setText(R.id.tv_name, item.person.name.getOrElse(""))
    helper.setText(R.id.tv_role, item.role)
    val url: String = item.person.avatars.getOrElse(Avatars("", "", "")).large
    Glide.`with`(helper.itemView.getContext)
      .load(url)
      .placeholder(R.drawable.img_default_movie)
      .error(R.drawable.img_default_movie)
      .crossFade()
      .into(helper.getView[ImageView](R.id.iv_avatar))
  }
}
