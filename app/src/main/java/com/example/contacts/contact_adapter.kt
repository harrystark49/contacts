package com.example.contacts

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.each_contact_res.view.*

class contact_adapter(var context: Context,var contact_list: ArrayList<contact_model>,var show_delete_icon:(Boolean)->Unit):RecyclerView.Adapter<contact_adapter.view>(){
     var selectedlist=ArrayList<String>()
    var helper=myDBHelper(context)
    var  db=helper.readableDatabase

    var x=false
    var seleteditemmode=false
    inner class view(var view:View):RecyclerView.ViewHolder(view){
       fun data_set(contact:contact_model?,pos: Int){
           itemView.name_of_contact.text="Name: "+ contact!!.name
           itemView.mail_of_contact.text="Email: "+contact!!.email
           itemView.number_of_contact.text="Number: "+contact!!.number


           itemView.linear.setOnLongClickListener {

               if(seleteditemmode==false){
                   markselecteditems(pos)
                   seleteditemmode=true
               }
               true
           }
           if(seleteditemmode==true && contact.is_seleted==false){
               itemView.linear.setOnClickListener {
                   markselecteditems(pos)
               }
           }
           else{
               itemView.linear.setOnClickListener {
                   deselectitem(pos)
               }
           }

           if(contact.is_seleted==true){
               itemView.seleted_item.visibility=View.VISIBLE
           }else{
               itemView.seleted_item.visibility=View.GONE
           }
        }

    }

    private fun deselectitem(pos: Int) {

            var name=contact_list.get(pos).name
            if(selectedlist.contains(name)){
                selectedlist.remove(name)
        }
        contact_list.get(pos).is_seleted=false
        notifyDataSetChanged()
        show_delete_icon(selectedlist.isNotEmpty())
    }

        private fun markselecteditems(pos: Int):Boolean {
            for(i in contact_list){
                i.is_seleted=false
            }
            var s=contact_list.get(pos)
            var name=s.name
            if(!selectedlist.contains(name)){
                selectedlist.add(name)
            }
            selectedlist.forEach{
                for(i in contact_list){
                    if(i.name==it){
                        i.is_seleted=true
                    }
                }
            }

            show_delete_icon(true)
            notifyDataSetChanged()
            return true
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
        var rview=LayoutInflater.from(context).inflate(R.layout.each_contact_res,parent,false)
        return  view(rview)
    }

    override fun onBindViewHolder(holder: view, position: Int) {
        var num=contact_list[position]
        holder.data_set(num,position)
    }

    override fun getItemCount(): Int {
        return contact_list.size
    }

    fun delete_seleted_items() {
        for(i in selectedlist){
            helper.deleteData(i)
        }
        contact_list.removeAll { item->item.is_seleted==true }
        notifyDataSetChanged()
    }
}

