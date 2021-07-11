package com.wiryadev.jakartavaxavailability.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wiryadev.jakartavaxavailability.data.SearchType
import com.wiryadev.jakartavaxavailability.data.VaccineRepository
import com.wiryadev.jakartavaxavailability.data.response.VaccineResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: VaccineRepository
) : ViewModel() {

    val query = mutableStateOf("")
    val searchType = mutableStateOf(SearchType.LOKASI)

    val loading = mutableStateOf(false)

    val isRefreshing = mutableStateOf(false)

    val vaccines: MutableState<List<VaccineResponseItem>> = mutableStateOf(listOf())
    val searchResult: MutableState<List<VaccineResponseItem>> = mutableStateOf(listOf())

    var scrollPosition: Int = 0

    init {
        getVaccines()
    }

    private fun getVaccines() {
        if (!isRefreshing.value) {
            loading.value = true
        }

        viewModelScope.launch {
            vaccines.value = repository.getVaccines(isRefreshing.value)
            loading.value = false
            isRefreshing.value = false
        }
    }

    fun onQueryChanged(query: String) {
        this.query.value = query

        if (this.query.value.isNotEmpty()) {
            searchResult.value = repository.searchFromList(
                query = this.query.value,
                searchType = this.searchType.value,
            )
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
        isRefreshing.value = true
        query.value = ""

        getVaccines()
    }

    private fun setListScrollPosition(position: Int) {
        scrollPosition = position
    }
}