package ir.zngis.yaraapplication.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.widget.Toast
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import ir.zngis.yaraapplication.R
import ir.zngis.yaraapplication.databinding.ActivityMainBinding
import ir.zngis.yaraapplication.repository.model.Movie
import ir.zngis.yaraapplication.view.adaptor.MovieAdapter
import ir.zngis.yaraapplication.vm.MovieVm
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    lateinit var mViewModel: MovieVm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mViewModel = ViewModelProviders.of(this, mViewModelFactory)
            .get(MovieVm::class.java)

        setContentView(R.layout.activity_main)

        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        manager.isSmoothScrollbarEnabled = true

        mViewModel.Movies().observe(this, Observer { movies ->

            movies?.let {
                rv_movies.apply {
                    layoutManager = manager
                    adapter = MovieAdapter(it, object : MovieAdapter.Callback {
                        override fun onItemClicked(movie: Movie) {
                            showDetailFragment(movie)
                        }
                    })
                }
            }
        })


        mViewModel.loadMovies()

    }

    private fun showDetailFragment(movie: Movie) {
        val frg = MovieDetailFragment.newInstance(movie.imdbID)
        supportFragmentManager.beginTransaction().add(R.id.frg_container, frg).addToBackStack(MovieDetailFragment::class.java.simpleName).commit()
    }


    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStackImmediate()
        else
            super.onBackPressed()
    }
}
