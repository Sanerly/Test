package sunset.test

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Created by sunset on 2018/2/2.
 */
class PathMagicCircle :View{

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
        centerX=w/2f
        centerY=h/2f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.translate(centerX,centerY)



    }


    class DataPoint(var x: Float,var y:Float) {

    }

    class ControlPoint{

    }
}