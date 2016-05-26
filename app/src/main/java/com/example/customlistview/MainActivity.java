package com.example.customlistview;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.squareup.picasso.Downloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    ListAdapter listAdapter;
    ArrayList<Movie> movieArray = new ArrayList<Movie>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new FetchData().execute();


    }

    public class FetchData extends AsyncTask<Void, Void, Void> {
        String Response;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            JSONParser jsonParser = new JSONParser();

            Response = jsonParser.makeServiceCall("http://api.androidhive.info/json/movies.json");
            Log.i("", "doInBackground: " + Response);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try {

                JSONArray jsonArray = new JSONArray(Response);

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    // inflate the array list with data
                    movieArray.add(new Movie(jsonObject.getString("title"), jsonObject.getString("image"), jsonObject.getString("rating"), jsonObject.getString("releaseYear")));

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            // set the array adapter to use the above array list and tell the listview to set as the adapter
            // our custom adapter
            listAdapter = new ListAdapter(MainActivity.this, R.layout.list_item, movieArray);
            listview= (ListView) findViewById(R.id.listview);
            listview.setItemsCanFocus(false);
            listview.setAdapter(listAdapter);

        }

    }

}
