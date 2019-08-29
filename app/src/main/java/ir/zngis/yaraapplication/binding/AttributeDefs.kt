package ir.zngis.yaraapplication.binding

import android.annotation.SuppressLint
import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import ir.zngis.yaraapplication.R
import ir.zngis.yaraapplication.repository.model.Movie
import ir.zngis.yaraapplication.repository.model.MovieDetail
import ir.zngis.yaraapplication.util.GeneralUtil
import java.io.File


class AttributeDefs {

    companion object {


        @JvmStatic
        @BindingAdapter("app:binding")
        fun bindAttachmentToImageView(imageView: ImageView, movie: Movie?) {

            movie ?: return

            val imagePath = GeneralUtil.getPicsDirectory(imageView.context)

            val filePath = imagePath?.absolutePath + File.separatorChar + movie.imdbID

            val file = File(filePath)
            if (file.exists()) {
                bindImageView(imageView, movie.imdbID)
            } else {
                setImageUrl(imageView, movie.Poster)
            }


        }

        @JvmStatic
        fun bindImageView(imageView: ImageView, fileName: String) {

            val imagePath = GeneralUtil.getPicsDirectory(imageView.context)

            val filePath = imagePath?.absolutePath + File.separatorChar + fileName

            Glide.with(imageView.context).load(filePath).apply(
                RequestOptions().override(imageView.measuredWidth, imageView.measuredHeight).centerCrop()
                    .dontAnimate().error(R.drawable.error).diskCacheStrategy(DiskCacheStrategy.ALL)
            ).into(imageView)
        }

        @SuppressLint("CheckResult")
        @JvmStatic
        fun setImageUrl(imageView: ImageView, url: String) {
            if (url.isNotEmpty()) {
                val requestOptions = RequestOptions()
                requestOptions.error(R.drawable.error)
                requestOptions.downsample(DownsampleStrategy.NONE)
                requestOptions.placeholder(R.drawable.ic_photo_black_24dp)

                val context = imageView.context

                Glide.with(context).asBitmap().load(url).apply(requestOptions).into(imageView)


            }
        }

    }

}