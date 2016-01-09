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

import adapter.AdapterFoodCategoryRecord;
import adapter.AdapterFoodListRecord;
import it.neokree.materialtabs.MaterialTabHost;
import touchListener.RecyclerTouchListener;

public class FoodList extends ActionBarActivity {

    private static final String EXTRA_MESSAGE = "";
    private Toolbar toolbar;
    private MaterialTabHost tabHost;
    private ViewPager viewPager;

    private RecyclerView FragmentFoodListRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AdapterFoodListRecord adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentFoodListRecyclerView = (RecyclerView) findViewById(R.id.FragmentFoodListRecyclerView);
        FragmentFoodListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AdapterFoodListRecord(this);
        FragmentFoodListRecyclerView.setAdapter(adapter);
        FragmentFoodListRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, FragmentFoodListRecyclerView, new Communicator.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                int currentPosition = position;
                Toast.makeText(getApplication(), "Onclick" + position, Toast.LENGTH_LONG).show();

                Intent i = new Intent(getApplicationContext(), FoodDetail.class);
                i.putExtra(EXTRA_MESSAGE, currentPosition);
                startActivity(i);

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplication(), "onLongClick " + position, Toast.LENGTH_SHORT).show();


            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_food_list, menu);
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
