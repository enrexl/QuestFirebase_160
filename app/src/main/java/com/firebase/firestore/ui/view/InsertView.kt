package com.firebase.firestore.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.firebase.firestore.model.Mahasiswa
import com.firebase.firestore.ui.viewmodel.FormErrorState
import com.firebase.firestore.ui.viewmodel.InsertUiState
import com.firebase.firestore.ui.viewmodel.InsertViewModel
import com.firebase.firestore.ui.viewmodel.MahasiswaEvent
import com.firebase.firestore.ui.viewmodel.PenyediaViewModel

@Composable
fun InsertView(
    onBack: () -> Unit,
    onNavigate : () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertViewModel = viewModel(factory = PenyediaViewModel.Factory)
){

}

@Composable
fun FormMahasiswa(
    mahasiswaEvent: MahasiswaEvent = MahasiswaEvent(),
    onValueChange:(MahasiswaEvent)-> Unit,
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
){
    val jenisKelamin = listOf("Laki-Laki","Perempuan")
    val kelas = listOf("A", "B", "C", "D", "E")

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mahasiswaEvent.nama,
            onValueChange = {onValueChange(mahasiswaEvent.copy(nama = it))},
            label = {Text("Nama")},
            isError = errorState.nama != null,
            placeholder = {Text("Masukkan nama")}
        )
        Text(text = errorState.nama?:"", color = Color.Red)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mahasiswaEvent.nim,
            onValueChange = {onValueChange(mahasiswaEvent.copy(nim = it))},
            label = {Text("NIM")},
            isError = errorState.nim != null,
            placeholder = {Text("Masukkan NIM")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(text = errorState.nim?:"", color = Color.Red)

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Jenis Kelamin")

        Row(modifier = Modifier.fillMaxWidth()) {
            jenisKelamin.forEach{ jk ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = mahasiswaEvent.jenisKelamin == jk,
                        onClick = {onValueChange(mahasiswaEvent.copy(jenisKelamin = jk))}
                    )
                    Text( text = jk)
                }
            }
        }
        Text(text = errorState.jenisKelamin?:"", color = Color.Red)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mahasiswaEvent.alamat,
            onValueChange = {onValueChange(mahasiswaEvent.copy(alamat = it))},
            label = {Text("Alamat")},
            isError = errorState.alamat != null,
            placeholder = {Text("Masukkan ALamat")}
        )

        Text(text = errorState.alamat?:"", color = Color.Red)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Kelas")
        Row {

            kelas.forEach{kelas ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                   RadioButton(
                       selected = mahasiswaEvent.kelas == kelas,
                       onClick = {onValueChange(mahasiswaEvent.copy(kelas = kelas))}
                   )
                   Text(text = kelas)
                }
            }
        }
        Text(text = errorState.kelas?:"'", color = Color.Red)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mahasiswaEvent.angkatan,
            onValueChange = {onValueChange(mahasiswaEvent.copy(angkatan = it))},
            label = {Text("Angkatan")},
            placeholder = {Text("Masukkan Angkatan")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(text = errorState.angkatan?:"", color = Color.Red)
    }
}