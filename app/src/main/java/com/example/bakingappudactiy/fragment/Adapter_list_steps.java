package com.example.bakingappudactiy.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingappudactiy.R;
import com.example.bakingappudactiy.servies.StepsBaking;

import java.util.ArrayList;

public  class Adapter_list_steps extends RecyclerView.Adapter<Adapter_list_steps.MyHolderSteps> {



   private ArrayList<StepsBaking> steps;
    private final boolean TPFlag;
    private final StepsRecipes stepsRecipes;


    public Adapter_list_steps(ArrayList<StepsBaking> steps, boolean TPFlag, StepsRecipes stepsRecipes) {
        this.steps = steps;
        this.TPFlag = TPFlag;
        this.stepsRecipes = stepsRecipes;
    }

    public void setSteps(ArrayList<StepsBaking> steps) {
        this.steps = steps;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolderSteps onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_contant_steps,parent,false);
        return new MyHolderSteps(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderSteps holder, int position) {
            holder.name_steps.setText(steps.get(holder.getAdapterPosition()).getmShortDescription());
            holder.num_step.setText(steps.get(holder.getAdapterPosition()).getmId()+".");
    }

    @Override
    public int getItemCount() {
        if(steps !=null){
            return steps.size();
        }
        return 0;
    }

    public class MyHolderSteps extends RecyclerView.ViewHolder {

        TextView name_steps;
        TextView num_step;
        ImageView image_paly_active;
        int pos;

        public MyHolderSteps(@NonNull final View itemView) {
            super(itemView);
            name_steps=itemView.findViewById(R.id.name_steps);
            num_step=itemView.findViewById(R.id.num_steps);
            image_paly_active=itemView.findViewById(R.id.play_icon);
            image_paly_active.setVisibility(View.INVISIBLE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    pos=getAdapterPosition();
                    image_paly_active.setVisibility(View.VISIBLE);
                        if(TPFlag){
                            Bundle bundle=new Bundle();
                            bundle.putInt("position",pos);
                            bundle.putParcelableArrayList("steps",steps);
                            fragment_Deatils fragment=new fragment_Deatils();
                            fragment.setArguments(bundle);
                            stepsRecipes.getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.details_vd_container,fragment)
                                    .commit();


                        }else {
                            Context context=v.getContext();
                            Intent intent=new Intent(context,Recipe_Deatils.class);
                            intent.putExtra("position",pos);
                            intent.putParcelableArrayListExtra("steps",steps);
                            context.startActivity(intent);
                        }

                    }

            });
        }
    }
}
