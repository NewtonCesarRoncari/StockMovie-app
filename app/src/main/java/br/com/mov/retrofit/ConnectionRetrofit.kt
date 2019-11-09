package br.com.mov.retrofit

import br.com.mov.retrofit.service.UserService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

private const val BASE_URL = "http://192.168.137.1:8080/"

class ConnectionRetrofit {

    private val client by lazy {
        val interceptador = HttpLoggingInterceptor()
        interceptador.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
                .addInterceptor(interceptador)
                .build()
    }
    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client)
                .build()
    }

    val userService: UserService by lazy {
        retrofit.create(UserService::class.java)
    }
}