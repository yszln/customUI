package com.yszln.advancedui.ordershaft

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.TextView

/**
 * 订单进度
 */
class OrderShaftView : TextView {

    private var mList = ArrayList<OrderShaftBean>()

    private var mStartFontWidth = 0f
    private var mEndFontWidth = 0f

    /**
     * 选中的画笔
     */
    private var mSelectPaint = Paint()
    private var mSelectPaint2 = Paint()
    /**
     * 普通文字的画笔
     */
    private var mPaint = Paint()

    private var mLinePaint = Paint()

    /**
     * 选中的圆的半径
     */
    private var mSelectWidth = 20f
    private var mSelectWidth2 = 8f
    /**
     * 未选中圆的半径
     */
    private var mUnSelectWidth = 10f

    /**
     * 直线的高度
     */
    private var mLineHeight = 4f

    private var mMeasuredWidth = 0

    private var mTextPaddingTop = 40f

    //往后每个item的中心点距离
    private var mItemWidth = 0
    //开始画圆的位置
    private var mStartWidth = 0f


    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {

        mSelectPaint.textSize = textSize
        mSelectPaint.isAntiAlias = true
        mSelectPaint.typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
        mSelectPaint.color = Color.RED

        mSelectPaint2.textSize = textSize
        mSelectPaint2.isAntiAlias = true
        mSelectPaint2.typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
        mSelectPaint2.color = Color.WHITE

        mPaint.textSize = textSize
        mPaint.isAntiAlias = true
        mPaint.color = Color.BLACK

        mLinePaint.isAntiAlias = true
        mLinePaint.color = Color.BLUE
        mLinePaint.strokeWidth = mLineHeight
    }


    fun initData(list: List<OrderShaftBean>) {
        mList.clear()
        mList.addAll(list)
        invalidate()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mMeasuredWidth = measuredWidth



        if (mList.size > 2) {
            //测量第一个文字的长度
            mStartFontWidth =
                if (mList[0].isSelect) mSelectPaint.measureText(mList[0].title) else mPaint.measureText(
                    mList[0].title
                )
            //测量最后一个文字的长度
            mEndFontWidth =
                if (mList[mList.size - 1].isSelect) mSelectPaint.measureText(mList[mList.size - 1].title) else mPaint.measureText(
                    mList[mList.size - 1].title
                )
        }

        mStartWidth = mStartFontWidth / 2
        mItemWidth =
            ((mMeasuredWidth - mStartFontWidth / 2 - mEndFontWidth / 2) / (mList.size - 1)).toInt()

        setMeasuredDimension(measuredWidth, (mSelectWidth * 2 + mTextPaddingTop + 10).toInt())
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawLine(
            mStartWidth,
            mSelectWidth,
            measuredWidth - mEndFontWidth / 2,
            mSelectWidth,
            mLinePaint
        )

        for (i in mList.indices) {
            drawText(canvas, i, mList[i].isSelect, mList[i].title)
            if (mList[i].isSelect) {
                drawSelectCircle(canvas, i)
            } else {
                drawUnSelectCircle(canvas, i)
            }
        }

    }

    private fun drawText(canvas: Canvas, position: Int, isSelect: Boolean, text: String) {
        val textWidth = getTextWidth(text, isSelect)
        canvas.drawText(
            text,
            mStartWidth + position * mItemWidth - textWidth / 2,
            mSelectWidth * 2 + mTextPaddingTop,
            if (isSelect) mSelectPaint else mPaint
        )
    }

    /**
     * 测量文字宽度
     */
    private fun getTextWidth(text: String, select: Boolean): Float {
        return if (select) {
            mSelectPaint.measureText(text)
        } else {
            mPaint.measureText(text)
        }
    }


    /**
     * 画未选中的圆
     */
    private fun drawUnSelectCircle(canvas: Canvas, position: Int) {
        canvas.drawCircle(
            mStartWidth + position * mItemWidth,
            mSelectWidth,
            mUnSelectWidth,
            mPaint
        )

    }

    /**
     * 画选中的圆
     */
    private fun drawSelectCircle(canvas: Canvas, position: Int) {
        canvas.drawCircle(
            mStartWidth + position * mItemWidth,
            mSelectWidth,
            mSelectWidth,
            mSelectPaint
        )

        canvas.drawCircle(
            mStartWidth + position * mItemWidth,
            mSelectWidth2+ mSelectWidth / 2,
            mSelectWidth2,
            mSelectPaint2
        )


    }


}