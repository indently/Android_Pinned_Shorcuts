package com.federicocotogno.shortcuts

import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.*

const val shortcut_website_id = "id_website"
const val shortcut_messages_id = "id_messages"

//Requires api level 25
@RequiresApi(Build.VERSION_CODES.N_MR1)
object Shortcuts {

    fun setUp(context: Context) {
        val shortcutManager =
            getSystemService<ShortcutManager>(context, ShortcutManager::class.java)


        val shortcut = ShortcutInfo.Builder(context, shortcut_website_id)
            .setShortLabel("Website")
            .setLongLabel("Open the website")
            .setIcon(Icon.createWithResource(context, R.drawable.ic_website))
            .setIntent(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/codepalace")
                )
            )
            .build()

        //Create an array of intents to create a more fluent user experience in the back stack
        val intents = arrayOf(
            Intent(Intent.ACTION_VIEW, null, context, MainActivity::class.java),
            Intent(Intent.ACTION_VIEW, null, context, Messages::class.java)
        )

        val shortcut2 = ShortcutInfo.Builder(context, shortcut_messages_id)
            .setShortLabel("Messages")
            .setLongLabel("Open messages")
            .setIcon(Icon.createWithResource(context, R.drawable.ic_messages))
            .setIntents(intents)
            .build()


        shortcutManager!!.dynamicShortcuts = listOf(shortcut, shortcut2)
    }

}