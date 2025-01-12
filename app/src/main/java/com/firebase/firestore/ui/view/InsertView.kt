package com.firebase.firestore.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.firebase.firestore.ui.viewmodel.InsertUiState
import com.firebase.firestore.ui.viewmodel.InsertViewModel
import com.firebase.firestore.ui.viewmodel.PenyediaViewModel

@Composable
fun InsertView(
    onBack: () -> Unit,
    onNavigate : () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertViewModel = viewModel(factory = PenyediaViewModel.Factory)
){

}

