package sunset.test

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * Created by sunset on 2018/1/30.
 */
class PathHeartBezier : View {

    private val C = 0.551915024494f     // 一个常量，用来计算绘制圆形贝塞尔曲线控制点的位置
    private var mCircleRadius: Float = 0f                  // 圆的半径
    private var mDifference: Float = 0f

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    private val TAG: String = javaClass.simpleName

    private var mPaint: Paint = Paint()

    private var centerX: Float = 0f
    private var centerY: Float = 0f


    private var mDataArray = FloatArray(8)
    private var mCtrlArray = FloatArray(16)

    init {
        mPaint.color = Color.RED
        mPaint.strokeWidth = 10f
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.FILL

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2f
        centerY = h / 2f

        mCircleRadius = Math.min(centerX, centerY) * 0.8f
        mDifference = mCircleRadius * C        // 圆形的控制点与数据点的差值


        mDataArray[0]=mCircleRadius
        mDataArray[1]=0f

        mDataArray[2]=0f
        mDataArray[3]=mCircleRadius

        mDataArray[4]=-mCircleRadius
        mDataArray[5]=0f

        mDataArray[6]=0f
        mDataArray[7]=-mCircleRadius



        mCtrlArray[0]=mDataArray[0]
        mCtrlArray[1]=mDataArray[1]+mDifference

        mCtrlArray[2]=mDataArray[2]+mDifference
        mCtrlArray[3]=mDataArray[3]

        mCtrlArray[4]=mDataArray[2]-mDifference
        mCtrlArray[5]=mDataArray[3]

        mCtrlArray[6]= mDataArray[4]
        mCtrlArray[7]= mDataArray[5]+mDifference

        mCtrlArray[8]=mDataArray[4]
        mCtrlArray[9]=mDataArray[5]-mDifference

        mCtrlArray[10]=mDataArray[6]-mDifference
        mCtrlArray[11]= mDataArray[7]

        mCtrlArray[12]=mDataArray[6]+mDifference
        mCtrlArray[13]=mDataArray[7]

        mCtrlArray[14]=mDataArray[0]
        mCtrlArray[15]=mDataArray[1]-mDifference


    }

    private val mHeatMagin=150
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.translate(centerX, centerY)


        val  path=Path()
        path.moveTo(mDataArray[0],mDataArray[1])

        path.cubicTo(mCtrlArray[0],mCtrlArray[1],mCtrlArray[2],mCtrlArray[3]+mHeatMagin,mDataArray[2],mDataArray[3])


        path.cubicTo(mCtrlArray[4],mCtrlArray[5],mCtrlArray[6],mCtrlArray[7],mDataArray[4],mDataArray[5])


        path.cubicTo(mCtrlArray[8],mCtrlArray[9],mCtrlArray[10],mCtrlArray[11],mDataArray[6],mDataArray[7]+mHeatMagin)

        path.cubicTo(mCtrlArray[12],mCtrlArray[13],mCtrlArray[14],mCtrlArray[15],mDataArray[0],mDataArray[1])

        canvas.drawPath(path,mPaint)


    }

}