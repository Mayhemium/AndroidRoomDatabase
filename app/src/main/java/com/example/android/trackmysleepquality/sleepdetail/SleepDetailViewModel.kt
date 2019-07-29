package com.example.android.trackmysleepquality.sleepdetail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight

class SleepDetailViewModel(dataSource: SleepDatabaseDao, nightId: Long): ViewModel() {

    private val _night = MediatorLiveData<SleepNight>()
    val night: LiveData<SleepNight>
    get() = _night

    private val _navigateToSleepTracker = MutableLiveData<Boolean?>()
    val navigateToSleepTracker: LiveData<Boolean?>
    get() = _navigateToSleepTracker

    init {
        _night.addSource(dataSource.getNightWithId(nightId),_night::setValue)
    }

    fun doneNavigating(){
        _navigateToSleepTracker.value = null
    }

    fun onClose(){
        _navigateToSleepTracker.value = true
    }
}