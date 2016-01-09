package fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tarucfyp.dietplan.R;

import java.util.ArrayList;
import java.util.List;

import adapter.AdapterDietPackageRecord;
import database.DietPlanPackageDataSource;
import database.DietPlanPackageRecord;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetDietPackageRecord extends Fragment {




    private ArrayList<DietPlanPackageRecord> values ;
    int length;

    public GetDietPackageRecord() {
        // Required empty public constructor
    }

    //get data from database
    public View viewer;
    public ArrayList<DietPlanPackageRecord> getDietPackageRecord(View view){
        DietPlanPackageDataSource dietPlanPackageDataSource;
        viewer = view;
        try {
            dietPlanPackageDataSource = new DietPlanPackageDataSource(viewer.getContext()) ;
            dietPlanPackageDataSource.open();
            values = dietPlanPackageDataSource.getAllData();

        }
        catch (Exception e)
        {
            Toast.makeText(viewer.getContext(),e.toString(),Toast.LENGTH_LONG).show();
        }

        return values;
    }



}

