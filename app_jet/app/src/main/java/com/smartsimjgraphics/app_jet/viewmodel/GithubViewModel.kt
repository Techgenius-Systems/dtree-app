package com.smartsimjgraphics.app_jet.viewmodel;


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartsimjgraphics.app_jet.model.GithubResponse
import com.smartsimjgraphics.app_jet.network.RetrofitService
import com.smartsimjgraphics.app_jet.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class GithubViewModel: ViewModel() {

    var githubDataLst = MutableLiveData<List<GithubResponse>>()

    fun getApiData(){

        val retrofitService = RetrofitInstance.getRetrofitIinstance().create(RetrofitService::class.java)
        retrofitService.getData().enqueue(object : Callback<List<GithubResponse>>{
            override fun onResponse(
                call: Call<List<GithubResponse>>,
                response: Response<List<GithubResponse>>
            ) {
                //success
                githubDataLst.value = response.body()


            }

            override fun onFailure(call: Call<List<GithubResponse>>, t: Throwable) {
                //Faillure



            }
        })

    }

}