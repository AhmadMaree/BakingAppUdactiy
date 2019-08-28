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
import androidx.appcompat.app.AppCompatActivity;
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


    public static final String READY = "Ready";
    public static final String AUTOPLAY = "autoplay";
    public static final String CURRENT_WINDOW_INDEX = "current_window_index";
    public static final String PLAYBACK_POSITION = "playback_position";

    TextView desc;
    SimpleExoPlayerView simpleExoPlayerView;
    SimpleExoPlayer simpleExoPlayer;
    ArrayList<StepsBaking> stepsBakings;
    int pos_view;
    private Context context;
    long position=0;
    Uri viduouri;
    // try to solve crash
    boolean state = true;
    private long playbackPosition;
    private boolean playWhenReady;
    String urlViduo;
    private boolean autoPlay = false;
    private int currentWindow;


    public fragment_Deatils(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view= inflater.inflate(R.layout.details_vd,container,false);
            simpleExoPlayerView= view.findViewById(R.id.simple_exo_player);
            desc= view.findViewById(R.id.step_description);
          context= getContext();
        if(getArguments().containsKey("steps")){

               stepsBakings= getArguments().getParcelableArrayList("steps");
               pos_view=getArguments().getInt("position");
               urlViduo=stepsBakings.get(pos_view).getmVideoURl();
               viduouri= Uri.parse(urlViduo);
            simpleExoPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(),R.drawable.baking_image1));
                if(savedInstanceState !=null){
                    position=savedInstanceState.getLong("pos");
                    state=savedInstanceState.getBoolean("states");
                    stepsBakings=savedInstanceState.getParcelableArrayList("steps");
                    pos_view=savedInstanceState.getInt("pos1");
                    playbackPosition = savedInstanceState.getLong(PLAYBACK_POSITION, 0);
                    currentWindow = savedInstanceState.getInt(CURRENT_WINDOW_INDEX, 0);
                    autoPlay = savedInstanceState.getBoolean(AUTOPLAY, false);
                    playWhenReady = savedInstanceState.getBoolean(READY);
                    context=getContext();
                    urlViduo=savedInstanceState.getString("url");
                    initializePlayer(position,state,context,viduouri);

                }
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
            outState.putParcelableArrayList("steps",stepsBakings);
            outState.putInt("pos1",pos_view);
            outState.putString("url",urlViduo);
        outState.putLong(PLAYBACK_POSITION, playbackPosition);
        outState.putInt(CURRENT_WINDOW_INDEX, currentWindow);
        outState.putBoolean(AUTOPLAY, autoPlay);
        outState.putBoolean(READY, playWhenReady);

    }


    private void relaseExoplayer() {

        if (simpleExoPlayer != null) {
            playbackPosition = simpleExoPlayer.getCurrentPosition();
            currentWindow = simpleExoPlayer.getCurrentWindowIndex();
            autoPlay = simpleExoPlayer.getPlayWhenReady();
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setPlayWhenReady(false);
            relaseExoplayer();
        }
    }

    @Override
    public  void onStop() {
        super.onStop();
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setPlayWhenReady(playWhenReady);
            simpleExoPlayer.seekTo(playbackPosition);
        } else {
            initializePlayer(position, state, context, viduouri);
            simpleExoPlayer.setPlayWhenReady(playWhenReady);
            simpleExoPlayer.seekTo(playbackPosition);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setPlayWhenReady(true);
        }
        initializePlayer(position, state, context, viduouri);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (simpleExoPlayer != null)
            simpleExoPlayer.setPlayWhenReady(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        relaseExoplayer();
    }


}
