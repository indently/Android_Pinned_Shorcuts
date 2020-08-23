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


        if (Build.VERSION.SDK_INT >= 25) {
            Shortcuts.setUp(applicationContext)
        }


        if (Build.VERSION.SDK_INT >= 28) {
            shortcutPins()
        }

    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun shortcutPins() {

        btn_youtube_sc.setOnClickListener {

            val shortcutManager = getSystemService(ShortcutManager::class.java)

            if (shortcutManager!!.isRequestPinShortcutSupported) {
                val pinShortcutInfo = ShortcutInfo.Builder(applicationContext, "id_website").build()

                // Create the PendingIntent object only if your app needs to be notified
                // that the user allowed the shortcut to be pinned. Note that, if the
                // pinning operation fails, your app isn't notified. We assume here that the
                // app has implemented a method called createShortcutResultIntent() that
                // returns a broadcast intent.
                val pinnedShortcutCallbackIntent =
                    shortcutManager.createShortcutResultIntent(pinShortcutInfo)

                // Configure the intent so that your app's broadcast receiver gets
                // the callback successfully.For details, see PendingIntent.getBroadcast().
                val successCallback = PendingIntent.getBroadcast(
                    applicationContext, /* request code */ 0,
                    pinnedShortcutCallbackIntent, /* flags */ 0
                )

                Log.d("Main", successCallback.toString())

                shortcutManager.requestPinShortcut(
                    pinShortcutInfo,
                    successCallback.intentSender
                )
            }
        }

        btn_messages_sc.setOnClickListener {

            val shortcutManager = getSystemService(ShortcutManager::class.java)

            if (shortcutManager!!.isRequestPinShortcutSupported) {
                val pinShortcutInfo =
                    ShortcutInfo.Builder(applicationContext, "id_messages").build()

                val pinnedShortcutCallbackIntent =
                    shortcutManager.createShortcutResultIntent(pinShortcutInfo)

                val successCallback = PendingIntent.getBroadcast(
                    applicationContext, /* request code */ 1,
                    pinnedShortcutCallbackIntent, /* flags */ 0
                )
                shortcutManager.requestPinShortcut(
                    pinShortcutInfo,
                    successCallback.intentSender
                )
            }
        }


    }
}