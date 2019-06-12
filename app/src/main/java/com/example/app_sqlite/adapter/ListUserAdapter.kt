package com.example.app_sqlite.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.example.app_sqlite.R
import com.example.app_sqlite.model.Usuario
import kotlinx.android.synthetic.main.row_layout.view.*

class ListUserAdapter (internal var activity: Activity,
                       internal var lstUser:List<Usuario>,
                       internal var edt_id:EditText,
                       internal var edt_user:EditText,
                       internal var edt_password:EditText) : BaseAdapter(){

    internal var inflater:LayoutInflater

    init {
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView:View
        rowView = inflater.inflate(R.layout.row_layout, null)

        rowView.txt_row_id.text = lstUser[position].id.toString()
        rowView.txt_user.text = lstUser[position].user.toString()
        rowView.txt_password.text = lstUser[position].password.toString()

        //Event
        rowView.setOnClickListener {
            edt_id.setText(rowView.txt_row_id.text.toString())
            edt_user.setText(rowView.txt_user.text.toString())
            edt_password.setText(rowView.txt_password.text.toString())
        }

        return rowView
    }

    override fun getItem(position: Int): Any {
        return lstUser[position]
    }

    override fun getItemId(position: Int): Long {
        return lstUser[position].id.toLong()
    }

    override fun getCount(): Int {
        return lstUser.size
    }
}