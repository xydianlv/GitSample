package com.example.gitsample.canvas.text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.utils.UIUtils

class CanvasTextView : View {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val sizeDivide = UIUtils.dpToPx(20.0f) * 1.0f
    private val sizeHeight = UIUtils.dpToPx(40.0f) * 1.0f
    private val halfScreen = UIUtils.screenWidth() / 2.0f

    private val array = arrayOf(1.0f, 1.0f)
    private val paint = Paint()

    init {
        // 抗锯齿
        paint.isAntiAlias = true
        // 线条宽度
        paint.strokeWidth = UIUtils.dpToPx(1.0f) * 1.0f
        // 文字大小
        paint.textSize = UIUtils.dpToPx(22.0f) * 1.0f
        // 从精准线的左边开始绘制，相当于左对齐
        paint.textAlign = Paint.Align.LEFT
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        refreshArray(0)
        // 提示线的色值
        paint.color = ContextCompat.getColor(context, com.example.gitsample.R.color.color_alert)
        // 绘制第一行文字的基准线（起点坐标X，起点坐标Y，终点坐标X，终点坐标Y，画笔）
        canvas.drawLine(array[0], array[1], array[0] + halfScreen, array[1], paint)

        // 文字色值
        paint.color = ContextCompat.getColor(context, com.example.gitsample.R.color.ct_1)
        // 画笔风格，空心
        paint.style = Paint.Style.STROKE
        // 绘制一段空心文字（绘制的文案，基准点坐标X，基准点坐标Y，画笔），基准点位于文字的左下角
        canvas.drawText("PaintStroke", array[0], array[1], paint)

        refreshArray(1)
        // 画笔风格，实心
        paint.style = Paint.Style.FILL
        // 绘制一段实心文字
        canvas.drawText("PaintFill", array[0], array[1], paint)

        refreshArray(2)
        // 画笔风格，含边框的实心
        paint.style = Paint.Style.FILL_AND_STROKE
        // 绘制一段实心带边框的文字
        canvas.drawText("PaintAll", array[0], array[1], paint)

        refreshArray(3)
        // 画笔风格，实心
        paint.style = Paint.Style.FILL
        // 设置粗体
        paint.isFakeBoldText = true
        // 绘制一段实心粗体的文字
        canvas.drawText("PaintFillBold", array[0], array[1], paint)

        refreshArray(4)
        // 设置粗体
        paint.isFakeBoldText = false
        // 设置带下划线
        paint.isUnderlineText = true
        // 绘制一段带下划线的实心文字
        canvas.drawText("PaintFillWithUnderLine", array[0], array[1], paint)

        refreshArray(5)
        // 设置带下划线
        paint.isUnderlineText = false
        // 设置带倾斜角度
        paint.textSkewX = -0.25f
        // 绘制一段倾斜的实心文字
        canvas.drawText("PaintFillWithSkew", array[0], array[1], paint)

        refreshArray(6)
        // 设置带倾斜角度
        paint.textSkewX = 0.0f
        // 设置是否带删除线
        paint.isStrikeThruText = true
        // 绘制一段带删除线的实心文字
        canvas.drawText("PaintFillWidthDeleteLine", array[0], array[1], paint)
    }

    private fun refreshArray(index: Int) {
        array[0] = sizeDivide
        array[1] = (index + 1) * (sizeHeight + sizeDivide)
    }
}