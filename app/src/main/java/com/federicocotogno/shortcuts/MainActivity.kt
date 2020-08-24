package com.federicocotogno.shortcuts

import android.app.PendingIntent
import android.content.Context
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

            //Takes care of activating the pinned shortcuts

            btn_youtube_sc.setOnClickListener {
                shortcutPin(applicationContext, shortcut_website_id, 0)
            }

            btn_messages_sc.setOnClickListener {
                shortcutPin(applicationContext, shortcut_messages_id, 1)
            }
        }

    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun shortcutPin(context: Context, shortcut_id: String, requestCode: Int) {

            val shortcutManager = getSystemService(ShortcutManager::class.java)

            if (shortcutManager!!.isRequestPinShortcutSupported) {
                val pinShortcutInfo =
                    ShortcutInfo.Builder(context, shortcut_id).build()

                val pinnedShortcutCallbackIntent =
                    shortcutManager.createShortcutResultIntent(pinShortcutInfo)

                val successCallback = PendingIntent.getBroadcast(
                    context, /* request code */ requestCode,
                    pinnedShortcutCallbackIntent, /* flags */ 0
                )

                shortcutManager.requestPinShortcut(
                    pinShortcutInfo,
                    successCallback.intentSender
                )
            }
    }
}