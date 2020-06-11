package com.yszln.advancedui.view.ordershaft

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.yszln.advancedui.R
import com.yszln.advancedui.main.DisplayUtil

/**
 * 订单进度
 */
class ScheduleView : AppCompatTextView {

    /**
     * 需要绘制的数据
     */
    private var mList = ArrayList<ScheduleItem>()
    /**
     * 第一个文字绘制的大小
     */
    private var mStartFontWidth = 0f
    /**
     * 最后一个文字绘制的大小
     */
    private var mEndFontWidth = 0f

    /**
     * 选中的画笔
     */
    private var mSelectTextPaint = Paint()

    /**
     * 普通文字的画笔
     */
    private var mUnSelectTextPaint = Paint()


    /**
     * 选中圆的画笔
     */
    private var mSelectCirclePaint = Paint()
    /**
     * 未选中圆的画笔
     */
    private var mUnSelectCirclePaint = Paint()

    /**
     * 线条的画笔
     */
    private var mLinePaint = Paint()

    /**
     * 选中的圆的半径
     */
    private var mSelectWidth = DisplayUtil.dpToPx(10).toFloat()
    /**
     * 空心圆的边框大小，为大圆的2/3
     */
    private var mSelectBorder = mSelectWidth / 3 * 2
    /**
     * 未选中圆的半径
     */
    private var mUnSelectWidth = DisplayUtil.dpToPx(3).toFloat()

    /**
     * 直线的高度
     */
    private var mLineHeight = DisplayUtil.dpToPx(1)

    /**
     * 当前view绘制的大小
     */
    private var mMeasuredWidth = 0

    private var mPaddingTop = DisplayUtil.dpToPx(2)

    /**
     * 文字距离顶部的距离，至少是圆的直径
     */
    private var mTextPaddingTop = mSelectWidth * 2 + DisplayUtil.dpToPx(7)

    /**
     * 往后每个item的中心点距离
     */
    private var mItemWidth = 0
    /**
     * 开始画圆的位置
     */
    private var mStartWidth = 0f

    /**
     * 选中文字是否加粗
     */
    private var mSelectIsBold = false


    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
//        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ScheduleView)
//        mSelectIsBold = typedArray.getBoolean(R.styleable.ScheduleView_selectTextBold, false)
//        typedArray.recycle()
        initPaint()
    }

    /**
     * 初始化画笔
     */
    fun initPaint() {

        /*选中文字*/
        mSelectTextPaint.textSize = textSize
        mSelectTextPaint.isAntiAlias = true
        if (mSelectIsBold) {
            mSelectTextPaint.typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
        }
        mSelectTextPaint.color = context.resources.getColor(R.color.colorAccent)


        /*选中圆*/
        mSelectCirclePaint.textSize = textSize
        mSelectCirclePaint.isAntiAlias = true
        mSelectCirclePaint.color = context.resources.getColor(R.color.color_schedule_select)
        mSelectCirclePaint.strokeWidth = mSelectBorder
        mSelectCirclePaint.style = Paint.Style.STROKE

        /*未选中圆*/
        mUnSelectCirclePaint.isAntiAlias = true
        mUnSelectCirclePaint.color = context.resources.getColor(R.color.color_schedule_un_select)
        mUnSelectCirclePaint.typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)


        /*未选中文字*/
        mUnSelectTextPaint.textSize = textSize
        mUnSelectTextPaint.isAntiAlias = true
        mUnSelectTextPaint.color = context.resources.getColor(R.color.gray5)

        /*线条*/
        mLinePaint.isAntiAlias = true
        mLinePaint.color = context.resources.getColor(R.color.cut_off_color)
        mLinePaint.strokeWidth = mLineHeight.toFloat()
    }


    /**
     * 添加数据
     */
    fun init(list: List<ScheduleItem>) {
        mList.clear()
        mList.addAll(list)
        requestLayout()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mMeasuredWidth = measuredWidth



        if (mList.size > 2) {
            //测量第一个文字的长度
            mStartFontWidth =
                if (mList[0].isSelect) mSelectTextPaint.measureText(mList[0].title) else mUnSelectTextPaint.measureText(
                    mList[0].title
                )
            //测量最后一个文字的长度
            mEndFontWidth =
                if (mList[mList.size - 1].isSelect) mSelectTextPaint.measureText(mList[mList.size - 1].title) else mUnSelectTextPaint.measureText(
                    mList[mList.size - 1].title
                )
        }

        mStartWidth = mStartFontWidth / 2
        mItemWidth =
            ((mMeasuredWidth - mStartFontWidth / 2 - mEndFontWidth / 2) / (mList.size - 1)).toInt()


        //重新设置控件高度
        setMeasuredDimension(measuredWidth, (mSelectWidth * 2 + mTextPaddingTop + 10).toInt())
    }


    override fun onDraw(canvas: Canvas) {
        for (i in mList.indices) {
            //画文字
            drawText(canvas, i, mList[i].isSelect, mList[i].title)
            //画直线
            drawLine(canvas, i)
            //画圆
            drawCircle(canvas, i)

        }

    }

    /**
     * 画圆
     */
    private fun drawCircle(canvas: Canvas, i: Int) {
        if (mList[i].isSelect) {
            drawSelectCircle(canvas, i)
        } else {
            drawUnSelectCircle(canvas, i)
        }
    }

    /**
     * 画直线
     */
    private fun drawLine(canvas: Canvas, position: Int) {
        if (position + 1 < mList.size) {
            canvas.drawLine(
                mStartWidth + position * mItemWidth + getLineMargin(position),
                mSelectWidth + mPaddingTop,
                mStartWidth + (position + 1) * mItemWidth - getLineMargin(position + 1),
                mSelectWidth + mPaddingTop,
                mLinePaint
            )
        }


    }

    /**
     * 获取线条距离圆形的长度
     */
    private fun getLineMargin(position: Int): Int {
        return if (mList[position].isSelect) {
            (mSelectWidth / 2).toInt()
        } else {
            (mUnSelectWidth / 2).toInt()
        }

    }

    /**
     * 画文字
     */
    private fun drawText(canvas: Canvas, position: Int, isSelect: Boolean, text: String) {
        canvas.drawText(
            text,
            mStartWidth + position * mItemWidth - getTextWidth(text, isSelect) / 2,
            mSelectWidth * 2 + mTextPaddingTop + mPaddingTop,
            if (isSelect) mSelectTextPaint else mUnSelectTextPaint
        )
    }

    /**
     * 测量文字宽度
     */
    private fun getTextWidth(text: String, select: Boolean): Float {
        return if (select) {
            mSelectTextPaint.measureText(text)
        } else {
            mUnSelectTextPaint.measureText(text)
        }
    }


    /**
     * 画未选中的圆
     */
    private fun drawUnSelectCircle(canvas: Canvas, position: Int) {
        canvas.drawCircle(
            mStartWidth + position * mItemWidth,
            mSelectWidth + mPaddingTop,
            mUnSelectWidth,
            mUnSelectCirclePaint
        )

    }

    /**
     * 画选中的圆
     */
    private fun drawSelectCircle(canvas: Canvas, position: Int) {
        canvas.drawCircle(
            mStartWidth + position * mItemWidth,
            mSelectWidth + mPaddingTop,
            mSelectWidth - mSelectBorder / 3,
            mSelectCirclePaint
        )

    }


}