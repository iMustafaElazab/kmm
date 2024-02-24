package mostafa.roqay.project

import android.app.Application
import di.getSharedModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(getSharedModules())
        }
    }
}