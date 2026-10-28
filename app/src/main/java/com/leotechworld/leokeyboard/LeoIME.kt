
package com.leotechworld.leokeyboard

import android.inputmethodservice.InputMethodService
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.LinearLayout

class LeoKeyboardService : InputMethodService() {

    override fun onCreateInputView(): View {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(4, 8, 4, 8)
            setBackgroundColor(Color.rgb(18, 22, 35))
        }

        val rows = listOf(
            listOf("q","w","e","r","t","y","u","i","o","p"),
            listOf("a","s","d","f","g","h","j","k","l"),
            listOf("⇧","z","x","c","v","b","n","m","⌫"),
            listOf("?123",",","🌐","space",".","↵")
        )

        rows.forEach { keys ->
            val row = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER
            }

            keys.forEach { key ->
                val button = Button(this).apply {
                    text = if (key == "space") "English" else key
                    textSize = 15f
                    setTextColor(Color.WHITE)
                    setBackgroundColor(Color.rgb(42, 52, 75))
                    isAllCaps = false
                    minWidth = 0
                    setPadding(0, 0, 0, 0)
                }

                button.setOnClickListener {
                    handleKey(key)
                }

                val params = LinearLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    if (key == "space") 3f else 1f
                )
                params.setMargins(2, 3, 2, 3)
                row.addView(button, params)
            }

            root.addView(
                row,
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            )
        }

        return root
    }

    private fun handleKey(key: String) {
        val ic: InputConnection = currentInputConnection ?: return

        when (key) {
            "⌫" -> ic.deleteSurroundingText(1, 0)
            "↵" -> ic.performEditorAction(
                android.view.inputmethod.EditorInfo.IME_ACTION_UNSPECIFIED
            )
            "space" -> ic.commitText(" ", 1)
            "⇧", "?123", "🌐" -> return
            else -> ic.commitText(key, 1)
        }
    }
}
