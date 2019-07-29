package com.example.android.trackmysleepquality.sleepdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepDatabase
import com.example.android.trackmysleepquality.databinding.FragmentSleepDetailBinding
import com.example.android.trackmysleepquality.sleeptracker.SleepTrackerViewModel

class SleepDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentSleepDetailBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_sleep_detail,container,false)


        val application = requireNotNull(this.activity).application
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao

        val arguments = SleepDetailFragmentArgs.fromBundle(arguments!!)
        val nightId = arguments.nightId

        val viewModel: SleepDetailViewModel = ViewModelProviders.of(this, SleepDetailViewModelFactory(dataSource,nightId)).get(SleepDetailViewModel::class.java)


        binding.sleepDetailViewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.navigateToSleepTracker.observe(this, Observer {
            it?.let {
                if(it){
                    this.findNavController().navigate(SleepDetailFragmentDirections.actionSleepDetailFragmentToSleepTrackerFragment())
                    viewModel.doneNavigating()
                }
            }
        })

        return binding.root
    }

}
