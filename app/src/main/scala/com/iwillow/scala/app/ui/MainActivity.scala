package com.iwillow.scala.app.ui

import java.util

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.{LinearLayoutManager, RecyclerView}
import com.iwillow.scala.app.Api.DoubanParser
import com.iwillow.scala.app.adapter.MovieAdapter
import com.iwillow.scala.app.entity.Data.Subject
import com.iwillow.scala.app.util.{LogExt, RxHttp}
import com.iwillow.scala.app.{Api, R}

/**
  * Created by iwillow on 2017/10/11.
  */
class MainActivity extends AppCompatActivity {
  val TAG = getClass.getSimpleName
  var mRvMovie: RecyclerView = _
  var mAdapter: MovieAdapter = _
  var mProgressDialog: ProgressDialog = _

  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    mProgressDialog = new ProgressDialog(this)
    mProgressDialog.setMessage("loading...")
    setContentView(R.layout.activity_main)
    mRvMovie = findViewById(R.id.rv_movie).asInstanceOf[RecyclerView]
    mRvMovie.setLayoutManager(new LinearLayoutManager(this))
    mAdapter = new MovieAdapter(R.layout.item_movie, new util.ArrayList[Subject]())
    mRvMovie.setAdapter(mAdapter)
    mProgressDialog.show()

    RxHttp.get(Api.URL_TOP250)
      .map(DoubanParser parseTop250 _)
      .subscribe(
        top250 => {
          mProgressDialog.hide()
          if (!top250.subjects.isEmpty) {
            import scala.collection.JavaConverters._
            mAdapter.addData(top250.subjects.asJava)
          }
        },
        e => {
          mProgressDialog.hide()
          LogExt.e(TAG, "parseTop250", e)
        }
      )

  }

}
