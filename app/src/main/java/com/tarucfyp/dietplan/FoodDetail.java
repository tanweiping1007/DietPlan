package com.tarucfyp.dietplan;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import adapter.AdapterFoodDetailRecord;
import adapter.AdapterFoodListRecord;
import it.neokree.materialtabs.MaterialTabHost;

public class FoodDetail extends ActionBarActivity {

    private static final String EXTRA_MESSAGE = "";
    private Toolbar toolbar;
    private MaterialTabHost tabHost;
    private ViewPager viewPager;

    private RecyclerView FragmentFoodDetailRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AdapterFoodDetailRecord adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);



        FragmentFoodDetailRecyclerView = (RecyclerView) findViewById(R.id.FragmentFoodDetailRecyclerView);
        FragmentFoodDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AdapterFoodDetailRecord(this);
        FragmentFoodDetailRecyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_food_detail, menu);
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
