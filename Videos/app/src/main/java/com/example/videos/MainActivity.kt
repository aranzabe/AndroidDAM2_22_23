package com.example.videos

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView

class MainActivity : AppCompatActivity() {
    lateinit var vid : VideoView
    var mediaControls: MediaController? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vid = findViewById(R.id.vv)

        if (mediaControls == null) {
            // creating an object of media controller class
            mediaControls = MediaController(this)

            // set the anchor view for the video view
            mediaControls!!.setAnchorView(this.vid)
        }


        // set the media controller for video view
        vid!!.setMediaController(mediaControls)

        // set the absolute path of the video file which is going to be played
        vid!!.setVideoURI(
            Uri.parse("android.resource://"
                + packageName + "/" + R.raw.mememe))

        vid!!.requestFocus()

        // starting the video
        vid!!.start()

        // display a toast message
        // after the video is completed
        vid!!.setOnCompletionListener {
            Toast.makeText(applicationContext, "Video completed",
                Toast.LENGTH_LONG).show()
        }

        // display a toast message if any
        // error occurs while playing the video
        vid!!.setOnErrorListener { mp, what, extra ->
            Toast.makeText(applicationContext, "An Error Occurred " +
                    "While Playing Video !!!", Toast.LENGTH_LONG).show()
            false
        }

    }
}