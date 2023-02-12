package com.iamnaran.firefly.ui.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import coil.ImageLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(
    val imageLoader: ImageLoader
): LifecycleObserver, ViewModel() {

    init {

    }


}