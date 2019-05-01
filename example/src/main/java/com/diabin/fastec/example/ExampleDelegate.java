package com.diabin.fastec.example;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.diabin.latte.app.Latte;
import com.diabin.latte.delegates.LatteDelegate;
import com.diabin.latte.net.RestClient;
import com.diabin.latte.net.callback.IFailure;
import com.diabin.latte.net.callback.ISuccess;

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }


    private TextView mContent;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContent = view.findViewById(R.id.content);
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("https://www.baidu.com")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        mContent.setText(response);
                        //Toast.makeText(Latte.getAppContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(Latte.getAppContext(), "onFailure", Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();

    }
}
