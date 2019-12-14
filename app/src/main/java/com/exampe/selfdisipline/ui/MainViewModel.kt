package com.exampe.selfdisipline.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exampe.selfdisipline.model.Exercise
import com.exampe.selfdisipline.repositories.ExercizeRepository

class MainViewModel(private val exercizeRepository: ExercizeRepository):ViewModel() {
    val exercises by lazy { MutableLiveData<ArrayList<Exercise>>() }
    var position = -1


}