package com.yszln.advancedui.givelike

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yszln.advancedui.R
import kotlinx.android.synthetic.main.activity_give_like.*

class GiveLikeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_give_like)
        likeBtn.setOnClickListener {
            likeView.start()
        }

    }
}