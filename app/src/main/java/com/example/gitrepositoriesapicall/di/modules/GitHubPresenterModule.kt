package com.example.gitrepositoriesapicall.di.modules
import com.example.gitrepositoriesapicall.presenter.GitHubPresenter
import dagger.Module
import dagger.Provides

@Module
class GitHubPresenterModule {

    @Provides
    fun providePresenter(): GitHubPresenter{
        return GitHubPresenter()
    }
}