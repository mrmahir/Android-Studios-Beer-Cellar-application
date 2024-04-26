package com.example.mobiledevelopment_aflevering_mahir

import android.app.Application

class BeerApplication : Application() {
    val repository: BeerRepository by lazy {
        BeerRepository(RetrofitInstance.api)
    }
}
