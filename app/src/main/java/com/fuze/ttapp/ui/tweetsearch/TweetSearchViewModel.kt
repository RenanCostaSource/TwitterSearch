package com.fuze.ttapp.ui.tweetsearch

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.fuze.ttapp.api.models.TweetsResponse
import com.fuze.ttapp.api.models.Twit
import com.fuze.ttapp.domain.TweetSearchUseCase
import com.fuze.ttapp.ui.BaseViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TweetSearchViewModel(val tweetSearchUseCase: TweetSearchUseCase): BaseViewModel()  {

       var searchTweetLiveData = MutableLiveData<List<Twit>>()
    fun searchTweetObservable() = searchTweetLiveData

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun clearDispose(){

        compositeDisposable?.let {
            if (!it.isDisposed)
                it.dispose()
        }
    }

    fun initCompositeDisposable(){
        compositeDisposable = CompositeDisposable(  )
    }

    fun search(query:String){
            addDisposable(  tweetObservable(query).subscribe({

                  searchTweetLiveData.postValue(it.tweetsList)
              },{
                  Log.e("==error==","${it}")
              })
            )
    }

    private fun tweetObservable(query: String) : Observable<TweetsResponse>{

        return tweetSearchUseCase.searchTweets(query).observeOn(Schedulers.io()).subscribeOn(Schedulers.io())
    }

    fun unSubscribeTweet(){
        clearDispose()
        initCompositeDisposable()

    }

}
