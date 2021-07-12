package com.wiryadev.jakartavaxavailability.ui.detail

import androidx.lifecycle.ViewModel
import com.wiryadev.jakartavaxavailability.data.VaccineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: VaccineRepository
) : ViewModel() {
}