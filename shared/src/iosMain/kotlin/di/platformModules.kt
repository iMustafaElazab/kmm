package di

import org.koin.dsl.module
import viewmodel.HomeViewModel


actual fun getViewModelByPlatform() = module {
    single {
        HomeViewModel(get(),get(),get())
    }
}