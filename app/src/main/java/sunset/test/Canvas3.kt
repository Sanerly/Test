package sunset.test

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Created by sunset on 2018/1/15.
 */
class Canvas3 :View{

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var mTag: String = "Canvas3"
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


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.translate(mWidth/2f,mHeight/2f)
       var bitmap:Bitmap=BitmapFactory.decodeResource(context.resources,R.mipmap.icon_photo)

//        canvas.drawBitmap(bitmap, 200f,500f, Paint())

        //显示图片上的一块区域，当大于图片大小的时候，显示整个图片
        val  rectSrc:Rect =Rect(0,0,150,300)
        //图片展示的大小，缩放图片显示的区域
        val  rectDts:Rect =Rect(0,0,200,200)

        canvas.drawBitmap(bitmap,rectSrc,rectDts,mPaint)
    }
}