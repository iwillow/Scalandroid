package com.iwillow.scala.app.util

import android.content.Context
import com.bumptech.glide.{Glide, GlideBuilder}
import com.bumptech.glide.module.GlideModule

/**
  * Created by iwillow on 2017/10/11.
  */
class MyGlideModule extends GlideModule {
  val TAG = getClass.getName
  override def registerComponents(context: Context, glide: Glide): Unit = {
    LogExt.d(TAG, "registerComponents")
  }
  override def applyOptions(context: Context, builder: GlideBuilder): Unit = {
    import com.bumptech.glide.load.DecodeFormat
    import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
    import com.bumptech.glide.load.engine.cache.LruResourceCache
    val maxMemory = Runtime.getRuntime.maxMemory / 1024
    val cacheSize = (maxMemory / 10).toInt
    builder.setMemoryCache(new LruResourceCache(cacheSize))
    val maxBitmapPoolSize = 5 * 1024 * 1024
    //5Mb
    val bitmapPool = new LruBitmapPool(maxBitmapPoolSize)
    builder.setBitmapPool(bitmapPool)
    builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888)
    LogExt.d(TAG, "applyOptions MemoryCache:" + cacheSize + "kb" + ";")
  }
}
