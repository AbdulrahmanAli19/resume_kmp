package abdulrahman.ali19

import abdulrahman.ali19.di.initKoin
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent

class ResumeApplication : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@ResumeApplication)
        }
    }
}