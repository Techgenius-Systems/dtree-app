package com.smartsimjgraphics.app_jet;

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartsimjgraphics.app_jet.adapter.GithubAdapter
import com.smartsimjgraphics.app_jet.model.GithubResponse
import com.smartsimjgraphics.app_jet.ui.theme.App_jetTheme
import com.smartsimjgraphics.app_jet.viewmodel.GithubViewModel
//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ComponentActivity() {


    private lateinit var githubViewModel: GithubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //WebViewPage(url = "https://d-tree-org.github.io/exercise-646d/")
            initGitHubAPi()
        }
    }

    fun initGitHubAPi(){

        githubViewModel = ViewModelProvider(this).get(GithubViewModel::class.java)
        githubViewModel.getApiData()
        githubViewModel.githubDataLst.observe(this, {
            initAdapter(it)
        })

    }

    private fun initAdapter(data: List<GithubResponse>) {

        val rvGithubList: RecyclerView = findViewById(R.id.rvGithubList)
        rvGithubList.layoutManager = LinearLayoutManager(this)
        val adapter = GithubAdapter(data)

        rvGithubList.adapter = adapter

    }

}

class WebAppInterface(private val mContext: Context) {
    /** Show a toast from the web page  */
    @JavascriptInterface
    fun showToast(toast: String) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun WebViewPage(url: String){
    AndroidView(factory = {
        WebView(it).apply { 
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            addJavascriptInterface(WebAppInterface(getContext()), "Android")
            loadUrl(url)
        }
    },  update = {
        it.loadUrl(url)
    })
    
}