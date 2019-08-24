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
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

import static com.google.android.exoplayer2.util.Util.getUserAgent;

public class fragment_Deatils extends Fragment {


    TextView desc;
    SimpleExoPlayerView simpleExoPlayerView;
    SimpleExoPlayer simpleExoPlayer;
    ArrayList<StepsBaking> stepsBakings;
    int pos_view;
    private Context context;


    public fragment_Deatils(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view= inflater.inflate(R.layout.details_vd,container,false);
            simpleExoPlayerView= view.findViewById(R.id.simple_exo_player);
            desc= view.findViewById(R.id.step_description);
           long position=0;
           boolean state = true;
          context= container.getContext();


        if(getArguments().containsKey("steps")){

               stepsBakings= getArguments().getParcelableArrayList("steps");
               pos_view=getArguments().getInt("position");
                Uri viduouri= Uri.parse(stepsBakings.get(pos_view).getmVideoURl());
            simpleExoPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(),R.drawable.baking_image1));
                if(savedInstanceState !=null){
                    position=savedInstanceState.getLong("pos");
                    state=savedInstanceState.getBoolean("states");
                }
            initializePlayer(position, state, context, viduouri);
            desc.setText(stepsBakings.get(pos_view).getmDescription());

            }
            return view;

    }

    public void initializePlayer(long position, boolean state, Context context, Uri viduouri) {
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
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

            outState.putLong("pos",simpleExoPlayer.getCurrentPosition());
            outState.putBoolean("states",simpleExoPlayer.getPlayWhenReady());

    }
    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            relaseExoplayer();
        }
    }
    private void relaseExoplayer() {
        simpleExoPlayer.stop();
        simpleExoPlayer.release();
        simpleExoPlayer=null;
    }


}
