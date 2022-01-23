package com.madman.academybajp.ui.reader.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.madman.academybajp.data.ModuleEntity
import com.madman.academybajp.databinding.FragmentModuleContentBinding
import com.madman.academybajp.ui.reader.CourseReaderViewModel
import com.madman.academybajp.viewmodel.ViewModelFactory

class ModuleContentFragment : Fragment() {
    private lateinit var binding: FragmentModuleContentBinding
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
            val viewModel =
                ViewModelProvider(requireActivity(), factory)[CourseReaderViewModel::class.java]
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getSelectedModule().observe(viewLifecycleOwner, { module ->
                binding.progressBar.visibility = View.GONE
                if (module != null) {
                    populateWebView(module)
                }
            })
        }
    }

    private fun populateWebView(module: ModuleEntity) {
        binding.webView.loadData(module.contentEntity?.content ?: "", "text/html", "UTF-8")

    }

    companion object {
        val TAG: String = ModuleContentFragment::class.java.simpleName
        fun newInstance(): ModuleContentFragment = ModuleContentFragment()
    }
}