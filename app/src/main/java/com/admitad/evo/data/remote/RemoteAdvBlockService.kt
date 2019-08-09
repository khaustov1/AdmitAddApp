package com.admitad.evo.data.remote

import com.admitad.evo.data.remote.model.BlockResponse
import com.admitad.evo.data.remote.model.TerminalEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RemoteAdvBlockService {
    @Headers("Content-Type: application/json")
    @POST("offers/obtain")
    fun getAdvBlock(@Body terminalEntity: TerminalEntity): Call<BlockResponse>
}