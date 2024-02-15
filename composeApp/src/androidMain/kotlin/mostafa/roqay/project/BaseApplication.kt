package mostafa.roqay.project

import android.app.Application
import di.initKoin
import org.koin.dsl.module
import viewmodel.HomeViewModel


class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            listOf(
                module {
                    factory { HomeViewModel(get(),get(),get()) }
                }
            )
        )
    }
}