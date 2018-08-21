package com.example.dgnanaratnam.ankopresentation

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import android.R.array
import android.widget.ArrayAdapter
import android.widget.Spinner



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        linearLayout {
//            button("Login") {
//                textSize = 26f
//            }.lparams(width = wrapContent) {
//                horizontalMargin = dip(5)
//                topMargin = dip(10)
//            }
//        }

        OrderFormUI().setContentView(this)
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

class RequestFormUI : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            gravity = Gravity.CENTER
            padding = dip(20)

            textView {
                gravity = Gravity.CENTER
                text = "Enter your request"
                textColor = Color.BLACK
                textSize = 24f
            }.lparams(width = matchParent) {
                margin = dip(20)
            }

            val name = editText {
                hint = "What is your name?"
            }

            editText {
                hint = "What is your message?"
                lines = 3
            }

            button("Enter") {
                onClick {
                    toast( "Hey ${name.text}! Thank you for contacting us. We will get in touch with you soon.")
                }
            }.lparams(dip(280), sp(80))

        }
    }

}

class OrderFormUI : AnkoComponent<MainActivity> {
    var numOfConsoles: Int = 0
    var scoreView: TextView? = null

    private fun displayNumOfConsoles()  {
        scoreView?.text = numOfConsoles.toString()
    }

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        val items = arrayOf("PS4", "XBox", "Nitendo Switch")
        var name: EditText? = null
        var spinner: Spinner? = null

        relativeLayout {
            padding = dip(8)

            verticalLayout {
                padding = dip(20)
                gravity = Gravity.CENTER_HORIZONTAL

                textView {
                    gravity = Gravity.CENTER
                    text = "What is your order?"
                    textColor = Color.BLACK
                    textSize = 24f
                }.lparams(width = matchParent) {
                    margin = dip(20)
                }

                name = editText {
                    hint = "Name"
                }

                spinner = spinner {
                    adapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, items)
                }

                linearLayout {
                    gravity = Gravity.CENTER_HORIZONTAL
                    padding = dip(8)
                    button {
                        text = "-"
                        onClick {
                            if (numOfConsoles > 0) {
                                numOfConsoles--
                                displayNumOfConsoles()
                            }
                        }
                    }.lparams(dip(48), dip(48))

                    scoreView = textView {
                        text = numOfConsoles.toString()
                    }.lparams(wrapContent, wrapContent) {
                    }

                    scoreView?.leftPadding = dip(8)
                    scoreView?.rightPadding = dip(8)

                    button {
                        text = "+"
                        onClick {
                            numOfConsoles++
                            displayNumOfConsoles()
                        }
                    }.lparams(dip(48), dip(48))
                }.lparams(wrapContent, wrapContent) {
                }

            }.lparams(height = wrapContent, width = matchParent)

            button("Enter") {
                onClick {
                    toast("Hello ${name?.text}! Thank you for ordering $numOfConsoles ${spinner?.selectedItem}.")
                }
            }.lparams(dip(280), sp(80)) {
                alignParentBottom()
                centerHorizontally()
            }
        }

    }
}

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        linearLayout {
//            button("Login") {
//                textSize = 26f
//            }.lparams(width = wrapContent) {
//                horizontalMargin = dip(5)
//                topMargin = dip(10)
//            }
//        }


        // helper block
//        verticalLayout {
//            button("Ok")
//            button(R.string.username)
//        }

        // themed helper block
//        verticalLayout {
//            themedButton("Ok", theme = R.style.myTheme)
//        }


        // Include tag example
        include<View>(R.layout.activity_main) {
            backgroundColor = Color.RED
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

class TestActivityUI : AnkoComponent<TestActivity> {
    override fun createView(ui: AnkoContext<TestActivity>): View  = with(ui){
        verticalLayout {
            val name = editText()
            button("Say Hello") {
                onClick { ctx.toast("Hello, ${name.text}!") }
            }
        }

    }

}

class SingInView : AnkoComponent<MainActivity> {

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            lparams(width  = matchParent, height = matchParent)

            // Can access context using ctx, extension property

            editText {
                // Do not need to use getString
                hintResource = R.string.username
                textSize = 24f
            }.lparams(width = matchParent, height = wrapContent)

            editText {
                hint = "Password"
                textSize = 24f
            }.lparams(width = matchParent, height = wrapContent)

            button {
                text = "Sign In"
            }.lparams(width = matchParent, height = wrapContent)
        }
    }

}