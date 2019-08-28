package com.example.bakingappudactiy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bakingappudactiy.servies.Baking;
import com.example.bakingappudactiy.servies.Mainactivity_Adapter;
import com.example.bakingappudactiy.servies.Servies;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="ERROR";

    RecyclerView recyclerView ;
    Mainactivity_Adapter myAdapter;
    List<Baking> baking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baking=new ArrayList<>();

        recyclerView = findViewById(R.id.rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        myAdapter = new Mainactivity_Adapter(MainActivity.this, baking);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setHasFixedSize(true);
        Fetch_Data();
    }


    private void Fetch_Data(){

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/")
                .addConverterFactory(GsonConverterFactory.create(new Gson())).build();

            Servies BakingServies=retrofit.create(Servies.class);

        BakingServies.readjosnObject().enqueue(new Callback<List<Baking>>() {
            @Override
            public void onResponse(Call<List<Baking>> call, Response<List<Baking>> response) {
                if(response.isSuccessful()){

                    baking=response.body();
                    myAdapter.setRecipes(baking);
                    recyclerView.setAdapter(myAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setHasFixedSize(true);



                }
                else{
                    Toast.makeText(MainActivity.this,response.errorBody().toString(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Baking>> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: "+t.getMessage() );
            }
        });







    }
}
