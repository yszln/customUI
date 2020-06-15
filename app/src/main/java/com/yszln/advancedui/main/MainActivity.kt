package com.yszln.advancedui.main

import androidx.recyclerview.widget.GridLayoutManager
import com.yszln.advancedui.R
import com.yszln.advancedui.study.StudyActivity
import com.yszln.advancedui.view.particleball.BallViewActivity
import com.yszln.advancedui.view.givelike.GiveLikeActivity
import com.yszln.advancedui.view.schedule.ScheduleActivity
import com.yszln.advancedui.view.radarscanning.WXRadarScanningActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var mMainAdapter = MainAdapter()


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        recyclerView.layoutManager = GridLayoutManager(this, 4)
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
        mMainAdapter.addData(MainItemBean(4, "学习", R.mipmap.circle))
    }

    private fun clickEvent(position: Int, item: MainItemBean, holder: MainAdapter.VH) {
        when (position) {

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
                gotoActivity(StudyActivity::class.java)
            }

        }
    }

}