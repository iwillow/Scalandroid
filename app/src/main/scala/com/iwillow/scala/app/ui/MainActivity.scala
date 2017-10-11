package com.iwillow.scala.app.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alibaba.fastjson.JSON
import com.iwillow.scala.app.R
import com.iwillow.scala.app.api.Rating
import com.iwillow.scala.app.util.{LogExt, RxHttp}

/**
  * Created by iwillow on 2017/10/11.
  */
class MainActivity extends AppCompatActivity {
  val TAG = getClass.getSimpleName

  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    RxHttp.get("https://www.baidu.com/")
      .subscribe(str => {
        LogExt.d(TAG, "rep:" + str)
      })
  }


}
