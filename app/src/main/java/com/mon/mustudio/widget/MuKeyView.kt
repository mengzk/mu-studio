package com.mon.mustudio.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.mon.mustudio.R
import kotlin.random.Random

class MuKeyView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint()
    private val circles = mutableMapOf<Int, Circle>()
    private val soundPool: SoundPool
    private val soundIds = mutableListOf<Int>()
    private val handler = Handler(Looper.getMainLooper())
    private val beatInterval = 600L // 每节拍的间隔时间，单位为毫秒

    // 绘制钢琴键
    private val totalKeys = 32
    private val keysPerRow = totalKeys / 2

    val noteNames = arrayOf("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B")

    init {
        paint.style = Paint.Style.FILL

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(10)
            .setAudioAttributes(audioAttributes)
            .build()

        // 加载音频资源
        soundIds.add(soundPool.load(context, R.raw.a, 1))
        soundIds.add(soundPool.load(context, R.raw.b, 1))
        soundIds.add(soundPool.load(context, R.raw.c, 1))
        soundIds.add(soundPool.load(context, R.raw.d, 1))
        soundIds.add(soundPool.load(context, R.raw.e, 1))
        soundIds.add(soundPool.load(context, R.raw.f, 1))
        soundIds.add(soundPool.load(context, R.raw.g, 1))
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                val pointerIndex = event.actionIndex
                val pointerId = event.getPointerId(pointerIndex)
                val x = event.getX(pointerIndex)
                val y = event.getY(pointerIndex)
                val color = getRandomColor()
                circles[pointerId] = Circle(x, y, 0f, color)
                playSoundWithBeats(pointerId)
                invalidate()
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                val pointerIndex = event.actionIndex
                val pointerId = event.getPointerId(pointerIndex)
                stopSound(pointerId)
                circles.remove(pointerId)
                invalidate()
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val keyHeight = height / keysPerRow.toFloat()
        val keyWidth = width / 2f
        val blackKeyHeight = keyHeight * 0.6f
        val blackKeyWidth = keyWidth * 0.6f

        for (i in 0 until totalKeys) {
            val isWhiteKey = when (i % 12) {
                0, 2, 4, 5, 7, 9, 11 -> true
                else -> false
            }
            paint.color = if (isWhiteKey) Color.WHITE else Color.BLACK
            val row = i / keysPerRow
            val top = (i % keysPerRow) * keyHeight
            val bottom = top + keyHeight
            val left = row * keyWidth
            val right = left + keyWidth
            if (isWhiteKey) {
                canvas.drawRect(left, top, right, bottom, paint)
            } else {
                val blackKeyLeft = left + keyWidth - blackKeyWidth
                canvas.drawRect(blackKeyLeft, top, blackKeyLeft + blackKeyWidth, top + blackKeyHeight, paint)
            }

            // 绘制音阶描述
            paint.color = if (isWhiteKey) Color.LTGRAY else Color.DKGRAY
            paint.textSize = keyHeight / 3
//            val noteName = noteNames[i % 12]
            val noteName = "${i+1}"
            val textWidth = paint.measureText(noteName)
//            val textX = left + (keyWidth - textWidth) / 2
            val textX = left + (keyWidth - textWidth) - 26
            val textY = top + keyHeight / 2 + paint.textSize / 2
            canvas.drawText(noteName, textX, textY, paint)
        }

        // 绘制分割线
        paint.color = Color.DKGRAY
        paint.strokeWidth = 6f
        canvas.drawLine(keyWidth, 0f, keyWidth, height.toFloat(), paint)

        // 绘制圆点
        val iterator = circles.values.iterator()
        while (iterator.hasNext()) {
            val circle = iterator.next()
            paint.color = circle.color
            canvas.drawCircle(circle.x, circle.y, circle.radius, paint)
            if (circle.radius < 300) { // 圆点扩散的最大半径
                circle.radius += 10
            } else {
                iterator.remove()
            }
        }
        if (circles.isNotEmpty()) {
            invalidate()
        }
    }


    private fun getRandomColor(): Int {
        val random = Random
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }

    private fun playSoundWithBeats(pointerId: Int) {
        val randomSoundId = soundIds.random()
        val streamId = soundPool.play(randomSoundId, 1f, 1f, 1, 0, 1f)
        circles[pointerId]?.streamId = streamId

        // 设置每节拍播放一次音频
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (circles.containsKey(pointerId)) {
                    soundPool.play(randomSoundId, 1f, 1f, 1, 0, 1f)
                    handler.postDelayed(this, beatInterval)
                }
            }
        }, beatInterval)
    }

    private fun stopSound(pointerId: Int) {
        circles[pointerId]?.streamId?.let {
            soundPool.stop(it)
        }
        handler.removeCallbacksAndMessages(null)
    }

    private data class Circle(var x: Float, var y: Float, var radius: Float, var color: Int, var streamId: Int? = null)
}