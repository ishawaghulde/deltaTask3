package com.example.android.yagami;

import android.app.SearchManager;
import android.content.Context;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> items=new ArrayList<>();
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    ArrayList<String> filteredList;

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            int length = filteredList.size();
            int i = 0;
            if(length != 0){
                String clickedItem = filteredList.get(position).toLowerCase();
                for(String item : items){
                    if(item.toLowerCase().contains(clickedItem))
                        nav(i);
                    i++;
                }
            }
            else
                nav(position);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText = (EditText) findViewById(R.id.edittext);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setItemClickListener(onItemClickListener);
        makeList();
        recyclerViewAdapter.setItems(items);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }

        });

//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, items[position], Toast.LENGTH_SHORT).show();
//                navigate(position, items[position]);
//            }
//        });

//        searchView.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                (MainActivity.this).recyclerViewAdapter.getFilter().filter(s);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
    }

    private void filter(String text) {
        filteredList = new ArrayList<>();
        for(String item: items){
            if(item.toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        recyclerViewAdapter.filterList(filteredList);
    }

    private void makeList(){

        items.add("Avon and Somerset Constabulary");
        items.add("Bedfordshire Police");
        items.add("Cambridgeshire Constabulary");
        items.add("Cheshire Constabulary");
        items.add("City of London Police");
        items.add("Cleveland Police");
        items.add("Cumbria Constabulary");
        items.add("Derbyshire Constabulary");
        items.add("Devon and Cornwall Police");
        items.add("Dorset Police");
        items.add("Durham Constabulary");
        items.add("Essex Police");
        items.add("Gloucestershire Constabulary");
        items.add("Greater Manchester Police");
        items.add("Hampshire Constabulary");
        items.add("Hertfordshire Constabulary");
        items.add("Humberside Police");
        items.add("Kent Police");
        items.add("Lancashire Constabulary");
        items.add("Leicestershire Police");
        items.add("Lincolnshire Police");
        items.add("Merseyside Police");
        items.add("Metropolitan Police Service");
        items.add("Norfolk Constabulary");
        items.add("Northamptonshire Police");
        items.add("Northumbria Police");
        items.add("North Yorkshire Police");
        items.add("Nottinghamshire Police");
        items.add("South Yorkshire Police");
        items.add("Staffordshire Police");
        items.add("Suffolk Constabulary");
        items.add("Surrey Police");
        items.add("Sussex Police");
        items.add("Thames Valley Police");
        items.add("Warwickshire Police");
        items.add("West Mercia Police");
        items.add("West Midlands Police");
        items.add("West Yorkshire Police");
        items.add("Wiltshire Policee");
        items.add("Dyfed-Powys Police");
        items.add("Gwent Police");
        items.add("North Wales Police");
        items.add("South Wales Police");
    }

    protected void nav(int pos){
        String place = items.get(pos);
        Intent intent = new Intent(this, DetailsActivity.class);
        Bundle bundle = new Bundle();
        String str = String.valueOf(pos);
        bundle.putString("key1",str);
        bundle.putString("key2",place);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
