package com.yszln.advancedui.paint

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.yszln.advancedui.R


class GradientLayout: View {



    var mPaint: Paint = Paint()
    private lateinit var mBitmap: Bitmap;
    private lateinit var mShader: Shader

    constructor(context: Context?) : this(context,null)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs,0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        mBitmap=BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_foreground)
        mShader=BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        mPaint.shader = mShader;
    }

    init {
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.FILL

        /**
         * 1.线性渲染, LinearGradient(float x日, fLoat ye, float X1, float y1, @NonNull @ColorInt int colors[], @Nullable float positions[],
         * (xe,y):渐变起始点坐标
         * (x1,y1): 渐变结束点坐标
        colore:渐变开始点颜色16进制的颜色表示，必须要带有透明度
        color1:渐变结束颜色
        colors:渐变数组
        positions:位置数组，position 的取值范围[e, 1],作用是指定某个位置的颜色值，如果传null， 渐变就线性变化。
        tile:用于指定控件区域大于指定的渐变区域时，空白区域的颜色填充方法

         */

        /* mShader =
             LinearGradient(
                 0f,
                 0f,
                 500f,
                 500f,
                 //颜色集合
                 intArrayOf(Color.RED, Color.BLUE, Color.GREEN, Color.GRAY,Color.YELLOW),
                 //颜色比例
                 floatArrayOf(0f,0.25f,0.48f,0.8f,1f),
                 Shader.TileMode.CLAMP
             );*/
        /*   mShader =
               RadialGradient(
                   250f,
                   250f,
                   250f,
                   //颜色集合
                   intArrayOf(Color.RED, Color.BLUE, Color.GREEN, Color.GRAY,Color.YELLOW),
                   //颜色比例
                   floatArrayOf(0f,0.25f,0.48f,0.8f,1f),
                   Shader.TileMode.CLAMP
               );*/
        /*mShader =
            SweepGradient(
                250f,
                250f,
                Color.RED,
                Color.GREEN
            );*/


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        canvas.drawCircle(250f, 250f, 250f, mPaint);
        canvas.drawRect(0f, 0f, 500f, 500f, mPaint)
        setLayerType(LAYER_TYPE_NONE,mPaint)

    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        //这里回收资源
    }

}