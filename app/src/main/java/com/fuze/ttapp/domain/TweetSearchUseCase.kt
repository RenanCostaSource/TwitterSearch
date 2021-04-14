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
class TweetSearchUseCase @Inject constructor ( private val twitterApi: ApiService ,
                                               private val sharedPreferences: AppPreferences
) {
    val consumerKey: String = "cT1evo5TGNTYkXGjEAUvIUpIS"
    val consumerSecret: String = "j9Cc90QbNP8iBlv5HRKissGBXczarghDtJuGyJhYKe2ZBRBNq5"

    private val authorizationHeader: String?
            get() {
        try {
            val consumerKeyAndSecret = "$consumerKey:$consumerSecret"
            val data = consumerKeyAndSecret.toByteArray(charset("UTF-8"))
            val base64 = Base64.encodeToString(data, Base64.NO_WRAP)
            return "Basic $base64"
        } catch (e: UnsupportedEncodingException) {
            return null
        }
    }

    private val accessToken: String?
            get() {
        val accessToken = sharedPreferences.accessToken
        if (TextUtils.isEmpty(accessToken)) {
            return null
        }
        return "Bearer $accessToken"
    }

    fun searchTweets(query: String)  : Observable<TweetsResponse> {
        val accessToken = accessToken
        return if (TextUtils.isEmpty(accessToken)) {
            requestAccessTokenAndGetTweetList(query)
        } else {
            getTweetList(query)
        }
    }

    private fun requestAccessTokenAndGetTweetList(
            query: String
    ) : Observable<TweetsResponse> {
        return   reqAccessToken()
                .flatMap {
            getTweetList(query)
        }
    }

    fun reqAccessToken() : Observable<OAuthResponse> {

        return twitterApi.getAccessTokn(authorizationHeader.toString(), "client_credentials")
            .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .doOnNext {
            saveAccessToken(it.accessToken)
        }
    }

    fun getTweetList(query: String): Observable<TweetsResponse> {
        return  twitterApi.searchTweet(accessToken.toString(), query).observeOn(Schedulers.io())
    }

    fun getUserProfile(screen_name: String): Observable<TwitterProfile>{
        return twitterApi.userDetails(accessToken.toString(), screen_name).observeOn(Schedulers.io())
    }

    fun getUserTweets(screen_name: String): Observable<List<Twit>>{
        return twitterApi.searchUserTweet(accessToken.toString(), screen_name).observeOn(Schedulers.io())
    }

    private fun saveAccessToken(accessToken: String) {
        sharedPreferences.accessToken =accessToken
    }
}
