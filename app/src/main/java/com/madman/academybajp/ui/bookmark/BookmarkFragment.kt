package com.madman.academybajp.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.madman.academybajp.R
import com.madman.academybajp.data.CourseEntity
import com.madman.academybajp.databinding.FragmentBookmarkBinding
import com.madman.academybajp.utils.DataDummy

class BookmarkFragment : Fragment(), BookmarkFragmentCallback {
    private lateinit var binding:FragmentBookmarkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentBookmarkBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity!=null){
            val viewModel=ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[BookmarkViewModel::class.java]
            val courses=viewModel.getCourses()
            val adapter=BookmarkAdapter(this)
            adapter.setCourses(courses)

            with(binding.rvBookmark){
                layoutManager=LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter=adapter
            }

        }
    }

    override fun onShareClick(course: CourseEntity) {
        if(activity!=null){
            val mimeType="text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle("Bagikan aplikasi ini sekarang.")
                .setText(resources.getString(R.string.share_text,course.title))
                .startChooser()
        }
    }

}