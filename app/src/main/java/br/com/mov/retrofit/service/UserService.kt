package br.com.mov.retrofit.service

import br.com.mov.models.User
import br.com.mov.models.dto.UserRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    @GET("users/{email}")
    fun getUser(@Path("email") email: String): Call<UserRequest>

    @POST("users")
    fun postUser(@Body user: User): Call<UserRequest>

    @POST("users/auth")
    fun postUserAuth(@Body user: User): Call<UserRequest>
}