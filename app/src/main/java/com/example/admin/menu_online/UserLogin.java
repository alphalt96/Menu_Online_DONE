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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.menu_online.Database.MenuOnlineDatabase;
import com.example.admin.menu_online.models.User;

public class UserLogin extends AppCompatActivity {

    String save = "userinfo";

    private LinearLayout layoutLogged, layoutUnLogged;
    private ImageView imgLogin;
    private TextView txtUsernameTitle, txtPasswordTitle;
    private EditText txtUsernameLogin, txtPasswordLogin, txtEditUsername, txtEditPassword, txtEditAddress;;
    private Button btnLogin, btnSignOn, btnEdit, btnShip, btnLogout, btnBack, btnSave, btnCancel;
    private TextView txtHienThiUsername, txtHienThiPassword, txtHienThiAddress;

    private MenuOnlineDatabase menuOnlineDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        menuOnlineDatabase = new MenuOnlineDatabase(this);

        layoutLogged = (LinearLayout) findViewById(R.id.layoutLogged);
        layoutUnLogged = (LinearLayout) findViewById(R.id.layoutUnLogged);
        imgLogin = (ImageView) findViewById(R.id.imgLogin);
        txtUsernameTitle = (TextView) findViewById(R.id.txtUsernameTitle);
        txtPasswordTitle = (TextView) findViewById(R.id.txtPasswordTitle);
        txtUsernameLogin = (EditText) findViewById(R.id.txtUsernameLogin);
        txtPasswordLogin = (EditText) findViewById(R.id.txtPasswordLogin);
        txtEditUsername = (EditText) findViewById(R.id.txtEditUsername);
        txtEditPassword = (EditText) findViewById(R.id.txtEditPassword);
        txtEditAddress = (EditText) findViewById(R.id.txtEditAddress);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignOn = (Button) findViewById(R.id.btnSignOn);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnShip = (Button) findViewById(R.id.btnShip);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        txtHienThiUsername = (TextView) findViewById(R.id.txtHienThiUsername);
        txtHienThiPassword = (TextView) findViewById(R.id.txtHienThiPassword);
        txtHienThiAddress = (TextView) findViewById(R.id.txtHienThiAddress);


        final SharedPreferences sharedPreferences = getSharedPreferences(save, MODE_PRIVATE);
        if(sharedPreferences.getInt("USERID", 0)!=0) {
            txtHienThiUsername.setText(sharedPreferences.getString("USERNAME", ""));
            txtHienThiPassword.setText(sharedPreferences.getString("PASSWORD", ""));
            txtHienThiAddress.setText(sharedPreferences.getString("ADDRESS", ""));
            HideLogin();
            ShowInfo();
            //An di phan edit profile
            HideEdit();
        } else {
            HideInfo();
            ShowLogin();
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(menuOnlineDatabase.checkLogin(txtUsernameLogin.getText().toString(), txtPasswordLogin.getText().toString())) {
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
                    //an di phan edit khi dang nhap thanh cong vi mac dinh no se hien thi len cung voi layoutLogged
                    HideEdit();

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
                startActivity(new Intent(UserLogin.this, MainActivity.class));
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEditUsername.setText(sharedPreferences.getString("USERNAME", ""));
                txtEditPassword.setText(sharedPreferences.getString("PASSWORD", ""));
                txtEditAddress.setText(sharedPreferences.getString("ADDRESS", ""));
                HideInfoText();
                ShowEdit();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuOnlineDatabase.updateUser(txtEditUsername.getText().toString(), txtEditPassword.getText().toString(), txtEditAddress.getText().toString());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("USERNAME", txtEditUsername.getText().toString());
                editor.putString("PASSWORD", txtEditPassword.getText().toString());
                editor.putString("ADDRESS", txtEditAddress.getText().toString());
                editor.commit();

                txtHienThiUsername.setText(txtEditUsername.getText().toString());
                txtHienThiPassword.setText(txtEditPassword.getText().toString());
                txtHienThiAddress.setText(txtEditAddress.getText().toString());

                txtEditUsername.setText("");
                txtEditPassword.setText("");
                txtEditAddress.setText("");

                HideEdit();
                ShowInfoText();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEditUsername.setText("");
                txtEditPassword.setText("");
                txtEditAddress.setText("");
                HideEdit();
                ShowInfoText();
            }
        });
        btnShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLogin.this, DonHang.class));
            }
        });
    }
    private void HideLogin(){
        //an di phan dang nhap
        layoutUnLogged.setVisibility(View.GONE);
    }
    private void HideInfo(){
        layoutLogged.setVisibility(View.GONE);
    }
    private void ShowLogin(){
        layoutUnLogged.setVisibility(View.VISIBLE);
    }
    private void ShowInfo(){
        layoutLogged.setVisibility(View.VISIBLE);
    }
    private void HideEdit(){
        btnSave.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
        txtEditUsername.setVisibility(View.GONE);
        txtEditPassword.setVisibility(View.GONE);
        txtEditAddress.setVisibility(View.GONE);
    }
    private void ShowEdit(){
        btnSave.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);
        txtEditUsername.setVisibility(View.VISIBLE);
        txtEditPassword.setVisibility(View.VISIBLE);
        txtEditAddress.setVisibility(View.VISIBLE);
    }
    private void HideInfoText(){
        txtHienThiUsername.setVisibility(View.GONE);
        txtHienThiPassword.setVisibility(View.GONE);
        txtHienThiAddress.setVisibility(View.GONE);
        btnShip.setVisibility(View.GONE);
        btnLogout.setVisibility(View.GONE);
    }
    private void ShowInfoText(){
        txtHienThiUsername.setVisibility(View.VISIBLE);
        txtHienThiPassword.setVisibility(View.VISIBLE);
        txtHienThiAddress.setVisibility(View.VISIBLE);
        btnShip.setVisibility(View.VISIBLE);
        btnLogout.setVisibility(View.VISIBLE);
    }
}
