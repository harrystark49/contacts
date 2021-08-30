package com.example.contacts

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.diplay_numbers_res.*

class display_numbers:AppCompatActivity() {
    private lateinit var myadaper:contact_adapter
    var  mainmenu: Menu? =null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var bundle: Bundle? =intent.extras
        var uname=bundle?.getString("uname").toString()
        setContentView(R.layout.diplay_numbers_res)

        var temp_contact_model=ArrayList<contact_model>()
        var helper = myDBHelper(this)
        var db = helper.readableDatabase
        var n = db.rawQuery("SELECT * FROM ADDCONTACTNUMBER",null)

        while(n.moveToNext())
            {
                var x = n.getColumnIndex("NAME")
                var y = n.getColumnIndex("EMAIL")
                var z = n.getColumnIndex("NUMBER")

                var name = contact_model(
                    n.getString(x).toString(), n.getString(y).toString(), n.getString(z).toString()
                )
                temp_contact_model.add(name)
            }
        n.close()

        var layoutmanager = LinearLayoutManager(this)
        layoutmanager.orientation = LinearLayoutManager.VERTICAL
        recyclerview.layoutManager = layoutmanager
        myadaper = contact_adapter(this, temp_contact_model){show->hide_delete_icon(show)}
        recyclerview.adapter = myadaper
    }


    fun hide_delete_icon(show:Boolean){
        mainmenu?.findItem(R.id.delete_multiples)?.setVisible(show)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        mainmenu=menu
        hide_delete_icon(false)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_multiples->{
                if(::myadaper.isInitialized){
                    myadaper.delete_seleted_items()
                    hide_delete_icon(false)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}