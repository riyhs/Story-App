package com.riyaldi.storyapp.ui.main.liststory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.card.MaterialCardView
import com.riyaldi.storyapp.R
import com.riyaldi.storyapp.model.stories.Story
import kotlinx.android.synthetic.main.card_stories.view.*

class StoriesAdapter(private val stories: List<Story>, listener: StorySelectedListener) : RecyclerView.Adapter<StoriesAdapter.StoriesViewHolder>() {
    private var onClickListener: StorySelectedListener = listener

    inner class StoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val storyImageView: ImageView = itemView.findViewById(R.id.iv_item_photo)

        fun bind(item: Story) {
            with(itemView) {
                tv_item_name.text = item.name
                tv_story_desc.text = item.description
                iv_item_photo.apply {
                    transitionName = item.photoUrl
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    load(item.photoUrl) {
                        allowHardware(false)
                        build()
                    }
                }

                itemView.setOnClickListener{
                    onClickListener.onStorySelected(item.name, item.description, item.photoUrl, storyImageView)
                }

//                setOnClickListener {
//                    this.findNavController().navigate(
//                        ListStoryFragmentDirections.actionListStoryFragmentToDetailStoryFragment(
//                            item.name,
//                            item.description,
//                            item.photoUrl
//                        )
//                    )
//                }
            }
        }
    }

    interface StorySelectedListener {
        fun onStorySelected(name: String, description: String, url: String, storyImageView: ImageView)
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