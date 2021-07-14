package com.wiryadev.jakartavaxavailability.ui.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wiryadev.jakartavaxavailability.data.VaccineRepository
import com.wiryadev.jakartavaxavailability.data.response.Jadwal
import com.wiryadev.jakartavaxavailability.data.response.VaccineResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

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

    fun getDetailItem() {
        if (!_isRefreshing.value) {
            _loading.value = true
        }

        if (locationName.value.isNotEmpty()) {
            viewModelScope.launch {
                try {
                    _vaccineResponseItem.emit(
                        repository.getLocationByName(locationName.value)
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
    }

    fun goToMaps() {
        val gmmIntentUri = Uri.parse("geo:0,0?q=${vaccineResponseItem.value?.namaLokasiVaksinasi}")
        val intent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        intent.setPackage("com.google.android.apps.maps")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

}