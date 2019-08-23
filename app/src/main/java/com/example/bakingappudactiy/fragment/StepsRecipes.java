package com.example.bakingappudactiy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingappudactiy.R;
import com.example.bakingappudactiy.servies.Baking;
import com.example.bakingappudactiy.servies.Ingredients;
import com.example.bakingappudactiy.servies.StepsBaking;

import java.util.ArrayList;
import java.util.List;

public class StepsRecipes extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter_list_steps adapter_list_steps;
    ArrayList<StepsBaking>stepsBakings;
    ArrayList<Ingredients>ingredients1;
    boolean Tablateorphone;
    TextView ingredients;
    Baking baking;

    String data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_deatils_list);
        ingredients=findViewById(R.id.recipe_details_ingredients);
        Intent intent = getIntent();
        data="";

        data +="Ingredients list :\n";
        if(intent.hasExtra("ListData")) {
            baking = intent.getParcelableExtra("ListData");
            ingredients1=intent.getParcelableArrayListExtra("ing");
            stepsBakings = intent.getParcelableArrayListExtra("steps");
            Log.d("SHOWING", ingredients1.size()+"");
            for (int i=0 ;i<ingredients1.size();i++){
                data += "♦" + ingredients1.get(i).getMingredient();
                data+="("+ingredients1.get(i).getmQuantity()+ingredients1.get(i).getmMeasure()+")"+".";
                data+="\n";
            }
            ingredients.setText(data);
            setTitle(baking.getmName());

            //check continar
            if(findViewById(R.id.details_vd_container) !=null){
                Tablateorphone =true;
            }
            recyclerView=findViewById(R.id.recipe_details_steps);
            adapter_list_steps=new Adapter_list_steps(stepsBakings,Tablateorphone,this);
            recyclerView.setAdapter(adapter_list_steps);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setHasFixedSize(true);
        }
    }
}
