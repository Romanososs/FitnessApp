package com.example.fitnessapp.feature.schedule.ui.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.feature.schedule.model.LessonModel

class LessonsListAdapter() : RecyclerView.Adapter<LessonItemViewHolder>() {
    private var list: List<LessonModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.schedule_card, parent, false)
        return LessonItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newsList: List<LessonModel>) {
        list = newsList
        notifyDataSetChanged()
    }
}

class LessonItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val startTimeView: TextView = view.findViewById(R.id.start_time)
    private val endTimeView: TextView = view.findViewById(R.id.end_time)
    private val titleView: TextView = view.findViewById(R.id.title)
    private val coachNameView: TextView = view.findViewById(R.id.coach_name)
    private val placeNameView: TextView = view.findViewById(R.id.place_name)

    fun bind(lesson: LessonModel) {
        startTimeView.text = lesson.lesson.startTime
        endTimeView.text = lesson.lesson.endTime
        titleView.text = lesson.lesson.name
        coachNameView.text = lesson.coach?.fullName
        placeNameView.text = lesson.lesson.place
    }
}