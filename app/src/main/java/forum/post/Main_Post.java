package forum.post;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
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
import com.tarucfyp.dietplan.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import forum.thread.*;
import forum.thread.Thread;

/**
 * Created by weiping-tan on 10/1/2016.
 */
public class Main_Post  extends Activity implements SwipeRefreshLayout.OnRefreshListener ,
        AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    // Log tag
    private static final String TAG = Main_Post.class.getSimpleName();

    // Movies json url
    private static final String url = "http://fitnessdietplan.hostoi.com/weiping/getAllPost.php";
    private static final String url2 = "http://fitnessdietplan.hostoi.com/weiping/AddPost.php";
    private ProgressDialog pDialog;


    private List<Post> movieList = new ArrayList<>() ;
    private ListView listView;
    private CustomListView_Post adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String categoryID , threadID;
    private EditText txtMessage;
    private String message ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_listview_post);
        Intent intent = getIntent();
        categoryID = intent.getExtras().getString("id_category");
        threadID = intent.getExtras().getString("id_thread");


        listView = (ListView) findViewById(R.id.list_item_post);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_post);

        movieList = new ArrayList<>();
        adapter = new CustomListView_Post(this, movieList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
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
        postParam.put("threadID", threadID);


        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "JSONArray response :" + response.toString());
                        hidePDialog();
                        if (response.length() > 0) {
                            // Parsing json
                            try {
                                JSONArray movies = response.getJSONArray("post");
                                movieList.clear();
                                for (int i = 0; i < movies.length(); i++) {
                                    JSONObject obj = movies.getJSONObject(i);
                                    Post movie = new Post();
                                    String title = obj.isNull("title") ? null : obj.getString("title");
                                    movie.setTitle(title);
                                    movie.setMessage(obj.getString("message"));
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

    public void addPost(View v)
    {
        boolean a = validateTextArea();
        if (a == true)
        {
            submitPost();
        }
    }

    private void submitPost() {
        RequestQueue queue = Volley.newRequestQueue(this);
        Map<String, String> postParam = new HashMap<String, String>();
        postParam.put("categoryID", categoryID);
        postParam.put("threadID", threadID);
        postParam.put("username", "abc");
        postParam.put("message", String.valueOf(message));

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url2 , new JSONObject(postParam),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int success = response.getInt("success");
                            String message = response.getString("message");

                            if (success == 1) {
                                Toast.makeText(Main_Post.this, "" + message, Toast.LENGTH_SHORT).show();
                                txtMessage.setError(null);
                                fetchMovies();
                            } else {
                                // failed to create product
                                Toast.makeText(Main_Post.this, "" + message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        queue.add(req);
    }

    private boolean validateTextArea(){
        txtMessage= (EditText) findViewById(R.id.txtMessage_Post);

         message = txtMessage.getText().toString();
        if (TextUtils.isEmpty(message))
        {
            txtMessage.setError("Please enter the title !");
            return false;
        }
        else
        {
            txtMessage.setError(null);
            return true;
        }
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

       // String rating = ((TextView) view.findViewById(R.id.id)).getText().toString();
       // Toast.makeText(Main_Post.this, "rating : " + rating, Toast.LENGTH_LONG).show();

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
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        int topRowVerticalPosition =
                (listView == null || listView.getChildCount() == 0) ?
                        0 : listView.getChildAt(0).getTop();
        swipeRefreshLayout.setEnabled(firstVisibleItem == 0 && topRowVerticalPosition >= 0);
    }
   /* @Override
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
    }*/
}
