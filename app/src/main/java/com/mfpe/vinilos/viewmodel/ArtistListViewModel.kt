package com.mfpe.vinilos.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mfpe.vinilos.data.model.Band
import com.mfpe.vinilos.data.model.Musician
import com.mfpe.vinilos.data.repository.BandRepository
import com.mfpe.vinilos.data.repository.MusicianRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ArtistListViewModel : ViewModel() {

    private val bandRepository: BandRepository = BandRepository()
    private val musicianRepository: MusicianRepository= MusicianRepository()

    private val _bands = MutableLiveData<List<Band>>()
    val bands: LiveData<List<Band>> get() = _bands

    private val _musicians = MutableLiveData<List<Musician>>()
    val musicians: LiveData<List<Musician>> get() = _musicians

    private var _networkError = MutableLiveData(false)
    val networkError: LiveData<Boolean> get() = _networkError

    fun fetchBands() {
        bandRepository.getBands().enqueue(object : Callback<List<Band>> {

            override fun onResponse(call: Call<List<Band>>, res: Response<List<Band>>) {
                if (res.isSuccessful) {
                    _bands.value = res.body()
                    _networkError.value = false
                }
            }

            override fun onFailure(call: Call<List<Band>>, t: Throwable) {
                _networkError.value = true
                Log.d("Error", t.toString())
            }
        })
    }

    fun fetchMusicians() {
        musicianRepository.getMusician().enqueue(object : Callback<List<Musician>> {

            override fun onResponse(call: Call<List<Musician>>, res: Response<List<Musician>>) {
                if (res.isSuccessful) {
                    _musicians.value = res.body()
                    _networkError.value = false
                }
            }

            override fun onFailure(call: Call<List<Musician>>, t: Throwable) {
                _networkError.value = true
                Log.d("Error", t.toString())
            }
        })
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}