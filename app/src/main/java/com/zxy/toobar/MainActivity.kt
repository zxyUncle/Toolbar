package com.zxy.toobar

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gyf.immersionbar.ImmersionBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()

        ImmersionBar.with(this)
            .keyboardEnable(true)
            .navigationBarWithKitkatEnable(true)
            .statusBarDarkFont(true)//导航栏图标
            .fitsSystemWindows(true)//解决布局和状态栏重叠
            .statusBarColor(R.color.colorPrimaryDark)
            .init()
    }

    private fun initToolbar() {
    }
}
