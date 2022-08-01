package com.example.githubusersearch

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class GithubReposAdapter(val dataList: List<GitRepos>)
    :RecyclerView.Adapter<GithubReposAdapter.ItemViewHolder>()
{
    class ItemViewHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.view.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(dataList[position].htmlUrl.toString()))
            holder.view.context.startActivity(intent)
        }

        holder.view.findViewById<TextView>(R.id.repo_name).text = dataList[position].name
        holder.view.findViewById<TextView>(R.id.description).text = dataList[position].description
        holder.view.findViewById<TextView>(R.id.forks_count).text = "fork : ${dataList[position].forksCount.toString()}"
        holder.view.findViewById<TextView>(R.id.watchers_count).text = "watchers : ${dataList[position].watchersCount.toString()}"
        holder.view.findViewById<TextView>(R.id.stargazer_count).text = "star : ${dataList[position].stargazerCount.toString()}"


    }

    override fun getItemCount(): Int {
         return dataList.size
    }
    override fun getItemViewType (position: Int) :Int{  //
        return R.layout.github_repos_item
    }
}