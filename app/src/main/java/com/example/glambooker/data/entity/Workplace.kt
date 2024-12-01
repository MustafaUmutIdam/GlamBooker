package com.example.glambooker.data.entity

import java.io.Serializable

data class Workplace(val id:Int,
                     val boss:String,
                     val category:String,
                     val name:String,
                     val detail:String,
                     val city:String,
                     val town:String,
                     val rate:String,
                     val imageName:String) : Serializable