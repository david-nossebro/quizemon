package com.quizemon.app.map.api

/**
 * Created by Erka on 2018-02-09.
 */

import com.quizemon.app.map.Types.QuizCoordinatesDto
import com.quizemon.app.map.api.types.QuizCoordinateResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface  MapsApi {

    @GET("quizs/coordinates")
    fun getQuizCoordinates(@Query("latitude") after: String, @Query("longitude") limit: String): Observable<QuizCoordinateResponse>

    companion object {
        fun create(): MapsApi {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl("https://en.wikipedia.org/w/")
                    .build()

            return retrofit.create(MapsApi::class.java)
        }
    }
}
