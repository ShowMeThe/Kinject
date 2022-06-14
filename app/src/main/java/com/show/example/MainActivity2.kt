package com.show.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.show.kInject.core.ext.currentScope
import com.show.kInject.core.ext.factory
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        btn.setOnClickListener {
            val main: Main? = factory(currentScope)
            Log.e("22222", "main == $main")

            val maina: MainA? = factory(currentScope)
            Log.e("22222", "maina == $maina")
        }

    }
}