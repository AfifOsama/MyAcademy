package com.madman.academybajp.ui.academy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.madman.academybajp.databinding.FragmentAcademyBinding
import com.madman.academybajp.utils.DataDummy

class AcademyFragment : Fragment() {
    private lateinit var binding: FragmentAcademyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAcademyBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val courses = DataDummy.generateDummyCourses()
            val academyAdapter = AcademyAdapter()
            binding.progressBar.visibility = View.GONE
            academyAdapter.setCourses(courses)

            with(binding.rvAcademy) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = academyAdapter
            }
        }
    }

}