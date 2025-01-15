package com.firebase.firestore.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Density
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firebase.firestore.model.Mahasiswa
import com.firebase.firestore.repository.MahasiswaRepository
import com.firebase.firestore.repository.NetworkMahasiswaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

sealed class DetailUiState {
    data class Success(val mahasiswa: Mahasiswa) : DetailUiState()
    data class Error(val Exception: Throwable) : DetailUiState()
    object Loading : DetailUiState()
}

class DetailViewModel(
    private val mhs : MahasiswaRepository) : ViewModel(){

        private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
        val detailUiState: StateFlow<DetailUiState> = _detailUiState.asStateFlow()

        fun getDetailMahasiswa(nim: String){
            viewModelScope.launch{
                _detailUiState.value = DetailUiState.Loading
                try {
                    val mahasiswa = mhs.getMahasiswaByNim(nim)
                    //_detailUiState.value = DetailUiState.Success(nim? mahasiswa? kurang paham mas)
                }
                catch (e: Exception){
                    _detailUiState.value = DetailUiState.Error(e)
                }
            }
        }
    }


