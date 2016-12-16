package com.yasser.movies;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class DetailsActivity extends AppCompatActivity {
    TextView title;
    TextView description;
    ImageView poster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        title = (TextView) findViewById(R.id.movieTitle);
        description = (TextView) findViewById(R.id.movieDiscription);
        poster = (ImageView) findViewById(R.id.moviePoster);
        Intent intent = getIntent();
        String id = intent.getStringExtra("movieID");
        DownloadTask task = new DownloadTask();
        DownloadImageTask task2 = new DownloadImageTask();
        try {
            String result = task.execute("http://www.omdbapi.com/?i=" + id).get();
            JSONObject object = new JSONObject(result);
            String url = object.getString("Poster");
            Bitmap image = task2.execute(url).get();
            title.setText(object.getString("Title"));
            description.setText(object.getString("Plot"));
            poster.setImageBitmap(image);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
