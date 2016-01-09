package fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tarucfyp.dietplan.MainActivity;
import com.tarucfyp.dietplan.R;

import java.util.ArrayList;
import java.util.List;

import Communicator.ClickListener;
import data.Information;
import adapter.AdapterDrawerList;
import touchListener.RecyclerTouchListener;


public class FragmentNavigationDrawer extends Fragment implements AdapterDrawerList.ClickListener{

    public static final String PREF_FILE_NAME="testpref";
    public static final String KEY_USER_LEARNED_DRAWER="user_learned_drawer";

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;

    private View containView;
    private RecyclerView recyclerView;

    private AdapterDrawerList adapter;
    public FragmentNavigationDrawer() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.navigationDrawerList);

        //setup the data information and implement the listener
        adapter=new AdapterDrawerList(getActivity(),getData());
        adapter.setClickListener(this);

        //add the
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                Toast.makeText(getActivity(),"onClick "+position,Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).OnDrawerItemClicked(position);

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(),"onLongClick "+position,Toast.LENGTH_SHORT).show();

            }
        }) );

        return layout;

    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
      //  containView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //  super.onDrawerSlide(drawerView, slideOffset);
                if(slideOffset<0.6)
                {
                    toolbar.setAlpha(1 - slideOffset);
                }
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static List<Information> getData(){
        List<Information> data = new ArrayList<>();
        int[] icons={R.drawable.ic_navigation_drawer_icon_1,R.drawable.ic_navigation_drawer_icon_2,R.drawable.ic_navigation_drawer_icon_3,R.drawable.ic_navigation_drawer_icon_4,
                     R.drawable.ic_navigation_drawer_icon_5,R.drawable.ic_navigation_drawer_icon_6,R.drawable.ic_navigation_drawer_icon_7,R.drawable.ic_navigation_drawer_icon_8};
        String[] titles={"Easy High Fiber","Food Combining","No Meat Weight Loss","Vegetarian Countdown", "Easy Vegan","Simple Italian Diet","Bu Blood Type","Low cholesterol"};
        for(int i=0; i<titles.length && i<icons.length; i++ ){
            Information current = new Information();
            current.iconId=icons[i];
            current.title=titles[i];
            data.add(current);
        }
        return data;
    }

    @Override
    public void itemClicked(View view, int position) {
    }

    /*


    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener){
            this.clickListener=clickListener;
            gestureDetector=new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    //return super.onSingleTapUp(e);
                    View child=recyclerView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clickListener!=null)
                    {
                        clickListener.onClick(child,recyclerView.getChildPosition(child));
                    }
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recyclerView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clickListener!=null)
                    {
                        clickListener.onLongClick(child,recyclerView.getChildPosition(child));
                    }
                    super.onLongPress(e);
                }
            });
        }

        //first will call this function when touch the navigation view pager
        //then call the gestureDectector
        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            gestureDetector.onTouchEvent(e);
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }
    }

    // new Touch Listener migrate
    */
/*
    //interface for touchEvent called when the gestureDectector called
    //new ClickListener is Communicator
    public static interface ClickListener{
        public void onClick(View view, int position);
        public void onLongClick(View view, int position);
    }

    */
}
