package com.firebase.firestore.repository

import com.firebase.firestore.model.Mahasiswa
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class NetworkMahasiswaRepository(
    private val firestore: FirebaseFirestore

): MahasiswaRepository {
    override suspend fun getAllMahasiswa(): Flow<List<Mahasiswa>> = callbackFlow {
        val mhsCollection = firestore.collection("Mahasiswa") //nama collection CASE sensitive
            .orderBy("nim", Query.Direction.DESCENDING)
            .addSnapshotListener{ //biar realtime
                 value, error ->
                if (value != null){
                    val mhsList = value.documents.mapNotNull {
                        it.toObject(Mahasiswa::class.java)
                    }
                    trySend(mhsList)
                }
            }
        awaitClose{
            mhsCollection.remove()
        }
    }

    override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
            try {
                firestore.collection("Mahasiswa").add(mahasiswa).await()
            }
            catch (e: Exception) {
                throw Exception("Gagal menambahkan data mahasiswa:${e.message}")
            }
    }


    override suspend fun updateMahasiswa(
        nim: String,
        mahasiswa: Mahasiswa
    ) {
        try {
            firestore.collection("Mahasiswa")
                .document(mahasiswa.nim)
                .set(mahasiswa)
                .await()
        }
        catch (e: Exception) {
            throw Exception("Gagal mengupdate data mahasiswa:${e.message}")
        }
    }


    override suspend fun deleteMahasiswa(nim: String) {
        try {
            firestore.collection("Mahasiswa")
                .document(nim)
                .delete()
                .await()
        }
        catch ( e: Exception){
            throw Exception("Gagal Menghapus data mahasiswa:${e.message}")
        }
    }

    override suspend fun getMahasiswaByNim(nim: String): Flow<Mahasiswa> = callbackFlow {
        val mhsDocument = firestore.collection("Mahasiswa")
            .document(nim)
            .addSnapshotListener{value, error ->
                if (value != null){
                    val mhs = value.toObject(Mahasiswa::class.java) !! //
                    trySend(mhs)
                }
            }
        awaitClose{
            mhsDocument.remove()
        }
    }


}