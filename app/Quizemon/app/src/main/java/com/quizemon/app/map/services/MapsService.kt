package com.quizemon.app.map.services


import com.quizemon.app.map.Types.QuizCoordinate
import com.quizemon.app.map.api.MapsApi
import io.reactivex.disposables.Disposable
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Erka on 2018-02-10.
 */
class MapsService() {
    val mapsApi by lazy {
        MapsApi.create()
    }



    fun fetchQuizCoordinates(longitude: String, latitude: String) : Observable<List<QuizCoordinate>> {
                return mapsApi.getQuizCoordinates(longitude, latitude)
                        .subscribeOn(Schedulers.io())
                        .map { c ->  c.coordinates.map { b -> QuizCoordinate(b.longitude, b.latitude) }}
                        .observeOn(AndroidSchedulers.mainThread())
    }
}