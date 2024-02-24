package utils

import di.getSharedModules
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.component.get
import org.koin.dsl.module
import viewmodel.HomeViewModel


fun initKoin() {
    startKoin {
        modules(getSharedModules())
    }
}

class KoinHelper: KoinComponent {
    fun getAppViewModel() = get<HomeViewModel>()
}