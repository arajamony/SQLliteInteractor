package com.gmail.rocka.sqlliteinteractor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnInsert.setOnClickListener({
            if(EdtName.text.toString().length>0 && EdtAge.text.toString().length>0)
            {

            }
        })
    }
}
