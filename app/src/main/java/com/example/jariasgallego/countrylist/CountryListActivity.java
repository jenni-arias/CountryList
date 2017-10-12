package com.example.jariasgallego.countrylist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, int pos, long id) {
                Toast.makeText(
                        CountryListActivity.this,
                        String.format("Has escogido: '%s'", country_list.get(pos)),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View item, int pos, long id) {
                country_list.remove(pos);
                adapter.notifyDataSetChanged();             // Notificación de que han cambiado los datos
                return true;                                // LongClick ha ocurrido --> No hagas el click
            }
        });
    }
}
