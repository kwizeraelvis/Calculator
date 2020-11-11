package com.elvis.kotlinapps.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException


class MainActivity : AppCompatActivity() {

    var lastNumeric : Boolean = false
    var lastDot : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View){
        txt_input.append((view as Button).text)
        lastNumeric = true
    }
    fun onClear(view: View){
        txt_input.text = ""
        lastNumeric = false
        lastDot = false
    }
    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            txt_input.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        if(lastNumeric && !isOperatorAdded(txt_input.text.toString())){
            txt_input.append((view as Button).text)
            Toast.makeText(this, txt_input.text, Toast.LENGTH_LONG).show();
            lastNumeric = false
            lastDot = false
        }
    }
    private fun isOperatorAdded(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("*")
                    || value.contains("+") || value.contains("-")
        }
    }

    fun onEquals(view: View ){
        if(lastNumeric){
            var input_value = txt_input.text.toString()
            var prefix = ""
            try {
                if(input_value.startsWith("-")){
                    prefix = "-"
                    input_value = input_value.substring(1)
                }
                if(input_value.contains("-")){
                    var splitValue = input_value.split("-")
                    var value_one = splitValue[0]
                    var value_two = splitValue[1]
                    if(!prefix.isNotEmpty()){
                        value_one = prefix + value_one
                    }
                    txt_input.text = (value_one.toDouble() - value_two.toDouble()).toString()
                } else if(input_value.contains("+")){
                    var splitValue = input_value.split("+")
                    var value_one = splitValue[0]
                    var value_two = splitValue[1]
                    if(!prefix.isNotEmpty()){
                        value_one = prefix + value_one
                    }
                    txt_input.text = (value_one.toDouble() + value_two.toDouble()).toString()
                } else if(input_value.contains("*")){
                    var splitValue = input_value.split("*")
                    var value_one = splitValue[0]
                    var value_two = splitValue[1]
                    if(!prefix.isNotEmpty()){
                        value_one = prefix + value_one
                    }
                    txt_input.text = (value_one.toDouble() * value_two.toDouble()).toString()
                } else if(input_value.contains("/")){
                    var splitValue = input_value.split("/")
                    var value_one = splitValue[0]
                    var value_two = splitValue[1]
                    if(!prefix.isNotEmpty()){
                        value_one = prefix + value_one
                    }
                    txt_input.text = (value_one.toDouble() / value_two.toDouble()).toString()
                } else {
                    Toast.makeText(this, "Illegal Operation", Toast.LENGTH_SHORT).show()
                }
            }catch (e: ArithmeticException){

            }
        }
    }
}