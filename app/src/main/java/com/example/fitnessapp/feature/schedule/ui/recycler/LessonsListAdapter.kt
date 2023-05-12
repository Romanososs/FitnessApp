package com.example.fitnessapp.feature.schedule.ui.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.feature.schedule.model.LessonDate
import com.example.fitnessapp.feature.schedule.model.LessonModel
import com.example.fitnessapp.feature.schedule.model.ScheduleListItem
import com.example.fitnessapp.feature.schedule.model.ScheduleListItem.Companion.DATE_ITEM
import com.example.fitnessapp.feature.schedule.model.ScheduleListItem.Companion.LESSON_CARD_ITEM
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LessonsListAdapter() : RecyclerView.Adapter<ScheduleItemVieHolder>() {
    private var list: List<ScheduleListItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleItemVieHolder {
        return when (viewType) {
            DATE_ITEM -> DateItemViewHolder(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.date_view, parent, false)
            )
            else -> {
                LessonItemViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.schedule_card, parent, false)
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].type
    }

    override fun onBindViewHolder(holder: ScheduleItemVieHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(scheduleList: List<ScheduleListItem>) {
        list = scheduleList
        notifyDataSetChanged()
    }
}

abstract class ScheduleItemVieHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: ScheduleListItem)
}

class LessonItemViewHolder(view: View) : ScheduleItemVieHolder(view) {
    private val startTimeView: TextView = view.findViewById(R.id.start_time)
    private val endTimeView: TextView = view.findViewById(R.id.end_time)
    private val titleView: TextView = view.findViewById(R.id.title)
    private val coachNameView: TextView = view.findViewById(R.id.coach_name)
    private val placeNameView: TextView = view.findViewById(R.id.place_name)

    override fun bind(item: ScheduleListItem) {
        val lesson = item as LessonModel
        startTimeView.text = lesson.lesson.startTime
        endTimeView.text = lesson.lesson.endTime
        titleView.text = lesson.lesson.name
        coachNameView.text = lesson.coach?.fullName
        placeNameView.text = lesson.lesson.place
    }
}

class DateItemViewHolder(view: View) : ScheduleItemVieHolder(view) {
    private val dateView: TextView = view.findViewById(R.id.date)

    override fun bind(item: ScheduleListItem) {
        val date = item as LessonDate
        dateView.text = date.date.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM"))
    }
}