package com.victor.xapogithubtrending.features.repository_details

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.victor.xapogithubtrending.R
import com.victor.xapogithubtrending.base.BaseActivity
import com.victor.xapogithubtrending.features.state.ViewResource
import com.victor.xapogithubtrending.features.state.ViewState
import com.victor.xapogithubtrending.model.RepositoryView
import com.victor.xapogithubtrending.util.myObserver
import kotlinx.android.synthetic.main.activity_github_repository_details.*
import kotlinx.android.synthetic.main.content_github_repository_details.*

import javax.inject.Inject

class GithubRepositoryDetailsActivity : BaseActivity() {

    @Inject
    lateinit var githubRepositoryDetailsViewModel: GithubRepositoryDetailsViewModel

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

        githubRepositoryDetailsViewModel.loadGithubRepositories(repositoryOwnerName!!,repositoryName!!)

    }

    private fun handleViewResources( resource : ViewResource<RepositoryView>){
        when(resource.status){
            ViewState.SUCCESS -> {
                loading_bar.visibility = View.GONE
                populateView(resource.data!!)
            }
            ViewState.LOADING ->{
                loading_bar.visibility = View.VISIBLE
            }
            ViewState.ERROR ->{
                showError(resource.message)
                loading_bar.visibility = View.GONE
            }
        }
    }

    private fun populateView(repositoryView: RepositoryView){
        tv_description.text = repositoryView.repoDescription
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
