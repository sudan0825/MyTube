package com.example.dansu.mytube;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpRequest;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;

import java.io.IOException;
import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Created by dansu on 10/9/15. To avoid dealing with Youtube API directly from our activity, we
 * create another class;
 */
public class YoutubeConnector {
    private YouTube youtube;
    private  YouTube.Search.List query;
    //Developer key
    public  static final  String KEY="AIzaSyBlpA_SyyLJ7y2xbsymU54vJ4dTV1-h7_s";
    public YoutubeConnector(Context context) {
        youtube = new YouTube.Builder(new NetHttpTransport(),
                new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest hr) throws IOException {}
        }).setApplicationName("MyTube").build();

        try{
            query = youtube.search().list("id,snippet");
            query.setKey(KEY);
            query.setType("video");
            query.setFields("items(id/videoId,snippet/title,snippet/publishedAt," +
                    "snippet/thumbnails/default/url)");

        }catch(IOException e){
            Log.d("YC", "Could not initialize: "+e);
        }
    }
    public List<VideoItem> search(String keywords){
        query.setQ(keywords);
        try{
            SearchListResponse response = query.execute();
            List<SearchResult> results = response.getItems();

            List<VideoItem> items = new ArrayList<VideoItem>();
            for(SearchResult result:results){
                VideoItem item = new VideoItem();
                item.setTitle(result.getSnippet().getTitle());
                item.setThumbnailURL(result.getSnippet().getThumbnails().getDefault().getUrl());
                item.setId(result.getId().getVideoId());
                //get the date of publish
                item.setPublishDate(result.getSnippet().getPublishedAt());

                //getViewCount from video Id
              YouTube.Videos.List listVideosRequest = youtube.videos().list("snippet, statistics").setId(item.getId());
                listVideosRequest.setKey(KEY);
                Video video = listVideosRequest.execute().getItems().get(0);
                item.setViews(video.getStatistics().getViewCount());


                items.add(item);
            }
            return items;
        }catch(IOException e){
            Log.d("YC", "Could not search: "+e);
            return null;
        }
    }
}