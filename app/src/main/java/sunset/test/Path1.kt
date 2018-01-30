package sunset.test

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.nfc.Tag
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * Created by sunset on 2018/1/24.
 */
class Path1 : View {


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val TAG: String = "Path1"
    private var i = 0
    private var mPaint: Paint = Paint()
    private var mWidth: Int = 0
    private var mHeight: Int = 0

    init {
        mPaint.color = Color.BLACK
        mPaint.strokeWidth = 10f
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
    }

    private fun initCanvas(canvas: Canvas) {
        canvas.translate(mWidth / 2f, mHeight / 2f)
        mPaint.color = Color.BLUE
        mPaint.strokeWidth = 1f
        canvas.drawLine(-mWidth / 2f, 0f, mWidth / 2.toFloat(), 0f, mPaint)
        canvas.drawLine(0f, -mHeight / 2f, 0f, mHeight / 2.toFloat(), mPaint)
        mPaint.color = Color.BLACK
        mPaint.strokeWidth = 10f
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        initCanvas(canvas)

        val path = Path()
        val src = Path()
        /*path.lineTo(200f,200f)*/

        //移动上一条线的终点，会影响上一条线的显示位置
        /*path.setLastPoint(200f,100f)*/

        //移动上一条线的终点，不影响之前的动作
        /*path.moveTo(200f,100f)*/

        /*path.lineTo(400f,0f)*/

        //闭合View，连接终点和起点
        /*path.close()*/


        // Path.Direction.CW  CCW 顺时针和逆时针
//        path.addRect(-200f,-200f,200f,200f,Path.Direction.CW)
////        path.setLastPoint(-300f,300f)
//
//        src.addCircle(0f,0f,Math.sqrt(2.0).toFloat()*200,Path.Direction.CCW)
////        path.setLastPoint(-300f,300f)
//
//        path.addPath(src,0f,-500f)
       var rf=RectF(-200f, -200f, 200f, 200f)
//

//        path.addArc(rf, 0f, 360f)
        path.arcTo(rf,10f,-359.99f,true)

        path.addRect(rf,Path.Direction.CW)

        path.offset(200f,0f)
        canvas.drawPath(path, mPaint)


    }


}