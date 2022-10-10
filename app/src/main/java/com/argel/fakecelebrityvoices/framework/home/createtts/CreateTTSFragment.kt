package com.argel.fakecelebrityvoices.framework.home.createtts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.argel.fakecelebrityvoices.R

class CreateTTSFragment : Fragment() {

    private lateinit var viewModel: CreateTTSViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_tts, container, false)
    }
}