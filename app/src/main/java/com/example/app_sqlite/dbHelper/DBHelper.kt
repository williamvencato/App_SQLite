package com.example.app_sqlite.dbHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.app_sqlite.model.User

class DBHelper(context: Context):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {

    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "SQLite_www.db"

        //Table
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

    val allUsers : List<User>

        get() {
            val lstUsers = ArrayList<User>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)

            if(cursor.moveToFirst()) {
                do {
                    val user = User()
                    user.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    user.user = cursor.getString(cursor.getColumnIndex(COL_USER))
                    user.password = cursor.getString(cursor.getColumnIndex(COL_PASSWORD))

                    lstUsers.add(user)
                } while(cursor.moveToNext())
            }
            db.close()
            return lstUsers
        }

        fun addUser(user : User) {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(COL_ID, user.id)
            values.put(COL_USER, user.user)
            values.put(COL_PASSWORD, user.password)

            db.insert(TABLE_NAME, null, values)
            db.close()
        }

        fun updateUser(user : User):Int {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(COL_ID, user.id)
            values.put(COL_USER, user.user)
            values.put(COL_PASSWORD, user.password)

            return db.update(TABLE_NAME, values, "$COL_ID=?", arrayOf(user.id.toString()))
        }

        fun deleteUser(user:User) {
            val db = this.writableDatabase
            db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(user.id.toString()))
            db.close()
        }
}