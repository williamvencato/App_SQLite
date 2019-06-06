package com.example.app_sqlite.model

class Usuario {
    var id       : Int = 0
    var user     : String? = null
    var password : String? = null

    constructor(){}

    constructor(id:Int, user:String, password:String) {
        this.id = id
        this.user = user
        this.password = password
    }


}