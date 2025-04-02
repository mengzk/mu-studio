package com.mon.mustudio.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class TouchesView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint()
    private var radius = 0f
    private var x = 0f
    private var y = 0f

    init {
        paint.style = Paint.Style.FILL
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            x = event.x
            y = event.y
            paint.color = getRandomColor()
            radius = 0f
            invalidate()
            return true
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(x, y, radius, paint)
        if (radius < 300) { // 圆点扩散的最大半径
            radius += 10
            invalidate()
        }
    }

    private fun getRandomColor(): Int {
        val random = Random
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }
}