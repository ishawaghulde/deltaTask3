package com.example.android.yagami;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrimeDetailsActivity extends AppCompatActivity {
    private PoliceDetailApi policeApi;
    int pos;
    private DbHandler dbHandler;
    private boolean exists;
    private boolean click = false;
    private  Menu optionsMenu;

    String date;
    String latitude;
    String longitude;
    TypeWriter details;
    String colCategory;
    String colLocation_type;
    String colLocation;
    String colContext;
    String colOutcome_status;
    String colPersistent_id;
    String colCrime_id;
    String colLocation_subtype;
    String colMonth;
    private String content = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_details);
        Bundle bundle = getIntent().getExtras();
        String str = bundle.getString("key1");
        pos = Integer.parseInt(str);
        date = bundle.getString("key2");
        latitude = bundle.getString("key3");
        longitude = bundle.getString("key4");

        details = findViewById(R.id.details);
        RelativeLayout relativeLayout = findViewById(R.id.relativeLayout);
        details.setText("");
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click = true;
                getDetailPosts();
                writeText(content, click);
            }
        });

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
                    Toast.makeText(CrimeDetailsActivity.this, "Data Not Found!", Toast.LENGTH_LONG).show();
                    return;
                }

                List<DetailPost> posts = response.body();

                for (DetailPost post : posts) {
                    if(pos ==0){
                        String str = post.getCategory();
                        colCategory = "Category : " + str + "\n";
                        colLocation_type = post.getLocationType();
                        colLocation = post.getLocation();
                        colContext = post.getContext();
                        colOutcome_status = post.getOutcomeStatus();
                        colPersistent_id= post.getPersistentId();
                        colCrime_id = post.getID();
                        colLocation_subtype = post.getLocationSubtype();
                        colMonth = post.getMonth();

                        content = colCategory + colLocation_type + colLocation + colContext + colOutcome_status + colPersistent_id + colCrime_id + colLocation_subtype + colMonth;
                        dbHandler = new DbHandler(CrimeDetailsActivity.this);
                        exists = dbHandler.GetUserByCrimeId(colCrime_id);
                        if(exists){
                            MenuItem item = optionsMenu.findItem(R.id.addfav);
                            item.setIcon(getResources().getDrawable(R.drawable.favorite_added_heart));
                        }
                        writeText(content, click);
                    }
                    pos= pos-1;
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<DetailPost>> call,@NonNull Throwable t) {
                Toast.makeText(CrimeDetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void writeText(String content, boolean click){
        if(!click){
            details.animateText(content);
        }
        else{
            details.setText("");
            details.setChars();
            details.setText(content);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        optionsMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.addfav){
            if(!exists){
                dbHandler.insertUserDetails(colCategory, colLocation_type, colLocation, colContext, colOutcome_status, colPersistent_id, colCrime_id, colLocation_subtype, colMonth);
                Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Already in favorites", Toast.LENGTH_SHORT).show();
            }

        }
        return super.onOptionsItemSelected(item);
    }

}