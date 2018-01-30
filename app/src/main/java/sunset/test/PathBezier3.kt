package sunset.test

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class PathBezier3 : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var TAG: String = javaClass.simpleName
    private var mPaint: Paint = Paint()

    private var centerX: Int = 0
    private var centerY: Int = 0

    private var start: PointF
    private var end: PointF

    private var control_1: PointF
    private var control_2: PointF

    var isMode:Boolean=false

    init {
        mPaint.color = Color.RED
        mPaint.strokeWidth = 10f
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.STROKE

        start = PointF()
        end = PointF()
        control_1 = PointF()
        control_2=PointF()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2
        centerY = h / 2

        start.x = centerX - 200f
        start.y = centerY.toFloat()

        end.x = centerX + 200f
        end.y = centerY.toFloat()

        control_1.x = centerX+ 200f
        control_1.y = centerY + 200f

        control_2.x = centerX- 200f
        control_2.y = centerY + 200f

    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (isMode){
            control_1.x = event.x
            control_1.y = event.y
        }else{
            control_2.x = event.x
            control_2.y = event.y
        }

        invalidate()
        return true
    }


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 绘制三个点
        mPaint.color = Color.GRAY
        mPaint.strokeWidth = 20f
        canvas.drawPoint(start.x, start.y, mPaint)
        canvas.drawPoint(end.x, end.y, mPaint)
        canvas.drawPoint(control_1.x, control_1.y, mPaint)
        canvas.drawPoint(control_2.x, control_2.y, mPaint)
        // 绘制辅助线
        mPaint.strokeWidth = 4f
        canvas.drawLine(start.x, start.y, control_2.x, control_2.y, mPaint)
        canvas.drawLine(end.x, end.y, control_1.x, control_1.y, mPaint)
        canvas.drawLine(control_1.x, control_1.y, control_2.x, control_2.y, mPaint)

        // 绘制贝塞尔曲线
        mPaint.color = Color.RED
        mPaint.strokeWidth = 8f
        var path = Path()

        path.moveTo(start.x, start.y)

//        path.quadTo(control_1.x, control_1.y, end.x, end.y)

        path.cubicTo(control_2.x, control_2.y,control_1.x, control_1.y, end.x, end.y)

        canvas.drawPath(path, mPaint)
    }
}