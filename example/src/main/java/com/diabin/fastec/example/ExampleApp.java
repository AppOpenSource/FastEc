package com.diabin.fastec.example;

import android.app.Application;

import com.diabin.latte.app.Latte;

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                //.withIcon(new FontAwesomeModule())
                .withApiHost("http://127.0.0.1")
                .configure();
    }
}