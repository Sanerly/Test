package sunset.test

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.view.View

@SuppressLint("ViewConstructor")

class PerfectArcView : View {


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    private val mPaint: Paint = Paint()
    private var mHeight: Int = 0
    private var mWidth: Int = 0
    private var mRadius: Int = 0
    private var mCx: Int = 0
    private var mCy: Int = 0
    private var mRectF: RectF = RectF()
    private var mBitmap: Bitmap
    private lateinit var mLinearGradient: LinearGradient

    @ColorInt
    private var startColor: Int = Color.YELLOW
    @ColorInt
    private var endColor: Int = Color.LTGRAY

    init {
        mPaint.color = Color.BLUE
        mPaint.strokeWidth = 10f
        mPaint.style = Paint.Style.FILL
        mPaint.isAntiAlias = true
        mBitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.icon_photo)
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h

        mRadius = mWidth

        mCx = w / 2
        mCy =0

        mRectF.top = 0f
        mRectF.left = 0f
        mRectF.bottom = mHeight.toFloat()
        mRectF.right = mWidth.toFloat()

        mLinearGradient = LinearGradient(mWidth / 2f, 0f, mWidth / 2f, mHeight.toFloat(), startColor, endColor, Shader.TileMode.MIRROR)

    }

    fun setColor(@ColorInt start: Int, @ColorInt end: Int) {
        this.startColor=start
        this.endColor=end
        invalidate()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var layerId: Int = canvas!!.saveLayer(0f,0f,canvas.width.toFloat(),canvas.height.toFloat(),null, Canvas.ALL_SAVE_FLAG)
        canvas!!.drawCircle(mCx.toFloat(), mCy.toFloat(), mRadius.toFloat(), mPaint)
        mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        mPaint.shader = mLinearGradient
        canvas!!.drawRect(mRectF, mPaint)
//      canvas.drawBitmap(mBitmap, null, mRectF, mPaint)
        mPaint.xfermode = null
        canvas.restoreToCount(layerId)

    }
}