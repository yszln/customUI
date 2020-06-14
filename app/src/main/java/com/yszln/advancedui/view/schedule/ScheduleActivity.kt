package com.yszln.advancedui.view.schedule

import com.yszln.advancedui.R
import com.yszln.advancedui.main.BaseActivity
import kotlinx.android.synthetic.main.act_order_shaft.*

/**
 * @fileName: OrderShaftActivity
 * @author: huwei
 * @date: 2020/6/11 17:28
 * @description:
 * @history:
 */
class ScheduleActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.act_order_shaft
    }

    override fun initView() {
        val arrayList = ArrayList<ScheduleItem>()
        arrayList.add(ScheduleItem("进度1", true))
        arrayList.add(ScheduleItem("进度2", true))
        arrayList.add(ScheduleItem("进度3", false))
        arrayList.add(ScheduleItem("进度4", false))
        scheduleView.init(arrayList)
    }
}