package com.victor.xapogithubtrending.features.repository_details

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.victor.xapogithubtrending.R
import com.victor.xapogithubtrending.model.GithubUserView
import javax.inject.Inject

/**
 * Created by victor on 10/8/18
 */
class ContributorsAdapter @Inject constructor(val context: Context) : RecyclerView.Adapter<ContributorsAdapter.GithubContributorViewHolder>() {

    var listOfContributors: MutableList<GithubUserView> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun addAll(repoList : List<GithubUserView>){
        listOfContributors.addAll(repoList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubContributorViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.contributor_list_item, parent, false)
        return GithubContributorViewHolder(itemView)
    }

    override fun getItemCount() = listOfContributors.size

    override fun onBindViewHolder(holder: GithubContributorViewHolder, position: Int) {
        holder.bindData(listOfContributors[position])
    }

    inner class GithubContributorViewHolder( view : View) : RecyclerView.ViewHolder(view) {

        private val  ivOwnerAvatar : ImageView = view.findViewById(R.id.iv_owner_avatar)

        fun bindData(githubUserView: GithubUserView){

            Glide.with(context)
                    .load(githubUserView.avatarUrl)
                    .into(ivOwnerAvatar)
        }
    }
}