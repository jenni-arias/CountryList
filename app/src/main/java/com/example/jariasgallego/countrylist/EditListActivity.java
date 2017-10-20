package com.example.jariasgallego.countrylist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


/**
 * Created by j.arias.gallego on 20/10/2017.
 */

public class EditListActivity extends AppCompatActivity{

    private int pos;
    private EditText edit_list;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_country_list);

        Intent intent = getIntent();
        String item = intent.getStringExtra("pais");
        pos = intent.getIntExtra("pos", -1);
        edit_list = (EditText) findViewById(R.id.edit_list);
        edit_list.setText(item);
        edit_list.setSelection(edit_list.length());
        //        num_telefono.setSelection(num_telefono.length());

    }

    public void saveItem (View view) {
        String new_item = edit_list.getText().toString();
        Intent data = new Intent();
        data.putExtra("new_item", new_item);
        data.putExtra("pos", pos);
        setResult(RESULT_OK, data);
        finish();
    }
}
