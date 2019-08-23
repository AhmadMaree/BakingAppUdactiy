package com.example.bakingappudactiy.servies;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingappudactiy.R;
import com.example.bakingappudactiy.widget.WidgetHelper;
import com.example.bakingappudactiy.fragment.StepsRecipes;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class Mainactivity_Adapter extends RecyclerView.Adapter<Mainactivity_Adapter.MyViewHolder> {


    Context context;
    List<Baking> recipes;

    public Mainactivity_Adapter(Context context, List<Baking> recipes) {
        this.context = context;
        this.recipes = recipes;

    }

    public void setRecipes(List<Baking> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Mainactivity_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_main_activity,parent,false);
                   return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Mainactivity_Adapter.MyViewHolder holder, int position) {
                    holder.servingText.setText(recipes.get(holder.getAdapterPosition()).getmServing());
                    holder.recipeText.setText(recipes.get(holder.getAdapterPosition()).getmName());

                        Picasso.with(context)
                                .load(R.drawable.baking_image1)
                                .into(holder.recipeImage);





    }

    @Override
    public int getItemCount() {
        if(recipes == null){
            return 0;
        }
        return recipes.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView recipeImage;
            TextView  recipeText;
            TextView  servingText;
            int position ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            recipeText=itemView.findViewById(R.id.name_Baking);
            recipeImage=itemView.findViewById(R.id.image_card_activity);
            servingText=itemView.findViewById(R.id.serving_baking);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    position=getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        Intent intent=new Intent(context, StepsRecipes.class);
                        intent.putExtra("ListData",recipes.get(position));
                        Log.d("SHOWSISE",  "size :"+recipes.get(position).getmIngredients().size());
                        ArrayList<Ingredients> ingredients;
                        ingredients=recipes.get(position).getmIngredients();
                        intent.putParcelableArrayListExtra("ing",ingredients);
                        ArrayList<StepsBaking> stepsBakings;
                        stepsBakings=recipes.get(position).getmSteps();
                        intent.putParcelableArrayListExtra("steps",stepsBakings);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        // for widget
                        Intent widgethelper=new Intent(context, WidgetHelper.class);

                        widgethelper.setData(Uri.parse(TextUtils.join("\n",recipes.get(position).getmIngredients())));
                        Log.e("show", "onClick: "+Uri.parse(TextUtils.join("\n",recipes.get(position).getmIngredients())));

                        context.startActivity(intent);
                        context.startService(widgethelper);
                    }
                }
            });

        }
    }
}
