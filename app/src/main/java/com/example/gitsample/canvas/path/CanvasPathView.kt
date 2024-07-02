package com.example.gitsample.canvas.path

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.resource.R
import com.example.utils.UIUtils

class CanvasPathView(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val sizeWidth = UIUtils.screenWidth() / 2.0f + UIUtils.dpToPx(40.0f)
    private val sizeDivide = UIUtils.dpToPx(40.0f) * 1.0f
    private val array = arrayOf(0.0f, 0.0f)

    private val paint = Paint()
    private val rect = RectF()
    private val path = Path()

    private val pathArc = Path()
    private val rectArc = RectF()

    init {
        // 抗锯齿
        paint.isAntiAlias = true
        // 线条宽度
        paint.strokeWidth = UIUtils.dpToPx(1.0f) * 1.0f
        // 线条颜色
        paint.color = ContextCompat.getColor(context, R.color.ct_1)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.style = Paint.Style.STROKE

        refreshArray(0)
        path.moveTo(array[0], array[1])
        path.lineTo(array[0] + sizeWidth, array[1])
        // 画一条直线（路径，画笔）
        canvas.drawPath(path, paint)

        refreshArray(1)
        path.moveTo(array[0], array[1])
        path.lineTo(array[0] + sizeWidth, array[1] + sizeDivide / 2)
        // 画一条直线（路径，画笔）
        canvas.drawPath(path, paint)

        refreshArray(2)
        rect.set(array[0], array[1], array[0] + sizeWidth, array[1] + sizeDivide)
        path.addRect(rect, Path.Direction.CW)
        // 画一个框框（路径，画笔）
        canvas.drawPath(path, paint)

        refreshArray(4)
        rect.set(array[0], array[1], array[0] + sizeWidth, array[1] + sizeDivide)
        path.addRect(rect, Path.Direction.CCW)
        // 画一个框框（路径，画笔）
        canvas.drawPath(path, paint)

        paint.style = Paint.Style.FILL_AND_STROKE

        refreshArray(6)
        rect.set(array[0], array[1], array[0] + sizeWidth, array[1] + sizeDivide)
        path.addRect(rect, Path.Direction.CCW)
        // 画一个框框（路径，画笔）
        canvas.drawPath(path, paint)

        refreshArray(8)
        rect.set(array[0], array[1], array[0] + sizeWidth, array[1] + sizeDivide)
        path.addRect(rect, Path.Direction.CCW)
        // 画一个框框（路径，画笔）
        canvas.drawPath(path, paint)

        refreshArray(10)
        pathArc.moveTo(array[0], array[1])
        pathArc.lineTo(array[0], array[1] + sizeDivide * 2)
        rectArc.set(array[0],array[1] +sizeDivide ,array[0] + sizeDivide *2,array[1] + sizeDivide*3)
        // startAngle：开始的角度
        // sweepAngle：从 startAngle 开始旋转的度数，顺时针为正值，逆时针为负值
        pathArc.arcTo(rectArc,180.0f,-90.0f)
        pathArc.lineTo(array[0] + sizeDivide * 2, array[1] + sizeDivide * 3)
        rectArc.set(array[0] + sizeDivide,array[1] +sizeDivide ,array[0] + sizeDivide *3,array[1] + sizeDivide*3)
        // startAngle：开始的角度
        // sweepAngle：从 startAngle 开始旋转的度数，顺时针为正值，逆时针为负值
        pathArc.arcTo(rectArc,90.0f,-55.0f)
        pathArc.lineTo(array[0] + sizeDivide * 4, array[1])
        pathArc.close()
        canvas.drawPath(pathArc, paint)
    }

    private fun refreshArray(index: Int) {
        array[0] = sizeDivide
        array[1] = sizeDivide * (index + 1)
    }
}