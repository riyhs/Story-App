package com.riyaldi.storyapp.ui.main.liststory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.riyaldi.storyapp.databinding.CardStoriesBinding
import com.riyaldi.storyapp.model.stories.Story

class StoriesAdapter(private val callback: (story: Story, imageView: View, nameView: View, descView: View) -> Unit)
    : ListAdapter<Story, StoriesViewHolder>(object : DiffUtil.ItemCallback<Story>() {
    override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
        return oldItem.id == newItem.id
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
        val view = CardStoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        val item = getItem(position)
        holder.view.root.setOnClickListener{
            callback.invoke(
                item,
                holder.view.ivItemPhoto,
                holder.view.tvItemName,
                holder.view.tvStoryDesc,
            )
        }
        holder.bind(item)
    }
}

class StoriesViewHolder(val view: CardStoriesBinding) : RecyclerView.ViewHolder(view.root) {
    fun bind(item: Story) {
        view.story = item

        val drawable = CircularProgressDrawable(view.root.context)
        drawable.strokeWidth = 5f
        drawable.centerRadius = 30f
        drawable.start()

        view.ivItemPhoto.load(item.photoUrl) {
            placeholder(drawable)
            allowHardware(false)
        }

        view.executePendingBindings()
    }
}