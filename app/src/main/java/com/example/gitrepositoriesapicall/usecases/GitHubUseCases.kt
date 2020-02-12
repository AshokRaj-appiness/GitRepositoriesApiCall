package com.example.gitrepositoriesapicall.usecases

import com.example.gitrepositoriesapicall.model.mvi.PartialGitHubState
import com.example.gitrepositoriesapicall.network.GitHubApi
import com.example.gitrepositoriesapicall.network.RetrofitClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object GitHubUseCases {
    val endPoint = RetrofitClient.getRetrofit().create(GitHubApi::class.java)
    fun loadGitHubRepositoriesDefault():Observable<PartialGitHubState>{
       return endPoint.getRepositories("tetris+language:assembly","stars","desc")
            .map<PartialGitHubState>{
                PartialGitHubState.RepositoryAvailable(it)
            }
            .startWith(PartialGitHubState.showLoader)
            .onErrorReturn {
                PartialGitHubState.Error(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadGitHubRepositoryWhenSwipe():Observable<PartialGitHubState>{
        return endPoint.getRepositories("tetris+language:assembly","stars","desc")
            .map<PartialGitHubState>{
                PartialGitHubState.RepositoryAvailable(it)
            }
            .startWith(PartialGitHubState.showSwipeRefreshLayout)
            .onErrorReturn {
                PartialGitHubState.Error(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}