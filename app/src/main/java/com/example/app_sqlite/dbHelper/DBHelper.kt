package com.example.app_sqlite.dbHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.app_sqlite.model.Usuario

class DBHelper(context: Context):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "SQLite_www.db"

        //tabela
        private val TABLE_NAME = "Users"
        private val COL_ID = "Id"
        private val COL_USER = "User"
        private val COL_PASSWORD = "Password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = ("CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY, $COL_USER TEXT, $COL_PASSWORD TEXT)")
        db!!.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    val allUsers:List<Usuario>
        get() {
            val listaUsuarios = ArrayList<Usuario>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)

            if(cursor.moveToFirst()) {
                do {
                    val user = Usuario()
                    user.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    user.user = cursor.getString(cursor.getColumnIndex(COL_USER))
                    user.password = cursor.getString(cursor.getColumnIndex(COL_PASSWORD))
                    listaUsuarios.add(user)
                } while(cursor.moveToNext())
            }
            db.close()
            return listaUsuarios
        }

        fun addUser(user:Usuario) {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(COL_ID, user.id)
            values.put(COL_USER, user.user)
            values.put(COL_PASSWORD, user.password)

            db.insert(TABLE_NAME, null, values)
            db.close()
        }

        fun updateUser(user:Usuario):Int {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(COL_ID, user.id)
            values.put(COL_USER, user.user)
            values.put(COL_PASSWORD, user.password)

            return db.update(TABLE_NAME, values, "COL_ID=?", arrayOf(user.id.toString()))

        }

        fun deleteUser(user:Usuario):Int {
            val db = this.writableDatabase

            return db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(user.id.toString()))
            db.close()
        }
}