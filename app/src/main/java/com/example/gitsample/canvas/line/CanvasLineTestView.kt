package com.example.gitsample.canvas.line

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.utils.UIUtils

class CanvasLineTestView : View {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val dotYList: ArrayList<Float> = ArrayList()
    private val paint: Paint = Paint()
    private val rectF: RectF = RectF()

    init {
        paint.setColor(Color.GREEN)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val lineHeight = MeasureSpec.getSize(heightMeasureSpec)
        val dotHeight = UIUtils.dpToPx(4.0f)
        val dotCount = lineHeight / dotHeight

        dotYList.clear()
        for (index in 0 until dotCount) {
            dotYList.add(index * dotHeight * 1.0f)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (dotYList.isEmpty()) {
            return
        }
        dotYList.forEach { dotY ->
            rectF.top = dotY
            rectF.left = 50.0f
            rectF.right = 50.0f + UIUtils.dpToPx(1.0f)
            rectF.bottom = dotY + UIUtils.dpToPx(2.0f)
            canvas.drawOval(rectF, paint)
        }
    }
}