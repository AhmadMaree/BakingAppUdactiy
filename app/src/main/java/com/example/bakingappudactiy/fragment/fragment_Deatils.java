package com.example.bakingappudactiy.fragment;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bakingappudactiy.R;
import com.example.bakingappudactiy.servies.StepsBaking;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.util.ArrayList;

import static com.google.android.exoplayer2.util.Util.getUserAgent;

public class fragment_Deatils extends Fragment {


    TextView desc;
    SimpleExoPlayerView simpleExoPlayerView;
    SimpleExoPlayer simpleExoPlayer;
    ArrayList<StepsBaking> stepsBakings;
    int pos_view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view= inflater.inflate(R.layout.details_vd,container,false);
            simpleExoPlayerView= view.findViewById(R.id.simple_exo_player);
            desc= view.findViewById(R.id.step_description);
           long position=0;
           boolean state = true;
        Context context= container.getContext();


        if(getArguments().containsKey("steps")){

               stepsBakings= getArguments().getParcelableArrayList("steps");
               pos_view=getArguments().getInt("position");
                Uri viduouri= Uri.parse(stepsBakings.get(pos_view).getmVideoURl());
            simpleExoPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(),R.drawable.baking_image1));
                if(savedInstanceState !=null){
                    position=savedInstanceState.getLong("pos");
                    state=savedInstanceState.getBoolean("states");
                }


                if(simpleExoPlayer == null){
                    TrackSelector selector = new DefaultTrackSelector();
                    LoadControl control = new DefaultLoadControl();
                    simpleExoPlayer= ExoPlayerFactory.newSimpleInstance(context,selector,control);
                    simpleExoPlayerView.setPlayer(simpleExoPlayer);
                    String s = getUserAgent(context,"BakingApp");
                    MediaSource mediaSource= new ExtractorMediaSource(viduouri,new DefaultDataSourceFactory(context,s)
                     ,new DefaultExtractorsFactory(),null,null);
                    simpleExoPlayer.prepare(mediaSource);
                    simpleExoPlayer.seekTo(position);
                    simpleExoPlayer.setPlayWhenReady(state);
                }
                    desc.setText(stepsBakings.get(pos_view).getmDescription());


            }
            return view;

    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

            outState.putLong("pos",simpleExoPlayer.getCurrentPosition());
            outState.putBoolean("states",simpleExoPlayer.getPlayWhenReady());

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        relaseExoplayer();
    }

    private void relaseExoplayer() {
        simpleExoPlayer.stop();
        simpleExoPlayer.release();
        simpleExoPlayer=null;
    }
}
