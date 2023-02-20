package com.example.newsapiclient.data.db

import androidx.room.TypeConverter
import com.example.newsapiclient.data.model.Source

class Converters {
    //function to return name of the source instead of source object
    @TypeConverter
    fun fromSource(source:Source):String{
        return source.name
    }
     @TypeConverter
    fun toSource(name:String):Source{
        return Source(name,name)
    }
}