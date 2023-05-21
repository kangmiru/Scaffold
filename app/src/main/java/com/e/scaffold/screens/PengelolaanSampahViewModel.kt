package com.e.scaffold.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.scaffold.model.SetoranSampah
import com.e.scaffold.repositories.SetoranSampahRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PengelolaanSampahViewModel @Inject constructor(private val setoranSampahRepository: SetoranSampahRepository) : ViewModel()
{
    private val _isLoading: MutableLiveData<Boolean> =
        MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _success: MutableLiveData<Boolean> =
        MutableLiveData(false)
    val success: LiveData<Boolean> get() = _success

    private val _toast: MutableLiveData<String> =
        MutableLiveData()
    val toast: LiveData<String> get() = _toast

    private val _list: MutableLiveData<List<SetoranSampah>> =
        MutableLiveData()
    val list: LiveData<List<SetoranSampah>> get() = _list

    suspend fun loadItems()
    {
        _isLoading.postValue(true)
        setoranSampahRepository.loadItems(onSuccess = {
            _isLoading.postValue(false)
            _list.postValue(it)
        }, onError = { list, message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _list.postValue(list)
        })
    }

    suspend fun insert(tanggal: String,
                       nama: String,
                       berat: String){
        _isLoading.postValue(true)
        setoranSampahRepository.insert(tanggal, nama, berat,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }

    suspend fun loadItem(id: String, onSucces : (SetoranSampah?) -> Unit){
        val item = setoranSampahRepository.find(id)
        onSucces(item)
    }

    suspend fun update(id: String,
    tanggal: String,
    nama: String,
    berat: String){
        _isLoading.postValue(true)
        setoranSampahRepository.update(id, tanggal, nama, berat,
        onError = {
            item, message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
        }, onSuccess = {
            _isLoading.postValue(false)
            _success.postValue(true)
        })
    }
}