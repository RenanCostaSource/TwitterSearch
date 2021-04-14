package com.fuze.ttapp.ui.tweetsearch

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.fuze.ttapp.api.models.Twit
import com.fuze.ttapp.api.models.TwitterProfile
import com.fuze.ttapp.domain.TweetSearchUseCase
import com.fuze.ttapp.ui.BaseViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserProfileViewModel (val tweetSearchUseCase: TweetSearchUseCase): BaseViewModel()  {

    var searchUserLiveData = MutableLiveData<TwitterProfile>()
    fun searchUserObservable() = searchUserLiveData

    var userTweetLiveData = MutableLiveData<List<Twit>>()
    fun searchTweetObservable() = userTweetLiveData

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
        compositeDisposable = CompositeDisposable()
    }

    fun searchUser(screen_name: String){
        addDisposable(  userObservable(screen_name).subscribe({
            searchUserLiveData.postValue(it)
        }, {
            Log.e("==error==", "${it}")
        })
        )
        addDisposable(  tweetObservable(screen_name).subscribe({

            userTweetLiveData.postValue(it)
        },{
            Log.e("==error==","${it}")
        })
        )
    }

    private fun userObservable(screen_name: String) : Observable<TwitterProfile>{
        return tweetSearchUseCase.getUserProfile(screen_name).observeOn(Schedulers.io()).subscribeOn(Schedulers.io())
    }

    private fun tweetObservable(query: String) : Observable<List<Twit>>{
        return tweetSearchUseCase.getUserTweets(query).observeOn(Schedulers.io()).subscribeOn(Schedulers.io())
    }

    fun unSubscribeUser(){
        clearDispose()
        initCompositeDisposable()
    }
}
