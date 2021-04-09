package com.fuze.ttapp.domain

import android.text.TextUtils
import android.util.Base64
import android.util.Log
import com.fuze.ttapp.api.ApiService
import com.fuze.ttapp.api.models.TweetsResponse
import com.fuze.ttapp.api.models.Twit
import com.fuze.ttapp.api.models.TwitterProfile
import com.fuze.ttapp.api.models.oauth.OAuthResponse
import com.fuze.ttapp.api.pref.AppPreferences
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.io.UnsupportedEncodingException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TweetSearchUseCase @Inject constructor ( private val mTwitterApi: ApiService ,
                                               private val mSharedPreferences: AppPreferences

) {

    val mConsumerKey: String = "cT1evo5TGNTYkXGjEAUvIUpIS"
    val mConsumerSecret: String = "j9Cc90QbNP8iBlv5HRKissGBXczarghDtJuGyJhYKe2ZBRBNq5"

    private val authorizationHeader: String?
            get() {
        try {
            val consumerKeyAndSecret = "$mConsumerKey:$mConsumerSecret"
            val data = consumerKeyAndSecret.toByteArray(charset("UTF-8"))
            val base64 = Base64.encodeToString(data, Base64.NO_WRAP)
            Log.e("==auth==", base64)
            return "Basic $base64"
        } catch (e: UnsupportedEncodingException) {
            return null
        }
    }

    private val accessToken: String?
            get() {
        val accessToken = mSharedPreferences.accessToken
        if (TextUtils.isEmpty(accessToken)) {
            return null
        }
        return "Bearer " + accessToken!!
    }

    fun searchTweets(query: String)  : Observable<TweetsResponse> {
        Log.i("==refreshing==","getting tweets")
        val accessToken = accessToken
        return if (TextUtils.isEmpty(accessToken)) {
            requestAccessTokenAndGetTweetList(query)
        } else {
            getTweetList(query)
        }
    }


    private fun requestAccessTokenAndGetTweetList(
            query: String
    )  : Observable<TweetsResponse> {
        Log.i("==refreshing==","refreshing tweets")
        return   reqAccessToken()
                .flatMap {
            getTweetList(query)
        }
            //.subscribeOn(Schedulers.io())
            //    .observeOn(Schedulers.io())
    }

    fun reqAccessToken() : Observable<OAuthResponse> {

        return mTwitterApi.getAccessTokn(authorizationHeader!!, "client_credentials")
            .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .doOnNext {
            saveAccessToken(it.accessToken)
        }


    }

    fun getTweetList(query: String) : Observable<TweetsResponse> {
        return  mTwitterApi.searchTweet(accessToken!!, query).observeOn(Schedulers.io())//.observeOn(Schedulers.io())
    }
    fun getUserProfile(screen_name: String) : Observable<TwitterProfile>{
        return mTwitterApi.userDetails(accessToken!!, screen_name).observeOn(Schedulers.io())
    }
    fun getUserTweets(screen_name: String) : Observable<List<Twit>>{
        return mTwitterApi.searchUserTweet(accessToken!!, screen_name).observeOn(Schedulers.io())
    }
    private fun saveAccessToken(accessToken: String) {
        mSharedPreferences.accessToken =accessToken
    }
}