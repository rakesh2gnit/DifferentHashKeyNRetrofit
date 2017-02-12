package com.rakesh.hashkeynretrofit.retrofit.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.rakesh.hashkeynretrofit.AppSingleton;
import com.rakesh.hashkeynretrofit.R;
import com.rakesh.hashkeynretrofit.retrofit.adapter.CountryAdapter;
import com.rakesh.hashkeynretrofit.retrofit.model.Channels;
import com.rakesh.hashkeynretrofit.retrofit.model.Country;
import com.rakesh.hashkeynretrofit.util.Constant;

import java.util.ArrayList;

/**
 * Created by Admin on 10-02-2017.
 */

public class ChannelActivity extends AppCompatActivity {

    private CountryAdapter channelAdapter;
    private RecyclerView rv_channel;
    private TextView txtViewTitle, txtViewNoResult;
    private EditText editTxtSearch;
    private ArrayList<Channels> channelArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        getSupportActionBar().setTitle("Select Channel");

        channelArrayList = getIntent().getParcelableArrayListExtra("channellist");

        rv_channel = (RecyclerView) findViewById(R.id.rv_country);

        txtViewTitle = (TextView) findViewById(R.id.textView_title);
        txtViewTitle.setText("Select country");

        editTxtSearch = (EditText) findViewById(R.id.editText_search);
        //editText_search.setHint(codePicker.getSearchHintText());

        txtViewNoResult = (TextView) findViewById(R.id.textView_noresult);
        //textView_noResult.setText(codePicker.getNoResultFoundText());

        Log.e("ChannelActivity: ", AppSingleton.getInstance().sLoadSavedPreferencesString(ChannelActivity.this, Constant.sharePrefChannelKey));
        //channelAdapter = new CountryAdapter(this, channelArrayList, editTxtSearch, txtViewNoResult, txtViewTitle);
        //rv_channel.setLayoutManager(new LinearLayoutManager(this));
        //rv_channel.setAdapter(channelAdapter);
    }
}
