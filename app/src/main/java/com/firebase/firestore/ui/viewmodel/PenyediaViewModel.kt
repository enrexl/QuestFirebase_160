package com.firebase.firestore.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.firebase.firestore.MahasiswaApplications


object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer { HomeViewModel(aplikasiKontak().container.mahasiswaRepository) }
        initializer { InsertViewModel(aplikasiKontak().container.mahasiswaRepository) }
        initializer { DetailViewModel(aplikasiKontak().container.mahasiswaRepository) }
      //  initializer { UpdateViewModel(aplikasiKontak().container.mahasiswaRepository) }
    }

    fun CreationExtras.aplikasiKontak(): MahasiswaApplications =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as MahasiswaApplications)
}