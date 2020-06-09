package com.yszln.advancedui.givelike.huashu

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/** Copyright (C), 汀沐科技
 * @fileName: GiveLikeHeartView
 * @author: huwei
 * @date: 2020/6/9 16:22
 * @description: 点赞效果
 * @history:
 */
class HuaShuGiveLikeButton : View {
    constructor(context: Context) : this(context,null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if(event.action==MotionEvent.ACTION_DOWN){

        }
        return super.onTouchEvent(event)
    }
}