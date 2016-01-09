package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tarucfyp.dietplan.MyApplication;
import com.tarucfyp.dietplan.R;

import Communicator.ClickListener;
import adapter.AdapterDashBoard;
import forum.category.Main_Category;
import test.abc;
import touchListener.RecyclerTouchListener;
import user.authentication.Login;

public class FragmentDashBoard extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView DashBoardRecyclerView;
    private AdapterDashBoard adapter;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTwo.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDashBoard newInstance(String param1, String param2) {
        FragmentDashBoard fragment = new FragmentDashBoard();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentDashBoard() {
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
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        DashBoardRecyclerView = (RecyclerView) view.findViewById(R.id.DashBoardRecyclerView);
        DashBoardRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new AdapterDashBoard(getActivity());
        DashBoardRecyclerView.setAdapter(adapter);
        DashBoardRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), DashBoardRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getActivity(), "onClick " + position, Toast.LENGTH_SHORT).show();
                //((MainActivity) getActivity()).OnDrawerItemClicked(position);
                 if (position == 3) {
                //    String username = ((MyApplication) getActivity().getApplication()).getUsername();
                //    if (username == null) {
                       // Intent iinent = new Intent(getActivity(), Login.class);
                     //   startActivity(iinent);
                 //   } else
                //    {
                        Intent iinent = new Intent(getActivity(), Main_Category.class);
                      //  iinent.putExtra("username" , username);
                        startActivity(iinent);
                 //   }
                }
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "onLongClick " + position, Toast.LENGTH_SHORT).show();

            }
        }));

        return view;
    }
}
