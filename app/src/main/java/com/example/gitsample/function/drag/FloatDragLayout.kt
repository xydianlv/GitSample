package com.example.gitsample.function.drag

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.customview.widget.ViewDragHelper
import com.example.utils.ZLog
import com.example.utils.UIUtils

class FloatDragLayout : ConstraintLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val midHorizontal = UIUtils.screenWidth() / 2
    private var dragHelper: ViewDragHelper? = null

    init {
        dragHelper = ViewDragHelper.create(this, object : ViewDragHelper.Callback() {
            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                return true
            }

            override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                return left
            }

            override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
                return top
            }

            override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
                super.onViewReleased(releasedChild, xvel, yvel)
                val viewX = releasedChild.width / 2 + releasedChild.left
                val viewLeft = if (viewX < midHorizontal) {
                    0
                } else {
                    midHorizontal * 2 - releasedChild.width
                }
                dragHelper?.settleCapturedViewAt(viewLeft, releasedChild.top)
                invalidate()
            }
        })
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return if (dragHelper == null || ev == null) {
            super.onInterceptTouchEvent(ev)
        } else {
            dragHelper!!.shouldInterceptTouchEvent(ev)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        performClick()
        if (dragHelper == null || event == null) {
            return super.onTouchEvent(event)
        }
        dragHelper!!.processTouchEvent(event)
        val rect = Rect()
        for (index in 0 until childCount) {
            val child = getChildAt(index)
            rect.set(
                child.x.toInt(),
                child.y.toInt(),
                (child.x + child.width).toInt(),
                (child.y + child.height).toInt()
            )
            if (rect.contains(event.x.toInt(), event.y.toInt())) {
                return true
            }
        }
        return false
    }

    override fun performClick(): Boolean {
        ZLog.d("performClick")
        return super.performClick()
    }

    override fun computeScroll() {
        super.computeScroll()
        if (dragHelper?.continueSettling(true) == true) {
            invalidate()
        }
    }
}