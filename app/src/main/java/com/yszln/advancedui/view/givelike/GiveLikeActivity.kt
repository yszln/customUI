package com.yszln.advancedui.view.givelike

import android.view.animation.Interpolator
import com.yszln.advancedui.R
import com.yszln.advancedui.main.BaseActivity
import kotlinx.android.synthetic.main.activity_give_like.*

class GiveLikeActivity : BaseActivity() {
    private var mHeartList = ArrayList<Int>()

    init {
        mHeartList.add(R.mipmap.heart1)
        mHeartList.add(R.mipmap.heart2)
        mHeartList.add(R.mipmap.heart3)
        mHeartList.add(R.mipmap.heart4)
        mHeartList.add(R.mipmap.heart5)
        mHeartList.add(R.mipmap.heart6)
        mHeartList.add(R.mipmap.heart7)
    }
    var position=0
    override fun getLayoutId()=R.layout.activity_give_like
    override fun initView() {
        likeBtn.setOnClickListener {
            likeView.start()


            likeBtn.setImageResource(mHeartList[ position])
            position++
            if(position>6){
                position=0
            }
        }
    }
}