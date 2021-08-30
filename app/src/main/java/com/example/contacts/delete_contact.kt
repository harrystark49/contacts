package com.example.contacts

import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.delecte_res.*

class delete_contact:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var helper = myDBHelper(this)
        var db=helper.readableDatabase
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delecte_res)
        Delete_contact_number.setOnClickListener{

            var text=delete_name.text.toString()
            var array=ArrayList<String>()
            array.add(text)
            var arg= listOf<String>(text).toTypedArray()
            var cursor=db.rawQuery("SELECT NAME FROM ADDCONTACTNUMBER WHERE NAME=?",arg)
           if( cursor.moveToNext()){
            helper.deleteData(text)
            Toast.makeText(this,"Sucessfully Deleted",Toast.LENGTH_LONG).show()
               delete_name.setText("")
               delete_name.requestFocus()

        }else{
               Toast.makeText(this,"enter valid name",Toast.LENGTH_LONG).show()
           }
        }


    }
}