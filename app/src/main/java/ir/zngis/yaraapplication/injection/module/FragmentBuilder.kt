package ir.zngis.yaraapplication.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ir.zngis.yaraapplication.view.MovieDetailFragment


@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector
    internal abstract fun contributeMovieDetailFragment(): MovieDetailFragment
}