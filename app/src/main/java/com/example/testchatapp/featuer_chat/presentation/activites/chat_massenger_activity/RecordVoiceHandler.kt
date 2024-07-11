package com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


class RecordVoiceHandler(val recorder: MediaRecorder) {


    @RequiresApi(Build.VERSION_CODES.Q)
    fun setupRecordVoice(activity: ChatMessengerActivity) {
        setupAudioPath(activity)
        setupFileName()

        try {

            recorder.apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                setOutputFile(UtilsReference.audioPath)

            }
        } catch (e: IOException) {
            println("---------------->setup error$e")
        }
    }

    fun startRecord() {
        try {
            recorder.prepare()
        } catch (e: IOException) {
            println("---------------------->error in the record of sound = $e")

        } catch (e: IllegalStateException) {
            println("---------------------->error in the IllegalStateException= $e")

        }
        recorder.start()
        UtilsReference.isRecorded = true
        UtilsReference.isPaused = false
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun pauseRecorder() {
        try {
            recorder.pause()
            UtilsReference.isPaused = true
        } catch (e: IOException) {
            println("---------------------->error in the  recorder.pause = $e")

        } catch (e: IllegalStateException) {
            println("---------------------->error in the  recorder.pause IllegalStateException= $e")

        }


    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun resumeRecorder() {
        try {
            recorder.resume()
            UtilsReference.isPaused = false

        } catch (e: IOException) {
            println("---------------------->error in the recorder.resume() = $e")

        } catch (e: IllegalStateException) {
            println("---------------------->error in the recorder.resume() IllegalStateException= $e")

        }

    }

    fun removeRecorder() {
        try {
            recorder.stop()
            deleteRecord()
            UtilsReference.isRecorded = false
            UtilsReference.isPaused = false
        } catch (e: IOException) {
            println("---------------------->error in  recorder.stop() = $e")

        } catch (e: IllegalStateException) {
            println("---------------------->error in recorder.stop() IllegalStateException= $e")

        }

    }

    private fun deleteRecord() {

        println("=============== before finding the file${UtilsReference.audioPath}")
        if (UtilsReference.audioFile.exists()) {
            println("=============== after mean is done${UtilsReference.audioFile.absolutePath}")
            UtilsReference.audioFile.delete()
        } else {
            println("-------------------file not found")
        }
    }

    private fun setupAudioPath(activity: ChatMessengerActivity) {
        val context: Context = activity.applicationContext

        val audioDir = File(context.getExternalFilesDir(Environment.DIRECTORY_MUSIC), "AudioMemos")
        audioDir.mkdirs()
        val audioDirPath = audioDir.absolutePath

        val curTimeStr = setupDate()

         UtilsReference.audioFile = File("$audioDirPath/$curTimeStr.mp3")
        UtilsReference.audioPath =  UtilsReference.audioFile.absolutePath
        println("============== -> ${UtilsReference.audioPath}")
    }

    private fun setupFileName() {
        UtilsReference.fileName = "audio_record${setupDate()}"
    }

    private fun setupDate(): String {
        val currentTime: Date = Calendar.getInstance().time // current time
        val curTimeStr = currentTime.toString().replace(" ", "_")
        return curTimeStr
    }


}