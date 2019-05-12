package com.diabin.latte.ec.sign;

import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.diabin.latte.app.AccountManager;
import com.diabin.latte.app.Latte;

public class SignHandler {

    public static void onSignIn(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }


    public static void onSignUp(String response, ISignListener signListener) {
        final Integer code = JSON.parseObject(response).getInteger("code");
        if (code == 100) {
            final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
            final long userId = profileJson.getLong("userId");
            final String userName = profileJson.getString("userName");
            final String passWord = profileJson.getString("passWord");
            final String phone = profileJson.getString("phone");
            final String avatar = profileJson.getString("avatar");
            final String sex = profileJson.getString("sex");
            final String nickName = profileJson.getString("nickName");

            //已经注册并登录成功了
            Toast.makeText(Latte.getAppContext(), "已经注册并登录成功了", Toast.LENGTH_LONG).show();
            AccountManager.setSignState(true);
            signListener.onSignUpSuccess();
        } else {
            Toast.makeText(Latte.getAppContext(), "用户注册失败", Toast.LENGTH_SHORT).show();
        }
    }
}
