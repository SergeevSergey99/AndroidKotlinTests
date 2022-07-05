package com.example.webtest

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.BoringLayout
import android.view.Menu
import android.view.View
import android.webkit.*
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.webtest.databinding.ActivityMainBinding
import com.example.webtest.databinding.ContentMainBinding

class MainActivity : AppCompatActivity() {

    var networkAvailable = false
    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingCobtent: ContentMainBinding
    lateinit var mWebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        bindingCobtent = ContentMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        setContentView(bindingCobtent.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        mWebView = bindingCobtent.webView
        var url = getString(R.string.url)
        mWebView.getSettings().setJavaScriptEnabled(true);

        loadWebSite(mWebView, url, applicationContext)
        bindingCobtent.swipeRefreshLayout.setColorSchemeColors(R.color.black, R.color.purple_200, R.color.teal_200)
        bindingCobtent.swipeRefreshLayout.apply {
            setOnRefreshListener {
                if(mWebView.url != null)
                    url = mWebView.url.toString()
                loadWebSite(mWebView, url, applicationContext)
            }
        }


        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
    }

    private fun loadWebSite(mWebView: WebView, url: String, context: Context) {
        bindingCobtent.progressBar.visibility = View.VISIBLE
        networkAvailable = isNetworkAvailable(context)
        mWebView.clearCache(true)
        if (networkAvailable) {
            wvVisible(mWebView)
            mWebView.loadUrl(url)
            mWebView.webViewClient = WebViewClient()
        } else {
            wvGone(mWebView)
        }
    }

    private fun onLoadSite() {
        bindingCobtent.swipeRefreshLayout.isRefreshing = false
        bindingCobtent.progressBar.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    private fun wvVisible(mWebView: WebView) {
        mWebView.visibility = View.VISIBLE
        bindingCobtent.textViewCheckConnection.visibility = View.GONE
    }

    private fun wvGone(mWebView: WebView) {
        mWebView.visibility = View.GONE
        bindingCobtent.textViewCheckConnection.visibility = View.VISIBLE
    }

    @Suppress("DEPRECATION")
    private fun isNetworkAvailable(context: Context): Boolean {
        try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return if (Build.VERSION.SDK_INT > 22) {
                val an = cm.activeNetwork ?: return false
                val capabilities = cm.getNetworkCapabilities(an) ?: return false
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            } else {
                val a = cm.activeNetworkInfo ?: return false
                a.isConnected && (a.type == ConnectivityManager.TYPE_WIFI || a.type == ConnectivityManager.TYPE_MOBILE)
            }
        } catch (e: Exception) {
        }
        return false

    }


    private inner class MyWebViewClient : WebViewClient() {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            val url = request?.url.toString()
            return urlOverride(url)
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
            return urlOverride(url)
        }

        private fun urlOverride(url: String): Boolean {
            bindingCobtent.progressBar.visibility = View.VISIBLE
            networkAvailable = isNetworkAvailable(applicationContext)

            if (networkAvailable) {
                if (Uri.parse(url).host == getString(R.string.url)) return false
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
                onLoadSite()
                return true
            } else {
                wvGone(bindingCobtent.webView)
                return false
            }
        }

        @Suppress("DEPRECATION")
        override fun onReceivedError(
            view: WebView?,
            errorCode: Int,
            description: String?,
            failingUrl: String?
        ) {
            super.onReceivedError(view, errorCode, description, failingUrl)

            if (errorCode == 0) {
                view?.visibility = View.GONE
                bindingCobtent.textViewCheckConnection.visibility = View.VISIBLE
                onLoadSite()
            }

        }

        @TargetApi(Build.VERSION_CODES.M)
        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            onReceivedError(view, error!!.errorCode, error.description.toString(), request?.url.toString())
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            onLoadSite()
        }

    }

}