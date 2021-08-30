package com.example.contacts

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.register_res.*

class Register:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_res)
        var helper = myDBHelper(this)
        var db = helper.readableDatabase
        Register_details.setOnClickListener {
            onregister()
            if (reg_uname.text.toString().isNotEmpty() && reg_pass.text.toString()
                    .isNotEmpty() && reg_confirm_pass.text.toString()
                    .isNotEmpty() && reg_name.text.toString().isNotEmpty()
            ) {
                var uname = reg_uname.text.toString()
                var pass = reg_pass.text.toString()
                var pass2 = reg_confirm_pass.text.toString()
                var name = reg_name.text.toString()
                var args = arrayOf(uname)
                var cursor = db.rawQuery("SELECT UNAME FROM ADMIN WHERE UNAME=?", args)
                if (!cursor.moveToNext()) {
                    if (pass == pass2) {
                        Toast.makeText(this, "Registration succesfull", Toast.LENGTH_LONG).show()
                        var cv = ContentValues()
                        cv.put("UNAME", uname)
                        cv.put("PASS", pass)
                        cv.put("NAME", name)
                        db.insert("ADMIN", null, cv)
                        var intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "password mismatch", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(
                        this,
                        "Username already exists please select another one",
                        Toast.LENGTH_LONG
                    ).show()
                    reg_uname.setText("")
                    reg_pass.setText("")
                    reg_confirm_pass.setText("")
                    reg_name.setText("")
                    reg_uname.requestFocus()
                }
            }
        }

    }

    private fun onregister() {
        if(reg_uname.text.toString().isEmpty()){
            input_reg_uname.error="UserName  is Mandatory"
        }else{
            input_reg_uname.isErrorEnabled=false
        }
        if(reg_name.text.toString().isEmpty()){
            input_reg_name.error="Name  is Mandatory"
        }else{
            input_reg_name.isErrorEnabled=false
        }
        if(reg_pass.text.toString().isEmpty()){
            input_reg_pass.error="Password is Mandatory"
        }else{
            input_reg_pass.isErrorEnabled=false
        }
        if(reg_confirm_pass.text.toString().isEmpty()){
            input_reg_confirm_pass.error="Confirm Password is Mandatory"
        }else{
            input_reg_confirm_pass.isErrorEnabled=false
        }
    }

}