package com.exampe.selfdisipline.ui.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.exampe.selfdisipline.R
import com.exampe.selfdisipline.model.Exercise
import com.exampe.selfdisipline.ui.MainActivity
import com.exampe.selfdisipline.ui.MainFragment
import com.exampe.selfdisipline.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class AddFragment : Fragment() {
    val mainViewModel:MainViewModel by sharedViewModel()
    val exercises by lazy { ArrayList<Exercise>() }
    lateinit var exercize:Exercise
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.exercises.value?.let {
            exercises.addAll(it)
        }
        add_btn.setOnClickListener {
            exercises.add(Exercise(title = et_title.text.toString(),description = et_description.text.toString()))
            mainViewModel.exercises.value = exercises
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main_fragment, MainFragment()
            ).commit()
        }
    }

}
