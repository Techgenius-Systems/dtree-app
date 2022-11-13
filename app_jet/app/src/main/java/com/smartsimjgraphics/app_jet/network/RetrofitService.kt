package com.smartsimjgraphics.app_jet.network

import com.smartsimjgraphics.app_jet.model.GithubResponse
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    //https://github.com/repos/octocat/hello-world/issues
    @GET("https://d-tree-org.github.io/exercise-646d/")
    fun getData(): Call<List<GithubResponse>>
    
}