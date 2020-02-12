package com.example.gitrepositoriesapicall.di.component

import com.example.gitrepositoriesapicall.view.MainActivity
import com.example.gitrepositoriesapicall.di.modules.GitHubPresenterModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(GitHubPresenterModule::class))
interface GitHubRepositoryComponent {
    fun inject(mainActivity: MainActivity)
}