package com.smartsimjgraphics.app_jet.adapter;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.smartsimjgraphics.app_jet.R
import com.smartsimjgraphics.app_jet.model.GithubResponse

class GithubAdapter(private var mList: List<GithubResponse>):
    RecyclerView.Adapter<GithubAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){


        val title: MaterialTextView = itemView.findViewById(R.id.tvTitle)
        val date: MaterialTextView = itemView.findViewById(R.id.tvDate)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_github_list, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = mList[position].title
        holder.date.text = mList[position].created_at
    }

    override fun getItemCount(): Int {
        return mList.size
    }


}