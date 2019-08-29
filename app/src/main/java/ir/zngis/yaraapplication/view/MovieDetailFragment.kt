package ir.zngis.yaraapplication.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ir.zngis.yaraapplication.R
import ir.zngis.yaraapplication.databinding.FragmentMovieDetailBinding
import ir.zngis.yaraapplication.injection.Injectable
import ir.zngis.yaraapplication.repository.model.MovieDetail
import ir.zngis.yaraapplication.vm.MovieVm
import javax.inject.Inject


class MovieDetailFragment : Fragment(), Injectable {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    lateinit var mViewModel: MovieVm

    lateinit var mBinding: FragmentMovieDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mViewModel.CurrentMovie()?.observe(this, Observer { movie: MovieDetail? ->
            movie?.let { movieDetail ->
                mBinding.movie = movieDetail
            }
        })

        arguments?.let { bundle ->
            mViewModel.getMovieDetail(bundle.getString(KEY_MOVIE_DETAIL))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mViewModelFactory)
            .get(MovieVm::class.java)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie_detail,
            container,
            false
        )

        return mBinding.root
    }


    companion object {

        const val KEY_MOVIE_DETAIL = "KEY_MOVIE_DETAIL"

        @JvmStatic
        fun newInstance(movieId: String) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_MOVIE_DETAIL, movieId)
                }
            }
    }
}
