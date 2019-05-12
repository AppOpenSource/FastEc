package com.diabin.fastec.example;

import android.app.Application;

import com.diabin.latte.app.Latte;
import com.diabin.latte.ec.database.DatabaseManager;
import com.diabin.latte.ec.icon.FontEcModule;
import com.diabin.latte.net.interceptors.DebugInterceptor;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withLoaderDelayed(1000)
                //.withApiHost("http://127.0.0.1")
                .withApiHost("http://192.168.1.100")
                .withInterceptor(new DebugInterceptor("/index", R.raw.mock_respones))
                .configure();
        initStetho();
        DatabaseManager.getInstance().init(this);
    }

    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}
