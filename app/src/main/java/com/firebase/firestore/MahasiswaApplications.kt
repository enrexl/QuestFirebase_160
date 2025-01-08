package com.firebase.firestore

import android.app.Application
import com.firebase.firestore.dependenciesinjection.AppContainer
import com.firebase.firestore.dependenciesinjection.MahasiswaContainer

class MahasiswaApplications:Application() {
    lateinit var container: AppContainer
    override fun onCreate(){
        super.onCreate()
        container = MahasiswaContainer()
    }
}