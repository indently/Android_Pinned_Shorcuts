package com.federicocotogno.shortcuts

import android.app.PendingIntent
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Check's that the api version meets the requirements
        if (Build.VERSION.SDK_INT >= 25) {
            //Sets up our shortcuts
            Shortcuts.setUp(applicationContext)
        }

        //Check's that the api version meets the requirements
        if (Build.VERSION.SDK_INT >= 28) {
            //Sets up the option to use pinned shortcuts
            shortcutPins()
        }

    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun shortcutPins() {

        btn_youtube_sc.setOnClickListener {
            val shortcutManager = getSystemService(ShortcutManager::class.java)

            if (shortcutManager!!.isRequestPinShortcutSupported) {
                val pinShortcutInfo = ShortcutInfo.Builder(applicationContext, "id_website").build()

                val pinnedShortcutCallbackIntent =
                    shortcutManager.createShortcutResultIntent(pinShortcutInfo)

                val successCallback = PendingIntent.getBroadcast(
                    applicationContext, /* request code */ 0,
                    pinnedShortcutCallbackIntent, /* flags */ 0
                )

                shortcutManager.requestPinShortcut(
                    pinShortcutInfo,
                    successCallback.intentSender
                )

            }
        }

        btn_messages_sc.setOnClickListener {
            val shortcutManager = getSystemService(ShortcutManager::class.java)

            //Checks if we can support the pin feature
            if (shortcutManager!!.isRequestPinShortcutSupported) {
                // Gets the current id of the shortcut we want to use
                val pinShortcutInfo =
                    ShortcutInfo.Builder(applicationContext, "id_messages").build()

                //Create the callback
                val pinnedShortcutCallbackIntent =
                    shortcutManager.createShortcutResultIntent(pinShortcutInfo)

                //Notifies us that it has been successfully pinned
                val successCallback = PendingIntent.getBroadcast(
                    applicationContext, /* request code */ 1,
                    pinnedShortcutCallbackIntent, /* flags */ 0
                )
                //Requests the pin shortcut, and if successful, will display it.
                shortcutManager.requestPinShortcut(
                    pinShortcutInfo,
                    successCallback.intentSender
                )
            }
        }


    }
}