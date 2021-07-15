package com.wiryadev.jakartavaxavailability.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wiryadev.jakartavaxavailability.data.VaccineRepository
import com.wiryadev.jakartavaxavailability.data.local.entity.VaccineBookmarkEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val repository: VaccineRepository
) : ViewModel() {

    private val _bookmarkList = MutableStateFlow<List<VaccineBookmarkEntity>>(emptyList())
    val bookmarkList: StateFlow<List<VaccineBookmarkEntity>>
        get() = _bookmarkList.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getBookmarkList().collect { list ->
                _bookmarkList.emit(list)
            }
        }
    }

}