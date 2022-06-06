package com.distantfutures.a20220606_az_nycschools.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.distantfutures.a20220606_az_nycschools.data.School

@BindingAdapter("listData")
fun bindRecyclerView(recycler: RecyclerView, data: List<School>?) {
    val adapter = recycler.adapter as SchoolsRecyclerViewAdapter
    adapter.submitList(data)
}