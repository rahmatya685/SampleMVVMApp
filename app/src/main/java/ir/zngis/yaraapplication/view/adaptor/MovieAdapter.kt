package ir.zngis.yaraapplication.view.adaptor

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.edsab.gedat.edsgeneral.util.inflate
import ir.zngis.yaraapplication.R
import ir.zngis.yaraapplication.databinding.ItemMovieBinding
import ir.zngis.yaraapplication.repository.model.Movie

class MovieAdapter(val movies: List<Movie>,var callback:Callback) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val binding = DataBindingUtil.inflate<ItemMovieBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.item_movie,
            viewGroup,
            false
        )
        return ViewHolder(binding,callback)
    }


    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(vh: ViewHolder, postion: Int) {
        vh.bind(movies[postion])
    }


    class ViewHolder(val binding: ItemMovieBinding,var callback:Callback) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.root.setOnClickListener { callback.onItemClicked(movie) }
        }
    }

    interface Callback{
        fun onItemClicked(movie: Movie)
    }
}