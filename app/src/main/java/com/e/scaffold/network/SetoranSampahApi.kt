package com.e.scaffold.network

import com.e.scaffold.model.SetoranSampah
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.*

interface SetoranSampahApi {
    @GET("api/setoran-sampah")
    suspend fun all(): ApiResponse<SetoranSampahGetResponse>
    @GET("api/setoran-sampah/{id}")
    suspend fun find(@Path("id") id: String):
            ApiResponse<SetoranSampahSingleGetResponse>
    @POST("api/setoran-sampah")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body item: SetoranSampah):
            ApiResponse<SetoranSampahSingleGetResponse>
    @PUT("api/setoran-sampah/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") pathId: String,
                       @Body item: SetoranSampah):
            ApiResponse<SetoranSampahSingleGetResponse>
    @DELETE("api/setoran-sampah/{id}")
    suspend fun delete(@Path("id") id: String):
            ApiResponse<SetoranSampahSingleGetResponse>
}