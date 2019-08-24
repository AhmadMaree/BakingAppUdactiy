package com.example.bakingappudactiy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bakingappudactiy.R;

public class Recipe_Deatils extends AppCompatActivity
{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deatils_list_steps);

        if(savedInstanceState ==null){
            Bundle bundle=new Bundle();
            bundle.putInt("position",getIntent().getIntExtra("position",0));
            bundle.putParcelableArrayList("steps",getIntent().getParcelableArrayListExtra("steps"));
            fragment_Deatils fragment=new fragment_Deatils();
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.continer,fragment)
                    .commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, StepsRecipes.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
