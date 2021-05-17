package com.zxy.zxytoolbar

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.titlebar.view.*


/**
 * Created by zxy on 2020/6/1 0001 15:52
 * ******************************************
 * * 自定义Title
 * ******************************************
 */
class ZToolbar : LinearLayout {
    var mContext: Context? = null
    var rightTextColor: Int? = null
    var titleTextColor: Int? = null
    var rightImg1: Drawable? = null
    var rightImg2: Drawable? = null
    var rightText: String? = null
    var titleText: String? = null
    var titleSize: Int? = null

    @SuppressLint("ResourceType")
    constructor(
        context: Context?,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        this.mContext = context

        //取出自定义Attr的属性分组
        val array =
            context!!.theme.obtainStyledAttributes(attrs, R.styleable.ZXY, R.attr.attr_zxy, 0)
        //遍历，这边只有一个自定义attr属性，循环可有可无
        for (index in 0 until array.indexCount) {
            when (array.getIndex(index)) {
                R.styleable.ZXY_rightTextColor ->
                    rightTextColor = array.getColor(R.styleable.ZXY_rightTextColor, Color.BLACK)
                R.styleable.ZXY_titleTextColor ->
                    titleTextColor = array.getColor(R.styleable.ZXY_titleTextColor, Color.BLACK)
                R.styleable.ZXY_rightText ->
                    rightText = array.getString(R.styleable.ZXY_rightText)
                R.styleable.ZXY_titleText ->
                    titleText = array.getString(R.styleable.ZXY_titleText)
                R.styleable.ZXY_rightImg1 ->
                    rightImg1 = array.getDrawable(R.styleable.ZXY_rightImg1)
                R.styleable.ZXY_rightImg2 ->
                    rightImg2 = array.getDrawable(R.styleable.ZXY_rightImg2)
            }
        }

        array.recycle()//回收
        init()
    }

    /**
     * 点击事件
     * @param onBack    返回按钮
     * @param onIvRight2 右边第一个
     * @param onIvRight1 右边第二个
     * @param ontvRight  右边提交
     */
    fun addOnToolbarListener(
        onBack: (LinearLayout) -> Unit,
        onIvRight1:((LinearLayout) -> Unit)={},
        onIvRight2: ((LinearLayout) -> Unit)={},
        ontvRight: ((LinearLayout) -> Unit)={}
    ) {
        llTitleLeft.setOnClickListener {
            onBack(it as LinearLayout)
        }
        llTitleRightIv1.setOnClickListener {
            onIvRight1(it as LinearLayout)
        }
        llTitleRightIv2.setOnClickListener {
            onIvRight2(it as LinearLayout)
        }
        llTitleRightTv.setOnClickListener {
            ontvRight(it as LinearLayout)
        }
    }

    private fun init() {
        var titleView = LayoutInflater.from(mContext).inflate(R.layout.titlebar, null)
        val layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        titleView.layoutParams = layoutParams
        addView(titleView)
        //右边第一个图片
        if (rightImg1 != null) {
            Glide.with(mContext!!).load(rightImg1).into(titleView.ivTitleRightIv1)
            titleView.llTitleRightIv1.visibility = View.VISIBLE
        }
        //右边第二个图片
        if (rightImg2 != null) {
            Glide.with(mContext!!).load(rightImg2).into(titleView.ivTitleRightIv2)
            titleView.llTitleRightIv2.visibility = View.VISIBLE
        }
        //保存
        if (rightText != null) {
            titleView.llTitleRightTv.visibility = View.VISIBLE
            titleView.tvTitleRight.text = rightText
            if (rightTextColor != null) {//颜色
                titleView.tvTitleRight.setTextColor(rightTextColor!!)
            }
        }
        if (titleText != null) {
            titleView.tvTitle.visibility = View.VISIBLE
            titleView.tvTitle.text = titleText
            if (titleTextColor != null) {
                titleView.tvTitle.setTextColor(titleTextColor!!)
            }
        }

        titleView.tvTitle.isSelected = tvTitle.text.length > 9  //开启关闭走马灯
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

}