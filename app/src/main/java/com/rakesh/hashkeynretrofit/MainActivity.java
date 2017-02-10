package com.rakesh.hashkeynretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.rakesh.hashkeynretrofit.retrofit.RetrofitActivity;
import com.rakesh.hashkeynretrofit.util.Constant;

public class MainActivity extends AppCompatActivity {

    private Button btn_hash_key, btn_retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_hash_key = (Button) findViewById(R.id.btn_hash_key);
        btn_retrofit = (Button) findViewById(R.id.btn_retrofit);

        btn_hash_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, HashKeyCreatorActivity.class);
                startActivityForResult(in, Constant.HashKeyCreatorActivity);
            }
        });

        btn_retrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, RetrofitActivity.class);
                startActivityForResult(in, Constant.RetrofitActivity);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {

            if (resultCode == RESULT_OK) {
                Log.e("MainActivity: ", "OK-1");
            }

            if (resultCode == RESULT_CANCELED) {
                Log.e("MainActivity: ", "CANCELED-1");
            }
        }

        if (requestCode == 2) {

            if (resultCode == RESULT_OK) {
                Log.e("MainActivity: ", "OK-2");
            }

            if (resultCode == RESULT_CANCELED) {
                Log.e("MainActivity: ", "CANCELED-2");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
