package com.leotechworld.leokeyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.LinearLayout

class LeoKeyboardService : InputMethodService() {

    override fun onCreateInputView(): View {
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(6, 6, 6, 6)
        }

        val rows = listOf(
            "qwertyuiop",
            "asdfghjkl",
            "⇧zxcvbnm⌫",
            "?123,🌐😊"
        )

        for (row in rows) {
            val rowLayout = LinearLayout(this)
            for (character in row) {
                val button = Button(this).apply {
                    text = character.toString()
                    isAllCaps = false
                    textSize = 16f
                    setOnClickListener {
                        val connection: InputConnection? = currentInputConnection
                        when (character) {
                            '⌫' -> connection?.deleteSurroundingText(1, 0)
                            ' ' -> connection?.commitText(" ", 1)
                            '⇧', '🌐', '😊' -> {}
                            else -> connection?.commitText(character.toString(), 1)
                        }
                    }
                }
                rowLayout.addView(
                    button,
                    LinearLayout.LayoutParams(0, 52, 1f)
                )
            }
            layout.addView(rowLayout)
        }

        val space = Button(this).apply {
            text = "SPACE"
            setOnClickListener {
                currentInputConnection?.commitText(" ", 1)
            }
        }
        layout.addView(space)

        return layout
    }
}
