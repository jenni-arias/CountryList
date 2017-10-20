package com.example.jariasgallego.countrylist;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class CountryListActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private ArrayList<String> country_list;
    private String pais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        String[] countries = getResources().getStringArray(R.array.countries);
        country_list = new ArrayList<>(Arrays.asList(countries));

        final ListView list = (ListView) findViewById(R.id.country_list);

        // todos los ListViews tienen un Adaptador
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, country_list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, final int pos, long id) {
                Toast.makeText(
                        CountryListActivity.this,
                        String.format("Has escogido: '%s'", country_list.get(pos)),
                        Toast.LENGTH_SHORT
                ).show();
                pais = country_list.get(pos);
                editList(item, pais, pos);
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View item, final int pos, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CountryListActivity.this);
                builder.setTitle(R.string.confirm);
                String msg = getResources().getString(R.string.confirm_message);
                builder.setMessage(msg + " " + country_list.get(pos) + "?");
                builder.setPositiveButton(R.string.erase, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        country_list.remove(pos);
                        adapter.notifyDataSetChanged();             // Notificación de que han cambiado los datos
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, null);
                builder.create().show();
                return true;                                // LongClick ha ocurrido --> No hagas el click
            }
        });

    }

    public void editList (View view, String pais, int pos) {
        Intent intent = new Intent(this, EditListActivity.class);
        intent.putExtra("pais", pais);
        intent.putExtra("pos", pos);        //extra
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (resultCode == Activity.RESULT_OK) {
                    String new_item = data.getStringExtra("new_item");
                    int pos = data.getIntExtra("pos", -1);
                    country_list.set(pos, new_item);
                    adapter.notifyDataSetChanged();
                }
        }
    }


}
