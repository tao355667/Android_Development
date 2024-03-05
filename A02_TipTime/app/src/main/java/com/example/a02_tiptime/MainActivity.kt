package com.example.a02_tiptime

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.a02_tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    //绑定对象实例，可以访问activity_main.xml布局中的视图
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        //初始化binding，用于访问activity_main.xml布局中的UI元件（Views对象）
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        //设置root（根/所有）将内容视图设置为activity（活动）的布局的根视图
        setContentView(binding.root)
        //在按钮上设置点击监听器--执行计算小费的方法calculateTip()
        binding.buttonCalculate.setOnClickListener { calculateTip() }
        displayTip(0.0) //一开始就显示小费金额
    }

    //得到输入金额，转化为成本cost
    private fun calculateTip() {
        val inputAmount = binding.costOfServiceEditText.text.toString()
        val cost = inputAmount.toDoubleOrNull()//将toDouble改为toDoubleOrNull，从而避免空String异常
        if (cost == null) {
            displayTip(0.0)
            return
        }
        //根据选择的按钮确定百分比
        val tipPercentage = when (binding.radioGroup.checkedRadioButtonId) {
            R.id.Amazing21 -> 0.21
            R.id.Good16 -> 0.16
            else -> 0.10
        }
        //小费=成本*百分比
        var tip = cost * tipPercentage
        //四舍五入
        if (binding.roundUpTip.isChecked) {
            tip = round(tip) //四舍五入
//            tip = ceil(tip)  //向上取整
//            tip = floor(tip) //向下取整
        }
        //显示小费的金额tipAmount
        binding.tipAmount.text = tip.toString()

        displayTip(tip)
        hintKeyBoard() //计算完成后，隐藏小键盘
    }

    //显示小费的方法
    fun displayTip(tip: Double){
        //货币格式显示
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip) //货币格式
        binding.tipAmount.text = getString(R.string.tip_amount,formattedTip)
    }

    //隱藏软键盘
    private fun hintKeyBoard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm.isActive && currentFocus != null) {
            if (currentFocus!!.windowToken != null) {
                imm.hideSoftInputFromWindow(
                    currentFocus!!.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }
    }
}