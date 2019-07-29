@file:Suppress("UNCHECKED_CAST")

package com.example.android.trackmysleepquality.sleepdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.trackmysleepquality.database.SleepDatabaseDao

class SleepDetailViewModelFactory(private val dataSource: SleepDatabaseDao, private val nightId: Long): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepDetailViewModel::class.java)){
            return SleepDetailViewModel(dataSource,nightId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}