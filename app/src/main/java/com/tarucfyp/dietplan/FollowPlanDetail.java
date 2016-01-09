package com.tarucfyp.dietplan;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.util.ArrayList;
import java.util.Date;

import adapter.AdapterFollowPlan;
import adapter.AdapterFollowPlanDetail;
import database.DatabaseRecord;
import it.neokree.materialtabs.MaterialTabHost;
import touchListener.RecyclerTouchListener;
import user.authentication.Login;
import user.authentication.ServerRequest;

public class FollowPlanDetail extends ActionBarActivity {

    private static final String EXTRA_MESSAGE = "";
    private Toolbar toolbar;
    private MaterialTabHost tabHost;
    private ViewPager viewPager;

    private RecyclerView FollowPlanDetailRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AdapterFollowPlanDetail adapter;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://fitnessdietplan.hostoi.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_plan_detail);

        toolbar = (Toolbar)findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FollowPlanDetailRecyclerView = (RecyclerView) findViewById(R.id.followPlanDetailRecyclerView);
        FollowPlanDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AdapterFollowPlanDetail(getApplicationContext());
        FollowPlanDetailRecyclerView.setAdapter(adapter);
        FollowPlanDetailRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), FollowPlanDetailRecyclerView, new Communicator.ClickListener() {
            @Override
            public void onClick(View view, int position) {

               // Toast.makeText(getApplicationContext(), "onClick " + position, Toast.LENGTH_SHORT).show();

                DatabaseRecord.FoodRecord food = new DatabaseRecord.FoodRecord("hello", "hello", 1.00, 2.00, 3.00, 4.00, 5.00,"Zello");

                addFood(food);

         }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "onLongClick " + position, Toast.LENGTH_SHORT).show();

            }
        }));
    }

    private void addFood(DatabaseRecord.FoodRecord food) {
        FoodServerRequest serverRequest = new FoodServerRequest(this);

        serverRequest.storeFoodDataInBackground(food, new GetDietPackageCallBack() {
            @Override
            public void done(DatabaseRecord.FoodRecord returnedFood) {
                Toast.makeText(getApplicationContext(), "success " , Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_follow_plan_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
