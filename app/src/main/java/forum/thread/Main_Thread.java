package forum.thread;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.tarucfyp.dietplan.MyApplication;
import com.tarucfyp.dietplan.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by weiping-tan on 3/1/2016.
 */
public class Main_Thread extends Activity implements SwipeRefreshLayout.OnRefreshListener ,AdapterView.OnItemClickListener {
    // Log tag
    private static final String TAG = Main_Thread.class.getSimpleName();

    // Movies json url
    private static final String url = "http://fitnessdietplan.hostoi.com/weiping/getAllThread.php";
    private ProgressDialog pDialog;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "movies";

    private List<Thread> movieList = new ArrayList<>() ;
    private ListView listView;
    private CustomListView_Thread adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String categoryID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_listview_thread);
        Intent intent = getIntent();
        categoryID = intent.getExtras().getString("id_category");

        listView = (ListView) findViewById(R.id.list_item_thread);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_thread);

        movieList = new ArrayList<>();
        adapter = new CustomListView_Thread(this, movieList);
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
        RequestQueue queue = Volley.newRequestQueue(this);
        Map<String, String> postParam= new HashMap<String, String>();
        postParam.put("categoryID", categoryID);


        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "JSONArray response :" + response.toString());
                        hidePDialog();
                        if (response.length() > 0) {
                            // Parsing json
                            try {
                                JSONArray movies = response.getJSONArray("thread");
                                movieList.clear();
                                for (int i = 0; i < movies.length(); i++) {
                                    JSONObject obj = movies.getJSONObject(i);
                                    Thread movie = new Thread();
                                    movie.setTitle(obj.getString("title"));
                                    movie.setThumbnail(obj.getString("thumbnail"));
                                    movie.setUsername(obj.getString("username"));
                                    movie.setCreated_at(obj.getString("created_at"));

                                    movieList.add(movie);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapter.notifyDataSetChanged();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        queue.add(req);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String rating = ((TextView) view.findViewById(R.id.id_category)).getText().toString();
        Toast.makeText(Main_Thread.this, "rating : " + rating, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onRefresh() {
        fetchMovies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_thread, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
          case R.id.action_add_thread:
              Intent intent = new Intent(this, Add_Thread.class);
              intent.putExtra("id_category", categoryID);
              startActivity(intent);

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
