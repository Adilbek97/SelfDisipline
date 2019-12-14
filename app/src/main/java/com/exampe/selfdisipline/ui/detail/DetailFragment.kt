package com.exampe.selfdisipline.ui.detail

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.exampe.selfdisipline.R
import com.exampe.selfdisipline.model.Exercise
import com.exampe.selfdisipline.ui.MainActivity
import com.exampe.selfdisipline.ui.MainFragment
import com.exampe.selfdisipline.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.viewmodel.ext.android.sharedViewModel


class DetailFragment : Fragment() {
    val mainViewModel:MainViewModel by sharedViewModel()
    lateinit var exercize:Exercise
    lateinit var ad: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.exercises.value?.let {
            if (mainViewModel.position != -1){
                exercize = it[mainViewModel.position]
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            tv_title.setText(exercize.title)
            tv_description.setText(exercize.description)
            did_chb.isChecked = mainViewModel.exercises.value!![mainViewModel.position].isDone
        add_btn.setOnClickListener {
            mainViewModel.exercises.value!![mainViewModel.position].apply {
                title = tv_title.text.toString()
                description = tv_description.text.toString()
                isDone = did_chb.isChecked
            }
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main_fragment, MainFragment()
            ).commit()
        }
    }

}
