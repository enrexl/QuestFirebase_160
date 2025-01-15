package com.firebase.firestore.ui.viewmodel

import androidx.compose.material3.formatWithSkeleton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firebase.firestore.model.Mahasiswa
import com.firebase.firestore.repository.MahasiswaRepository
import kotlinx.coroutines.launch

class InsertViewModel(
    private val mhs: MahasiswaRepository
): ViewModel(){

    var uiEvent: InsertUiState by mutableStateOf(InsertUiState())
        private set

    var uiState: FormState by mutableStateOf(FormState.Idle)
        private set

    //Memperbarui State berdasarkan input pengguna
    fun updateState(mahasiswaEvent: MahasiswaEvent){
        uiEvent = uiEvent.copy(
            insertUiEvent = mahasiswaEvent
        )
    }

    //validasi input pengguna
    fun validateFields(): Boolean{
        val event = uiEvent.insertUiEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isNotEmpty()) null else "NIM tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "NIM tidak boleh kosong",
            jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "NIM tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "NIM tidak boleh kosong",
            kelas = if (event.kelas.isNotEmpty()) null else "NIM tidak boleh kosong",
            angkatan = if (event.angkatan.isNotEmpty()) null else "NIM tidak boleh kosong",
            judul = if (event.judul.isNotEmpty()) null else "NIM tidak boleh kosong",
            dosen1 = if (event.dosen1.isNotEmpty()) null else "NIM tidak boleh kosong",
            dosen2 = if (event.dosen2.isNotEmpty()) null else "NIM tidak boleh kosong",
        )

        uiEvent = uiEvent.copy(isEntryValid = errorState)
        return errorState.isValid()

    }

    fun insertMhs(){
        if (validateFields()){
            viewModelScope.launch{
                uiState = FormState.Loading
                try {
                    mhs.insertMahasiswa(uiEvent.insertUiEvent.toMhsModel())
                    uiState = FormState.Success("Data Berhasil disimpan")
                }
                catch (e: Exception){
                    uiState = FormState.Error("Data tidak Valid")
                }
            }
        }
        else{
            uiState = FormState.Error("Data Tidak Valid")
        }
    }

    fun resetForm(){
        uiEvent = InsertUiState()
        uiState = FormState.Idle
    }

    fun resetSnackBarMessage(){
        uiState = FormState.Idle
    }
}

sealed class FormState{
    object Idle : FormState()
    object Loading : FormState()
    data class Success(val message: String) : FormState()
    data class Error(val message: String) : FormState()
}

data class  InsertUiState(
    val insertUiEvent: MahasiswaEvent = MahasiswaEvent(),
    val isEntryValid: FormErrorState = FormErrorState()
)

data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val jenisKelamin: String? = null,
    val alamat: String? = null,
    val kelas: String? = null,
    val angkatan: String? = null,
    val judul: String? = null,
    val dosen1: String? = null,
    val dosen2: String? = null
){
    fun isValid(): Boolean{
        return nim == null && nama == null && jenisKelamin == null &&
                alamat == null && kelas == null && angkatan == null
    }
}

data class MahasiswaEvent(
    val nim: String = "",
    val nama: String = "",
    val jenisKelamin: String = "",
    val alamat: String = "",
    val kelas: String = "",
    val angkatan: String = "",
    val judul: String = "",
    val dosen1: String = "",
    val dosen2: String = ""
)


// Menyimpan input form ke dalam entity
fun MahasiswaEvent.toMhsModel(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    jenisKelamin = jenisKelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan,
    judul = judul,
    dosen1 = dosen1,
    dosen2 = dosen2
)