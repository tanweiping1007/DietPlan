package user.authentication;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import org.apache.http.HttpConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class ServerRequest {
    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://fitnessdietplan.hostoi.com/";

    public ServerRequest(Context context)
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please wait...");
    }


    public void storeUserDataInBackground(User user, GetUserCallBack userCallBack){
        progressDialog.show();
        new StoreUserDataAsyncTask(user, userCallBack).execute();
    }

    public void fetchUserDataInBackground(User user, GetUserCallBack callBack){
        progressDialog.show();
        new FetchUserDataAsyncTask(user, callBack).execute();

    }

    public class StoreUserDataAsyncTask extends AsyncTask<Void,Void,Void>{
        User user;
        GetUserCallBack userCallBack;

        public StoreUserDataAsyncTask(User user, GetUserCallBack userCallBack){
            this.user = user;
            this.userCallBack = userCallBack;
        }
        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("username",user.username));
            dataToSend.add(new BasicNameValuePair("password",user.password));
            dataToSend.add(new BasicNameValuePair("name",user.name));
            dataToSend.add(new BasicNameValuePair("age",user.age + ""));
            dataToSend.add(new BasicNameValuePair("dateOfBirth",user.birthDate));
            dataToSend.add(new BasicNameValuePair("email",user.email));

            dataToSend.add(new BasicNameValuePair("weight",user.weight + ""));
            dataToSend.add(new BasicNameValuePair("height",user.height + ""));
            dataToSend.add(new BasicNameValuePair("sensitive_food",user.sensitiveFood + ""));
            dataToSend.add(new BasicNameValuePair("heartRate",user.heartRate + ""));
            dataToSend.add(new BasicNameValuePair("registerDate",user.registerDate + ""));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "Register.php");

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
            userCallBack.done(null);
            super.onPostExecute(aVoid);
        }
    }



    public class FetchUserDataAsyncTask extends AsyncTask<Void,Void,User> {

        User user;
        GetUserCallBack userCallBack;


        public FetchUserDataAsyncTask(User user, GetUserCallBack userCallBack) {
            this.user = user;
            this.userCallBack = userCallBack;
        }

        @Override
        protected User doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("username",user.username));
            dataToSend.add(new BasicNameValuePair("password",user.password));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams,CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchUserData.php");

            User returnedUser = null;

            try {

                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpRespond = client.execute(post);


                HttpEntity entity = httpRespond.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jObject = new JSONObject(result);


                if(jObject.length() == 0){
                    returnedUser = null;
                }
                else {
                    String name = jObject.getString("name");
                    int age = jObject.getInt("age");

                    String dateOfBirth = jObject.getString("dateOfBirth");
                    String email = jObject.getString("email");
                    Double weight = jObject.getDouble("weight");
                    Double height = jObject.getDouble("height");
                    String sensitive_food = jObject.getString("sensitive_food");
                    Double heartRate = jObject.getDouble("heartRate");
                    String registerDate = jObject.getString("registerDate");

                    returnedUser = new User(user.username,user. password, name, age, dateOfBirth, email, weight,height, sensitive_food, heartRate, registerDate);

                }
            }catch (Exception e){

                e.printStackTrace();
            }

            return returnedUser;

        }

        @Override
        protected void onPostExecute(User returnedUser) {
            progressDialog.dismiss();
            userCallBack.done(returnedUser);
            super.onPostExecute(returnedUser);
        }

    }
}
