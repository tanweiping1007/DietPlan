package user.authentication;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tarucfyp.dietplan.R;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dialog.DateDialog;

public class Register extends Activity{

    Button btnLogin, btnRegister;
    EditText etUsername                    ;
    EditText etPassword;
    EditText etName                    ;
    EditText etAge;
    EditText txtDate;
    EditText etEmail;
    EditText etWeight;
    EditText etHeight;
    EditText etSensitiveFood;
    EditText etHeartRate;

    ProgressDialog pDialog;

   // JSONParser jsonParser = new JSONParser();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        txtDate=(EditText)findViewById(R.id.txtdate);
        etEmail =(EditText)findViewById(R.id.etEmail);
        etWeight=(EditText)findViewById(R.id.etWeight);
        etHeight=(EditText)findViewById(R.id.etHeight);
        etSensitiveFood=(EditText)findViewById(R.id.etSensitiveFood);
        etHeartRate=(EditText)findViewById(R.id.etHeartRate);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Login.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String name = etName.getText().toString();
                int age = Integer.parseInt(etAge.getText().toString());

                String birthDate = txtDate.getText().toString();
                String email = etEmail.getText().toString();
                Double weight = Double.parseDouble(etWeight.getText().toString());
                Double height = Double.parseDouble(etHeight.getText().toString());
                String sensitiveFood = etSensitiveFood.getText().toString();
                Double heartRate = Double.parseDouble(etHeartRate.getText().toString());

                User user = new User(username, password, name, age, birthDate, email, weight,height, sensitiveFood, heartRate, new Date().toString());

                registerUser(user);

            }

        });
    }

    private void registerUser(User user) {
        ServerRequest serverRequest = new ServerRequest(this);

        serverRequest.storeUserDataInBackground(user, new GetUserCallBack() {
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
    }



    public void onStart(){
        super.onStart();

        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View view, boolean hasfocus){
                if(hasfocus){
                    DateDialog dialog=new DateDialog(view);
                    FragmentTransaction ft =getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");

                }
            }

        });
    }
}
