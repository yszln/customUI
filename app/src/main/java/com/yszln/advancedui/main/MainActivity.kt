package com.yszln.advancedui.main

import android.content.Intent
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.yszln.advancedui.R
import com.yszln.advancedui.study.StudyActivity
import com.yszln.advancedui.view.dashboard.DashboardViewActivity
import com.yszln.advancedui.view.particleball.BallViewActivity
import com.yszln.advancedui.view.givelike.GiveLikeActivity
import com.yszln.advancedui.view.piechart.PiechartActivity
import com.yszln.advancedui.view.progress.SprotsProgressView
import com.yszln.advancedui.view.schedule.ScheduleActivity
import com.yszln.advancedui.view.radarscanning.WXRadarScanningActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KProperty1

class MainActivity : BaseActivity() {

    private var mMainAdapter = MainAdapter()


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        recyclerView.layoutManager = GridLayoutManager(this, 6)
        recyclerView.adapter = mMainAdapter
        mMainAdapter.setOnItemClickListener(object : MainAdapter.OnItemClickListener {
            override fun onClick(position: Int, item: MainItemBean, holder: MainAdapter.VH) {
                clickEvent(position, item, holder)
            }
        })

        addItem()
    }

    private fun addItem() {

        mMainAdapter.addData(MainItemBean(0, "雷达", R.mipmap.ic_launcher))
        mMainAdapter.addData(MainItemBean(1, "粒子爆炸效果", R.mipmap.ic_launcher))
        mMainAdapter.addData(MainItemBean(2, "仿花束直播点赞", R.mipmap.heart1))
        mMainAdapter.addData(MainItemBean(3, "横向进度时间轴", R.mipmap.circle))
        mMainAdapter.addData(MainItemBean(4, "仪表盘", R.mipmap.circle))
        mMainAdapter.addData(MainItemBean(5, "饼状图", R.mipmap.circle))
        mMainAdapter.addData(MainItemBean(6, "饼状图", R.mipmap.circle))
        mMainAdapter.addData(MainItemBean(7, "环形进度条", R.mipmap.circle))
        mMainAdapter.addData(MainItemBean(666, "test", R.mipmap.circle))
    }

    private fun clickEvent(position: Int, item: MainItemBean, holder: MainAdapter.VH) {
        when (item.type) {

            0 -> {
                //雷达
                val activityOptions =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this,
                        holder.imageView,
                        "SceneTransition"
                    )
                val intent = Intent(this, WXRadarScanningActivity::class.java)
                startActivity(intent, activityOptions.toBundle())
//                gotoActivity(WXRadarScanningActivity::class.java)
            }
            1 -> {
                //粒子爆炸效果
                gotoActivity(BallViewActivity::class.java)
            }
            2 -> {
                //仿花束直播点赞效果
                gotoActivity(GiveLikeActivity::class.java)
            }
            3 -> {
                //进度
                gotoActivity(ScheduleActivity::class.java)
            }
            4 -> {
                //仪表盘
                gotoActivity(DashboardViewActivity::class.java)
            }
            5 -> {
                //饼状图
                gotoActivity(PiechartActivity::class.java)
            }
            6 -> {

            }
            7 -> {
                seeView(SprotsProgressView::class.java)
            }
            666 -> {
                gotoActivity(StudyActivity::class.java)
            }

        }
    }

    private fun seeView(clazz: Class<*>) {
        val intent = Intent(this, ViewActivity::class.java)
        intent.putExtra("clazz",clazz.canonicalName)
        startActivity(intent)
    }

}