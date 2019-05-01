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
import com.diabin.latte.net.RestCreator;
import com.diabin.latte.net.callback.IFailure;
import com.diabin.latte.net.callback.ISuccess;
import com.diabin.latte.net.rx.RxRestClient;

import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
        //testRestClient();
        //testLocalResponse();
        //onCallRxGet();
        onCallRxRestClient();
    }

    /**
     * 测试基础框架的网络基本功能
     */
    private void testRestClient() {
        RestClient.builder()
                .url("https://www.baidu.com")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        mContent.setText(response);
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

    /**
     * 测试，本地拦截器模拟后台返回json
     */
    private void testLocalResponse() {
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Latte.getAppContext(), response, Toast.LENGTH_LONG).show();
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

    /**
     * 测试Rxjava基本的功能
     */
    //TODO:测试方法，没啥卵用
    void onCallRxGet() {
        final String url = "index.php";
        final WeakHashMap<String, Object> params = new WeakHashMap<>();
        final Observable<String> observable = RestCreator.getRxRestService().get(url, params);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull String s) {
                        Toast.makeText(getContext(), "onCallRxGet response -> "+s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //TODO:测试方法，没啥卵用X2
    private void onCallRxRestClient() {
        final String url = "index.php";
        RxRestClient.builder()
                .url(url)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull String s) {
                        Toast.makeText(getContext(), "onCallRxRestClient response -> "+s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
