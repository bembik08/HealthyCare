package com.teck.healthycare.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.teck.healthycare.R
import com.teck.healthycare.databinding.MainFragmentBinding
import com.teck.healthycare.domain.AppState
import com.teck.healthycare.domain.model.Health
import com.teck.healthycare.presentation.base.BaseFragment
import com.teck.healthycare.presentation.dialog.HealthDialog
import com.teck.healthycare.presentation.main.adapter.AdapterHealth
import org.koin.android.ext.android.getKoin
import org.koin.core.scope.Scope

class MainFragment() : BaseFragment<MainFragmentBinding>(R.layout.main_fragment) {
    override val scope: Scope by lazy { getKoin().createScope<MainFragment>() }
    override val viewBinding: MainFragmentBinding by viewBinding()
    override val viewModel: MainViewModel = scope.get()


    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                if (appState.data is List<*>) {
                    Log.e("main frg", appState.data.toString())
                    val adapter: AdapterHealth = AdapterHealth(appState.data as List<Health>)
                    viewBinding.recycleViewHeal.adapter = adapter
                }
            }

            is AppState.Error -> Toast.makeText(
                requireContext(),
                appState.error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it as AppState) }
        viewModel.getData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.fab.setOnClickListener {
            HealthDialog(viewModel).show(childFragmentManager, DIALOG_FRG)
        }
    }


    companion object {
        fun newInstance() = MainFragment()
        private const val DIALOG_FRG =  "dialog"
    }
}