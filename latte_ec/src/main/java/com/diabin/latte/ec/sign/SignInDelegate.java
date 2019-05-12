package com.diabin.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.diabin.latte.app.Latte;
import com.diabin.latte.delegates.LatteDelegate;
import com.diabin.latte.ec.R;
import com.diabin.latte.net.RestClient;
import com.diabin.latte.net.callback.IError;
import com.diabin.latte.net.callback.IFailure;
import com.diabin.latte.net.callback.ISuccess;
import com.google.android.material.textfield.TextInputEditText;

public class SignInDelegate extends LatteDelegate implements View.OnClickListener {

    private static final String TAG = "SignInDelegate";
    private TextInputEditText mEmail = null;
    private TextInputEditText mPassword = null;
    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    private void onClickSignIn() {
        if (checkForm()) {
            RestClient.builder()
                    .url("http://192.168.1.100:8080/user/v1/tokens")
                    //.params("userName", "zhongcai@163.com")
                    //.params("passWord", "joah108")
                    .params("userName", mEmail.getText().toString())
                    .params("passWord", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            //LatteLogger.json("USER_PROFILE", response);
                            SignHandler.onSignIn(response, mISignListener);
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(Latte.getAppContext(), "onFailure", Toast.LENGTH_LONG).show();
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            Log.d(TAG, "onError, code = "+code+", msg = "+msg);
                            Toast.makeText(Latte.getAppContext(), "登录失败，请输入正确的用户和密码", Toast.LENGTH_LONG).show();
                        }
                    })
                    .build()
                    .post();
        }
    }

    private void onClickWeChat() {
        /*LatteWeChat
                .getInstance()
                .onSignSuccess(new IWeChatSignInCallback() {
                    @Override
                    public void onSignInSuccess(String userInfo) {
                        Toast.makeText(getContext(), userInfo, Toast.LENGTH_LONG).show();
                    }
                })
                .signIn();*/
    }

    private void onClickLink() {
        getSupportDelegate().start(new SignUpDelegate());
    }

    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEmail = view.findViewById(R.id.edit_sign_in_email);
        mPassword = view.findViewById(R.id.edit_sign_in_password);
        view.findViewById(R.id.btn_sign_in).setOnClickListener(this);
        view.findViewById(R.id.tv_link_sign_up).setOnClickListener(this);
        view.findViewById(R.id.icon_sign_in_wechat).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_sign_in) {
            onClickSignIn();
        } else if (i == R.id.tv_link_sign_up) {
            onClickLink();
        } else if (i == R.id.icon_sign_in_wechat) {
            onClickWeChat();
        }
    }
}
