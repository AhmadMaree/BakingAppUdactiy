package com.example.bakingappudactiy.servies;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;
import java.util.List;

public class Baking implements Parcelable {

    @SerializedName("id")
    private int mId ;
    @SerializedName("name")
    private String mName;

    @SerializedName("ingredients")
    @Expose
    private ArrayList<Ingredients> mIngredients=null;

    @SerializedName("steps")
    private ArrayList<StepsBaking> mSteps;


    @SerializedName("servings")
    private String mServing;


    @SerializedName("image")
    private String mImage;


    public static final Creator<Baking> CREATOR = new Creator<Baking>() {
        @Override
        public Baking createFromParcel(Parcel in) {
            return new Baking(in);
        }

        @Override
        public Baking[] newArray(int size) {
            return new Baking[size];
        }
    };

    protected Baking(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mIngredients = in.createTypedArrayList(Ingredients.CREATOR);
        mServing = in.readString();
        mImage = in.readString();
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public ArrayList<Ingredients> getmIngredients() {
        return mIngredients;
    }

    public void setmIngredients(ArrayList<Ingredients> mIngredients) {
        this.mIngredients = mIngredients;
    }

    public ArrayList<StepsBaking> getmSteps() {
        return mSteps;
    }

    public void setmSteps(ArrayList<StepsBaking> mSteps) {
        this.mSteps = mSteps;
    }

    public String getmServing() {
        return mServing;
    }

    public void setmServing(String mServing) {
        this.mServing = mServing;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeString(mServing);
        dest.writeString(mImage);
        dest.writeTypedList(mIngredients);

    }



}
