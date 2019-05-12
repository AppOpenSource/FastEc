package com.diabin.fastec.example;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import com.diabin.latte.activity.ProxyActivity;
import com.diabin.latte.delegates.LatteDelegate;
import com.diabin.latte.ec.launcher.LauncherDelegate;
import com.diabin.latte.ec.sign.ISignListener;
import com.diabin.latte.ec.sign.SignInDelegate;
import com.diabin.latte.ui.launcher.ILauncherListener;
import com.diabin.latte.ui.launcher.OnLauncherFinishTag;

public class ExampleActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener {

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
        return new LauncherDelegate();
        //return new SignUpDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_LONG).show();
                //getSupportDelegate().start(new ExampleDelegate());
                getSupportDelegate().replaceFragment(new ExampleDelegate(), false);
                //getSupportDelegate().startWithPop(new ExampleDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                //getSupportDelegate().start(new SignInDelegate());
                getSupportDelegate().replaceFragment(new SignInDelegate(), false);
                //getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }

    @Override
    public void post(Runnable runnable) {

    }
}
