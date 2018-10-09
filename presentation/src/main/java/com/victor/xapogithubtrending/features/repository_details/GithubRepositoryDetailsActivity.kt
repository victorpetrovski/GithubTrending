package com.victor.xapogithubtrending.features.repository_details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.victor.xapogithubtrending.R
import com.victor.xapogithubtrending.base.BaseActivity
import com.victor.xapogithubtrending.features.state.ViewResource
import com.victor.xapogithubtrending.features.state.ViewState
import com.victor.xapogithubtrending.model.RepositoryView
import com.victor.xapogithubtrending.util.RecyclerItemClickListener
import com.victor.xapogithubtrending.util.SpanningGridLayoutManager
import com.victor.xapogithubtrending.util.myObserver
import kotlinx.android.synthetic.main.activity_github_repository_details.*
import kotlinx.android.synthetic.main.content_github_repository_details.*
import java.text.SimpleDateFormat

import javax.inject.Inject

class GithubRepositoryDetailsActivity : BaseActivity() {

    @Inject
    lateinit var githubRepositoryDetailsViewModel: GithubRepositoryDetailsViewModel

    @Inject
    lateinit var contributorsAdapter : ContributorsAdapter

    var repositoryName : String? = null

    var repositoryOwnerName : String? = null

    companion object {
        val REPOSITORY_NAME = "com.victor.xapogithubtrending.features.repository_details.GithubRepositoryDetailsActivity.REPOSITORY_NAME"
        val OWNER_NAME = "com.victor.xapogithubtrending.features.repository_details.GithubRepositoryDetailsActivity.OWNER_NAME"

    }

    override fun getLayout() = R.layout.activity_github_repository_details

    override fun setupViews(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)

        githubRepositoryDetailsViewModel.getLiveData().myObserver(this){
            it?.let {
                handleViewResources(it)
            }
        }

        repositoryName = intent.getStringExtra(REPOSITORY_NAME)
        repositoryOwnerName = intent.getStringExtra(OWNER_NAME)


        title = repositoryName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        githubRepositoryDetailsViewModel.loadGithubRepositories(repositoryOwnerName!!,repositoryName!!)
        setupRecyclerView()

    }

    private fun handleViewResources( resource : ViewResource<RepositoryView>){
        when(resource.status){
            ViewState.SUCCESS -> {
                loading_bar.visibility = View.GONE

                cl_holder.visibility = View.VISIBLE
                fab_repo_website.visibility = View.VISIBLE
                populateView(resource.data!!)
            }
            ViewState.LOADING ->{
                cl_holder.visibility = View.GONE
                fab_repo_website.visibility = View.GONE
                loading_bar.visibility = View.VISIBLE
            }
            ViewState.ERROR ->{
                showError(resource.message)
                cl_holder.visibility = View.GONE
                fab_repo_website.visibility = View.GONE
                loading_bar.visibility = View.GONE
            }
        }
    }

    private fun populateView(repositoryView: RepositoryView){
        tv_project_name.text = repositoryView.name
        tv_owner_name.text = repositoryView.ownerName
        tv_description.text = repositoryView.repoDescription
        tv_creation_date.text = formatDate(repositoryView.dateCreated)

        Glide.with(this)
                .load(repositoryView.ownerAvatar)
                .into(iv_owner_avatar)

        repository_info_cell_watch.setValue(R.drawable.ic_watch,getString(R.string.watch),repositoryView.watchCount.toString())
        repository_info_star.setValue(R.drawable.ic_star_black,getString(R.string.star),repositoryView.starCount.toString())
        repository_info_fork.setValue(R.drawable.ic_repo_forked,getString(R.string.fork),repositoryView.forkCount.toString())

        repositoryView.contributorsList?.let {
            contributorsAdapter.addAll(it)
        }

        fab_repo_website.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/${repositoryView.ownerName}/${repositoryView.name}"))
            startActivity(browserIntent)
        }

    }

    private fun formatDate(dateString : String) : String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val date = dateFormat.parse(dateString)
        val formatter = SimpleDateFormat("MM.dd.yyyy")
        return formatter.format(date)
    }


    private fun setupRecyclerView() {
        val mLayoutManager = GridLayoutManager(baseContext,4,GridLayoutManager.VERTICAL,false)
        rv_contributors.layoutManager = mLayoutManager
        rv_contributors.adapter = contributorsAdapter

    }

    var snackbar: Snackbar? = null

    private fun showError(message: String?) {
        snackbar = Snackbar.make(parentLayout(), message.toString(), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.close)) {
                    snackbar?.dismiss()
                }
                .setActionTextColor(resources.getColor(android.R.color.holo_red_light))

        snackbar?.show()
    }

    private fun dismissError() {
        snackbar?.dismiss()
    }

}
