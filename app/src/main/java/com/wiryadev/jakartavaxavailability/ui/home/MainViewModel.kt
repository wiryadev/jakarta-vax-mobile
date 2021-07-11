package com.wiryadev.jakartavaxavailability.ui.home

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
class MainViewModel @Inject constructor(
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

    private val _vaccines = MutableStateFlow<List<VaccineResponseItem>>(listOf())
    val vaccines: StateFlow<List<VaccineResponseItem>>
        get() = _vaccines.asStateFlow()

    private val _searchResult = MutableStateFlow<List<VaccineResponseItem>>(listOf())
    val searchResult: StateFlow<List<VaccineResponseItem>>
        get() = _searchResult.asStateFlow()

    init {
        getVaccines()
    }

    private fun getVaccines() {
        viewModelScope.launch {
            if (!_isRefreshing.value) {
                _loading.value = true
            }

            _vaccines.emit(repository.getVaccines(_isRefreshing.value))
            _loading.emit(false)
            _isRefreshing.emit(false)
        }
    }

    fun onQueryChanged(newQuery: String) {
        query.value = newQuery

        if (this.query.value.isNotEmpty()) {
            viewModelScope.launch {
                _searchResult.emit(
                    repository.searchFromList(
                        query = query.value,
                        searchType = searchType.value,
                    )
                )
            }
        }
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
        query.value = ""

        getVaccines()
    }

}