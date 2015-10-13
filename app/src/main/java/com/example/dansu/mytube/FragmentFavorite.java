package com.example.dansu.mytube;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.youtube.player.YouTubeIntents;
import com.google.api.services.youtube.model.Playlist;

import java.util.List;


/**
 * Created by dansu on 10/9/15.
 */
public class FragmentFavorite extends Fragment{
    private Button mButton;
    private List<VideoItem> favoritePlayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.activity_favorite_fragment, container, false);
        ListView listView=(ListView)v.findViewById(R.id.video_favorite);
        //insert an videoItem to favorite playList
       //favoritePlayList=favoritePlayList.add();
        //ArrayAdapter<VideoItem> adapter=new ArrayAdapter<VideoItem>(getActivity().getBaseContext(),R.layout.video_item,favoritePlayList)

        return v;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
