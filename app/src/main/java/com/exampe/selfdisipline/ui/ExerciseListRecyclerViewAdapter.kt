package com.exampe.selfdisipline.ui

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.exampe.selfdisipline.R
import com.exampe.selfdisipline.model.Exercise
import kotlinx.android.synthetic.main.exercise_item.view.*

class ExerciseListRecyclerViewAdapter(val interaction: (exercise:Exercise,position:Int, detail:Boolean, delete:Boolean, edit:Boolean) -> Unit) :
    ListAdapter<Exercise, ExerciseListRecyclerViewAdapter.ViewHolder>(
        ExerciseDC()
    ) {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        context = parent.context
        return ViewHolder(inflater.inflate(R.layout.exercise_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position),position)
    }

    fun swapData(data: List<Exercise>) {
        submitList(data.toMutableList())
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Exercise,position: Int) {
            itemView.title_tv.text = item.title
            itemView.delete_iv.setOnClickListener {
                interaction(item,position,false,true,false)
            }
            itemView.setOnClickListener {
                interaction(item,position,true,false,false)
            }
            item.isDone.apply {
                if (this){
                    itemView.exercize_card_view.setBackgroundColor(Color.parseColor("#2196F3"))
                    itemView.done_status_iv.setImageResource(R.drawable.ic_alarm_add)
                }else{
                    itemView.exercize_card_view.setBackgroundColor(Color.parseColor("#6F50FA"))
                    itemView.done_status_iv.setImageResource(R.drawable.ic_access_alarm)
                }
            }
        }
    }

    private class ExerciseDC : DiffUtil.ItemCallback<Exercise>() {
        override fun areItemsTheSame(
            oldItem: Exercise,
            newItem: Exercise
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Exercise,
            newItem: Exercise
        ): Boolean {
            return oldItem == newItem
        }
    }
}