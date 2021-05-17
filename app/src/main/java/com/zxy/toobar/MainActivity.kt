package com.zxy.toobar

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
        zToolbar.addOnToolbarListener(onBack = {
            Toast.makeText(this, "返回1", Toast.LENGTH_LONG).show()
        }, onIvRight1 = {
            Toast.makeText(this, "分享1", Toast.LENGTH_LONG).show()
        }, onIvRight2 = {
            Toast.makeText(this, "分享2", Toast.LENGTH_LONG).show()
        }, ontvRight = {
            Toast.makeText(this, "提交", Toast.LENGTH_LONG).show()
        })

    }
}
