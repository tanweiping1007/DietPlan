package forum.thread;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weiping-tan on 6/1/2016.
 */
public class Add_Thread extends Activity {
    private String categoryID;
    private EditText txtTitle, txtSubject;
    private String title, subject;
    private static final String url = "http://fitnessdietplan.hostoi.com/weiping/AddThread.php";
    private static final String url2 = "http://fitnessdietplan.hostoi.com/weiping/AddPostAuthor.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_thread);
        Intent intent = getIntent();
        categoryID = intent.getExtras().getString("id_category");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_compose, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_compose:
                txtTitle = (EditText) findViewById(R.id.add_thread_title);
                txtSubject = (EditText) findViewById(R.id.add_thread_message);
                title = txtTitle.getText().toString();
                subject = txtSubject.getText().toString();
                if (TextUtils.isEmpty(title)) {
                    txtTitle.setError("Please enter the title !");
                    txtSubject.setError(null);
                    return false;
                }
                // txtTitle.setError("Please enter the title !");
                else if (TextUtils.isEmpty(subject)) {
                    txtSubject.setError("Please enter the subject !");
                    txtTitle.setError(null);
                    return false;
                } else if (TextUtils.isEmpty(title) & TextUtils.isEmpty(subject)) {

                    txtTitle.setError("Please enter the title !");
                    txtSubject.setError("Please enter the subject !");
                    return false;
                } else {
                    txtSubject.setError(null);
                    txtTitle.setError(null);
                    AddThread();
                    return true;
                }

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void AddThread() {
        String username = ((MyApplication) this.getApplication()).getUsername();
        RequestQueue queue = Volley.newRequestQueue(this);
        Map<String, String> postParam = new HashMap<String, String>();
        postParam.put("categoryID", categoryID);
        postParam.put("username", "abc");
        postParam.put("title", String.valueOf(title));
        postParam.put("subject", String.valueOf(subject));

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int success = response.getInt("success");

                            if (success == 1) {
                                AddPostThread();
                            } else {
                                // failed to create product
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

    public void AddPostThread() {
        String username = ((MyApplication) this.getApplication()).getUsername();
        RequestQueue queue = Volley.newRequestQueue(this);
        Map<String, String> postParam = new HashMap<String, String>();
        postParam.put("categoryID", categoryID);
        postParam.put("username", "abc");
        postParam.put("title", String.valueOf(title));
        postParam.put("subject", String.valueOf(subject));

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url2, new JSONObject(postParam),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int success = response.getInt("success");
                            String message = response.getString("message");
                            if (success == 1) {
                                Toast.makeText(Add_Thread.this, "" + message, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Add_Thread.this, Main_Thread.class);
                                intent.putExtra("id_category", categoryID);
                                startActivity(intent);

                            } else {
                                Toast.makeText(Add_Thread.this, "" + message, Toast.LENGTH_SHORT).show();
                                // failed to create product
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

}
