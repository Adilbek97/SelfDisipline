package com.exampe.selfdisipline.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Exercise(
    var title:String = "",
    var isDone:Boolean = false,
    val date:String = "",
    var description:String = ""
):Parcelable{

}