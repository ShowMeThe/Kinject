package com.show.example

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.show.kInject.core.Logger
import com.show.kInject.core.ext.*
import com.show.kInject.core.initScope
import com.show.kInject.lifecyleowner.module.lifeModule
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<MainViewModel>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.enableLog()

        val viewModel by viewModels<MainViewModel>()

        initScope {
            module(viewModel, lifeModule {
                scopeLifeOwner(viewModel)
            })
        }

        btn.setOnClickListener {
            val main: Main = factory(currentScope, "12312")
            Log.e("22222", "main == $main")

            val main2: Main = factory(currentScope, "12312",1)
            Log.e("22222", "main == $main2")

            val main3: Main = factory(currentScope, "12312",1,1.0)
            Log.e("22222", "main == $main3")
        }

        btn2.setOnClickListener {
            startActivity(Intent(this,MainActivity2::class.java))
        }

    }
}