package user.authentication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tarucfyp.dietplan.MainActivity;
import com.tarucfyp.dietplan.MyApplication;
import com.tarucfyp.dietplan.R;
import com.tarucfyp.dietplan.SubActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Login extends Activity{

    public static final String LoggedUserMessage = "userRecord";
    Button btnLogin,btnRegister;
    EditText etUsername;
    EditText etPassword;
    View view;

    ProgressDialog pDialog;
    private String username , password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view = v;
                 username = etUsername.getText().toString();
                 password = etPassword.getText().toString();

                User user = new User(username, password);

                authenticate(user);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Register.class));

            }
        });


    }

    private void authenticate(User user) {
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.fetchUserDataInBackground(user, new GetUserCallBack() {
            @Override
            public void done(User returnedUser) {
                if(returnedUser == null)
                {
                    showErrorMessage();
                }
                else {
                    LogUserIn(returnedUser);
                }
            }
        });
    }

    private void LogUserIn(User returnedUser) {
      //  startActivity(new Intent(view.getContext(), MainActivity.class));

        //once user success login , the username will be store into global.
        ((MyApplication) this.getApplication()).setUsername(username);
        Intent i = new Intent(view.getContext(),MainActivity.class);
       // i.putExtra(LoggedUserMessage, (Serializable) returnedUser);
        startActivity(i);
        // Toast.makeText(getApplicationContext(), returnedUser.registerDate.toString(),Toast.LENGTH_SHORT).show();
    }

    private  void showErrorMessage( ){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("incorrect detail.");
        dialogBuilder.setPositiveButton("OK",null);
        dialogBuilder.show();
    }


    class AttemptLogin extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Attempting for login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {



            return null;
        }

        protected void onPostExecute(String message) {
            pDialog.dismiss();

        }
    }


}
