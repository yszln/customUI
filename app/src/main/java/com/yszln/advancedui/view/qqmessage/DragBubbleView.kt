package com.yszln.advancedui.view.qqmessage

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.yszln.advancedui.R
import java.util.ArrayList


class DragBubbleView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    /**
     * 气泡默认状态--静止
     */
    private val BUBBLE_STATE_DEFAULT = 0

    /**
     * 气泡相连
     */
    private val BUBBLE_STATE_CONNECT = 1

    /**
     * 气泡分离
     */
    private val BUBBLE_STATE_APART = 2

    /**
     * 气泡消失
     */
    private val BUBBLE_STATE_DISMISS = 3

    /**
     * 气泡半径
     */
    private val mBubbleRadius = 0f

    /**
     * 气泡颜色
     */
    private val mBubbleColor = 0

    /**
     * 气泡消息文字
     */
    private val mTextStr: String? = null

    /**
     * 气泡消息文字颜色
     */
    private val mTextColor = 0

    /**
     * 气泡消息文字大小
     */
    private val mTextSize = 0f

    /**
     * 不动气泡的半径
     */
    private var mBubFixedRadius = 0f

    /**
     * 可动气泡的半径
     */
    private var mBubMovableRadius = 0f

    /**
     * 不动气泡的圆心
     */
    private var mBubFixedCenter: PointF = PointF()

    /**
     * 可动气泡的圆心
     */
    private var mBubMovableCenter: PointF = PointF()

    /**
     * 气泡的画笔
     */
    private var mBubblePaint: Paint

    /**
     * 贝塞尔曲线path
     */
    private var mBezierPath: Path

    private var mTextPaint: Paint

    //文本绘制区域
    private var mTextRect: Rect

    private var mBurstPaint: Paint

    //爆炸绘制区域
    private var mBurstRect: Rect

    /**
     * 气泡状态标志
     */
    private val mBubbleState = BUBBLE_STATE_DEFAULT

    /**
     * 两气泡圆心距离
     */
    private val mDist = 0f

    /**
     * 气泡相连状态最大圆心距离
     */
    private var mMaxDist = 0f

    /**
     * 手指触摸偏移量
     */
    private var MOVE_OFFSET = 0f

    /**
     * 气泡爆炸的bitmap数组
     */
    private var mBurstBitmapsArray=ArrayList<Bitmap>()

    /**
     * 是否在执行气泡爆炸动画
     */
    private val mIsBurstAnimStart = false

    /**
     * 当前气泡爆炸图片index
     */
    private val mCurDrawableIndex = 0

    /**
     * 气泡爆炸的图片id数组
     */
    private val mBurstDrawablesArray = intArrayOf(
        R.mipmap.burst_1,
        R.mipmap.burst_2,
        R.mipmap.burst_3,
        R.mipmap.burst_4,
        R.mipmap.burst_5
    )

    init {

        //两个圆半径大小一致
        mBubFixedRadius = mBubbleRadius
        mBubMovableRadius = mBubFixedRadius
        mMaxDist = 8 * mBubbleRadius

        MOVE_OFFSET = mMaxDist / 4

        //抗锯齿

        //抗锯齿
        mBubblePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mBubblePaint.color = mBubbleColor
        mBubblePaint.style = Paint.Style.FILL
        mBezierPath = Path()


        //文本画笔
        mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mTextPaint.color = mTextColor
        mTextPaint.textSize = mTextSize
        mTextRect = Rect()


        //爆炸画笔
        mBurstPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mBurstPaint.isFilterBitmap = true
        mBurstRect = Rect()

        for (i in mBurstDrawablesArray.indices) {
            //将气泡爆炸的drawable转为bitmap
            val bitmap =
                BitmapFactory.decodeResource(resources, mBurstDrawablesArray[i])
            mBurstBitmapsArray[i] = bitmap
        }
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //1.禁止状态，小球和消息数

        //2.连接状态，小球加消息数，贝塞尔曲线，小球原来的位置，大小可变化

        //3.分离状态，小球加消息数

        //4.消失状态，爆炸效果
    }

}