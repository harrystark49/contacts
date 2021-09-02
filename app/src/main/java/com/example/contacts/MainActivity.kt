package com.example.contacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.contains as contains1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var helper=myDBHelper(this)
        var db=helper.readableDatabase

        register.setOnClickListener {
            var intent= Intent(this,Register::class.java)
            startActivity(intent)
        }
        login.setOnClickListener {
            signin()
            var arg = listOf<String>(PersonName.text.toString()).toTypedArray()
            var curs = db.rawQuery("SELECT * FROM ADMIN WHERE UNAME =? ", arg)

            if (curs.moveToNext()) {
                var args = listOf<String>(
                    PersonName.text.toString(),
                    Password.text.toString()
                ).toTypedArray()
                var cursor = db.rawQuery("SELECT * FROM ADMIN WHERE UNAME =? AND PASS =?", args)

                if (cursor.moveToNext()) {
                    var s = cursor.getString(cursor.getColumnIndex("NAME"))
                    var intent = Intent(this, com.example.contacts.login::class.java)
                    intent.putExtra("name", s)
                    startActivity(intent)
                    PersonName.setText("")
                    Password.setText("")
                    PersonName.requestFocus()
                } else {
                    Toast.makeText(this, "password is wrong", Toast.LENGTH_LONG).show()
                }
            } else {
                PersonName.setText("")
                Password.setText("")
                PersonName.requestFocus()
                Toast.makeText(
                    this,
                    "user is not REGISTERED please register and again login",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    private fun signin() {
        if(PersonName.text.toString().isEmpty()){
            input_name.error="Name is Mandatory"
        }else{
            input_name.isErrorEnabled=false
        }
        if(Password.text.toString().isEmpty()){
            input_pass.error="Password is Mandatory"
        }
        else{
            input_pass.isErrorEnabled=false
        }
    }
}
