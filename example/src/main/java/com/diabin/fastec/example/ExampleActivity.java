package com.diabin.fastec.example;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import com.diabin.latte.activity.ProxyActivity;
import com.diabin.latte.delegates.LatteDelegate;
import com.diabin.latte.ec.sign.ISignListener;
import com.diabin.latte.ec.sign.SignUpDelegate;

public class ExampleActivity extends ProxyActivity implements ISignListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        //return new ExampleDelegate();
        //return new LauncherDelegate();
        return new SignUpDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(ExampleActivity.this, "onSignInSuccess", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(ExampleActivity.this, "onSignUpSuccess", Toast.LENGTH_LONG).show();
    }
}
