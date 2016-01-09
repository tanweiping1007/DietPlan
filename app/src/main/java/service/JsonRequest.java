package service;

// call this function will be go to the website and request the data
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.tarucfyp.dietplan.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import adapter.AdapterDietPackageRecord;
import database.DietPlanPackageDataSource;
import database.DietPlanPackageRecord;
import fragment.GetDietPackageRecord;
import network.VolleySingleton;

public class JsonRequest {
    private static final String URL_DIET_PLAN_PACKAGE = "http://fitnessdietplan.hostoi.com/dietplan.json";
    public String errorMsg;
    public String Msg;
    public View viewer;


    public void JsonRequest(){

    }

    public static String getRequestUrl() {
        return URL_DIET_PLAN_PACKAGE;
    }

    public void sendJsonRequest(final View view){

        viewer = view;
        //volley get the request from the singleton for connect to web service
        RequestQueue requestQueue = VolleySingleton.getsInstance().getRequestQueue();
        //   RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "http://fitnessdietplan.hostoi.com/dietplanv4.json",
                (String)null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseJSONResponse(response);
                    }
                },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof TimeoutError || error instanceof NoConnectionError){
                    Toast.makeText(viewer.getContext().getApplicationContext(),"Timeout / No Connection Error, Unable to Update the Current List",Toast.LENGTH_LONG).show();
                }else if(error instanceof AuthFailureError){
                    Toast.makeText(viewer.getContext().getApplicationContext(),"Auth Failure Error, Unable to Update the Current List",Toast.LENGTH_LONG).show();
                }else if(error instanceof NetworkError){
                    Toast.makeText(viewer.getContext().getApplicationContext(),"Network Error, Unable to Update the Current List",Toast.LENGTH_LONG).show();
                }else if(error instanceof ParseError){

                }
            }
        }
        );
        requestQueue.add(request);
    }


    private void parseJSONResponse(JSONObject respond){
        if(respond !=null || respond.length()>0){


            try{
                //try get all the data form web service
                JSONArray arrayDietPackage = respond.getJSONArray("package");

                for (int i =0 ; i < arrayDietPackage.length() ; i++){
                    //retrieve the particular record
                    JSONObject currentDietPackageRecord = arrayDietPackage.getJSONObject(i);

                    String package_id ="String";
                    String package_thumbnail ="String";
                    String package_suitable ="String";

                    String package_description_day1_morning ="String";
                    String package_description_day1_evening ="String";
                    String package_description_day1_night ="String";

                    String package_description_day2_morning ="String";
                    String package_description_day2_evening ="String";
                    String package_description_day2_night ="String";

                    String package_description_day3_morning ="String";
                    String package_description_day3_evening ="String";
                    String package_description_day3_night ="String";

                    String package_description_day4_morning ="String";
                    String package_description_day4_evening ="String";
                    String package_description_day4_night ="String";

                    String package_description_day5_morning ="String";
                    String package_description_day5_evening ="String";
                    String package_description_day5_night ="String";


                     package_id= currentDietPackageRecord.getString("package_id");
                     package_thumbnail= currentDietPackageRecord.getString("package_thumbnail");
                     package_suitable= currentDietPackageRecord.getString("package_suitable");

                    JSONObject package_description = currentDietPackageRecord.getJSONObject("package_description");
                    //day 1
                    if(package_description.has("day1")){
                        JSONObject day1 = package_description.getJSONObject("day1");
                        if(day1.has("morning")){
                            package_description_day1_morning = day1.getString("morning");
                        }
                        if(day1.has("evening")){
                            package_description_day1_evening = day1.getString("evening");
                        }
                        if(day1.has("night")){
                            package_description_day1_night = day1.getString("night");
                        }
                    }
                    //day 2
                    if(package_description.has("day2")){
                        JSONObject day2 = package_description.getJSONObject("day2");
                        if(day2.has("morning")){
                            package_description_day2_morning = day2.getString("morning");
                        }
                        if(day2.has("evening")){
                            package_description_day2_evening = day2.getString("evening");
                        }
                        if(day2.has("night")){
                            package_description_day2_night = day2.getString("night");
                        }
                    }
                    //day 3
                    if(package_description.has("day3")){
                        JSONObject day3 = package_description.getJSONObject("day3");
                        if(day3.has("morning")){
                            package_description_day3_morning = day3.getString("morning");
                        }
                        if(day3.has("evening")){
                            package_description_day3_evening = day3.getString("evening");
                        }
                        if(day3.has("night")){
                            package_description_day3_night = day3.getString("night");
                        }
                    }
                    //day 4
                    if(package_description.has("day4")){
                        JSONObject day4 = package_description.getJSONObject("day4");
                        if(day4.has("morning")){
                            package_description_day4_morning = day4.getString("morning");
                        }
                        if(day4.has("evening")){
                            package_description_day4_evening = day4.getString("evening");
                        }
                        if(day4.has("night")){
                            package_description_day4_night = day4.getString("night");
                        }
                    }
                    //day 5
                    if(package_description.has("day5")){
                        JSONObject day5 = package_description.getJSONObject("day5");
                        if(day5.has("morning")){
                            package_description_day5_morning = day5.getString("morning");
                        }
                        if(day5.has("evening")){
                            package_description_day5_evening = day5.getString("evening");
                        }
                        if(day5.has("night")){
                            package_description_day5_night = day5.getString("night");
                        }
                    }



                    DietPlanPackageRecord dietPlanPackageRecord = new DietPlanPackageRecord(package_id,package_thumbnail,package_suitable,
                            package_description_day1_morning,package_description_day1_evening,package_description_day1_night,
                            package_description_day2_morning,package_description_day2_evening,package_description_day2_night,
                            package_description_day3_morning,package_description_day3_evening,package_description_day3_night,
                            package_description_day4_morning,package_description_day4_evening,package_description_day4_night,
                            package_description_day5_morning,package_description_day5_evening,package_description_day5_night);

                    DietPlanPackageDataSource dietPlanPackageDataSource = new DietPlanPackageDataSource(viewer.getContext());
                    dietPlanPackageDataSource.insertData(dietPlanPackageRecord);
                }

            }
            catch (JSONException e)
            {
                Toast.makeText(viewer.getContext().getApplicationContext(),e.toString()+"",Toast.LENGTH_LONG).show();
            }
        }





    }




}
