package com.example.app_sqlite

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.app_sqlite.adapter.ListUserAdapter
import com.example.app_sqlite.dbHelper.DBHelper
import com.example.app_sqlite.model.Usuario


class MainActivity : AppCompatActivity() {

    internal lateinit var  db:DBHelper
    internal var lstUsuario : List<Usuario> = ArrayList<Usuario>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)

        refreshData()

        btn_cadastrar.setOnClickListener{
            val intent = Intent(this, CadastrarActivity::class.java)
            startActivity(intent)
        }

        btn_entrar.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun refreshData() {
        lstUsuario = db.allUsers
        val adapter = ListUserAdapter(this@MainActivity, lstUsuario, 
        //list
    }
}
