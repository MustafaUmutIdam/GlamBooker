package com.example.glambooker.data.entity

import java.io.Serializable

data class Filter(val cityFilter:String,
                  val townFilter:String,
                  val categoryFilter:String) : Serializable


