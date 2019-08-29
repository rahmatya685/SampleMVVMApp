package ir.zngis.yaraapplication.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ir.zngis.yaraapplication.view.MainActivity


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [FragmentBuilder::class])
    abstract fun contributeSurveyActivity(): MainActivity
}
