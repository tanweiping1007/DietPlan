package com.tarucfyp.dietplan;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.util.ArrayList;

import database.DatabaseRecord;

public class FoodServerRequest {
    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://fitnessdietplan.hostoi.com/";

    public FoodServerRequest(Context context)
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please wait...");
    }

    public void storeFoodDataInBackground(DatabaseRecord.FoodRecord food, GetDietPackageCallBack foodCallBack){
        progressDialog.show();
        new StoreFoodDataAsyncTask(food, foodCallBack).execute();
    }

    public class StoreFoodDataAsyncTask extends AsyncTask<Void,Void,Void> {
        DatabaseRecord.FoodRecord food;
        GetDietPackageCallBack foodCallBack;

        public StoreFoodDataAsyncTask(DatabaseRecord.FoodRecord user, GetDietPackageCallBack foodCallBack){
            this.food = user;
            this.foodCallBack = foodCallBack;
        }
        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();

            dataToSend.add(new BasicNameValuePair("category","hello" + ""));
            dataToSend.add(new BasicNameValuePair("name","Hello" + ""));
            dataToSend.add(new BasicNameValuePair("calories", 1.00 + ""));
            dataToSend.add(new BasicNameValuePair("carbohydrate",2.00 + ""));
            dataToSend.add(new BasicNameValuePair("protein",3.00 + ""));
            dataToSend.add(new BasicNameValuePair("fat",4.00 + ""));
            dataToSend.add(new BasicNameValuePair("fiber",5.00 + ""));
            dataToSend.add(new BasicNameValuePair("tag","Zello" + ""));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "try.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            foodCallBack.done(null);
            super.onPostExecute(aVoid);
        }
    }
}
