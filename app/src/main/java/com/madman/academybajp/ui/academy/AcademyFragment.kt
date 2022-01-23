package com.madman.academybajp.ui.academy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.madman.academybajp.databinding.FragmentAcademyBinding
import com.madman.academybajp.viewmodel.ViewModelFactory

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
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[AcademyViewModel::class.java]
            val courses = viewModel.getCourses()
            val academyAdapter = AcademyAdapter()

            binding.progressBar.visibility = View.VISIBLE
            viewModel.getCourses().observe(viewLifecycleOwner, {
                binding.progressBar.visibility = View.GONE
                academyAdapter.setCourses(it)
                academyAdapter.notifyDataSetChanged()
            })

            with(binding.rvAcademy) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = academyAdapter
            }
        }
    }

}