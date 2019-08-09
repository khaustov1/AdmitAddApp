package com.admitad.evo.data.remote

import com.admitad.evo.data.remote.model.BlockResponse
import com.admitad.evo.data.remote.model.TerminalEntity
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call

class RemoteAdvBlockDataSource : KoinComponent {
    private val remoteAdvBlockService: RemoteAdvBlockService by inject()

    fun getAdbBlock(terminalEntity: TerminalEntity): Call<BlockResponse> = remoteAdvBlockService.getAdvBlock(terminalEntity)
}