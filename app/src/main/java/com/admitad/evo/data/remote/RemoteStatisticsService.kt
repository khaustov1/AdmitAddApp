package com.admitad.evo.data.remote

import com.admitad.evo.data.remote.model.StatsResponse
import com.admitad.evo.data.remote.model.TerminalStatistic
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RemoteStatisticsService {
    @Headers("Content-Type: application/json")
    @POST("reports")
    fun sendStats(@Body terminalStatistic: TerminalStatistic) : Call<StatsResponse>
}