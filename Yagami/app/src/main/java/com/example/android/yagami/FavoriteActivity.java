package com.example.android.yagami;

import android.app.Activity;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FavoriteActivity extends AppCompatActivity {
    ArrayList<HashMap<String, String>> userList;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        DbHandler db = new DbHandler(this);
        userList = db.GetUsers();
        lv = (ListView) findViewById(R.id.user_list);
        ListAdapter adapter = new SimpleAdapter(FavoriteActivity.this, userList, R.layout.list_row,new String[]{"category","location_type","location", "context", "outcome_status", "persistent_id", "crime_id", "location_subtype", "month"}, new int[]{R.id.category, R.id.location_type, R.id.location, R.id.context, R.id.outcome_status, R.id.persistent_id, R.id.crime_id, R.id.location_subtype, R.id.month});
        lv.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fav_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query){
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText){
                ArrayList<String> userslist = new ArrayList<>();
                for (HashMap<String, String> map : userList) {
                    for (Map.Entry<String, String> mapEntry : map.entrySet()) {
                        String key = mapEntry.getKey();
                        String value = mapEntry.getValue();
                        if (key.toLowerCase().contains(newText.toLowerCase())) {
                            userslist.add(key);
                        }
                        if ( value != null && value.toLowerCase().contains(newText.toLowerCase())) {
                            userslist.add(value);
                        }
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(FavoriteActivity.this, android.R.layout.simple_list_item_1, userslist);
                lv.setAdapter(adapter);
//                for(Map.Entry<String, String> entry: userList.entrySet()){
//                    System.out.println(entry.getKey());
//                    System.out.println(entry.getValue());
//                }
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
