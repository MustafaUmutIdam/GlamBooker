package com.example.glambooker.data.entity

import java.io.Serializable

data class Workplace(var id:String? = "",
                     var boss:String? = "",
                     var category:String? = "",
                     var name:String? = "",
                     var detail:String? = "",
                     var city:String? = "",
                     var town:String? = "",
                     var rate:String? = "",
                     var imageName:String? = "") : Serializable