package com.firebase.firestore.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.firebase.firestore.model.Mahasiswa
import com.firebase.firestore.repository.MahasiswaRepository

class InsertViewModel(
    private val mhs: MahasiswaRepository
): ViewModel() {


}


// Menyimpan input form ke dalam entity
fun MahasiswaEvent.toMhsModel(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    jenisKelamin = jenisKelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan,
)