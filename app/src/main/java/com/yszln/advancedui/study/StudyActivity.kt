package com.yszln.advancedui.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yszln.advancedui.R

class StudyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(BezierView(this))
    }
}