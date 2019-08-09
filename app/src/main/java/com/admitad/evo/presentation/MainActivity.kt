package com.admitad.evo.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.admitad.evo.R
import com.admitad.evo.domain.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val mainPresenter: MainPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        printed_blocks_counter.text = mainPresenter.getPrintedBlocksCounter().toString()
    }
}
