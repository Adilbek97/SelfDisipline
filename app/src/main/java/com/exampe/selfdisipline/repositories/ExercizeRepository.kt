package com.exampe.selfdisipline.repositories

import com.exampe.selfdisipline.model.Exercise
import java.util.ArrayList

class ExercizeRepository {
    val exercises = ArrayList<Exercise>()

    fun getExercises():List<Exercise>{
        exercises.add(Exercise("title1"))
        exercises.add(Exercise("title2"))
        exercises.add(Exercise("title3"))
        exercises.add(Exercise("title4",isDone = true))
        exercises.add(Exercise("title5"))
        exercises.add(Exercise("title6"))
        exercises.add(Exercise("title7",isDone = true))
        exercises.add(Exercise("title8"))
        exercises.add(Exercise("title9",isDone = true))
        exercises.add(Exercise("title10"))
        exercises.add(Exercise("title11"))
        exercises.add(Exercise("title12"))
        exercises.add(Exercise("title13"))
        exercises.add(Exercise("title14",isDone = true))
        return exercises
    }

}