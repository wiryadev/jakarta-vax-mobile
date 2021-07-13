package com.wiryadev.jakartavaxavailability.ui.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wiryadev.jakartavaxavailability.data.SearchType
import com.wiryadev.jakartavaxavailability.data.VaccineRepository
import com.wiryadev.jakartavaxavailability.data.response.VaccineResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: VaccineRepository
) : ViewModel() {

    val query = mutableStateOf("")
    val searchType = mutableStateOf(SearchType.LOKASI)

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean>
        get() = _loading.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean>
        get() = _isError.asStateFlow()

    private val _result = MutableStateFlow<List<VaccineResponseItem>>(listOf())
    val result: StateFlow<List<VaccineResponseItem>>
        get() = _result.asStateFlow()

    init {
        getVaccines()
    }

    private fun getVaccines() {
        if (!_isRefreshing.value) {
            _loading.value = true
        }

        viewModelScope.launch {
            try {
                _result.emit(
                    repository.getVaccines(
                        isRefreshing = _isRefreshing.value,
                        query = query.value,
                        searchType = searchType.value
                    )
                )

                _isError.emit(false)
            } catch (e: Exception) {
                _isError.emit(true)
            }

            Log.d("Snackbar", "isError: ${isError.value}")

            _loading.emit(false)
            _isRefreshing.emit(false)
        }
    }

    fun onQueryChanged(newQuery: String) {
        query.value = newQuery

        getVaccines()
    }

    fun onSelectedTypeChanged(type: String) {
        val newType = SearchType.values().associateBy(SearchType::value)[type]
        if (newType != null) {
            searchType.value = newType
            onQueryChanged(this.query.value)
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
        }

        getVaccines()
    }

}