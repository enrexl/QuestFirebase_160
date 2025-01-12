package com.firebase.firestore.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.firebase.firestore.model.Mahasiswa
import com.firebase.firestore.repository.MahasiswaRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

sealed class HomeUiState {
    data class Success(val mahasiswa: List<Mahasiswa>) : HomeUiState()
    data class Error(val Exception: Throwable) : HomeUiState() //diganti jadi dataclass karena akan menampilkan pesan
    object Loading : HomeUiState()
}


class HomeViewModel(
    private val mhs : MahasiswaRepository
): ViewModel() {
    var mhsUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set
    init {
        getMhs()
    }

    fun getMhs(){
        viewModelScope.launch{
            mhs.getAllMahasiswa()
                .onStart {
                    mhsUiState = HomeUiState.Loading
                }
                .catch {
                    mhsUiState = HomeUiState.Error(it)
                }
                .collect {
                    mhsUiState = if (it.isEmpty()) {
                        HomeUiState.Error(Exception("blm ada daftar mhs"))
                    } else {
                        HomeUiState.Success(it)
                    }
                }
        }
    }

    fun deleteMhs(mahasiswa: Mahasiswa){
        viewModelScope.launch{
            try {
                mhs.deleteMahasiswa(mahasiswa.toString())
            }
            catch (e: Exception){
                mhsUiState = HomeUiState.Error(e)
            }
        }
    }
}
