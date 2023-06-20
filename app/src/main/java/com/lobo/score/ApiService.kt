package com.lobo.score

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/events/get_events_data_4")
    fun getEventsData(): Call<Data>
}