package com.example.bakingappudactiy.servies;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public  class StepsBaking implements Parcelable {


    @SerializedName("id")
    private int mId;
    @SerializedName("shortDescription")
    private String mShortDescription;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("videoURL")
    private String mVideoURl;

    @SerializedName("thumbnailURL")
    private String mThumbnailURL;


    protected StepsBaking(Parcel in) {
        mId = in.readInt();
        mShortDescription = in.readString();
        mDescription = in.readString();
        mVideoURl = in.readString();
        mThumbnailURL = in.readString();
    }

    public static final Creator<StepsBaking> CREATOR = new Creator<StepsBaking>() {
        @Override
        public StepsBaking createFromParcel(Parcel in) {
            return new StepsBaking(in);
        }

        @Override
        public StepsBaking[] newArray(int size) {
            return new StepsBaking[size];
        }
    };

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmShortDescription() {
        return mShortDescription;
    }

    public void setmShortDescription(String mShortDescription) {
        this.mShortDescription = mShortDescription;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmVideoURl() {
        return mVideoURl;
    }

    public void setmVideoURl(String mVideoURl) {
        this.mVideoURl = mVideoURl;
    }

    public String getmThumbnailURL() {
        return mThumbnailURL;
    }

    public void setmThumbnailURL(String mThumbnailURL) {
        this.mThumbnailURL = mThumbnailURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mShortDescription);
        dest.writeString(mDescription);
        dest.writeString(mVideoURl);
        dest.writeString(mThumbnailURL);
    }
}
