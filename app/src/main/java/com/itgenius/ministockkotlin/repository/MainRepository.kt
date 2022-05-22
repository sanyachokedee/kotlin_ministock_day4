package com.itgenius.ministockkotlin.repository

import com.itgenius.ministockkotlin.network.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    // เก็บ Rest API Method จาก RetrofitService ไว้ที่นี่
    fun getAllProducts() = retrofitService.getAllProducts();
}