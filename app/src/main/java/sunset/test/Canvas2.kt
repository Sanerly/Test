package sunset.test

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View


class Canvas2 : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var mTag: String = "Canvas2"
    private var mPaint: Paint = Paint()
    private var mWidth: Int = 0
    private var mHeight: Int = 0

    init {
        mPaint.color = Color.RED
        mPaint.strokeWidth = 10f
        mPaint.isAntiAlias=true
        mPaint.style = Paint.Style.STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
    }


    @SuppressLint("DrawAllocation", "NewApi")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//// 在坐标原点绘制一个黑色圆形
//        mPaint.color=Color.BLACK
//
//        canvas.translate(200f,200f);
//        canvas.drawCircle(0f,0f,100f,mPaint);
//
//// 在坐标原点绘制一个蓝色圆形
//        mPaint.color=Color.BLUE
//        canvas.translate(200f,200f);
//        canvas.drawCircle(0f,0f,100f,mPaint);


        // 将坐标系原点移动到画布正中心
        canvas.translate(mWidth / 2f, mHeight / 2f)

//        val rect = RectF(0f, -400f, 400f, 0f)   // 矩形区域

//        mPaint.color = Color.BLACK           // 绘制黑色矩形
//        canvas.drawRect(rect, mPaint)


//        canvas.scale(-0.5f, -0.5f,200f,0f) // 画布缩放

//        canvas.rotate(270f,200f,0f) //旋转
//
//        mPaint.color = Color.BLUE            // 绘制蓝色矩形
//        canvas.drawRect(rect, mPaint)


//        val rect = RectF(-400f, -400f, 400f, 400f)   // 矩形区域
//
//        for (i in 0..20) {
//            canvas.scale(0.9f, 0.9f)
////            canvas.drawRoundRect(rect,10f,10f,mPaint)
//            canvas.drawOval(rect,mPaint)
//        }


        // 将坐标系原点移动到画布正中心
//        canvas.translate(mWidth / 2f, mHeight / 2f)

//        canvas.drawCircle(0f, 0f, 400f, mPaint)          // 绘制两个圆形
//        canvas.drawCircle(0f, 0f, 380f, mPaint)
//
//        var i = 0
//        while (i <= 360) {               // 绘制圆形之间的连接线
//            canvas.drawLine(0f, 380f, 0f, 400f, mPaint)
//            canvas.rotate(10f)
//            i += 10
//        }

        val rect = RectF(0f, 0f, 200f, 200f)   // 矩形区域
        canvas.saveLayer(rect,mPaint)
        mPaint.color = Color.BLACK           // 绘制黑色矩形
        canvas.drawRect(rect, mPaint)
        canvas.skew(-0.5f, -1f)                       // 水平错切 <- 45度

        mPaint.color = Color.BLUE            // 绘制蓝色矩形
        canvas.drawRect(rect, mPaint)
        canvas.restore()


    }
}