package com.example.fitnessapp.feature.schedule.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.base.state.LoadingState
import com.example.fitnessapp.feature.schedule.model.LessonModel
import com.example.fitnessapp.feature.schedule.ui.recycler.LessonsListAdapter
import com.example.fitnessapp.feature.schedule.viewModel.ScheduleViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class LessonsFragment : Fragment() {
    private val viewModel: ScheduleViewModel by viewModel(named("ScheduleViewModel"))

    private val lessonsAdapter = LessonsListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_lessons, container, false)
        viewModel.onViewShown()
        root.findViewById<RecyclerView>(R.id.schedule_list).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = lessonsAdapter
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (val loadingState = state.loadingState) {
                        LoadingState.Loading -> showLoading()
                        LoadingState.Success -> showSuccess(state.lessons)
                        is LoadingState.Error -> showError(loadingState.message)
                    }
                }
            }
        }
        return root
    }

    private fun showLoading() {
        view?.findViewById<ConstraintLayout>(R.id.loading_state)?.visibility = View.VISIBLE
        view?.findViewById<ConstraintLayout>(R.id.error_state)?.visibility = View.GONE
        view?.findViewById<LinearLayout>(R.id.layout)?.visibility = View.GONE
    }

    private fun showSuccess(lessons: List<LessonModel>) {
        view?.findViewById<ConstraintLayout>(R.id.loading_state)?.visibility = View.GONE
        view?.findViewById<ConstraintLayout>(R.id.error_state)?.visibility = View.GONE
        view?.findViewById<LinearLayout>(R.id.layout)?.visibility = View.VISIBLE
        lessonsAdapter.updateList(lessons)
    }

    private fun showError(error: String) {
        view?.findViewById<ConstraintLayout>(R.id.loading_state)?.visibility = View.GONE
        view?.findViewById<ConstraintLayout>(R.id.error_state)?.visibility = View.VISIBLE
        view?.findViewById<LinearLayout>(R.id.layout)?.visibility = View.GONE
        view?.findViewById<TextView>(R.id.error_message)?.text = error
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onViewHidden()
    }
}