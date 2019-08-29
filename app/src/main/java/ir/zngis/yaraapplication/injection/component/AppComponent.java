package ir.zngis.yaraapplication.injection.component;

import android.app.Application;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import ir.zngis.yaraapplication.App;
import ir.zngis.yaraapplication.injection.module.ActivityBuilder;
import ir.zngis.yaraapplication.injection.module.AppModule;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AppModule.class,
            ActivityBuilder.class
})
public interface AppComponent {


    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);
        AppComponent build();

    }
    void inject(App applicationInit);
}