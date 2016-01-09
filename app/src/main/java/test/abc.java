package test;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tarucfyp.dietplan.MyApplication;
import com.tarucfyp.dietplan.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiping-tan on 2/1/2016.
 */
public class abc  extends Activity implements SwipeRefreshLayout.OnRefreshListener ,AdapterView.OnItemClickListener{
    // Log tag
    private static final String TAG = abc.class.getSimpleName();

    // Movies json url
    private static final String url = URL.URL_locahhost;
    private ProgressDialog pDialog;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "movies";

    private List<Movie> movieList = new ArrayList<>() ;
    private ListView listView;
    private CustomListAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1);
        String username = ((MyApplication) this.getApplication()).getUsername();
        Log.d(TAG, "UserName:" + username);
        listView = (ListView) findViewById(R.id.list_item2);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout1);

        movieList = new ArrayList<>();
        adapter = new CustomListAdapter(this, movieList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        movieList.clear();
                                        fetchMovies();
                                    }
                                }
        );

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // changing action bar color

    }

    private void fetchMovies()
    {
        swipeRefreshLayout.setRefreshing(true);

        JsonObjectRequest req = new JsonObjectRequest(url, null ,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "JSONArray response :" + response.toString());
                        hidePDialog();
                        if (response.length() > 0) {
                            // Parsing json
                            try {
                                JSONArray movies = response.getJSONArray("movie");
                                movieList.clear();
                                for (int i = 0; i < movies.length(); i++) {
                                    JSONObject obj = movies.getJSONObject(i);
                                    Movie movie = new Movie();
                                    movie.setTitle(obj.getString("title"));
                                    movie.setThumbnailUrl(obj.getString("image"));


                                    movie.setRating(Double.parseDouble(obj.getString("rating")));
                                    movie.setYear(Integer.parseInt(obj.getString("releaseYear")));

                                    // Genre is json array
//                                JSONArray genreArry = obj.getJSONArray("genre");
//                                ArrayList<String> genre = new ArrayList<String>();
//                                for (int j = 0; j < genreArry.length(); j++) {
//                                    genre.add((String) genreArry.get(j));
//                                }
//                                movie.setGenre(genre);

                                    // adding movie to movies array
                                    movieList.add(movie);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            // notifying list adapter about data changes
                            // so that it renders the list view with updated data
                            adapter.notifyDataSetChanged();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        MyApplication.getsInstance().addToRequestQueue(req);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String rating = ((TextView) view.findViewById(R.id.rating)).getText().toString();
        Toast.makeText(abc.this, "rating : " + rating, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        fetchMovies();
    }
}
