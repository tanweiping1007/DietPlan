
package com.tarucfyp.dietplan;
// last open week12
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import adapter.AdapterTabPager;
import fragment.FragmentNavigationDrawer;
import fragment.FragmentZero;
import fragment.GetDietPackageRecord;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import user.authentication.Login;
import user.authentication.User;


public class MainActivity extends ActionBarActivity implements MaterialTabListener, View.OnClickListener {

    private Toolbar toolbar;
    private MaterialTabHost tabHost;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Custom action bar
        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //Navigation Drawer
        FragmentNavigationDrawer drawerFragment = (FragmentNavigationDrawer)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer,(DrawerLayout)findViewById(R.id.navigationDrawer),toolbar);

        //TabHost
        AdapterTabPager adapter = new AdapterTabPager(getSupportFragmentManager(),getApplicationContext());
        tabHost = (MaterialTabHost)findViewById(R.id.materialTabHost);

        viewPager=(ViewPager)findViewById(R.id.viewPager);

        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
                //super.onPageSelected(position);
            }
        });

        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setIcon(adapter.getIcon(i))
                            .setTabListener(this)
            );
        }


        //Floating Button main
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_setting);

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setBackgroundDrawable(R.drawable.custom_bg)
                .setContentView(imageView)
                .build();


        //Floating Button sub
        ImageView iconFloat1 = new ImageView(this);
        iconFloat1.setImageResource(R.drawable.ic_tab1);

        ImageView iconFloat2 = new ImageView(this);
        iconFloat2.setImageResource(R.drawable.ic_tab2);

        ImageView iconFloat3 = new ImageView(this);
        iconFloat3.setImageResource(R.drawable.ic_tab3);
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this)
                .setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_bg));

        SubActionButton buttonSubFloating1 = itemBuilder.setContentView(iconFloat1).build();
        SubActionButton buttonSubFloating2 = itemBuilder.setContentView(iconFloat2).build();
        SubActionButton buttonSubFloating3 = itemBuilder.setContentView(iconFloat3).build();

        buttonSubFloating1.setTag("buttonSubFloating1");
        buttonSubFloating2.setTag("buttonSubFloating2");
        buttonSubFloating3.setTag("buttonSubFloating3");

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(buttonSubFloating1)
                .addSubActionView(buttonSubFloating2)
                .addSubActionView(buttonSubFloating3)
                .attachTo(actionButton)
                .build();

        buttonSubFloating1.setOnClickListener(this);
        buttonSubFloating2.setOnClickListener(this);
        buttonSubFloating3.setOnClickListener(this);

      //  Intent intent =  getIntent();
       // User loggedUser = (User) intent.getSerializableExtra(Login.LoggedUserMessage);
       // Toast.makeText(getApplicationContext(), loggedUser.toString() + "",Toast.LENGTH_SHORT).show();
//Toast.makeText(getApplicationContext(),"cc",Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            startActivity(new Intent(this, SubActivity.class));
        }
        if (id == R.id.action_search) {
            String username = ((MyApplication) this.getApplication()).getUsername();
            if (username == null) {
                startActivity(new Intent(this, Login.class));
            }
            else
                Toast.makeText(this , "You have been login already with username (" + username + ")", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        viewPager.setCurrentItem(materialTab.getPosition());

    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    @Override
    public void onClick(View v) {
    if(v.getTag().equals("buttonSubFloating1"))
    {
        Toast.makeText(this, "Current Page " + (viewPager.getCurrentItem()+1) + ", Floating Button 1", Toast.LENGTH_LONG).show();
    }
    if(v.getTag().equals("buttonSubFloating2"))
    {
        Toast.makeText(this, "Current Page " + (viewPager.getCurrentItem()+1) + ", Floating Button 2", Toast.LENGTH_LONG).show();
    }
    if(v.getTag().equals("buttonSubFloating3"))
    {
        Toast.makeText(this, "Current Page " + (viewPager.getCurrentItem()+1) + ", Floating Button 3", Toast.LENGTH_LONG).show();
    }
    }


    //drawer item click , navigate to the page
    public void OnDrawerItemClicked(int index){
        viewPager.setCurrentItem(index);
    }
}
