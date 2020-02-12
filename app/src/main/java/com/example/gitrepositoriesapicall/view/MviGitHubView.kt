package com.example.gitrepositoriesapicall.view

import com.example.gitrepositoriesapicall.model.mvi.GitHubState
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

interface MviGitHubView:MvpView {
    fun emitByDefault():Observable<Boolean>
    fun emitOnSwipe():Observable<Unit>
    fun render(gitHubState: GitHubState)
}