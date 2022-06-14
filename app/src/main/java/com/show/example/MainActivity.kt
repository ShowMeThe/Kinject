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
            val main: Main = factory(currentScope)
            Log.e("22222", "main == $main")

            val maina: MainA = factory(currentScope)
            Log.e("22222", "maina == $maina")
        }

        btn2.setOnClickListener {
            startActivity(Intent(this,MainActivity2::class.java))
        }

    }
}