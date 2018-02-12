package com.quizemon.app.map.services


import com.quizemon.app.map.Types.QuizCoordinate
import com.quizemon.app.map.api.MapsApi
import io.reactivex.Observable

/**
 * Created by Erka on 2018-02-10.
 */
class MapsService() {
    val mapsApi by lazy {
        MapsApi.create()
    }



    fun fetchQuizCoordinates(longitude: Double, latitude: Double) : Observable<MutableList<QuizCoordinate>> {
        return Observable.create {
            subscriber ->
            val news = mutableListOf<QuizCoordinate>()
            news.add(QuizCoordinate(-33.865143, 151.209900))
            news.add(QuizCoordinate(59.3793, 13.50357))
            news.add(QuizCoordinate(59.00, 13.00))
            subscriber.onNext(news)
        }
    }

}