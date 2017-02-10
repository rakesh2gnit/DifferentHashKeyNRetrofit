package com.rakesh.hashkeynretrofit.retrofit.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.rakesh.hashkeynretrofit.R;
import com.rakesh.hashkeynretrofit.retrofit.adapter.CountryAdapter;
import com.rakesh.hashkeynretrofit.retrofit.model.Country;

import java.util.ArrayList;

/**
 * Created by Admin on 10-02-2017.
 */

public class CountryActivity extends AppCompatActivity {

    private CountryAdapter countryAdapter;
    private RecyclerView rv_country;
    private TextView txtViewTitle, txtViewNoResult;
    private EditText editTxtSearch;
    private ArrayList<Country> countryArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        getSupportActionBar().setTitle("Select Country");

        countryArrayList = getIntent().getParcelableArrayListExtra("countrylist");

        rv_country = (RecyclerView) findViewById(R.id.rv_country);

        txtViewTitle = (TextView) findViewById(R.id.textView_title);
        txtViewTitle.setText("Select country");

        editTxtSearch = (EditText) findViewById(R.id.editText_search);
        //editText_search.setHint(codePicker.getSearchHintText());

        txtViewNoResult = (TextView) findViewById(R.id.textView_noresult);
        //textView_noResult.setText(codePicker.getNoResultFoundText());

        countryAdapter = new CountryAdapter(this, countryArrayList, editTxtSearch, txtViewNoResult, txtViewTitle);
        rv_country.setLayoutManager(new LinearLayoutManager(this));
        rv_country.setAdapter(countryAdapter);
    }
}