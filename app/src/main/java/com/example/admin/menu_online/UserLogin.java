package com.example.admin.menu_online;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.menu_online.Database.MenuOnlineDatabase;
import com.example.admin.menu_online.models.User;

public class UserLogin extends AppCompatActivity {

    String save = "userinfo";

    private ImageView imgLogin;
    private TextView txtUsernameTitle, txtPasswordTitle;
    private EditText txtUsernameLogin, txtPasswordLogin;
    private Button btnLogin, btnSignOn, btnEdit, btnShip, btnLogout, btnBack;
    private TextView txtHienThiUsername, txtHienThiPassword, txtHienThiAddress;

    private MenuOnlineDatabase menuOnlineDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        menuOnlineDatabase = new MenuOnlineDatabase(this);

        imgLogin = (ImageView) findViewById(R.id.imgLogin);
        txtUsernameTitle = (TextView) findViewById(R.id.txtUsernameTitle);
        txtPasswordTitle = (TextView) findViewById(R.id.txtPasswordTitle);
        txtUsernameLogin = (EditText) findViewById(R.id.txtUsernameLogin);
        txtPasswordLogin = (EditText) findViewById(R.id.txtPasswordLogin);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignOn = (Button) findViewById(R.id.btnSignOn);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnShip = (Button) findViewById(R.id.btnShip);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnBack = (Button) findViewById(R.id.btnBack);
        txtHienThiUsername = (TextView) findViewById(R.id.txtHienThiUsername);
        txtHienThiPassword = (TextView) findViewById(R.id.txtHienThiPassword);
        txtHienThiAddress = (TextView) findViewById(R.id.txtHienThiAddess);

        SharedPreferences sharedPreferences = getSharedPreferences(save, MODE_PRIVATE);
        if(sharedPreferences.getInt("USERID", 0)!=0) {
            txtHienThiUsername.setText(sharedPreferences.getString("USERNAME", ""));
            txtHienThiPassword.setText(sharedPreferences.getString("PASSWORD", ""));
            txtHienThiAddress.setText(sharedPreferences.getString("ADDRESS", ""));
            HideLogin();
            ShowInfo();
        } else {
            HideInfo();
            ShowLogin();
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(menuOnlineDatabase.getUser(txtUsernameLogin.getText().toString(), txtPasswordLogin.getText().toString())!= null) {
                    User user = menuOnlineDatabase.getUser(txtUsernameLogin.getText().toString(), txtPasswordLogin.getText().toString());
                    SharedPreferences sharedPreferences = getSharedPreferences(save, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("USERID", user.getId());
                    editor.putString("USERNAME", user.getUsername());
                    editor.putString("PASSWORD", user.getPassword());
                    editor.putString("ADDRESS", user.getAddress());
                    editor.commit();

                    txtHienThiUsername.setText(user.getUsername());
                    txtHienThiPassword.setText(user.getPassword());
                    txtHienThiAddress.setText(user.getAddress());

                    //an di phan dang nhap
                    HideLogin();
                    ShowInfo();

                    editor.commit();
                } else Toast.makeText(UserLogin.this, "Ten dang nhap hoac mat khau khong dung", Toast.LENGTH_SHORT).show();
            }
        });
        btnSignOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLogin.this, Register.class));
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(save, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("USERID", 0);
                editor.putString("USERNAME", "");
                editor.putString("PASSWORD", "");
                editor.putString("ADDRESS", "");
                editor.commit();
                ShowLogin();
                HideInfo();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void HideLogin(){
        //an di phan dang nhap
        imgLogin.setVisibility(View.GONE);
        txtUsernameTitle.setVisibility(View.GONE);
        txtPasswordTitle.setVisibility(View.GONE);
        txtUsernameLogin.setVisibility(View.GONE);
        txtPasswordLogin.setVisibility(View.GONE);
        btnLogin.setVisibility(View.GONE);
        btnSignOn.setVisibility(View.GONE);
    }
    private void HideInfo(){
        txtHienThiUsername.setVisibility(View.GONE);
        txtHienThiPassword.setVisibility(View.GONE);
        txtHienThiAddress.setVisibility(View.GONE);
        btnEdit.setVisibility(View.GONE);
        btnShip.setVisibility(View.GONE);
        btnLogout.setVisibility(View.GONE);
    }
    private void ShowLogin(){
        imgLogin.setVisibility(View.INVISIBLE);
        txtUsernameTitle.setVisibility(View.VISIBLE);
        txtPasswordTitle.setVisibility(View.VISIBLE);
        txtUsernameLogin.setVisibility(View.VISIBLE);
        txtPasswordLogin.setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.VISIBLE);
        btnSignOn.setVisibility(View.VISIBLE);
    }
    private void ShowInfo(){
        txtHienThiUsername.setVisibility(View.VISIBLE);
        txtHienThiPassword.setVisibility(View.VISIBLE);
        txtHienThiAddress.setVisibility(View.VISIBLE);
        btnEdit.setVisibility(View.VISIBLE);
        btnShip.setVisibility(View.VISIBLE);
        btnLogout.setVisibility(View.VISIBLE);
    }
}
