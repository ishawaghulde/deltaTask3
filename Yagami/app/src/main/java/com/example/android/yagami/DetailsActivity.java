package com.example.android.yagami;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.android.yagami.MainActivity.KEY_1;

public class DetailsActivity extends AppCompatActivity {
    TypeWriter textView;
    TextView head;
    RelativeLayout relLayout;
    private String content;
    private boolean click = false;
    DetailForcesAPI detailForcesAPI;
    String place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle bundle = getIntent().getExtras();
        place = bundle.getString(KEY_1);
        textView = findViewById(R.id.textView);
        head = findViewById(R.id.head);
        relLayout = findViewById(R.id.relLayout);
        textView.setText("");
        head.setText(place);
        relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click = true;
                getDetailForces();
                writeText(content, click);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://data.police.uk/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        detailForcesAPI = retrofit.create(DetailForcesAPI.class);

        getDetailForces();

    }

    private void getDetailForces(){
        Call<DetailForce> call = detailForcesAPI.getDetailForces(place);

        call.enqueue(new Callback<DetailForce>() {
            @Override
            public void onResponse(@NonNull Call<DetailForce> call,@NonNull Response<DetailForce> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(DetailsActivity.this, "Data Not Found!", Toast.LENGTH_LONG).show();
                    return;
                }

                DetailForce detailForce = response.body();
                content = detailForce.getDesc();
                content += detailForce.getUrl();
                content += detailForce.getEngagementMethods();
                content += detailForce.getTelephone();
                content += detailForce.getID();
                content += detailForce.getName();
                writeText(content, click);

            }

            @Override
            public void onFailure(@NonNull Call<DetailForce> call,@NonNull Throwable t) {
                Toast.makeText(DetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                writeText(t.getMessage(), click);
            }
        });
    }



    private void writeText(String content, boolean click){
        if(!click){
            textView.animateText(content);
        }
        else{
            textView.setText("");
            textView.setChars();
            textView.setText(content);
        }
    }
}
