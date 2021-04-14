package com.fuze.ttapp.api
import com.fuze.ttapp.api.models.TweetsResponse
import com.fuze.ttapp.api.models.Twit
import com.fuze.ttapp.api.models.TwitterProfile
import com.fuze.ttapp.api.models.oauth.OAuthResponse
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {

    companion object {
        const val HEADER_PARAM_SEPARATOR = ":"
    }

    @POST("oauth2/token")
    @FormUrlEncoded
    fun getAccessTokn(
            @Header("Authorization") authorization: String, @Field("grant_type") grantType: String
    ) : Observable<OAuthResponse>

    @GET("1.1/search/tweets.json")
    fun searchTweet(
            @Header("Authorization") authorization :String   ,@Query("q")  query: String
    ) : Observable<TweetsResponse>

    @GET("1.1/users/show.json")
    fun userDetails(
            @Header("Authorization") authorization: String,@Query("screen_name") screen_name: String
    ) : Observable<TwitterProfile>

    @GET("1.1/statuses/user_timeline.json")
    fun searchUserTweet(
            @Header("Authorization") authorization :String   ,@Query("screen_name") screen_name: String
    ) : Observable<List<Twit>>
}
