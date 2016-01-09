package com.tarucfyp.dietplan;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import adapter.AdapterDietPackageDetail;
import database.DietPlanPackageDataSource;
import database.DietPlanPackageRecord;
import fragment.FragmentZero;
import fragment.GetDietPackageRecord;


public class SubActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private RecyclerView dietPackageRecyclerView;
    private AdapterDietPackageDetail adapterDietPackageDetail;
    private int position;
    private View view;

    private DietPlanPackageRecord dietPlanPackageRecord;

    public SubActivity(int position){
        this.position = position;
    }

    public SubActivity(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        toolbar = (Toolbar)findViewById(R.id.app_bar);

        Intent intent = getIntent();
        position = intent.getIntExtra(FragmentZero.EXTRA_MESSAGE, 0);
        GetDietPackageRecord getDietPackageRecord = new GetDietPackageRecord();

        viewer viewer = new viewer(this);
        this.view = viewer;

        dietPlanPackageRecord = getDietPackageRecord.getDietPackageRecord(view).get(position);


        dietPackageRecyclerView = (RecyclerView)findViewById(R.id.dietPackageRecyclerView);
        adapterDietPackageDetail = new AdapterDietPackageDetail(this,dietPlanPackageRecord);
        dietPackageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dietPackageRecyclerView.setAdapter(adapterDietPackageDetail);

        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub, menu);
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

        if(id ==android.R.id.home)
        {
            NavUtils.navigateUpFromSameTask(this);

        }

        return super.onOptionsItemSelected(item);
    }

    class viewer extends View {
        public viewer(Context context) {
            super(context);
        }
    }

}
