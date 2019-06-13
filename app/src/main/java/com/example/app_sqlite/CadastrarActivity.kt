package com.example.app_sqlite

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.app_sqlite.adapter.ListUserAdapter
import com.example.app_sqlite.dbHelper.DBHelper
import com.example.app_sqlite.model.User
import kotlinx.android.synthetic.main.activity_cadastrar.*

class CadastrarActivity : AppCompatActivity() {

    internal lateinit var  db : DBHelper
    internal var lstUsers : List<User> = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        db = DBHelper(this)

        refreshData()

        btn_add.setOnClickListener() {
            val user = User(
                    Integer.parseInt(edt_id.text.toString()),
                    edt_user.text.toString(),
                    edt_password.text.toString()
            )
            db.addUser(user)
            refreshData()
        }

        btn_update.setOnClickListener() {
            val user = User(
                Integer.parseInt(edt_id.text.toString()),
                edt_user.text.toString(),
                edt_password.text.toString()
            )
            db.updateUser(user)
            refreshData()
        }

        btn_update.setOnClickListener() {
            val user = User(
                Integer.parseInt(edt_id.text.toString()),
                edt_user.text.toString(),
                edt_password.text.toString()
            )
            db.deleteUser(user)
            refreshData()
        }

//        btn_add.setOnClickListener{
//            val intent = Intent(this, CadastrarActivity::class.java)
//            startActivity(intent)
//        }
    }

    private fun refreshData() {
        lstUsers = db.allUsers
        val adapter = ListUserAdapter(this@CadastrarActivity, lstUsers, edt_id, edt_user, edt_password)
        list_users.adapter = adapter
    }
}
