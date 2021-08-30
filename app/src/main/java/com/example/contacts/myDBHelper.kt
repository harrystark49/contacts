package com.example.contacts

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class myDBHelper(context: Context): SQLiteOpenHelper(context,"USERDB",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE ADDCONTACTNUMBER(NAME VARCHAR2,EMAIL VARCHAR2,NUMBER VARCHAR2)")
        db?.execSQL("CREATE TABLE ADMIN(UNAME VARCHAR2,PASS VARCHAR2,NAME VARCHAR2)")
    }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("DROP TABLE IF EXISTS ADDCONTACTNUMBER")
            db?.execSQL("DROP TABLE IF EXISTS ADMIN")
        }
    public fun deleteData(name:String){
        var db:SQLiteDatabase=this.writableDatabase
        var arr= arrayOf(name)
            db.delete("ADDCONTACTNUMBER","NAME=?", arr)
            db.close()

    }
}