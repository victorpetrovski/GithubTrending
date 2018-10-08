package com.victor.xapogithubtrending.features.repository_list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.victor.xapogithubtrending.R
import com.victor.xapogithubtrending.R.id.tv_owner_name
import com.victor.xapogithubtrending.model.RepositoryView
import javax.inject.Inject

/**
 * Created by victor on 10/6/18
 */
class GithubRepositoryAdapter @Inject constructor(val context: Context) : RecyclerView.Adapter<GithubRepositoryAdapter.GithubRepositoryViewHolder>() {

    var listOfRepos: MutableList<RepositoryView> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun addAll(repoList : List<RepositoryView>){
        listOfRepos.addAll(repoList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubRepositoryViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.repository_list_item, parent, false)
        return GithubRepositoryViewHolder(itemView)
    }

    override fun getItemCount() = listOfRepos.size

    override fun onBindViewHolder(holder: GithubRepositoryViewHolder, position: Int) {
        holder.bindData(listOfRepos[position])
    }

    inner class GithubRepositoryViewHolder( view : View ) : RecyclerView.ViewHolder(view) {

        private val  tvOwnerName : TextView = view.findViewById(R.id.tv_owner_name)
        private val  ivOwnerAvatar : ImageView = view.findViewById(R.id.iv_owner_avatar)
        private val  tvProjectName : TextView = view.findViewById(R.id.tv_project_name)
        private val  tvStarsCount : TextView = view.findViewById(R.id.tv_stars_count)

        fun bindData(repositoryView: RepositoryView){
            tvOwnerName.text = repositoryView.ownerName
            tvProjectName.text = repositoryView.name
            tvStarsCount.text = repositoryView.starCount.toString()

            Glide.with(context)
                    .load(repositoryView.ownerAvatar)
                    .into(ivOwnerAvatar)
        }
    }
}