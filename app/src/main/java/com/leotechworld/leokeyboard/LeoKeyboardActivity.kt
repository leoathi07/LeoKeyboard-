package com.leotechworld.leokeyboard

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView

class LeoKeyboardActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val message = TextView(this).apply {
            text = "LeoKeyboard\n\nSmart Typing. Smarter You.\n\nEnable LeoKeyboard in Settings → Languages & input → On-screen keyboard."
            textSize = 20f
            gravity = Gravity.CENTER
            setPadding(24, 24, 24, 24)
        }

        setContentView(message)
    }
}
