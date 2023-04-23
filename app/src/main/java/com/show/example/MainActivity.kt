package com.show.example

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.show.kInject.core.Logger
import com.show.kInject.core.bind
import com.show.kInject.core.ext.currentScope
import com.show.kInject.core.ext.factory
import com.show.kInject.core.ext.inject
import com.show.kInject.core.ext.singleFactory
import com.show.kInject.core.initScope
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<MainViewModel>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel by viewModels<MainViewModel>()

        initScope("123") {
            module {
                singleOf {
                    Main("1231")
                }
                factory<MainA, String> {
                    MainA(it)
                }
            }

        }.bind(this.lifecycle)
        initScope(viewModel.currentScope) {
            module { singleOf(viewModel.currentScope) { this@MainActivity } }
        }


        btn.setOnClickListener {
            val main: Main = inject("123")
            Log.e("22222", "main == $main")

            val main2: Main = singleFactory("12312", 1)
            Log.e("22222", "main == $main2")

            val main3: Main = singleFactory("12312", 1, 1.0)
            Log.e("22222", "main == $main3")

            val main4: MainA = factory("123", "12312")
            Log.e("22222", "main == $main4")

            viewModel.get()
        }

        btn2.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

    }
}