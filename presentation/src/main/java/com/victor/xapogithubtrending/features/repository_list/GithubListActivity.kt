package com.victor.xapogithubtrending.features.repository_list

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.victor.xapogithubtrending.R
import com.victor.xapogithubtrending.base.BaseActivity
import com.victor.xapogithubtrending.features.repository_details.GithubRepositoryDetailsActivity
import com.victor.xapogithubtrending.features.state.ViewResource
import com.victor.xapogithubtrending.features.state.ViewState
import com.victor.xapogithubtrending.model.RepositoryView
import com.victor.xapogithubtrending.util.myObserver
import kotlinx.android.synthetic.main.activity_github_list.*
import kotlinx.android.synthetic.main.content_github_list.*
import javax.inject.Inject
import com.victor.xapogithubtrending.util.RecyclerItemClickListener




class GithubListActivity : BaseActivity() {

    val TAG = "GithubListActivity"

    @Inject
    lateinit var githubRepositoryListViewModel: GithubRepositoryListViewModel

    @Inject
    lateinit var githubRepositoryAdapter: GithubRepositoryAdapter

    override fun getLayout() = R.layout.activity_github_list

    override fun setupViews(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)
        setupRecyclerView()

        githubRepositoryListViewModel.getLiveData().myObserver(this) {
            it?.let {
                handleViewState(it)
            }
        }

        loadData()

        view_error.setOnClickListener {
            loadData()
        }

    }

    private fun loadData(){
        githubRepositoryListViewModel.loadGithubRepositories()
    }


    private fun handleViewState(viewResource: ViewResource<List<RepositoryView>>){
        when(viewResource.status){
            ViewState.SUCCESS -> {
                swipe_refresh.isRefreshing = false
                rv_repository_list.visibility = View.VISIBLE
                loading_bar.visibility = View.GONE
                view_error.visibility = View.GONE
                viewResource.data?.let {
                    githubRepositoryAdapter.addAll(it)
                }
            }
            ViewState.LOADING ->{
                loading_bar.visibility = View.VISIBLE
                view_error.visibility = View.GONE
            }
            ViewState.ERROR ->{
                showError(viewResource.message)
                loading_bar.visibility = View.GONE
                swipe_refresh.isRefreshing = false
                view_error.visibility = View.VISIBLE
            }
        }
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



    private fun setupRecyclerView() {
        val mLayoutManager = LinearLayoutManager(baseContext)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_repository_list.layoutManager = mLayoutManager
        rv_repository_list.adapter = githubRepositoryAdapter

        swipe_refresh.setColorSchemeResources(R.color.colorPrimary)

        swipe_refresh.setOnRefreshListener {
            githubRepositoryListViewModel.loadGithubRepositories()
        }

        rv_repository_list.addOnItemTouchListener(
                RecyclerItemClickListener(this, rv_repository_list, object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        openGithubDetailsRepo(githubRepositoryAdapter.listOfRepos[position])
                    }

                    override fun onLongItemClick(view: View, position: Int) {  }
                })
        )
    }

    private fun openGithubDetailsRepo(repositoryView: RepositoryView){
        val intent = Intent(this,GithubRepositoryDetailsActivity::class.java)
        intent.putExtra(GithubRepositoryDetailsActivity.REPOSITORY_NAME,repositoryView.name)
        intent.putExtra(GithubRepositoryDetailsActivity.OWNER_NAME,repositoryView.ownerName)
        startActivity(intent)
    }

}
