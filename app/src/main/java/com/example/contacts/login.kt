package com.example.contacts

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.add_contact_res.*
import kotlinx.android.synthetic.main.login_res.*

class login:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_res)
        var bundle=intent.extras
        var s= bundle?.get("name").toString()
        uname.text="Welcome $s"


        add.setOnClickListener {
            val intent= Intent(this,Add_Contact::class.java)

            startActivity(intent)
        }
        display_contacts.setOnClickListener {
            val intent= Intent(this,display_numbers::class.java)
            startActivity(intent)
        }
        delete.setOnClickListener {
            val intent= Intent(this,delete_contact::class.java)
            startActivity(intent)
        }
        signout.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}