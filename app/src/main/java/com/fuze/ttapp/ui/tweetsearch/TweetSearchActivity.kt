package com.fuze.ttapp.ui.tweetsearch

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fuze.ttapp.R
import com.fuze.ttapp.api.models.Twit
import com.fuze.ttapp.ui.MainActivity
import kotlinx.android.synthetic.main.activity_tweet_search.*
import javax.inject.Inject

const val USER = "com.fuze.ttapp.MESSAGE"
class TweetSearchActivity : MainActivity<TweetSearchViewModel>() {

    @Inject   lateinit var tweetSearchViewModel: TweetSearchViewModel
    private var tweetAdapter: TweetAdapter? = null

    override fun getViewModel(): TweetSearchViewModel  = tweetSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet_search)
            setUpUI()

    }

    private fun setUpUI(){
        setUpRecyclerView()
        setUpSearchView()
        bindViewModels()
    }

    private fun setUpRecyclerView(){
        var linearLayoutManager= LinearLayoutManager(this)
        rv_tweet.layoutManager = linearLayoutManager
        tweetAdapter = TweetAdapter(this)
        rv_tweet.adapter = tweetAdapter
     }

    fun searchTweets(q:String){
        progress_bar.visibility=View.VISIBLE
        tweetSearchViewModel.search(q)
    }

    fun bindViewModels(){
        tweetSearchViewModel.searchTweetObservable().observe(this, Observer {

            setData(it)
        })
    }

    fun setData(it: List<Twit>?) {
        progress_bar.visibility=View.GONE

        it?.let { it1 ->  tweetAdapter?.tweetsList = it1 }
    }

    private fun setUpSearchView(){

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    unsubscribeTweet()
                    searchTweets(it) }
                return true
             }

            override fun onQueryTextChange(p0: String?) = false
        }
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()

        unsubscribeTweet()
    }

    fun unsubscribeTweet(){
        tweetSearchViewModel.unSubscribeTweet()
    }

}
