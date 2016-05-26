package com.example.customlistview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Android Developer on 25-May-16.
 */
public class ListAdapter extends ArrayAdapter<Movie> {

    Context context;
    int layoutResourceId;
    ArrayList<Movie> movies = new ArrayList<Movie>();

    public ListAdapter(Context context, int layoutResourceId,
                          ArrayList<Movie> mv) {
        super(context, layoutResourceId, mv);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.movies = mv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        MovieWrapper MovieWrapper = null;

        if (item == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            item = inflater.inflate(layoutResourceId, parent, false);

            MovieWrapper = new MovieWrapper();
            MovieWrapper.title = (TextView) item.findViewById(R.id.title);
            MovieWrapper.rating = (TextView) item.findViewById(R.id.rating);
            MovieWrapper.releaseYear = (TextView) item.findViewById(R.id.release_year);
            MovieWrapper.image = (ImageView) item.findViewById(R.id.mv_image);

            item.setTag(MovieWrapper);
        } else {
            MovieWrapper = (MovieWrapper) item.getTag();
        }

        final Movie movie = movies.get(position);
        Picasso.with(context).load(movie.getImage()).placeholder(R.drawable.placeholder).into(MovieWrapper.image);
        MovieWrapper.title.setText(movie.getTitle());
        MovieWrapper.rating.setText("Rating : "+movie.getRating());
        MovieWrapper.releaseYear.setText("ReleaseYear : " +movie.getReleaseYear());

        MovieWrapper.image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),FullImage.class);
                intent.putExtra("image_url",movie.getImage());
            }
        });

        MovieWrapper.image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Image", Toast.LENGTH_LONG).show();
            }
        });

        return item;

    }

    static class MovieWrapper {

        TextView title;
        ImageView image;
        TextView rating;
        TextView releaseYear;

    }

}
