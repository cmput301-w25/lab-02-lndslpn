package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    String city;
    ListView cityList; //reference for list view
    ArrayAdapter<String> cityAdapter; //reference for array adapter
    ArrayList<String> dataList; //reference for array list
    Button add, delete, confirm;
    EditText cityInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list); //finding reference to ListView
        cityInput = findViewById(R.id.input_text);
        add = findViewById(R.id.add_button);
        delete = (Button) findViewById(R.id.delete_button);
        confirm = findViewById(R.id.confirm_button);

        String[] cities = {"Edmonton", "Paris", "London"}; //declaring a string array

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities)); //feeding city array into ListView

        cityAdapter = new ArrayAdapter<>(this,R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        //making confirm button and input box become visible when you click add
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm.setVisibility(View.VISIBLE);
                cityInput.setVisibility(View.VISIBLE);
            }
        });

        //pressing confirm adds city
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city = cityInput.getText().toString();
                dataList.add(city);
                cityAdapter.notifyDataSetChanged();
                confirm.setVisibility(View.GONE);
                cityInput.setVisibility(View.GONE);
            }
        });

        //deleting city when you click it
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dataList.remove(position);
                    cityAdapter.notifyDataSetChanged();
                }
            });

            }
        });
    }
}