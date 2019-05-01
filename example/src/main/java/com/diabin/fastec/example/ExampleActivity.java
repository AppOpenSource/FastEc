package com.diabin.fastec.example;

import com.diabin.latte.activity.ProxyActivity;
import com.diabin.latte.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
