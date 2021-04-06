package com.show.example

import android.os.Bundle
import android.util.Log
import com.show.kInject.core.Logger
import com.show.kInject.core.ext.single
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
            val data1 by single<String>()
            val data2 by single<String>("data")
            Log.e("2222222","$data1 $data2")
        }


    }
}