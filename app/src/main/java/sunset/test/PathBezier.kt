package sunset.test

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class PathBezier : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var mTag: String = "PathBezier"
    private var mPaint: Paint = Paint()

    private var centerX: Int = 0
    private var centerY: Int = 0
    private var start: PointF
    private var end: PointF
    private var control: PointF

    init {
        mPaint.color = Color.RED
        mPaint.strokeWidth = 10f
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.STROKE

        start = PointF()
        end = PointF()
        control = PointF()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2
        centerY = h / 2

        start.x = centerX - 200f
        start.y = centerY.toFloat()
        end.x = centerX + 200f
        end.y = centerY.toFloat()

        control.x = centerX.toFloat()
        control.y = centerY + 200f


    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        control.x = event.x
        control.y = event.y
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
        canvas.drawPoint(control.x, control.y, mPaint)

        // 绘制辅助线
        mPaint.strokeWidth = 4f
        canvas.drawLine(start.x, start.y, control.x, control.y, mPaint)
        canvas.drawLine(end.x, end.y, control.x, control.y, mPaint)

        // 绘制贝塞尔曲线
        mPaint.color = Color.RED
        mPaint.strokeWidth = 8f
        var path = Path()

        path.moveTo(start.x, start.y)

        path.quadTo(control.x, control.y, end.x, end.y)

        canvas.drawPath(path, mPaint)
    }
}