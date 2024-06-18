package com.example.gitsample.canvas.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.resource.R
import com.example.utils.UIUtils

class CanvasImageShadow : View {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.mipmap.img_test)

    private val matrix: Matrix = Matrix()
    private val paint: Paint = Paint()
    private val rect: Rect = Rect()

    private var linearGradient: LinearGradient? = null
    private var topShadow: LinearGradient? = null
    private var drawHeight: Int = 0
    private var drawWidth: Int = 0
    private var pixel: Int = 0

    init {
        pixel = bitmap.getPixel(bitmap.width / 2, bitmap.height - 1)

        drawWidth = UIUtils.screenWidth()
        val divide = drawWidth * 1.0f / bitmap.width
        matrix.postScale(divide, divide)
        drawHeight = (bitmap.height * divide).toInt()

        linearGradient = LinearGradient(
            0.0f,
            drawHeight * 1.0f / 3,
            0.0f,
            drawHeight * 2.0f / 3,
            0,
            pixel,
            Shader.TileMode.CLAMP
        )

        val colorStart = ContextCompat.getColor(context, R.color.color_33212121)
        val colorEnd = ContextCompat.getColor(context, R.color.color_FF1D1D1D)
        topShadow = LinearGradient(
            0.0f, 0.0f, 0.0f, drawHeight * 1.0f, colorStart, colorEnd, Shader.TileMode.CLAMP
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawBitmap(bitmap, matrix, null)

        paint.setColor(pixel)
        rect.left = 0
        rect.top = drawHeight / 3 * 2
        rect.right = drawWidth
        rect.bottom = drawHeight
        canvas.drawRect(rect, paint)

        paint.setShader(linearGradient)
        rect.left = 0
        rect.top = drawHeight / 3
        rect.right = drawWidth
        rect.bottom = drawHeight * 2 / 3
        canvas.drawRect(rect, paint)

        paint.setShader(topShadow)
        rect.left = 0
        rect.top = 0
        rect.right = drawWidth
        rect.bottom = drawHeight
        canvas.drawRect(rect, paint)

        bitmap.recycle()
    }
}