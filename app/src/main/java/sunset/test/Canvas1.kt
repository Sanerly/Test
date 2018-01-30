package sunset.test

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.View


class Canvas1(context: Context?) : View(context) {

    private var mTag: String = "Canvas1"
    private var mPaint: Paint = Paint()
    private var mWidth: Int = 0
    private var mHeight: Int = 0

    init {
        mPaint.color = Color.RED
        mPaint.strokeWidth = 10f
        mPaint.style = Paint.Style.FILL
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var cx: Int = mWidth / 2
        var cy: Int = mHeight / 2
        canvas!!.translate(cx.toFloat(), cy.toFloat())
        var rect = Rect(100, 100, 800, 400)

        var r: Float = ((Math.min(cx, cy)) * 0.6).toFloat()

        Log.d(mTag, "半径 = $r")

        val rectf = RectF(-r, -r, r, r)

        mPaint.color = Color.GRAY

        canvas!!.drawRect(rectf, mPaint)

//        canvas!!.drawRoundRect(rectf, 30f, 30f, mPaint)
//        canvas!!.drawOval(rectf, mPaint)

        /*startAngle, 开始角度，从12点钟顺时针算起
        sweepAngle, 从开始的角度算起，默认开始角度为零
        useCenter 为true时表示缺口处的起点和终点连接到圆心处 ， false时表示起点和终点连接成一条线 。*/

//      canvas!!.drawCircle(cx.toFloat(), cy.toFloat(), 400f, mPaint)
        mPaint.color = Color.RED
        canvas!!.drawArc(rectf, 0f, 120f, true, mPaint)
    }
}