package com.example.gitrepositoriesapicall.model.mvi

import com.example.gitrepositoriesapicall.model.Item

data class GitHubState(val isLoaderVisible:Boolean = false,val isSwipeRefreshLayout:Boolean = false,val itemList:List<Item>? = null,val error:Throwable? = null)
