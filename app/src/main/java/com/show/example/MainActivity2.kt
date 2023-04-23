package com.show.example

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.show.kInject.core.ext.factory
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        btn.setOnClickListener {
            val main: MainA? = factory("123", "12312")
            Log.e("22222", "main == $main")
        }

    }
}