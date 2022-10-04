package com.example.hmwrk17

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomtest.databinding.SingleSwipeCategoryLayoutBinding


class SwipeLayoutAdapter : ListAdapter<SwipeItems, SwipeLayoutAdapter.UsersViewHolder>(
    SwipeLayoutDiffCallback()
) {

    private lateinit var itemClickListener: (SwipeItems, Int) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): UsersViewHolder {
        val binding =
            SingleSwipeCategoryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bindData()
    }


    inner class UsersViewHolder(private val binding: SingleSwipeCategoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: SwipeItems? = null
        fun bindData() {
            model = getItem(bindingAdapterPosition)
            binding.apply {
                tvFirstName.text = model?.rategoryName

                binding.mainContainer.setOnClickListener {
                    itemClickListener.invoke(model!!, absoluteAdapterPosition)
                }
            }
        }

    }

    fun setOnItemClickListener(clickListener: (SwipeItems, Int) -> Unit) {
        itemClickListener = clickListener
    }
}



class SwipeLayoutDiffCallback : DiffUtil.ItemCallback<SwipeItems>() {
    override fun areItemsTheSame(
        oldItem: SwipeItems,
        newItem: SwipeItems
    ): Boolean {
        return oldItem.rategoryName == newItem.rategoryName
    }

    override fun areContentsTheSame(
        oldItem: SwipeItems,
        newItem: SwipeItems
    ): Boolean {
        return oldItem == newItem
    }
}