package com.yasser.movies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {


    private Button searchButton;
    private EditText text;
    public static ArrayList<Movie> movies;

    void search() {

        Log.i("aaa", "aaa");
        String query = text.getText().toString();
        if (query.equals("")) {
            Toast.makeText(this, "Search query is empty", Toast.LENGTH_SHORT).show();

        } else {
            movies.clear();

            try {
                query = URLEncoder.encode(query, "UTF-8");
                String x = "http://www.omdbapi.com/?s=" + query;
                DownloadTask task = new DownloadTask();
                String result = task.execute(x).get();
                JSONObject o = new JSONObject(result);
                JSONArray array = o.getJSONArray("Search");

                for (int i = 0; i < array.length(); i++) {
                    Movie m = new Movie();
                    JSONObject object = array.getJSONObject(i);
                    m.setId(object.getString("imdbID"));
                    m.setPoster(object.getString("Poster"));
                    m.setTitle(object.getString("Title"));
                    m.setYear(object.getString("Year"));
                    m.setType(object.getString("Type"));
                    Log.i("Title: ", m.getTitle());
                    movies.add(m);
                    Log.i("movies array: ", movies.toString());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchButton = (Button) findViewById(R.id.searchButton);
        text = (EditText) findViewById(R.id.editText);
        movies = new ArrayList<>();
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
                Intent i = new Intent(getApplicationContext(), MovieActivity.class);
                startActivity(i);
            }
        });
    }
}
