package com.e.scaffold.repositories

import com.benasher44.uuid.uuid4
import com.e.scaffold.model.SetoranSampah
import com.e.scaffold.network.SetoranSampahApi
import com.e.scaffold.persistence.SetoranSampahDao
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import javax.inject.Inject

class SetoranSampahRepository @Inject constructor(
    private val api: SetoranSampahApi,
    private val dao: SetoranSampahDao
) : Repository {
    suspend fun loadItems(
        onSuccess: (List<SetoranSampah>) -> Unit,
        onError: (List<SetoranSampah>, String) -> Unit
    ) {
        val list: List<SetoranSampah> = dao.getList()
        api.all()
        .suspendOnSuccess {
            data.whatIfNotNull {
                it.data?.let { list ->
                    dao.insertAll(list)
                    val items: List<SetoranSampah> =
                        dao.getList()
                    onSuccess(items)
                }
            }
        }
        .suspendOnError {
            onError(list, message())
        }
                .suspendOnException {
            onError(list, message())
        }
    }

    suspend fun insert(
        tanggal: String,
        nama: String,
        berat: String,
        onSuccess: (SetoranSampah) -> Unit,
        onError: (SetoranSampah?, String) -> Unit
    ) {
        val id = uuid4().toString()
        val item = SetoranSampah(id, tanggal, nama, berat)
        dao.insertAll(item)
        api.insert(item)
        .suspendOnSuccess {
            onSuccess(item)
        }
        .suspendOnError {
            onError(item, message())
        }
        .suspendOnException {
            onError(item, message())
        }
    }
    suspend fun update(
        id: String,
        tanggal: String,
        nama: String,
        berat: String,
        onSuccess: (SetoranSampah) -> Unit,
        onError: (SetoranSampah?, String) -> Unit
    ) {
        val item = SetoranSampah(id, tanggal, nama, berat)
        dao.insertAll(item)
        api.update(id, item)
        .suspendOnSuccess {
            onSuccess(item)
        }
            .suspendOnError {
            onError(item, message())
        }
            .suspendOnException {
            onError(item, message())
        }
    }

    suspend fun delete(id: String, onSuccess: () -> Unit,
                       onError: (String) -> Unit) {
        dao.delete(id)
        api.delete(id)
            .suspendOnSuccess {
            data.whatIfNotNull {
                onSuccess()
            }
        }
            .suspendOnError {
            onError(message())
        }
                .suspendOnException {
            onError(message())
        }
    }

    suspend fun find(id:String) : SetoranSampah? {
        return dao.find(id)
    }
}