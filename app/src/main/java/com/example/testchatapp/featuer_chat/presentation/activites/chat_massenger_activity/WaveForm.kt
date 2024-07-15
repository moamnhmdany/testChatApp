package com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference

class WaveForm(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private var normal = 0f
    private var left = 0f
    private var top = 0f
    private var right :Float = 0f
    private var bottom :Float = 0f
    private var paint  = Paint()
    private var amplitued = ArrayList<Float>()
    private var spikesRectangles = ArrayList<RectF>()
    private var raduis = 6f
    private var width = 4f
    private var screenWidth = 0f
    private var screenHeight = 40f
    private var distance = 5f
    private var maxSpikes = 0
    init {
        paint.color = Color.rgb(255,255,255)
        screenWidth = resources.displayMetrics.widthPixels.toFloat()
        maxSpikes = (350f  / (width + distance)).toInt()  // get how many rect
    }

    fun addAmplitude(amp: Float){
         normal = Math.min(amp.toInt()/7,screenHeight.toInt()).toFloat()
        amplitued.add(normal)
        spikesRectangles.clear()
       var lastAmps = amplitued.takeLast(maxSpikes) // array list for rect fit in screen only
        for (i :Int in lastAmps.indices ) {
             left = (screenWidth - 670f)  - i*(width + distance)
             top = (screenHeight/2 + lastAmps[i]/2) // for center Rectangles divided by 2
             right  = left + width
            bottom  = top + lastAmps[i]   // height of Rectangle
            spikesRectangles.add(RectF(left, top, right, bottom))
            invalidate() // clear or re-render the view
        }
    }

    override fun onDraw(canvas: Canvas) {

        super.onDraw(canvas)

        spikesRectangles.forEach {
            canvas.drawRoundRect(it,raduis, raduis, paint)
        }
    }

     fun clear(){
        if(!UtilsReference.isRecorded && !UtilsReference.isPaused){

            amplitued.clear()
            spikesRectangles.clear()
            invalidate()
        }
    }
}