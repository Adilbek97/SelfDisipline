package com.exampe.selfdisipline.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.exampe.selfdisipline.R
import com.exampe.selfdisipline.repositories.ExercizeRepository
import com.exampe.selfdisipline.ui.add.AddFragment
import com.exampe.selfdisipline.ui.detail.DetailFragment
import kotlinx.android.synthetic.main.fragment_blank.*
import kotlinx.android.synthetic.main.fragment_blank.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment() {
    val exercizeRepository by lazy { ExercizeRepository() }
    val mainViewModel:MainViewModel by sharedViewModel()
    lateinit var ad: AlertDialog.Builder
    val exerciseListRecyclerViewAdapter by lazy {
        ExerciseListRecyclerViewAdapter { exercise, position, detail, delete, edit ->
            mainViewModel.position = position
            if (detail) {
                (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main_fragment,DetailFragment()).addToBackStack(null).commit()
            }
            if (delete) {
                Toast.makeText(context,"delete",Toast.LENGTH_SHORT).show()
//                (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main_fragment,MainFragment()).commit()
                ad = AlertDialog.Builder(context!!)
                ad.setTitle("Are you sure?") // заголовок

//        ad.setMessage(id.message) // сообщение

                ad.setPositiveButton("yes") {dialog, arg1 ->
                    mainViewModel.exercises.value!!.removeAt(position)
                    mainViewModel.exercises.value = mainViewModel.exercises.value!!
                }

                ad.setNegativeButton("no") {dialog, arg1 ->
                    Toast.makeText(context, "Возможно вы правы", Toast.LENGTH_LONG)
                        .show()
                }
                ad.setCancelable(true)
                ad.setOnCancelListener {
                    Toast.makeText(
                        context, "Вы ничего не выбрали",
                        Toast.LENGTH_LONG
                    ).show()
                }
                ad.show()
            }

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_blank, container, false)
        view.add_btn.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main_fragment, AddFragment()
            ).addToBackStack(null).commit()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exercise_list_rv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = exerciseListRecyclerViewAdapter
//            exerciseListRecyclerViewAdapter.swapData(exercizeRepository.getExercises())
//            mainViewModel.exercises.value = exercizeRepository.getExercises()
        }

        mainViewModel.exercises.observe(this, Observer { list ->
            exerciseListRecyclerViewAdapter.swapData(list)
        })
    }
}
