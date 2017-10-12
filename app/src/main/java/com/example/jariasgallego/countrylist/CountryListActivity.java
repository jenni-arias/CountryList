package com.example.jariasgallego.countrylist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountryListActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private ArrayList<String> country_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        String[] countries = getResources().getStringArray(R.array.countries);
        country_list = new ArrayList<>(Arrays.asList(countries));

        ListView list = (ListView) findViewById(R.id.country_list);

        // todos los ListViews tienen un Adaptador
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, country_list);
        list.setAdapter(adapter);
    }
}
