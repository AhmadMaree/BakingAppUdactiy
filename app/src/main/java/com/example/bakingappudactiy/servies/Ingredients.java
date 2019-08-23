package com.example.bakingappudactiy.servies;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Ingredients implements Parcelable {



    @SerializedName("quantity")
    private double mQuantity;
    @SerializedName("measure")
    private  String mMeasure;
    @SerializedName("ingredient")
    private String mingredient;


    protected Ingredients(Parcel in) {
        mQuantity = in.readDouble();
        mMeasure = in.readString();
        mingredient = in.readString();
    }

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };

    public double getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String getmMeasure() {
        return mMeasure;
    }

    public void setmMeasure(String mMeasure) {
        this.mMeasure = mMeasure;
    }

    public String getMingredient() {
        return mingredient;
    }

    public void setMingredient(String mingredient) {
        this.mingredient = mingredient;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(mQuantity);
        dest.writeString(mMeasure);
        dest.writeString(mingredient);
    }

    @NonNull
    @Override
    public String toString() {

        return "♦"+getMingredient()+"("+getmQuantity()+getmMeasure()+")"+".";
    }
}
