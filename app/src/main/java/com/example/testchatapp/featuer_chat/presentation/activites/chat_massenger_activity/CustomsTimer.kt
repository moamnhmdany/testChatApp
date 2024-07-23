package com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity

import android.os.Handler
import android.os.Looper

class CustomsTimer(listener: OnTimerTickListener) {

    interface  OnTimerTickListener{
        fun onTimerTick(duration: String)
    }

    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable
    private var duration = 0L
    private var delay = 100L

    init {

        runnable = Runnable {
            duration += delay
            handler.postDelayed(runnable, delay)
            listener.onTimerTick(format())
        }
    }

    fun start(){
        handler.postDelayed(runnable, delay)
    }
    fun pause(){
        handler.removeCallbacks(runnable)
    }
    fun stop(){
        handler.removeCallbacks(runnable)
        duration = 0L
    }

    fun format():String{
        val seconds = (duration/1000) % 60
        val minutes = (duration/ (1000 * 60)) % 60
        var format = "%02d:%02d".format(minutes, seconds)
        return  format
    }






}