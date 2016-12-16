package com.yasser.movies;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Yasser_2 on 12/13/2016.
 */

public class ListAdapter extends ArrayAdapter<Movie> {
    List<Movie> movies;
    public ListAdapter(Context context, int resource, int textViewResourceId, List<Movie> objects) {
        super(context, resource, textViewResourceId, objects);

        movies = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Movie m = movies.get(position);
        TextView title = (TextView) convertView.findViewById(R.id.type);
        TextView type = (TextView) convertView.findViewById(R.id.type);
        ImageView poster = (ImageView) convertView.findViewById(R.id.poster);
        title.setText(movies.get(position).getTitle());
        type.setText(movies.get(position).getType());
        DownloadImageTask task2 = new DownloadImageTask();
        try {
            Bitmap image = task2.execute(movies.get(position).getPoster()).get();
            poster.setImageBitmap(image);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return super.getView(position, convertView, parent);
    }
}
