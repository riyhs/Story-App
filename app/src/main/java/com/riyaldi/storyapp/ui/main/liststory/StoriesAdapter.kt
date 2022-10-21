package com.riyaldi.storyapp.ui.main.liststory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.riyaldi.storyapp.R
import com.riyaldi.storyapp.model.stories.Story
import kotlinx.android.synthetic.main.card_stories.view.*

class StoriesAdapter(private val stories: List<Story>) : RecyclerView.Adapter<StoriesAdapter.StoriesViewHolder>() {
    inner class StoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Story) {
            with(itemView) {
                tv_item_name.text = item.name
                tv_story_desc.text = item.description
                iv_item_photo.apply {
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    load(item.photoUrl) {
                        crossfade(750)
                        build()
                    }
                }

                setOnClickListener {
                    this.findNavController().navigate(
                        ListStoryFragmentDirections.actionListStoryFragmentToDetailStoryFragment(
                            item.name,
                            item.description,
                            item.photoUrl
                        )
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.card_stories,
            parent,
            false
        )
        return StoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        holder.bind(stories[position])
    }

    override fun getItemCount(): Int = stories.size
}