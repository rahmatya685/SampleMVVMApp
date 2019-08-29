package ir.zngis.yaraapplication.vm.factory

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v4.util.ArrayMap
import ir.zngis.yaraapplication.injection.component.ViewModelSubComponent
import ir.zngis.yaraapplication.vm.MovieVm
import java.util.concurrent.Callable
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ProjectViewModelFactory @Inject
constructor(viewModelSubComponent: ViewModelSubComponent) : ViewModelProvider.NewInstanceFactory() {

    private val creators: ArrayMap<Class<*>, Callable<out AndroidViewModel>> = ArrayMap()

    init {
         creators.put(MovieVm::class.java, Callable { viewModelSubComponent.movieVm() })
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var callable = creators[modelClass]
        if (callable == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    callable = value
                }
            }
        }
        if (callable == null) {
            throw IllegalArgumentException("Unknown model class $modelClass")
        }
        try {
            return callable.call() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}