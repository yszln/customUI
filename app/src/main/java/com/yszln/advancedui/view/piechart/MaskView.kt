package com.yszln.advancedui.view.piechart

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.yszln.advancedui.R
import com.yszln.advancedui.main.DisplayUtil

class MaskView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var porterDuffXfermode: PorterDuffXfermode
    val WIDTH = DisplayUtil.dip2px(200f)

    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val saveArea = RectF()

    init {
        porterDuffXfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        mPaint.color=Color.YELLOW
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        saveArea.set(40f, 10f, 280f, 600f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //离屏缓冲
        canvas.drawOval(40f-10f, 0f, 280f+10f, 600f+10f, mPaint)
        val saved = canvas.saveLayer(saveArea, mPaint)
        canvas.drawOval(40f, 10f, 280f, 600f, mPaint)
        //往圆里面镶嵌bitmap
        mPaint.xfermode = porterDuffXfermode
        canvas.drawBitmap(getAvatar(WIDTH), 40f, 0f, mPaint)
        mPaint.xfermode = null
        //恢复离屏缓冲
        canvas.restoreToCount(saved)

    }

    private fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        //只会取到图片的宽高信息
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, R.mipmap.content, options);
        options.inJustDecodeBounds = false;
        //设置取出来的图片宽高比
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(resources, R.mipmap.content, options);

    }
}