package forum.category;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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

import forum.thread.Main_Thread;

/**
 * Created by weiping-tan on 3/1/2016.
 */
public class Main_Category extends Activity implements SwipeRefreshLayout.OnRefreshListener ,AdapterView.OnItemClickListener {
    // Log tag
    private static final String TAG = Main_Category.class.getSimpleName();

    // Movies json url
    private static final String url = "http://fitnessdietplan.hostoi.com/weiping/getAllCategory.php";
    private ProgressDialog pDialog;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "movies";

    private List<Category> movieList = new ArrayList<>() ;
    private ListView listView;
    private CustomListView_Category adapter;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_listview_category);

        listView = (ListView) findViewById(R.id.list_item_category);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_category);

        movieList = new ArrayList<>();
        adapter = new CustomListView_Category(this, movieList);
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
                                JSONArray movies = response.getJSONArray("category");
                                movieList.clear();
                                for (int i = 0; i < movies.length(); i++) {
                                    JSONObject obj = movies.getJSONObject(i);
                                    Category movie = new Category();
                                    movie.setTitle(obj.getString("title"));
                                    movie.setDesc(obj.getString("description"));
                                    movie.setCategoryID(Integer.parseInt(obj.getString("categoryID")));

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

        String id_category = ((TextView) view.findViewById(R.id.id_category)).getText().toString();


        Toast.makeText(Main_Category.this, "rating : " + id_category, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Main_Thread.class);
        intent.putExtra("id_category", id_category);
        startActivity(intent);

    }

    @Override
    public void onRefresh() {
        fetchMovies();
    }
}
