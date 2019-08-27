package com.neiljaywarner.tasc_exercise.di

import com.neiljaywarner.tasc_exercise.cart.ui.CartViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // this would be a lot more interesitng if there were singletons and repositories, etc
    viewModel { CartViewModel() }
}