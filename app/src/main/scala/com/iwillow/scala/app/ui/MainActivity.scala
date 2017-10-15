package com.iwillow.scala.app.ui

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.{LinearLayoutManager, RecyclerView}
import com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener
import com.iwillow.scala.app.Api.DoubanParser
import com.iwillow.scala.app.adapter.MovieAdapter
import com.iwillow.scala.app.util.{LogExt, RxHttp}
import com.iwillow.scala.app.{Api, R}
import rx.lang.scala.subscriptions.CompositeSubscription

/**
  * Created by iwillow on 2017/10/11.
  */
class MainActivity extends AppCompatActivity with SwipeRefreshLayout.OnRefreshListener {
  val TAG = getClass.getSimpleName
  var mSwipeRefreshLayout: SwipeRefreshLayout = _
  var mRvMovie: RecyclerView = _
  var mAdapter: MovieAdapter = _
  val subscriptions = CompositeSubscription()
  var start = 0
  val count = 10

  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_movie_list)
    mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout).asInstanceOf[SwipeRefreshLayout]
    mSwipeRefreshLayout.setColorSchemeResources(R.color.colorDoubanColor1, R.color.colorDoubanColor2, R.color.colorDoubanColor3)
    mSwipeRefreshLayout.setOnRefreshListener(this)
    mRvMovie = findViewById(R.id.rv_movie).asInstanceOf[RecyclerView]
    mRvMovie.setLayoutManager(new LinearLayoutManager(this))
    mAdapter = new MovieAdapter(R.layout.item_movie /*, new util.ArrayList[Subject]()*/)
    mRvMovie.setAdapter(mAdapter)
    mAdapter.setOnLoadMoreListener(new RequestLoadMoreListener {
      override def onLoadMoreRequested(): Unit = loadData(start)
    }, mRvMovie)
    onRefresh()
  }


  private def loadData(startIndex: Int) = {
    val param = "?start=" + startIndex + "&count=" + count
    val subscription = RxHttp.get(Api.URL_TOP250 + param)
      .map(DoubanParser parseTop250 _)
      .subscribe(
        top250 => {
          if (start == 0) {
            mSwipeRefreshLayout.setRefreshing(false)
          }

          if (!top250.subjects.isEmpty) {
            mAdapter.loadMoreComplete()
            import scala.collection.JavaConverters._
            if (start == 0) {
              mAdapter.clear()
            }
            mAdapter.addData(top250.subjects.asJava)
            start += top250.subjects.size
          } else {
            mAdapter.loadMoreEnd()
          }
        },
        e => {
          mAdapter.loadMoreFail()
          if (start == 0) mSwipeRefreshLayout.setRefreshing(false)
          LogExt.e(TAG, "parseTop250", e)
        }
      )

    subscriptions += subscription

  }


  override def onDestroy(): Unit = {
    super.onDestroy()
    subscriptions.unsubscribe()
  }

  override def onRefresh(): Unit = {
    start = 0
    mSwipeRefreshLayout.setRefreshing(true)
    loadData(start)
  }
}
