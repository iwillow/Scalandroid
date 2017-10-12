package com.iwillow.scala.app.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.iwillow.scala.app.Api.DoubanParser
import com.iwillow.scala.app.util.{LogExt, RxHttp}
import com.iwillow.scala.app.{Api, R}

/**
  * Created by iwillow on 2017/10/11.
  */
class MainActivity extends AppCompatActivity {
  val TAG = getClass.getSimpleName
  var tv: TextView = _

  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    tv = findViewById(R.id.txt).asInstanceOf[TextView]

    RxHttp.get(Api.URL_TOP250)
      .map(DoubanParser parseTop250 _)
      .subscribe(
        top250 => {
          LogExt.d(TAG, "total" + top250.total)
          tv.setText(top250.title)
        },
        e => LogExt.e(TAG, "parseTop250", e)
      )


  }


}
