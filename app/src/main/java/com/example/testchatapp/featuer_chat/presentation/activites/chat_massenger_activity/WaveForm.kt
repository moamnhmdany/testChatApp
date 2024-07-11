package com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.resourceinspection.annotation.Attribute

class WaveForm(context1: Context?,attribute: AttributeSet?) : View(context1, attribute) {

    private var paint  = Paint()
    private var amplitued = ArrayList<Float>()
    private var spikes = ArrayList<RectF>()
    private var raduis = 6f
    private var width = 9f
    private var screenWidth = 0f
    private var screenHeight = 40f
    private var distance = 6f
    private var maxSpikes = 0
    init {
        paint.color = Color.rgb(244,81,30)
        screenWidth = resources.displayMetrics.widthPixels.toFloat()
        maxSpikes = (screenWidth / (width + distance)).toInt()
    }

    fun addAmplitued(amp: Float){
        var normal = Math.min(amp.toInt()/7,screenHeight.toInt()).toFloat()
      amplitued.add(normal)

        spikes.clear()
        var amps = amplitued.takeLast(maxSpikes)
        for (i :Int in amps.indices ) {
            var left = screenWidth - i*(width + distance)
            var top = screenHeight/2 + amps[i]/2
            var right :Float = left + width
            var bottom :Float = top + amps[i]   // height of Rectangle
            spikes.add(RectF(left, top, right, bottom))
            invalidate()
        }
    }

    override fun onDraw(canvas: Canvas) {

        super.onDraw(canvas)
        spikes.forEach {
            canvas.drawRoundRect(it,raduis, raduis, paint)
        }

    }
}