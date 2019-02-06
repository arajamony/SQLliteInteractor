package com.gmail.rocka.sqlliteinteractor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gmail.rocka.sqlliteinteractor.Handler.DatabaseHandler
import com.gmail.rocka.sqlliteinteractor.Models.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context=this
        var db: DatabaseHandler = DatabaseHandler(this)

        btnInsert.setOnClickListener {
            if (EdtName.text.isNotEmpty() && EdtAge.text.isNotEmpty()) {
                var user: User = User(EdtName.text.toString(), EdtAge.text.toString().toInt())
                var result = db.insertData(user)

                if (result == (-1).toLong()) {
                    Toast.makeText(context, "Failed to insert the details", Toast.LENGTH_LONG).show()
                }
                else {
                    Toast.makeText(context, "User details inserted Successfully", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(context, "Please fill all the details", Toast.LENGTH_LONG).show()
            }
        }

        btnRead.setOnClickListener {
            var data =db.ReadData()
            tvResult.text=""
           for (i in 0..(data.size-1) ){
               tvResult.append("${data.get(i).id} ${data.get(i).name} ${data.get(i).age}\n")
           }

        }

        btnUpdate.setOnClickListener {
            db.updateData()
            btnRead.performClick()
        }

        btnDelete.setOnClickListener {
            db.deleteData()
            btnRead.performClick()
        }

    }
}
