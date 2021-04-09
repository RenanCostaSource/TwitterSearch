package com.fuze.ttapp.api

import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import javax.inject.Inject


class HeaderInterceptor @Inject
internal constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        try {
            val request = chain.request()
            val builder = request.newBuilder()


            return chain.proceed(builder.build())
        } catch (e: SocketTimeoutException) {
            return getFakeResponse(chain.request(), HttpURLConnection.HTTP_CLIENT_TIMEOUT)
        }

    }

    private fun getFakeResponse(request: Request, code: Int): Response {
        return Response.Builder().request(request)
            .code(code)
            .protocol(Protocol.HTTP_1_1).build()

    }

    companion object {
        private val TAG = "HeaderInterceptor"
    }
}
