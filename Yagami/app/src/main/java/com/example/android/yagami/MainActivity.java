package com.example.android.yagami;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> items=new ArrayList<>();
    ArrayList<String> idList=new ArrayList<>();
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    ArrayList<String> filteredList = new ArrayList<>();
    boolean search = false;
    public static final String KEY_1 = "key_1";

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
        EditText editText = findViewById(R.id.edittext);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setItemClickListener(onItemClickListener);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                search = true;
                filter(s.toString());
            }

        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://data.police.uk/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ForcesAPI forcesAPI = retrofit.create(ForcesAPI.class);

        Call<List<Force>> call = forcesAPI.getForces();
        call.enqueue(new Callback<List<Force>>() {
            @Override
            public void onResponse(@NonNull Call<List<Force>> call,@NonNull Response<List<Force>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Data Not Found!", Toast.LENGTH_LONG).show();
                    return;
                }

                List<Force> forces = response.body();

                for (Force force : forces) {
                    String content = force.getName();
                    items.add(content);
                    String id = force.getForceId();
                    idList.add(id);
                }
                makeList();
            }


            @Override
            public void onFailure(@NonNull Call<List<Force>> call,@NonNull Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void makeList(){
        recyclerViewAdapter.setItems(items);
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


    protected void nav(int pos){
        String place = idList.get(pos);
        Intent intent = new Intent(this, DetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(KEY_1,place);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
