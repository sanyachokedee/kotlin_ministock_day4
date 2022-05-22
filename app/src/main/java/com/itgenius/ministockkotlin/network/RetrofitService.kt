package com.itgenius.ministockkotlin.network

import com.itgenius.ministockkotlin.model.ProductModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    // สร้าง Method สำหรับเรียก API
    @GET("Products")
    fun getAllProducts(): Call<List<ProductModel>>

    // สร้าง Object ของ Retrofit
    companion object {
        // กำหนด BaseURL ของ API
        private const val BaseURL = "http://192.168.2.29:5001/api/"

        private var retrofitService: RetrofitService? = null

//         แบบไม่มี Authorization
//        fun getInstance(): RetrofitService{
//            if(retrofitService == null) {
//                val retrofit = Retrofit.Builder()
//                    .baseUrl(BaseURL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                retrofitService = retrofit.create(RetrofitService::class.java)
//            }
//            return retrofitService!!
//        }

        // แบบมี Authorization
        fun getInstance(): RetrofitService {
            if(retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BaseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClient.Builder().addInterceptor { chain ->
                        val request = chain.request().newBuilder().addHeader(
                            "Authorization",
                            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoic2FtaXQiLCJqdGkiOiIzZjRmOWJlMy04OWE0LTQ0NGQtOWI2Zi0zNTczZGRhYWY1OWYiLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiJBZG1pbiIsImV4cCI6MTY1MzIzNzY0MCwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDoxNjk3MCIsImF1ZCI6Imh0dHA6Ly9sb2NhbGhvc3Q6NDIwMCJ9.I3gwDctn8PVibRyR4xGg2qO04S_U2gbzL7F7sIjXQsU")
                            .build()
                        chain.proceed(request)
                    }.build())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }

}