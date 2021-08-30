package com.example.contacts

import android.content.ContentValues
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.add_contact_res.*
import kotlinx.android.synthetic.main.login_res.*

class Add_Contact:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var helper = myDBHelper(this)
        var db = helper.readableDatabase

        setContentView(R.layout.add_contact_res)

        Add_number.setOnClickListener {
            signup()
            if (new_name.text.toString().isNotEmpty() && email.text.toString()
                    .isNotEmpty() && mobile_number.text.toString().isNotEmpty()
            ) {
                var cv = ContentValues()
                cv.put("NAME", new_name.text.toString())
                cv.put("EMAIL", email.text.toString())
                cv.put("NUMBER", mobile_number.text.toString())
                db.insert("ADDCONTACTNUMBER", null, cv)
                new_name.setText("")
                email.setText("")
                mobile_number.setText("")
                new_name.requestFocus()
                Toast.makeText(this, "Contact Succefully Added", Toast.LENGTH_LONG).show()

            }
        }
    }

    private fun signup() {
        if(new_name.text.toString().isEmpty()){
            input_new_name.error="Name is Mandatory"
        }
        else{
            input_new_name.isErrorEnabled=false
        }
        if(email.text.toString().isEmpty()){
            input_email.error="Email is Mandatory"
        }else{
            input_email.isErrorEnabled=false
        }
        if(mobile_number.text.toString().isEmpty()){
            inputmobile_number.error="Number is Mandatory"
        }else{
            inputmobile_number.isErrorEnabled=false
        }
    }
}