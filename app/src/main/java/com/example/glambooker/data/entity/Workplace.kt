package com.example.glambooker.data.entity

import java.io.Serializable

data class Workplace(val id:Int,
                     val name:String,
                     val detail:String,
                     val rate:String,
                     val imageName:String) : Serializable{


}