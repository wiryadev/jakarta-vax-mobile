package com.wiryadev.jakartavaxavailability.ui.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wiryadev.jakartavaxavailability.data.VaccineRepository
import com.wiryadev.jakartavaxavailability.data.local.entity.VaccineBookmarkEntity
import com.wiryadev.jakartavaxavailability.data.remote.response.Jadwal
import com.wiryadev.jakartavaxavailability.data.remote.response.VaccineResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

// Given context is appContext, no actual leak
@SuppressLint("StaticFieldLeak")
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val context: Context,
    private val repository: VaccineRepository,
) : ViewModel() {

    val locationName = mutableStateOf("")

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean>
        get() = _loading.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean>
        get() = _isError.asStateFlow()

    private val _vaccineResponseItem = MutableStateFlow<VaccineResponseItem?>(null)
    val vaccineResponseItem: StateFlow<VaccineResponseItem?>
        get() = _vaccineResponseItem.asStateFlow()

    private val _schedules = MutableStateFlow(emptyList<Jadwal>())
    val schedules: StateFlow<List<Jadwal>>
        get() = _schedules.asStateFlow()

    private val _selectedSchedules = MutableStateFlow(0)
    val selectedSchedules: StateFlow<Int>
        get() = _selectedSchedules.asStateFlow()

    private val _isBookmarked = MutableStateFlow(false)
    val isBookmarked: StateFlow<Boolean>
        get() = _isBookmarked.asStateFlow()

    fun getDetailItem() {
        if (!_isRefreshing.value) {
            _loading.value = true
        }

        if (locationName.value.isNotEmpty()) {
            viewModelScope.launch {
                try {
                    _vaccineResponseItem.emit(
                        repository.getLocationByName(
                            name = locationName.value,
                            isRefreshing = _isRefreshing.value,
                        )
                    )

                    _vaccineResponseItem.value?.let {
                        _schedules.emit(
                            it.jadwal
                        )
                    }

                    _isError.emit(false)
                } catch (e: Exception) {
                    _isError.emit(true)
                }

                _loading.emit(false)
                _isRefreshing.emit(false)
            }
        }
    }

    fun setSelectedSchedule(newSchedule: Int) {
        viewModelScope.launch {
            _selectedSchedules.emit(newSchedule)
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
        }

        getDetailItem()
//        checkBookmark()
    }

    fun checkBookmark() = viewModelScope.launch {
        repository.checkBookmark(query = locationName.value).collect {
            if (it >= 1) {
                _isBookmarked.emit(true)
            } else {
                _isBookmarked.emit(false)
            }
        }
    }

    fun addToBookmark(entity: VaccineBookmarkEntity) {
        viewModelScope.launch {
            repository.addToBookmark(entity = entity)
        }
//        checkBookmark()
    }

    fun removeFromBookmark(entity: VaccineBookmarkEntity) {
        viewModelScope.launch {
            repository.removeFromBookmark(entity = entity)
        }
        checkBookmark()
    }

    fun goToMaps() {
        val gmmIntentUri = Uri.parse("geo:0,0?q=${vaccineResponseItem.value?.namaLokasiVaksinasi}")
        val intent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        intent.setPackage("com.google.android.apps.maps")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

}