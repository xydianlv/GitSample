package com.example.gitsample.function.lock.gesture

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.example.gitsample.utils.UIUtils
import com.example.gitsample.utils.ZLog
import com.example.gitsample.utils.ZToast
import com.example.resource.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GestureLockView : View {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    companion object {

        private const val NODE_COUNT = 9
        private const val RAW_COUNT = 3
    }

    private val drawIndexList: ArrayList<Int> = ArrayList()
    private val nodeList: ArrayList<NodeData> = ArrayList()

    // 绘制状态，0-初始状态，1-触屏状态，2-完结且成功，-1-完结但失败
    private var drawType: Int = 0

    private val paint = Paint()
    private val path = Path()

    init {
        initPaint()
        initNodeList()
    }

    private fun initPaint() {
        // 抗锯齿
        paint.isAntiAlias = true
        // 线条类型
        paint.style = Paint.Style.STROKE
        // 线条宽度
        paint.strokeWidth = 4.0f
        // 线条颜色
        paint.color = ContextCompat.getColor(context, com.example.gitsample.R.color.color_lock_n)
    }

    private fun initNodeList() {
        val sizePadding = UIUtils.dpToPx(20.0f)
        val sizeDivide = UIUtils.dpToPx(54.0f)
        val sizeNode = UIUtils.dpToPx(36.0f)

        for (index in 0 until NODE_COUNT) {
            val left = (index % RAW_COUNT) * (sizeDivide + sizeNode) + sizePadding
            val top = (index / RAW_COUNT) * (sizeDivide + sizeNode) + sizePadding
            val x = (left + left + sizeNode) * 1.0f / 2
            val y = (top + top + sizeNode) * 1.0f / 2

            nodeList.add(NodeData(index, left, top, left + sizeNode, top + sizeNode, x, y))
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.performClick()
        if (drawType == 2 || drawType == -1) {
            return false
        }
        return when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                resetGestureLockShow()
                drawType = 1
                true
            }
            MotionEvent.ACTION_MOVE -> {
                checkSelectedNode(event.x, event.y)
                refreshPathAndDraw(event.x, event.y, true)
                true
            }
            MotionEvent.ACTION_UP -> {
                refreshPathAndDraw(event.x, event.y, false)
                checkLockResult()
                true
            }
            else -> {
                false
            }
        }

    }

    override fun performClick(): Boolean {
        ZLog.d("performClick")
        return super.performClick()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawNode(canvas)
        drawPath(canvas)
    }

    private fun resetGestureLockShow() {
        for (node in nodeList) {
            node.resId = R.mipmap.img_lock_normal
        }
        drawIndexList.clear()

        paint.color = ContextCompat.getColor(context, com.example.gitsample.R.color.color_lock_n)
        path.reset()

        drawType = 0
        invalidate()
    }

    private fun checkSelectedNode(pressX: Float, pressY: Float) {
        for (node in nodeList) {
            if (pressX > node.left && pressX < node.right && pressY > node.top && pressY < node.bottom) {
                if (drawIndexList.contains(node.index)) {
                    break
                }
                node.resId = R.mipmap.img_lock_press
                drawIndexList.add(node.index)
                checkMiddleSelected()
            }
        }
    }

    private fun checkMiddleSelected() {
        if (drawIndexList.size <= 1) {
            return
        }
        val lastDrawIndex = drawIndexList[drawIndexList.lastIndex]
        val preDrawIndex = drawIndexList[drawIndexList.lastIndex - 1]
        if ((lastDrawIndex + preDrawIndex) % 2 != 0) {
            return
        }
        val midDrawIndex = (lastDrawIndex + preDrawIndex) / 2

        val lastDrawX = nodeList[lastDrawIndex].x
        val lastDrawY = nodeList[lastDrawIndex].y

        val midDrawX = nodeList[midDrawIndex].x
        val midDrawY = nodeList[midDrawIndex].y

        val preDrawX = nodeList[preDrawIndex].x
        val preDrawY = nodeList[preDrawIndex].y

        if ((lastDrawX + midDrawX + preDrawX == lastDrawY + midDrawY + preDrawY)
            || (lastDrawX == midDrawX && midDrawX == preDrawX)
            || (lastDrawY == midDrawY && midDrawY == preDrawY)
        ) {
            nodeList[midDrawIndex].resId = R.mipmap.img_lock_press
            drawIndexList.add(drawIndexList.lastIndex, midDrawIndex)
        }
    }

    private fun refreshPathAndDraw(pressX: Float, pressY: Float, onMove: Boolean) {
        path.reset()
        if (drawIndexList.size > 0) {
            path.moveTo(nodeList[drawIndexList[0]].x, nodeList[drawIndexList[0]].y)

            for (position in 1 until drawIndexList.size) {
                val index = drawIndexList[position]
                path.lineTo(nodeList[index].x, nodeList[index].y)
            }

            if (onMove) {
                path.lineTo(pressX, pressY)
            }
        }
        invalidate()
    }

    private fun checkLockResult() {
        val resultArray = arrayOf(0, 1, 2, 5, 8)
        var success = true

        if (resultArray.size != drawIndexList.size) {
            success = false
        } else {
            for (index in resultArray.indices) {
                if (resultArray[index] != drawIndexList[index]) {
                    success = false
                    break
                }
            }
        }
        if (success) {
            drawType = 2
            ZToast.show("解锁成功")
        } else {
            drawType = -1
            val color = ContextCompat.getColor(context, com.example.gitsample.R.color.color_lock_f)
            for (index in drawIndexList) {
                nodeList[index].resId = R.mipmap.img_lock_fail
                paint.color = color
            }
            invalidate()
            ZToast.show("密码错误")
        }
        MainScope().launch(Dispatchers.Main) {
            delay(3000L)
            resetGestureLockShow()
        }
    }

    private fun drawNode(canvas: Canvas?) {
        for (node in nodeList) {
            val rect = Rect(node.left, node.top, node.right, node.bottom)
            val bitmap = BitmapFactory.decodeResource(resources, node.resId)
            canvas?.drawBitmap(bitmap, null, rect, null)
        }
    }

    private fun drawPath(canvas: Canvas?) {
        if (drawType == 0) {
            return
        }
        canvas?.drawPath(path, paint)
    }
}