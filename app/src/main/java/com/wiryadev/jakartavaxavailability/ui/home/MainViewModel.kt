package com.wiryadev.jakartavaxavailability.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    val loading = mutableStateOf(false)

    val isRefreshing = mutableStateOf(false)

    val vaccines: MutableState<List<VaccineResponseItem>> = mutableStateOf(listOf())

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

    fun refresh() {
        isRefreshing.value = true
        query.value = ""

        getVaccines()
    }

    private fun setListScrollPosition(position: Int) {
        scrollPosition = position
    }
}