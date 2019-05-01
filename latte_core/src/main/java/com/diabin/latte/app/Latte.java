package com.diabin.latte.app;

import android.content.Context;
import android.os.Handler;

public final class Latte {

    public static Configurator init(Context context) {
        getConfigurator().getLatteConfigs()
                .put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return getConfigurator();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static final Context getAppContext() {
        return (Context) getConfiguration(ConfigType.APPLICATION_CONTEXT.name());
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigType.HANDLER.name());
    }




}
