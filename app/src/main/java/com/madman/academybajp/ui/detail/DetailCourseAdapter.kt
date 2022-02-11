package com.madman.academybajp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.madman.academybajp.data.source.local.entity.ModuleEntity
import com.madman.academybajp.databinding.ItemsModuleListBinding

class DetailCourseAdapter : RecyclerView.Adapter<DetailCourseAdapter.ViewHolder>() {
    private val listModule = ArrayList<ModuleEntity>()

    fun setModules(modules: List<ModuleEntity>?) {
        if (modules == null) return
        this.listModule.clear()
        this.listModule.addAll(modules)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemModuleListBinding =
            ItemsModuleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemModuleListBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val module = listModule[position]
        holder.bind(module)
    }

    override fun getItemCount(): Int = listModule.size

    class ViewHolder(private val binding: ItemsModuleListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(module: ModuleEntity) {
            binding.textModuleTitle.text = module.title
        }
    }
}