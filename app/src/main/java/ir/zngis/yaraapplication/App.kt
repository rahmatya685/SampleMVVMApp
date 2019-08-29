package ir.zngis.yaraapplication

import android.app.Activity
import android.app.Application
import android.app.Service
import dagger.android.*

import ir.zngis.yaraapplication.injection.AppInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {


    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }


    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)

    }

}