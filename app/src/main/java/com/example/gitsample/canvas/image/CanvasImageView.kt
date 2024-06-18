package com.example.gitsample.canvas.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.util.AttributeSet
import android.view.View
import com.example.resource.R

class CanvasImageView : View {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.mipmap.img_ball)

    private var matrix: Matrix = Matrix()
    private var paint: Paint = Paint()
    private var rectF: Rect = Rect()
    private var rectS: Rect = Rect()

    private var type: Int = 0

    init {
        type = 8
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (type == 0) {
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, null)
        }

        if (type == 1) {
            matrix.postScale(0.5f, 0.5f)
            canvas.drawBitmap(bitmap, matrix, null)
        }

        if (type == 2) {
            matrix.postTranslate(100.0f, 100.0f)
            canvas.drawBitmap(bitmap, matrix, null)
        }

        if (type == 3) {
            matrix.postRotate(90.0f, bitmap.getWidth() * 1.0f / 2, bitmap.getHeight() * 1.0f / 2)
            canvas.drawBitmap(bitmap, matrix, null)
        }

        if (type == 4) {
            matrix.postSkew(
                0.5f, 0.5f, bitmap.getWidth() * 1.0f / 2,
                bitmap.getHeight() * 1.0f / 2
            )
            canvas.drawBitmap(bitmap, matrix, null)
        }

        if (type == 5) {
            blurBitmap()
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, null)
        }

        if (type == 6) {
            blurBitmap()
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint)
        }

        if (type == 7) {
            blurBitmap()
            for (index in 0..255) {
                paint.setAlpha(index)
                rectF.left = 0
                rectS.left = 0
                rectF.top = index
                rectS.top = index
                rectF.bottom = index + 1
                rectS.bottom = index + 1
                rectF.right = bitmap.getWidth()
                rectS.right = bitmap.getWidth()
                canvas.drawBitmap(bitmap, rectF, rectS, paint)
            }
            paint.setAlpha(255)
            rectF.left = 0
            rectS.left = 0
            rectF.top = 255
            rectS.top = 255
            rectF.bottom = bitmap.getHeight()
            rectS.bottom = bitmap.getHeight()
            rectF.right = bitmap.getWidth()
            rectS.right = bitmap.getWidth()
            canvas.drawBitmap(bitmap, rectF, rectS, paint)
        }

        if (type == 8) {
            blurBitmap()
            colorBitmap()
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, null)
        }
    }

    // 高斯模糊
    private fun blurBitmap() {
        val width = Math.round(bitmap.width * 1.0f)
        val height = Math.round(bitmap.height * 1.0f)

        val inputBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false)
        val outputBitmap = Bitmap.createBitmap(inputBitmap)

        val script: RenderScript = RenderScript.create(context)

        val tmpIn = Allocation.createFromBitmap(script, inputBitmap)
        val tmpOut = Allocation.createFromBitmap(script, outputBitmap)

        val blur = ScriptIntrinsicBlur.create(script, Element.U8_4(script))

        blur.setRadius(0.7f * 25.0f)
        blur.setInput(tmpIn)
        blur.forEach(tmpOut)

        tmpOut.copyTo(outputBitmap)

        bitmap = outputBitmap
    }

    private fun colorBitmap() {

        val height = bitmap.height
        val width = bitmap.width

        val newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(newBitmap)

        canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);

        val paint = Paint()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            paint.setColor(0xffff0000)
        }

        for (index in 0 until height) {
            paint.setAlpha(((index * 1.0f / height) * 255).toInt())
            canvas.drawRect(0.0f, index * 1.0f, width * 1.0f, index + 1.0f, paint)
        }

        canvas.save()
        canvas.restore()

        bitmap = newBitmap
    }
}