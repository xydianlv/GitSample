package com.example.gitsample.canvas.path

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.resource.R
import com.example.utils.UIUtils
import com.example.utils.ZLog
import java.lang.Exception

class CanvasPathSwitchView : View {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private val pathNormal: Path = Path()
    private val pathSelected: Path = Path()
    private val pathImageCover: Path = Path()
    private val paintNormal: Paint = Paint()
    private val paintSelected: Paint = Paint()
    private val paintImageCover: Paint = Paint()

    private var imageBitmap: Bitmap? = null
    private var onSelectedLeft: Boolean = false
    private var linearGradient: LinearGradient? = null

    private var colorEnd: Int = 0
    private var colorStart: Int = 0
    private var smallRadius: Float = 0.0f
    private var largeRadius: Float = 0.0f

    init {
        smallRadius = UIUtils.dpToPx(8.0f) * 1.0f
        largeRadius = UIUtils.dpToPx(8.0f) * 1.0f
        colorEnd = ContextCompat.getColor(context, R.color.color_FFFFB27F)
        colorStart = ContextCompat.getColor(context, R.color.color_FFFFE4D6)

        try {
            imageBitmap = BitmapFactory.decodeResource(resources, R.mipmap.img_lock_fail)
        } catch (e: Exception) {
            ZLog.e(e)
        }
    }

    fun refreshSelectedLeft(onSelectedLeft: Boolean) {
        this.onSelectedLeft = onSelectedLeft
        this.invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawSelected(canvas)
        drawNormal(canvas)
    }

    private fun drawSelected(canvas: Canvas) {
        pathSelected.reset()
        if (onSelectedLeft) {
            buildSelectedPathLeft()
        } else {
            buildSelectedPathRight()
        }
        paintSelected.setShader(linearGradient)
        canvas.drawPath(pathSelected, paintSelected)
        if (onSelectedLeft) {
            drawImageLeft(canvas)
        } else {
            drawImageRight(canvas)
        }
    }

    private fun getOnDrawWidth(): ArrayList<Float> {
        val onDrawWidthValue = ArrayList<Float>()
        // 整个区域的，宽度
        onDrawWidthValue.add(measuredWidth * 1.0f)
        // 选中的卡片，高度
        onDrawWidthValue.add(measuredHeight * 1.0f)
        // 选中的卡片，上边沿宽度
        onDrawWidthValue.add(onDrawWidthValue[0] / 2.0f)
        // 选中的卡片，下边沿宽度，选中与非选中比例 194 / 142
        onDrawWidthValue.add(onDrawWidthValue[0] * 194.0f / (194.0f + 142.0f))
        return onDrawWidthValue
    }

    private fun buildSelectedPathLeft() {
        val widthValue = getOnDrawWidth()
        // 设定起始位置为左下角
        pathSelected.moveTo(0.0f, widthValue[1])
        // 向上画直线
        pathSelected.lineTo(0.0f, smallRadius)
        // 在左上角画90度弧
        val leftTop = RectF(0.0f, 0.0f, smallRadius * 2.0f, smallRadius * 2.0f)
        pathSelected.arcTo(leftTop, 180.0f, 90.0f)
        // 左侧Tab非选择状态不画圆弧
        // 在x轴上画横线`
        pathSelected.lineTo(widthValue[2], 0.0f)
        // 在右上角画80度弧
        // startAngle: 开始的角度: x轴正方向为（0度线）
        // sweepAngle: 旋转的度数: 正数顺时针方向旋转
        val rightTop = RectF(
            widthValue[2] - largeRadius, 0.0f, widthValue[2] + largeRadius, (2 * largeRadius)
        )
        pathSelected.arcTo(rightTop, 270.0f, 80.0f)
        // 画直线
        pathSelected.lineTo(widthValue[3], widthValue[1])
        // 闭合路径
        pathSelected.close()

        linearGradient = LinearGradient(
            0.0f, 0.0f, widthValue[3], 0.0f, colorStart, colorEnd, Shader.TileMode.CLAMP
        )
    }

    private fun buildSelectedPathRight() {
        val widthValue = getOnDrawWidth()
        // 设定起始位置为右下角
        pathSelected.moveTo(widthValue[0], widthValue[1])
        // 向上画直线
        pathSelected.lineTo(widthValue[0], smallRadius)
        // 在右上角画90度弧
        val leftTop = RectF(
            widthValue[0] - smallRadius * 2.0f, 0.0f, widthValue[0], smallRadius * 2.0f
        )
        pathSelected.arcTo(leftTop, 0.0f, -90.0f)

        val leftTopX = widthValue[0] - widthValue[2]
        // 在x轴上画横线`
        pathSelected.lineTo(leftTopX, 0.0f)
        // 在左上角画80度弧
        // startAngle: 开始的角度: x轴正方向为（0度线）
        // sweepAngle: 旋转的度数: 正数顺时针方向旋转
        val rightTop = RectF(
            leftTopX - largeRadius, 0.0f, leftTopX + largeRadius, (2 * largeRadius)
        )
        pathSelected.arcTo(rightTop, -90.0f, -80.0f)
        // 画直线
        pathSelected.lineTo(widthValue[0] - widthValue[3], widthValue[1])
        // 闭合路径
        pathSelected.close()

        linearGradient = LinearGradient(
            widthValue[0] - widthValue[3],
            0.0f,
            widthValue[0],
            0.0f,
            colorStart,
            colorEnd,
            Shader.TileMode.CLAMP
        )
    }

    private fun drawImageLeft(canvas: Canvas) {
        if (imageBitmap == null || imageBitmap!!.isRecycled) {
            return
        }
        val widthValue = getOnDrawWidth()
        val offsetValue = largeRadius
        canvas.drawBitmap(
            imageBitmap!!,
            widthValue[2] - imageBitmap!!.getWidth() + offsetValue,
            0.0f,
            paintSelected
        )

        pathImageCover.reset()
        // 设定起始位置为右下角
        pathImageCover.moveTo(widthValue[2] + offsetValue, smallRadius)
        // 向上画直线
        pathImageCover.lineTo(widthValue[2] + offsetValue, 0.0f)
        // 向左画直线
        pathImageCover.lineTo(widthValue[2], 0.0f)
        // 在左上角画80度弧
        val rightTop = RectF(
            widthValue[2] - largeRadius, 0.0f, widthValue[2] + largeRadius, (2 * largeRadius)
        )
        pathImageCover.arcTo(rightTop, 270.0f, 80.0f)
        // 连接到交叉点
        pathImageCover.lineTo(widthValue[2] + largeRadius - 1.0f, smallRadius)
        // 闭合路径
        pathImageCover.close()

        paintImageCover.setColor(ContextCompat.getColor(context, R.color.color_white))
        canvas.drawPath(pathImageCover, paintImageCover)
    }

    private fun drawImageRight(canvas: Canvas) {
        if (imageBitmap == null || imageBitmap!!.isRecycled) {
            return
        }
        val widthValue = getOnDrawWidth()
        canvas.drawBitmap(
            imageBitmap!!, widthValue[0] - imageBitmap!!.getWidth(), 0.0f, paintSelected
        )

        pathImageCover.reset()
        // 设定起始位置为右下角
        pathImageCover.moveTo(widthValue[0], 0.0f)
        // 向上画直线
        pathImageCover.lineTo(widthValue[0] - smallRadius, 0.0f)
        // 在右上角画90度弧
        val leftTop = RectF(
            widthValue[0] - smallRadius * 2.0f, 0.0f, widthValue[0], smallRadius * 2.0f
        )
        pathImageCover.arcTo(leftTop, -90.0f, 90.0f)
        // 闭合路径
        pathImageCover.close()

        paintImageCover.setColor(ContextCompat.getColor(context, R.color.color_white))
        canvas.drawPath(pathImageCover, paintImageCover)
    }

    private fun drawNormal(canvas: Canvas) {
        pathNormal.reset()
        paintNormal.setColor(ContextCompat.getColor(context, R.color.color_gray))
        if (onSelectedLeft) {
            buildNormalPathRight()
        } else {
            buildNormalPathLeft()
        }
        canvas.drawPath(pathNormal, paintNormal)
    }

    private fun buildNormalPathLeft() {
        val widthValue = getOnDrawWidth()
        // 设定起始位置为左下角
        pathNormal.moveTo(0.0f, widthValue[1])
        // 向上画直线
        pathNormal.lineTo(0.0f, smallRadius * 2.0f)
        // 在左上角画90度弧
        val leftTop = RectF(0.0f, smallRadius, smallRadius * 2.0f, smallRadius * 3.0f)
        pathNormal.arcTo(leftTop, 180.0f, 90.0f)
        // 左侧Tab非选择状态不画圆弧
        // 在x轴上画横线`
        pathNormal.lineTo(widthValue[0] - widthValue[2] - largeRadius + 1.0f, smallRadius)
        // 画直线
        pathNormal.lineTo(widthValue[0] - widthValue[3], widthValue[1])
        // 闭合路径
        pathNormal.close()
    }

    private fun buildNormalPathRight() {
        val widthValue = getOnDrawWidth()
        // 设定起始位置为左下角
        pathNormal.moveTo(widthValue[0], widthValue[1])
        // 向上画直线
        pathNormal.lineTo(widthValue[0], smallRadius * 2.0f)
        // 在右上角画80度弧
        // startAngle: 开始的角度: x轴正方向为（0度线）
        // sweepAngle: 旋转的度数: 正数顺时针方向旋转
        val rightTop = RectF(
            widthValue[0] - smallRadius * 2, smallRadius, widthValue[0], smallRadius * 3.0f
        )
        pathNormal.arcTo(rightTop, 0.0f, -90.0f)
        // 在x轴上画横线
        pathNormal.lineTo(widthValue[2] + largeRadius - 1.0f, smallRadius)
        // 画直线
        pathNormal.lineTo(widthValue[3], widthValue[1])
        // 闭合路径
        pathNormal.close()
    }
}