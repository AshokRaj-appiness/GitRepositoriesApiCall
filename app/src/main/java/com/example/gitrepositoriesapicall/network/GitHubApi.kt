package com.example.gitrepositoriesapicall.network

import com.example.gitrepositoriesapicall.model.GitRepository
import io.reactivex.Observable
import okio.Utf8
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {
    @GET("repositories")
    fun getRepositories(@Query("q",encoded = true)q:String,
                        @Query("sort")sort:String,
                        @Query("order")order:String):Observable<GitRepository>
}