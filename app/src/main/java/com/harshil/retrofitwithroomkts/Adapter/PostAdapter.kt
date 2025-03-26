package com.harshil.retrofitwithroomkts.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.harshil.retrofitwithroomkts.Model.PostModel
import com.harshil.retrofitwithroomkts.R

class PostAdapter : ListAdapter<PostModel, RecyclerView.ViewHolder>(PostDiffCallback()) {

    enum class VIEW_TYPE(i: Int) {
        TITLE(0),
        POST(1)
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).body.contains("TITLE")) {
            VIEW_TYPE.TITLE.ordinal
        } else {
            VIEW_TYPE.POST.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE.TITLE.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_header, parent, false)
                TitleViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_row, parent, false)
                PostViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is PostViewHolder -> holder.bind(item)
            is TitleViewHolder -> holder.bind(item)
        }
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.title)
        private val bodyTextView: TextView = itemView.findViewById(R.id.body)

        fun bind(postModel: PostModel) {
            titleTextView.text = "Post ${postModel.id}"
            bodyTextView.text = postModel.body
        }
    }

    class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val headerTextView: TextView = itemView.findViewById(R.id.header)

        fun bind(postModel: PostModel) {
            headerTextView.text = "TITLE"
            headerTextView.setTextColor(Color.CYAN)
            headerTextView.textSize = 18f
            headerTextView.setPadding(16, 16, 16, 16)
        }
    }

    class PostDiffCallback : DiffUtil.ItemCallback<PostModel>() {
        override fun areItemsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
            return if (oldItem.body.contains("TITLE") && newItem.body.contains("TITLE")) {
                oldItem.body == newItem.body
            } else {
                oldItem.id == newItem.id
            }
        }

        override fun areContentsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
            return oldItem == newItem
        }
    }
}
