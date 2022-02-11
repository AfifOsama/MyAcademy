package com.madman.academybajp.ui.reader.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.madman.academybajp.data.source.local.entity.ModuleEntity
import com.madman.academybajp.databinding.FragmentModuleContentBinding
import com.madman.academybajp.ui.reader.CourseReaderViewModel
import com.madman.academybajp.viewmodel.ViewModelFactory
import com.madman.academybajp.vo.Status

class ModuleContentFragment : Fragment() {
    private lateinit var binding: FragmentModuleContentBinding
    private lateinit var viewModel: CourseReaderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentModuleContentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            //share viewmodel
            viewModel = ViewModelProvider(requireActivity(), factory)[CourseReaderViewModel::class.java]

            viewModel.selectedModule.observe(viewLifecycleOwner, { moduleEntity ->
                if (moduleEntity != null) {
                    when (moduleEntity.status) {
                        Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                        Status.SUCCESS -> if (moduleEntity.data != null) {
                            binding?.progressBar?.visibility = View.GONE
                            if (moduleEntity.data.contentEntity != null) {
                                populateWebView(moduleEntity.data)
                            }
                            setButtonNextPrevState(moduleEntity.data)
                            if (!moduleEntity.data.read) {
                                viewModel.readContent(moduleEntity.data)
                            }
                        }
                        Status.ERROR -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }

                    binding?.btnNext?.setOnClickListener { viewModel.setNextPage() }
                    binding?.btnPrev?.setOnClickListener { viewModel.setPrevPage() }
                }
            })
        }
    }

    private fun populateWebView(module: ModuleEntity) {
        binding.webView.loadData(module.contentEntity?.content ?: "", "text/html", "UTF-8")

    }

    private fun setButtonNextPrevState(module: ModuleEntity) {
        if (activity != null) {
            when (module.position) {
                0 -> {
                    binding?.btnPrev?.isEnabled = false
                    binding?.btnNext?.isEnabled = true
                }
                viewModel.getModuleSize() - 1 -> {
                    binding?.btnPrev?.isEnabled = true
                    binding?.btnNext?.isEnabled = false
                }
                else -> {
                    binding?.btnPrev?.isEnabled = true
                    binding?.btnNext?.isEnabled = true
                }
            }
        }
    }

    companion object {
        val TAG: String = ModuleContentFragment::class.java.simpleName
        fun newInstance(): ModuleContentFragment = ModuleContentFragment()
    }
}