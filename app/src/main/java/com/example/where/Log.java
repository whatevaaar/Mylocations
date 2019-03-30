package com.example.where;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
public class Log extends AppCompatActivity {
    DBHelper mDBHelper;
    Activity context;
    Button regresar;
    ArrayList<String> listaViajes = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        context = this;
        regresar = findViewById(R.id.botonRegresar);
        mDBHelper = new DBHelper(context);
        regresar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent show = new Intent(context, MainActivity.class);
                startActivity(show);
            }
        });
        ListView listView = findViewById(R.id.list_viajes);
        Cursor data= mDBHelper.getAllData();
            while(data.moveToNext()){
                listaViajes.add(data.getString(1));
            }
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaViajes);
        listView.setAdapter(listAdapter);
    }
}
