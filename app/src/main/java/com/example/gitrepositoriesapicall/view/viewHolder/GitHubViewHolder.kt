package com.example.gitrepositoriesapicall.view.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gitrepositoriesapicall.R
import kotlinx.android.synthetic.main.item_view.view.*

class GitHubViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val textView:TextView = itemView.findViewById(R.id.textView)
}