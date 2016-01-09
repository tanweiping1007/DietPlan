package fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.tarucfyp.dietplan.R;
import com.tarucfyp.dietplan.SubActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Communicator.ClickListener;
import adapter.AdapterDietPackageRecord;
import database.*;
import database.DietPlanPackageRecord;
import network.VolleySingleton;
import service.JsonRequest;
import touchListener.RecyclerTouchListener;

public class FragmentZero extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ArrayList<DietPlanPackageRecord> listPlanRecord = new ArrayList<>();
    //private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    public DietPlanPackageRecord dietPlanPackageRecord2;
    TextView fragmentZeroTextView1,fragmentZeroTextView2;
    private DietPlanPackageDataSource datasource;
    private RecyclerView fragmentZeroDietPlanPackageRecyclerView;
    private  SwipeRefreshLayout swipeRefreshLayout;
    private AdapterDietPackageRecord adapter;

    public final static String EXTRA_MESSAGE = "record";


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentZero.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentZero newInstance(String param1, String param2) {
        FragmentZero fragment = new FragmentZero();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentZero() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zero, container, false);
        fragmentZeroDietPlanPackageRecyclerView = (RecyclerView) view.findViewById(R.id.fragmentZeroDietPlanPackageRecyclerView);
        fragmentZeroDietPlanPackageRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.fragmentZeroSwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);


        //get data from web service side and insert to db
        JsonRequest jsonRequest = new JsonRequest();
        jsonRequest.sendJsonRequest(view);

        //get data from db side
        GetDietPackageRecord getDietPackageRecord = new GetDietPackageRecord();
        listPlanRecord = getDietPackageRecord.getDietPackageRecord(view);

        //get data from database and set into the adaoter
        adapter = new AdapterDietPackageRecord(getActivity());
        adapter.setDietPlanArrayList(listPlanRecord);

        //and then call the adapter to setup the record into UI
        fragmentZeroDietPlanPackageRecyclerView.setAdapter(adapter);
        fragmentZeroDietPlanPackageRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),fragmentZeroDietPlanPackageRecyclerView,new Communicator.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                int currentPosition = position;
                Intent i = new Intent(getActivity(),SubActivity.class);
                i.putExtra(EXTRA_MESSAGE,currentPosition);
                startActivity(i);

               // startActivity(new Intent(getActivity(), SubActivity.class));

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(),"onLongClick "+position,Toast.LENGTH_SHORT).show();

            }
        }));
        return view;
    }


    @Override
    public void onRefresh() {
        if (swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
        adapter.setDietPlanArrayList(listPlanRecord);
        fragmentZeroDietPlanPackageRecyclerView.setAdapter(adapter);

    }

    //interface for touchEvent called when the gestureDectector called
    public static interface ClickListener{
        public void onClick(View view, int position);
        public void onLongClick(View view, int position);
    }



}
