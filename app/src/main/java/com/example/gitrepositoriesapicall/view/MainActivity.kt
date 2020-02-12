package com.example.gitrepositoriesapicall.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.example.gitrepositoriesapicall.R
import com.example.gitrepositoriesapicall.di.component.DaggerGitHubRepositoryComponent
import com.example.gitrepositoriesapicall.di.modules.GitHubPresenterModule
import com.example.gitrepositoriesapicall.model.mvi.GitHubState
import com.example.gitrepositoriesapicall.presenter.GitHubPresenter
import com.example.gitrepositoriesapicall.view.adapter.GitHubAdapter
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.jakewharton.rxbinding2.support.v4.widget.refreshes
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : MviActivity<MviGitHubView,GitHubPresenter>(),MviGitHubView {

    @Inject
    lateinit var gitHubPresenter: GitHubPresenter

    private lateinit var itemAdapter: GitHubAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerGitHubRepositoryComponent.builder().gitHubPresenterModule(GitHubPresenterModule()).build().inject(this)
        itemAdapter = GitHubAdapter()
        recyclerView.adapter = itemAdapter
    }

    override fun createPresenter(): GitHubPresenter {
        return gitHubPresenter
    }

    override fun emitByDefault(): Observable<Boolean>  = Observable.just(true).subscribeOn(Schedulers.io())

    override fun emitOnSwipe(): Observable<Unit> = swipeLayout.refreshes()

    override fun render(gitHubState: GitHubState) {
        if(gitHubState.isLoaderVisible){
            progress_circular.visibility = VISIBLE
            swipeLayout.isRefreshing = false
        }else if(gitHubState.isSwipeRefreshLayout){
            progress_circular.visibility = GONE
            swipeLayout.isRefreshing = true
        }else if(gitHubState.itemList!=null){
            progress_circular.visibility = GONE
            swipeLayout.isRefreshing = false
            itemAdapter.setData(gitHubState.itemList)
            itemAdapter.notifyDataSetChanged()
        }else if(gitHubState.error!=null){
            progress_circular.visibility = GONE
            swipeLayout.isRefreshing = false
            Toast.makeText(this,gitHubState.error.message,Toast.LENGTH_SHORT).show()
        }
    }
}
