package com.example.a01_first_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    /*
    因Android的app有生命周期，故入口是OnCreate而不是main函数
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //变量sum
    var sum = 0
    //变量sum自增1，之后显示
    fun increment(view: View) {
        sum+=1  //sum自增1
        showScores(sum)
    }
    //变量sum自减1，之后显示
    fun decrement(v: View) {
        sum-- //sum自减1
        showScores(sum)
    }
    //显示sum值
    private fun showScores(sum: Int) {
        val textView: TextView = findViewById(R.id.textView)
        textView.text = sum.toString()
    }
}