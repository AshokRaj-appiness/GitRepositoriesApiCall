package com.example.gitrepositoriesapicall.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gitrepositoriesapicall.R
import com.example.gitrepositoriesapicall.model.Item
import com.example.gitrepositoriesapicall.view.viewHolder.GitHubViewHolder

class GitHubAdapter: RecyclerView.Adapter<GitHubViewHolder>() {
    private val gitHubList = ArrayList<Item>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubViewHolder {
        return GitHubViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false))
    }

    override fun getItemCount(): Int {
        return gitHubList.size
    }

    override fun onBindViewHolder(holder: GitHubViewHolder, position: Int) {
        holder.textView.text = gitHubList.get(position).fullName
    }

    fun setData(list:List<Item>){
        this.gitHubList.clear()
        this.gitHubList.addAll(list)
    }
}