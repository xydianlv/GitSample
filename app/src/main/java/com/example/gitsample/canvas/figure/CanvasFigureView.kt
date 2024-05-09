package com.example.gitsample.canvas.figure

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.utils.UIUtils

class CanvasFigureView : View {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val sizeDivide = UIUtils.dpToPx(20.0f) * 1.0f
    private val sizeView = UIUtils.dpToPx(80.0f) * 1.0f
    private val halfScreen = UIUtils.screenWidth() / 2.0f

    private val paint = Paint()
    private val rect = RectF()

    init {
        // 抗锯齿
        paint.isAntiAlias = true
        // 线条宽度
        paint.strokeWidth = 4.0f
        // 线条颜色
        paint.color = ContextCompat.getColor(context, com.example.gitsample.R.color.ct_1)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 线条类型，边框
        paint.style = Paint.Style.STROKE

        val radius = sizeView / 2
        // 绘制一个圆（坐标X，坐标Y，半径，画笔）
        canvas.drawCircle(sizeDivide + radius, sizeDivide + radius, radius, paint)

        refreshRect(1, false)
        // 绘制一个扇形（扇形所在区域，扇形起始角度，扇形结束角度，是否使用圆心，画笔）
        canvas.drawArc(rect, -90.0f, 225.0f, true, paint)

        refreshRect(2, false)
        // 绘制一个首位相连的扇形
        canvas.drawArc(rect, -90.0f, 225.0f, false, paint)

        refreshRect(3, false)
        // 绘制一个矩形（矩形区域，画笔）
        canvas.drawRect(rect, paint)

        refreshRect(4, false)
        rect.right += sizeDivide
        // 绘制一个椭圆（椭圆区域，画笔）
        canvas.drawOval(rect, paint)

        refreshRect(5, false)
        // 绘制一个圆角矩形（矩形区域，圆角半径，圆角半径，画笔），圆角半径必须相等，且在 [0，90] 区间
        canvas.drawRoundRect(rect, 60.0f, 60.0f, paint)

        // 线条类型，充满
        paint.style = Paint.Style.FILL

        // 绘制一个实心圆
        canvas.drawCircle(sizeDivide + radius + halfScreen, sizeDivide + radius, radius, paint)

        refreshRect(1, true)
        // 绘制一个实心扇形
        canvas.drawArc(rect, -90.0f, 225.0f, true, paint)

        refreshRect(2, true)
        // 绘制一个首位相连的实心扇形
        canvas.drawArc(rect, -90.0f, 225.0f, false, paint)

        refreshRect(3, true)
        // 绘制一个实心矩形
        canvas.drawRect(rect, paint)

        refreshRect(4, true)
        rect.right += sizeDivide
        // 绘制一个实心椭圆
        canvas.drawOval(rect, paint)

        refreshRect(5, true)
        // 绘制一个实心圆角矩形
        canvas.drawRoundRect(rect, 60.0f, 60.0f, paint)
    }

    private fun refreshRect(index: Int, hasHalf: Boolean) {
        rect.left = if (hasHalf) {
            sizeDivide + halfScreen
        } else {
            sizeDivide
        }
        rect.right = rect.left + sizeView

        rect.top = sizeDivide + (sizeDivide + sizeView) * index
        rect.bottom = rect.top + sizeView
    }
}