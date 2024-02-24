package di

import org.koin.dsl.module
import viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

actual fun getViewModelByPlatform() = module {
    viewModel {
        HomeViewModel(get(),get(),get())
    }
}