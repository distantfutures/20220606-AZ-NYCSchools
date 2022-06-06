package com.distantfutures.a20220606_az_nycschools

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.distantfutures.a20220606_az_nycschools.data.School
import com.distantfutures.a20220606_az_nycschools.databinding.SchoolItemBinding

class MySchoolsRecyclerViewAdapter : ListAdapter<School, MySchoolsRecyclerViewAdapter.SchoolViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        return SchoolViewHolder(SchoolItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<School>() {
        override fun areItemsTheSame(oldItem: School, newItem: School): Boolean {
            return oldItem.schoolName == newItem.schoolName
        }

        override fun areContentsTheSame(oldItem: School, newItem: School): Boolean {
            return oldItem == newItem
        }
    }
    class SchoolViewHolder(private var binding: SchoolItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: School) {
            binding.data = item
        }
    }

}