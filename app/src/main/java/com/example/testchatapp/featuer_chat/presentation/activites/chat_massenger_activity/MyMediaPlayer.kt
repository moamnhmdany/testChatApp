package com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference


class MyMediaPlayer( val mediaPlayer: ExoPlayer)  {



    fun setupMediaPlayer(url : String) {

         UtilsReference.isSoundRuning = false
        UtilsReference.isSoundPause = false
        UtilsReference.isSoundStop = true
        mediaPlayer.setMediaItem(MediaItem.fromUri(url))
            try {
                mediaPlayer.prepare()

            }catch (e:Exception){
                println("-------setDataSource$e")
            }

        }


    fun changeUri(uri: String) {
        try {
              stopRunSound()
              mediaPlayer.setMediaItem(MediaItem.fromUri(uri))


        } catch (e: Exception) {
            println(e)
        }
    }

    fun startRunSound(){
        try {

            mediaPlayer.play()
            UtilsReference.isSoundRuning = true
            UtilsReference.isSoundStop = false
            UtilsReference.isSoundPause = false

        }catch (e:Exception){
            println(e)
        }
    }

    fun resumeSound(){

          try {
              mediaPlayer.play()
              UtilsReference.isSoundRuning = true
              UtilsReference.isSoundPause = false
              UtilsReference.isSoundStop = false
          }catch (e:Exception){
              println(e)
          }

    }

    fun pauseSound(){
       if (mediaPlayer.isPlaying){
           try {
               mediaPlayer.pause()
               UtilsReference.isSoundPause = true
               UtilsReference.isSoundRuning = false
               UtilsReference.isSoundStop = false
           }catch (e:Exception){
               println("--------------is = $e")
           }
       }
    }

    fun stopRunSound(){


             try {
                 mediaPlayer.stop()
                 mediaPlayer.release()
                 UtilsReference.isRecorded = false
                 UtilsReference.isSoundPause = false
                 UtilsReference.isSoundStop = true
             }catch (e:Exception){
                 println(e)
             }

    }
    fun onCompleteListener(onCompleteListener: ()-> Unit,onPause:()->Unit){

               mediaPlayer.addListener(object : Player.Listener {


                   override fun onIsPlayingChanged(isPlaying: Boolean) {
                       super.onIsPlayingChanged(isPlaying)

                        if (!mediaPlayer.isPlaying){
                         onPause()
                            println("play is changed --isPlaying---->")
                        }

                   }

                   override fun onPlaybackStateChanged(playbackState: Int) {

                       super.onPlaybackStateChanged(playbackState)
                       when(mediaPlayer.playbackState){
                           ExoPlayer.STATE_IDLE->{
                               UtilsReference.isSoundStop = true
                               UtilsReference.isSoundRuning = false
                               UtilsReference.isSoundPause  = false
                               stopRunSound()
                               onCompleteListener()
                               println("-----STATE_IDLE--------------complete sound stop")

                           }

                           Player.STATE_BUFFERING -> {
                               println("buffering data ----->")
                           }

                           Player.STATE_ENDED -> {
                               UtilsReference.isSoundStop = true
                               UtilsReference.isSoundRuning = false
                               UtilsReference.isSoundPause  = false

                               onCompleteListener()
                               println("-------STATE_ENDED------------complete sound stop")
                           }

                           Player.STATE_READY -> {
                               println("ready to run ------>")

                           }
                       }
                   }
               })




    }

}