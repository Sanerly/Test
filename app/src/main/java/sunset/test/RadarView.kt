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
 * Created by sunset on 2018/1/24.
 */
class RadarView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val TAG: String = "RadarView"

    private var mPaint: Paint
    private val textPaint: Paint

    //网格最大半径
    private var radius: Float = 0f

    private val count = 11

    private var centerX: Float = 0f
    private var centerY: Float = 0f
    //一周对应的角度为360度(角度)，对应的弧度为2π弧度。计算弧度
    private var radian: Double

    private val magin = 4

    private var space: Float = 0f

    init {
        radian = (Math.PI * 2 / count)
        mPaint = Paint()
        mPaint.color = Color.BLACK
        mPaint.strokeWidth = 5f
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.STROKE

        textPaint = Paint()
        textPaint.color = Color.BLACK
        textPaint.strokeWidth = 1f
        textPaint.isAntiAlias = true
        textPaint.style = Paint.Style.FILL
        textPaint.textSize = 50f             // 设置字体大小
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2f
        centerY = h / 2f
        radius = Math.min(centerX, centerY) * 0.9f
        space = radius / (count - 1)
    }


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate(centerX, centerY)

        setDrawCobweb(canvas)
        setDrawRays(canvas)
        setDrawText(canvas)
        setDrawArea(canvas)
    }

    /**
     * 画区域
     */
    private fun setDrawArea(canvas: Canvas) {
        val areaPaint = Paint()
        areaPaint.strokeWidth = 10f
        areaPaint.isAntiAlias = true
        areaPaint.style = Paint.Style.FILL

        val areaPath = Path()
        for (i in 0 until count) {
            val random: Int = (6 + Math.random() * (count - 6)).toInt()
            val cur = space * random
            val x = (cur * Math.cos(radian * i)).toFloat()
            val y = (cur * Math.sin(radian * i)).toFloat()
            if (i == 0) {
                areaPath.moveTo(x, y)
            } else {
                areaPath.lineTo(x, y)
            }
            areaPaint.color = Color.DKGRAY
            canvas.drawCircle(x, y, 15f, areaPaint)
        }
        areaPaint.color = Color.BLUE
        areaPaint.alpha = 120
        areaPath.close()
        canvas.drawPath(areaPath, areaPaint)
    }

    /**
     * 画文字
     */
    private fun setDrawText(canvas: Canvas) {
        for (i in 0 until count) {
            val textHeight = textPaint.measureText(i.toString())
            val x = ((radius) * Math.cos(radian * i)).toFloat()
            val y = ((radius) * Math.sin(radian * i)).toFloat()
            if (x >= 0 && y >= 0) {
                if (radian * i < (Math.PI * 2) / 8) {
                    canvas.drawText(i.toString(), x + magin * 3, y + textHeight / 2, textPaint)
                } else {
                    canvas.drawText(i.toString(), x - textHeight / 2, y + textHeight * 2, textPaint)
                }
            } else if (x < 0 && y > 0) {
                if (radian * i < (Math.PI * 2) / 8 * 3) {
                    canvas.drawText(i.toString(), x - textHeight, y + textHeight * 2, textPaint)
                } else {
                    canvas.drawText(i.toString(), x - textHeight - magin * 3, y + textHeight / 2, textPaint)
                }
            } else if (x < 0 && y < 0) {
                if (radian * i < (Math.PI * 2) / 8 * 5) {
                    canvas.drawText(i.toString(), x - textHeight, y, textPaint)
                } else {
                    canvas.drawText(i.toString(), x - textHeight / 2, y - textHeight / 2, textPaint)
                }
            } else if (x > 0 && y < 0) {
                canvas.drawText(i.toString(), x, y, textPaint)
            }
        }
    }

    /**
     * 画射线
     */
    private fun setDrawRays(canvas: Canvas) {
        val linePath = Path()
        for (i in 0 until count) {
            linePath.moveTo(0f, 0f)
            val x = (radius * Math.cos(radian * i))
            val y = (radius * Math.sin(radian * i))
            linePath.lineTo(x.toFloat(), y.toFloat())
        }
        canvas.drawPath(linePath, mPaint)
    }

    /**
     * 画蜘蛛网
     */
    private fun setDrawCobweb(canvas: Canvas) {
        val cobwebPath = Path()
        for (i in 1 until count) {
            val cur = space * i
            cobwebPath.reset()
            for (j in 0 until count) {
                val x = (cur * Math.cos(radian * j))
                val y = (cur * Math.sin(radian * j))
                if (j == 0) {
                    cobwebPath.moveTo(x.toFloat(), y.toFloat())
                } else {
                    cobwebPath.lineTo(x.toFloat(), y.toFloat())
                }
            }
            cobwebPath.close()
            canvas.drawPath(cobwebPath, mPaint)
        }
    }


}


