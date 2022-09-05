package com.example.test

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.graphics.Typeface
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {

    fun getScreenWidth(): Int {
        return Resources.getSystem().getDisplayMetrics().widthPixels
    }

    fun getScreenHeight(): Int {
        return Resources.getSystem().getDisplayMetrics().heightPixels
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginFunc = findViewById<Button>(R.id.btn_login)
        val user = "ichirofelipe"
        val pass = "admin123"
        val userInput = findViewById<EditText>(R.id.username)
        val passInput = findViewById<EditText>(R.id.password)
        var showPass : Boolean = false;

        loginFunc.setOnClickListener {
            val thisUser = userInput.text.toString()
            val thisPass = passInput.text.toString()
            if(thisUser == user && thisPass == pass){
                val intent = Intent(this, HomeActivity::class.java)

                startActivity(intent)

            }

        }

        passInput.setOnTouchListener(OnTouchListener { v, event ->
            val DRAWABLE_LEFT = 0
            val DRAWABLE_TOP = 1
            val DRAWABLE_RIGHT = 2
            val DRAWABLE_BOTTOM = 3

            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= passInput.getRight() - passInput.getCompoundDrawables()
                        .get(DRAWABLE_RIGHT).getBounds().width() - 30
                ) {
                    if(!showPass){
                        passInput.setInputType(InputType.TYPE_CLASS_TEXT)
                        passInput.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_eye_off, 0);
                    }
                    else{
                        passInput.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
                        passInput.setTypeface(Typeface.DEFAULT)
                        passInput.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_eye, 0);
                    }
                    showPass = !showPass
//                    Log.d("screen", "width: ${getScreenWidth().toString()} - height: ${getScreenHeight().toString()}")
                    return@OnTouchListener true
                }
            }
            false
        })
    }
}
