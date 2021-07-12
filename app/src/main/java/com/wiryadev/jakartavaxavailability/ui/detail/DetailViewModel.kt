package com.wiryadev.jakartavaxavailability.ui.detail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.wiryadev.jakartavaxavailability.data.VaccineRepository
import com.wiryadev.jakartavaxavailability.data.response.Jadwal
import com.wiryadev.jakartavaxavailability.data.response.VaccineResponseItem
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: VaccineRepository
) : ViewModel() {

    val locationName = mutableStateOf("")

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean>
        get() = _loading.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

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
        if (locationName.value.isNotEmpty()) {
            viewModelScope.launch {
                _vaccineResponseItem.emit(
                    repository.getLocationByName(locationName.value)
                )

                _vaccineResponseItem.value?.let {
                    _schedules.emit(
                        it.jadwal
                    )
                    Log.d("Detail", "VM schedule: ${_schedules.value}")
                }
            }
        }
    }

    fun setSelectedSchedule(newSchedule: Int) {
        viewModelScope.launch {
            _selectedSchedules.emit(newSchedule)
        }
    }

}