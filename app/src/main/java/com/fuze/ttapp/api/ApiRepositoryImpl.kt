package com.fuze.ttapp.api

import com.fuze.ttapp.api.models.TweetsResponse
import com.fuze.ttapp.api.models.Twit
import com.fuze.ttapp.api.models.TwitterProfile
import com.fuze.ttapp.api.models.oauth.OAuthResponse
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepositoryImpl @Inject
constructor( private val apiService: ApiService ): ApiRepository {
    override fun getAccessTokn(authorization: String, grantType: String) :
            Observable<OAuthResponse> = apiService.getAccessTokn(authorization,grantType)

    override fun searchTweet(authorization: String, query: String) :
            Observable<TweetsResponse> = apiService.searchTweet(authorization,query)

    override fun userDetails(authorization: String, screen_name: String) :
            Observable<TwitterProfile> = apiService.userDetails(authorization,screen_name)

    override fun searchUserTweet(authorization: String, screen_name: String) :
            Observable<List<Twit>> = apiService.searchUserTweet(authorization,screen_name)
}

