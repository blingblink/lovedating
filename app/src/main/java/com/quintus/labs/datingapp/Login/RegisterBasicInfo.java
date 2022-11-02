package com.quintus.labs.datingapp.Login;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.quintus.labs.datingapp.Profile.EditProfileActivity;
import com.quintus.labs.datingapp.R;
import com.quintus.labs.datingapp.Utils.GPS;
import com.quintus.labs.datingapp.Utils.User;



public class RegisterBasicInfo extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    GPS gps;
    private Context mContext;
    private String email, username, password;
    private EditText mEmail, mPassword, mUsername;
    private TextView loadingPleaseWait;
    private Button btnRegister;
    private String append = "";

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private ImageView google_img;
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerbasic_info);
        mContext = RegisterBasicInfo.this;
        Log.d(TAG, "onCreate: started");

        gps = new GPS(getApplicationContext());

        initWidgets();
        init();
        google_img = findViewById(R.id.google);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this,gso);
        google_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn();
            }
        });




    }
    private void SignIn(){
        Intent intent= gsc.getSignInIntent();
        startActivityForResult(intent,100);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode, data);

//        if (requestCode==100){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                EditProfileActivity();

            } catch (ApiException e) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
//        }

    }
    private void EditProfileActivity(){
        finish();
//        Intent intent = new Intent(getApplicationContext(),RegisterBasicInfo.class);
        Intent intent = new Intent(getApplicationContext(), EditProfileActivity.class);
        startActivity(intent);
    }

    private void init() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = mEmail.getText().toString();
                username = mUsername.getText().toString();
                password = mPassword.getText().toString();

                if (checkInputs(email, username, password)) {
                    //find geo location
                    //find geo location
                    Location location = gps.getLocation();
                    double latitude = 37.349642;
                    double longtitude = -121.938987;
                    if (location != null) {
                        latitude = location.getLatitude();
                        longtitude = location.getLongitude();
                    }
                    Log.d("Location==>", longtitude + "   " + latitude);


                    Intent intent = new Intent(RegisterBasicInfo.this, RegisterGender.class);
                    User user = new User("", "", "", "", email, username, false, false, false, false, "", "", "", latitude, longtitude);
                    intent.putExtra("password", password);
                    intent.putExtra("classUser", user);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean checkInputs(String email, String username, String password) {
        Log.d(TAG, "checkInputs: checking inputs for null values.");
        if (email.equals("") || username.equals("") || password.equals("")) {
            Toast.makeText(mContext, "All fields must be filed out.", Toast.LENGTH_SHORT).show();
            return false;
        }

        // checks if the email id is valid or not.
        if (!email.matches(emailPattern)) {
            Toast.makeText(getApplicationContext(), "Email không tồn tại, nhập lại email và nhấn tiếp tục", Toast.LENGTH_SHORT).show();
            return false;

        }


        return true;
    }

    private void initWidgets() {
        Log.d(TAG, "initWidgets: initializing widgets");
        mEmail = findViewById(R.id.input_email);
        mUsername = findViewById(R.id.input_username);
        btnRegister = findViewById(R.id.btn_register);
        mPassword = findViewById(R.id.input_password);
        mContext = RegisterBasicInfo.this;

    }

    public void onLoginClicked(View view) {
        startActivity(new Intent(getApplicationContext(), Login.class));

    }


}

