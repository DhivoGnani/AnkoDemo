package com.example.dgnanaratnam.ankopresentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linearLayout {
            button("Login") {
                textSize = 26f
            }.lparams(width = wrapContent) {
                horizontalMargin = dip(5)
                topMargin = dip(10)
            }
        }
    }

    private fun createLayoutUsingAnkoDSL() {
        verticalLayout {
            val name = editText()
            button("Say Hello") {
                onClick { toast("Hello, ${name.text}!") }
            }
        }
    }

    private fun createLayoutUsingPlainKotlin() {
        val act = this
        val layout = LinearLayout(act)
        layout.orientation = LinearLayout.VERTICAL
        val name = EditText(act)
        val button = Button(act)
        button.text = "Say Hello"
        button.setOnClickListener {
            Toast.makeText(act, "Hello, ${name.text}!", Toast.LENGTH_SHORT).show()
        }
        layout.addView(name)
        layout.addView(button)
        setContentView(layout)
    }
}

class MyActivityUI : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            val name = editText()
            button("Say Hello") {
                onClick { ctx.toast("Hello, ${name.text}!") }
            }
        }
    }
}
