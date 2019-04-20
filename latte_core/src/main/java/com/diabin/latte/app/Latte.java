package com.diabin.latte.app;

import android.content.Context;

import java.util.WeakHashMap;

public final class Latte {

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static WeakHashMap<Object, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }


}
