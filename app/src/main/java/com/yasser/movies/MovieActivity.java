package com.yasser.movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MovieActivity extends AppCompatActivity {
    ListView movieList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        movieList = (ListView) findViewById(R.id.movieList);
        Log.i("All: ", MainActivity.movies.toString());
        ListAdapter adapter = new ListAdapter(this, R.layout.list_item, R.id.title, MainActivity.movies);
        movieList.setAdapter(adapter);
        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), DetailsActivity.class);
                i.putExtra("movieID", MainActivity.movies.get(position).getId());
                startActivity(i);
            }
        });
    }
}
