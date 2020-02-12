package com.example.gitrepositoriesapicall.model.mvi

import com.example.gitrepositoriesapicall.model.GitRepository

sealed class PartialGitHubState {
    object showLoader:PartialGitHubState()
    object showSwipeRefreshLayout:PartialGitHubState()
    data class RepositoryAvailable(val gitRepository: GitRepository?):PartialGitHubState()
    data class Error(val error:Throwable?):PartialGitHubState()
}