package com.xamk.sumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Ads
        MobileAds.initialize(this) {}

        //Numbers
        com00.setOnClickListener { appendOnClick(true, "00") }
        com0.setOnClickListener { appendOnClick(true, "0") }
        com1.setOnClickListener { appendOnClick(true, "1") }
        com2.setOnClickListener { appendOnClick(true, "2") }
        com3.setOnClickListener { appendOnClick(true, "3") }
        com4.setOnClickListener { appendOnClick(true, "4") }
        com5.setOnClickListener { appendOnClick(true, "5") }
        com6.setOnClickListener { appendOnClick(true, "6") }
        com7.setOnClickListener { appendOnClick(true, "7") }
        com8.setOnClickListener { appendOnClick(true, "8") }
        com9.setOnClickListener { appendOnClick(true, "9") }
        comDot.setOnClickListener { appendOnClick(true, ".") }

        //Operators
        comPlus.setOnClickListener { appendOnClick(false, "+") }
        comMinus.setOnClickListener { appendOnClick(false, "-") }
        comMulti.setOnClickListener { appendOnClick(false, "*") }
        comDiv.setOnClickListener { appendOnClick(false, "/") }
        comleft.setOnClickListener { appendOnClick(false, "(") }
        comright.setOnClickListener { appendOnClick(false, ")") }

        comClear.setOnClickListener {
            clear()
        }

        comEqual.setOnClickListener {
        calculate()
        }
}

//now create methods

private fun appendOnClick(clear: Boolean, string: String) {

    if (clear) {
        comOut.text = ""
        comIn.append(string)
    } else {
        comIn.append(comOut.text)
        comIn.append(string)
        comOut.text = ""
    }
}

private fun clear() {

    comIn.text = ""
    comOut.text = ""
}

private fun calculate() {

    try {
        val input = ExpressionBuilder(comIn.text.toString()).build()
        val output = input.evaluate()
        val longOutput = output.toLong()
        if (output == longOutput.toDouble()){
            comOut.text = longOutput.toString()
        }else{
            comOut.text = output.toString()
        }
    } catch (e:Exception){
        Toast.makeText(this@MainActivity,e.message,Toast.LENGTH_LONG).show()
    }
}
}