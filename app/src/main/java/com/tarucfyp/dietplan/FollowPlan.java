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

import adapter.AdapterFollowPlan;
import adapter.AdapterFollowPlanDetail;
import adapter.AdapterFoodCategoryRecord;
import adapter.AdapterFoodListRecord;
import it.neokree.materialtabs.MaterialTabHost;
import touchListener.RecyclerTouchListener;

public class FollowPlan extends ActionBarActivity {

    private static final String EXTRA_MESSAGE = "";
    private Toolbar toolbar;
    private MaterialTabHost tabHost;
    private ViewPager viewPager;

    private RecyclerView FollowPlanRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AdapterFollowPlan adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_plan);

        toolbar = (Toolbar)findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FollowPlanRecyclerView = (RecyclerView) findViewById(R.id.FollowPlanRecyclerView);
        FollowPlanRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AdapterFollowPlan(this);

        //and then call the adapter to setup the record into UI
        FollowPlanRecyclerView.setAdapter(adapter);
        FollowPlanRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), FollowPlanRecyclerView, new Communicator.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(getApplicationContext(), FollowPlanDetail.class);
                startActivity(i);

                // startActivity(new Intent(getActivity(), SubActivity.class));

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "onLongClick " + position, Toast.LENGTH_SHORT).show();

            }
        }));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_follow_plan, menu);
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
