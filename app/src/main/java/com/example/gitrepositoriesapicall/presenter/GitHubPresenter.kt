package com.example.gitrepositoriesapicall.presenter

import com.example.gitrepositoriesapicall.model.mvi.GitHubState
import com.example.gitrepositoriesapicall.model.mvi.PartialGitHubState
import com.example.gitrepositoriesapicall.usecases.GitHubUseCases
import com.example.gitrepositoriesapicall.view.MviGitHubView
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GitHubPresenter:MviBasePresenter<MviGitHubView,GitHubState>() {
    override fun bindIntents() {
        val defaultLoader = intent(MviGitHubView::emitByDefault)
            .flatMap {
                GitHubUseCases.loadGitHubRepositoriesDefault()
            }
            .startWith(PartialGitHubState.showLoader)
            .onErrorReturn {
                PartialGitHubState.Error(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val swipeRefreshLoader = intent(MviGitHubView::emitOnSwipe)
            .flatMap {
                GitHubUseCases.loadGitHubRepositoryWhenSwipe()
            }
            .startWith(PartialGitHubState.showSwipeRefreshLayout)
            .onErrorReturn {
                PartialGitHubState.Error(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val initialState = GitHubState()

        val finalobservable = Observable.merge(defaultLoader,swipeRefreshLoader)
        val resultState = finalobservable.scan(initialState,{
            previousState:GitHubState,currentState:PartialGitHubState ->
            when(currentState){
                is PartialGitHubState.showLoader ->
                    GitHubState(
                        isLoaderVisible = true,
                        isSwipeRefreshLayout = false,
                        itemList = null,
                        error = null
                    )
                is PartialGitHubState.showSwipeRefreshLayout ->
                    GitHubState(
                        isLoaderVisible = false,
                        isSwipeRefreshLayout = true,
                        itemList = null,
                        error = null
                    )
                is PartialGitHubState.RepositoryAvailable ->
                    GitHubState(
                        isLoaderVisible = false,
                        isSwipeRefreshLayout = false,
                        itemList = currentState.gitRepository?.items,
                        error = null
                    )
                is PartialGitHubState.Error ->
                    GitHubState(
                        isLoaderVisible = false,
                        isSwipeRefreshLayout = false,
                        itemList = null,
                        error = currentState.error
                    )
            }
        })
        subscribeViewState(resultState,MviGitHubView::render)
    }
}