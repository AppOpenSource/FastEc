package com.diabin.fastec.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.diabin.latte.app.Latte;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(Latte.getAppContext(), "context in", Toast.LENGTH_SHORT).show();
    }
}
