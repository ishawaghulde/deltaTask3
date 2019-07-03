package com.example.android.yagami;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrimeListActivity extends AppCompatActivity{
    ArrayList<String> crimeList = new ArrayList<>();
    TextAdapter textAdapter;
    RecyclerView recyclerView;

    private PoliceDetailApi policeApi;
    private String date;
    private String latitude;
    private String longitude;

    private String colCategory;
    private String colLocation_type;
    private String colLocation;
    private String colContext;
    private String colOutcome_status;
    private String colPersistent_id;
    private String colCrime_id;
    private String colLocation_subtype;
    private String colMonth;

    int position;

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            navigate(position);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_list);
        Bundle bundle = getIntent().getExtras();
        String year = bundle.getString("key1");
        String month = bundle.getString("key2");
        latitude = bundle.getString("key3");
        longitude = bundle.getString("key4");

        date = year + "-" + month;

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        textAdapter = new TextAdapter();
        recyclerView.setAdapter(textAdapter);
        textAdapter.setItemClickListener(onItemClickListener);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                int pos = viewHolder.getAdapterPosition();
                String deletedItem = crimeList.get(pos);
                addToFav(pos);
                textAdapter.undoDelete(pos, deletedItem);
            }

        }).attachToRecyclerView(recyclerView);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://data.police.uk/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        policeApi = retrofit.create(PoliceDetailApi.class);

        getDetailPosts();
    }

    private void getDetailPosts() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("date", date);
        parameters.put("lat", latitude);
        parameters.put("lng", longitude);

        Call<List<DetailPost>> call = policeApi.getDetailPosts(parameters);

        call.enqueue(new Callback<List<DetailPost>>() {
            @Override
            public void onResponse(@NonNull Call<List<DetailPost>> call,@NonNull Response<List<DetailPost>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(CrimeListActivity.this, "Data Not Found!", Toast.LENGTH_LONG).show();
                    return;
                }

                List<DetailPost> posts = response.body();

                for (DetailPost post : posts) {
                    String content = post.getCategory();

                    crimeList.add(content);
                }
                makeList();
            }

            @Override
            public void onFailure(@NonNull Call<List<DetailPost>> call,@NonNull Throwable t) {
                Toast.makeText(CrimeListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void makeList(){
        textAdapter.setItems(crimeList);
    }

    private void addToFav(int pos){
        PoliceDetailApi policeApi;
        position = pos;


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://data.police.uk/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        policeApi = retrofit.create(PoliceDetailApi.class);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("date", date);
        parameters.put("lat", latitude);
        parameters.put("lng", longitude);

        Call<List<DetailPost>> call = policeApi.getDetailPosts(parameters);

        call.enqueue(new Callback<List<DetailPost>>() {
            @Override
            public void onResponse(@NonNull Call<List<DetailPost>> call,@NonNull Response<List<DetailPost>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(CrimeListActivity.this, "Data Not Found!", Toast.LENGTH_LONG).show();
                    return;
                }

                List<DetailPost> posts = response.body();

                for (DetailPost post : posts) {
                    if(position == 0){
                        colCategory = post.getCategory();
                        colLocation_type = post.getLocationType();
                        colLocation = post.getLocation();
                        colContext = post.getContext();
                        colOutcome_status = post.getOutcomeStatus();
                        colPersistent_id= post.getPersistentId();
                        colCrime_id = post.getID();
                        colLocation_subtype = post.getLocationSubtype();
                        colMonth = post.getMonth();


                    }
                    position -= 1;
                }

                DbHandler dbHandler = new DbHandler(CrimeListActivity.this);
                boolean exists = dbHandler.GetUserByCrimeId(colCrime_id);
                if(!exists){
                    dbHandler.insertUserDetails(colCategory, colLocation_type, colLocation, colContext, colOutcome_status, colPersistent_id, colCrime_id, colLocation_subtype, colMonth);
                    Toast.makeText(CrimeListActivity.this, "Added to favorites", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CrimeListActivity.this, "Already in favorites", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<DetailPost>> call,@NonNull Throwable t) {
                Toast.makeText(CrimeListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigate(int pos){
        Intent intent = new Intent(CrimeListActivity.this, CrimeDetailsActivity.class);
        Bundle bundle = new Bundle();
        String str = String.valueOf(pos);
        bundle.putString("key1",str);
        bundle.putString("key2",date);
        bundle.putString("key3",latitude);
        bundle.putString("key4",longitude);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
