package ir.zngis.yaraapplication.injection.component;

import dagger.Subcomponent;
import ir.zngis.yaraapplication.vm.MovieVm;

@Subcomponent
public interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder {
        public ViewModelSubComponent build();
    }

    public MovieVm movieVm();
}
