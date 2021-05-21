package com.zxy.zxytoolbar

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.titlebar.view.*


/**
 * Created by zxy on 2020/6/1 0001 15:52
 * ******************************************
 * * 自定义Title
 * ******************************************
 */
class ZToolbar : LinearLayout {
    lateinit var viewLayout:View
    var mContext: Context? = null
    var rightTextColor: Int? = null
    var titleTextColor: Int? = null
    var rightImg1: Drawable? = null
    var rightImg2: Drawable? = null
    var leftImg: Drawable? = null
    var rightText: String? = null
    var titleText: String? = null
    var titleSize: Int? = null
    var rightTextSize: Int? = null
    var attrs: AttributeSet? = null

    @SuppressLint("ResourceType")
    constructor(
        context: Context?,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        this.mContext = context
        this.attrs = attrs

        viewLayout = LayoutInflater.from(mContext).inflate(R.layout.titlebar, null)
        val layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        viewLayout.layoutParams = layoutParams
        addView(viewLayout)
        initView()
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        initView()
    }

    private fun initView() {
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
                R.styleable.ZXY_leftImg ->
                    leftImg = array.getDrawable(R.styleable.ZXY_leftImg)

                R.styleable.ZXY_titleSize ->
                    titleSize = px2sp(array.getDimensionPixelSize(R.styleable.ZXY_titleSize,20))
                R.styleable.ZXY_rightTextSize ->
                    rightTextSize = px2sp(array.getDimensionPixelSize(R.styleable.ZXY_rightTextSize,18))
            }
        }

        array.recycle()//回收
        initListener()
    }

    private fun px2sp(pxValue: Int): Int {
        return (pxValue / getDisplayMetrics(context).scaledDensity + 0.5f).toInt()
    }

    private fun getDisplayMetrics(context: Context?): DisplayMetrics {
        val manager = context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val metrics = DisplayMetrics()
        manager.defaultDisplay.getMetrics(metrics)
        return metrics
    }


    /**
     * 点击事件
     * @param onBack    返回按钮
     * @param onIvRight2 右边第一个
     * @param onIvRight1 右边第二个
     * @param ontvRight  右边提交
     */
    fun addOnToolbarListener(
        OnBack: (LinearLayout) -> Unit,
        OnIvRight1: ((LinearLayout) -> Unit) = {},
        OnIvRight2: ((LinearLayout) -> Unit) = {},
        OntvRight: ((LinearLayout) -> Unit) = {}
    ) {
        llTitleLeft.setOnClickListener {
            OnBack(it as LinearLayout)
        }
        llTitleRightIv1.setOnClickListener {
            OnIvRight1(it as LinearLayout)
        }
        llTitleRightIv2.setOnClickListener {
            OnIvRight2(it as LinearLayout)
        }
        llTitleRightTv.setOnClickListener {
            OntvRight(it as LinearLayout)
        }
    }

    private fun initListener() {

        //返回按钮
        if (leftImg != null) {
            viewLayout.ivTitleBack.setImageDrawable(leftImg)
            viewLayout.llTitleRightIv1.visibility = View.VISIBLE
        }

        //右边第一个图片
        if (rightImg1 != null) {
            viewLayout.ivTitleRightIv1.setImageDrawable(rightImg1)
            viewLayout.llTitleLeft.visibility = View.VISIBLE
        }
        //右边第二个图片
        if (rightImg2 != null) {
            viewLayout.ivTitleRightIv2.setImageDrawable(rightImg2)
            viewLayout.llTitleRightIv2.visibility = View.VISIBLE
        }
        //保存
        if (rightText != null) {
            viewLayout.llTitleRightTv.visibility = View.VISIBLE
            viewLayout.tvTitleRight.text = rightText
            if (rightTextColor != null) {//颜色
                viewLayout.tvTitleRight.setTextColor(rightTextColor!!)
            }
        }
        if (titleText != null) {
            viewLayout.tvTitle.visibility = View.VISIBLE
            viewLayout.tvTitle.text = titleText
            if (titleTextColor != null) {
                viewLayout.tvTitle.setTextColor(titleTextColor!!)
            }
        }

        if(titleSize!=null){
            viewLayout.tvTitle.textSize = titleSize!!.toFloat()
        }
        if(rightTextSize!=null){
            viewLayout.tvTitleRight.textSize = rightTextSize!!.toFloat()
        }

        viewLayout.tvTitle.isSelected = tvTitle.text.length > 9  //开启关闭走马灯
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    /**
     * 更新UI
     */
    fun update() {
        postInvalidate()
        requestLayout()
        invalidate()
    }

}