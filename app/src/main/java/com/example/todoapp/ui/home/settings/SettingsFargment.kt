package com.example.todoapp.ui.home.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todoapp.databinding.FragmentSettingsBinding

class SettingsFargment : Fragment() {
    lateinit var viewbinding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewbinding = FragmentSettingsBinding.inflate(
            inflater,
            container,
            false
        )
        return viewbinding.root
    }
}