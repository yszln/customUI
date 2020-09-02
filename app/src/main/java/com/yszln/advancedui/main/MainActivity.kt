package com.yszln.advancedui.main

import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import com.yszln.advancedui.R
import com.yszln.advancedui.study.StudyActivity
import com.yszln.advancedui.view.dashboard.DashboardViewActivity
import com.yszln.advancedui.view.particleball.BallViewActivity
import com.yszln.advancedui.view.givelike.GiveLikeActivity
import com.yszln.advancedui.view.piechart.MaskView
import com.yszln.advancedui.view.piechart.PiechartActivity
import com.yszln.advancedui.view.progress.SprotsProgressView
import com.yszln.advancedui.view.schedule.ScheduleActivity
import com.yszln.advancedui.view.radarscanning.WXRadarScanningActivity
import com.yszln.advancedui.view.special.DoraemonView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var mMainAdapter = MainAdapter()


    override fun getLayoutId(): Int {
        isAddTitle=false
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
        mMainAdapter.addData(MainItemBean(6, "椭圆遮罩图片", R.mipmap.circle))
        mMainAdapter.addData(MainItemBean(7, "环形进度条", R.mipmap.circle))
        mMainAdapter.addData(MainItemBean(8, "Doraemon", R.mipmap.circle))
        mMainAdapter.addData(MainItemBean(666, "test", R.mipmap.circle))
    }

    private fun clickEvent(position: Int, item: MainItemBean, holder: MainAdapter.VH) {
        when (item.type) {

            0 -> {
                //雷达
                gotoActivity(WXRadarScanningActivity::class.java)
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
                seeView(MaskView::class.java,item.name)
            }
            7 -> {
                seeView(SprotsProgressView::class.java, item.name)
            }
            8 -> {
                seeView(DoraemonView::class.java, item.name)
            }
            666 -> {
                gotoActivity(StudyActivity::class.java)
            }

        }
    }

    private fun seeView(clazz: Class<*>, name: String) {
        val intent = Intent(this, ViewActivity::class.java)
        intent.putExtra("clazz",clazz.canonicalName)
        intent.putExtra("title",name)
        startActivity(intent)
    }

}