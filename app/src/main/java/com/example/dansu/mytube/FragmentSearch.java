package com.example.dansu.mytube;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by dansu on 10/9/15.
 */
public class FragmentSearch extends Fragment {
    private FragmentSearch mfragmentsearch;
    private Button mButton;
    private EditText searchInput;
    private ListView videosFound;
    private Handler handler;
    String s;
    View v;
    private List<VideoItem> searchResults;
    private Search_Favorite search_favorite;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.activity_search_fragment, container, false);
        //wiring widgets in FragmentSearch
        searchInput = (EditText) v.findViewById(R.id.search_input);
        videosFound = (ListView) v.findViewById(R.id.videos_found);
        mButton = (Button) v.findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchAction(searchInput.getText().toString());


            }
        });

        return v;
    }

    public String getS() {
        return s;
    }

    public void searchAction(final String keywords) {
        new Thread() {
            public void run() {
                YoutubeConnector youtubeConnector = new YoutubeConnector(getContext());
                searchResults = youtubeConnector.search(keywords);

                handler.post(new Runnable() {
                    @Override
                    public void run() {


                        updateVideosFound();

                    }
                });

            }
        }.start();


    }

    private void updateVideosFound() {


        ArrayAdapter<VideoItem> adapter = new ArrayAdapter<VideoItem>(getActivity().getBaseContext(), R.layout.video_item, searchResults) {

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {

                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.video_item, parent, false);
                }
                ImageView thumbnail = (ImageView) convertView.findViewById(R.id.video_thumbnail);
                TextView title = (TextView) convertView.findViewById(R.id.video_title);
                TextView numOfReview = (TextView) convertView.findViewById(R.id.numberOfView);
                TextView pubDate = (TextView) convertView.findViewById(R.id.publishDate);
                final CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkbox_favorite);
                final VideoItem searchResult = searchResults.get(position);

                Picasso.with(getContext()).load(searchResult.getThumbnailURL()).into(thumbnail);
                title.setText(searchResult.getTitle());
                numOfReview.setText(searchResult.getViews().toString() + "views     ");
                pubDate.setText(searchResult.getPublishDate().toString().substring(0, 10));

                //play video when thumbnail is pressed
                thumbnail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), PlayerActivity.class);
                        intent.putExtra("VIDEO_ID", searchResults.get(position).getId());
                        startActivity(intent);
                    }
                });
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        Toast.makeText(getContext(), "Insert the viedo to favorite", Toast.LENGTH_LONG).show();
                        searchResult.setFavorite(checkBox.isChecked());
                        //call the method to insert the video
                        


                    }
                });

                return convertView;
            }
        };

        videosFound.setAdapter(adapter);


    }


    public void onViewCreated(View view, Bundle savedInstanceState) {
        ListView listView = (ListView) view.findViewById(R.id.videos_found);


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        handler = new Handler();


    }

    public View getRootView() {
        return v;

    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


}
