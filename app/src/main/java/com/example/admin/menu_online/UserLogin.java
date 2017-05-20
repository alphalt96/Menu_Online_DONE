package com.example.admin.menu_online;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.menu_online.Database.MenuOnlineDatabase;
import com.example.admin.menu_online.models.User;

public class UserLogin extends AppCompatActivity {

    String save = "userinfo";

    private Toolbar toolbar;
    private EditText txtUsernameLogin, txtPasswordLogin;
    private Button btnLogin, btnSignOn, btnForgotPassword;

    private MenuOnlineDatabase menuOnlineDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        getWidgets();
        setControls();
        setEvents();
    }

    private void getWidgets() {
        txtUsernameLogin = (EditText) findViewById(R.id.txtUsernameLogin);
        txtPasswordLogin = (EditText) findViewById(R.id.txtPasswordLogin);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignOn = (Button) findViewById(R.id.btnSignOn);
        btnForgotPassword = (Button) findViewById(R.id.btnForgotPassword);
    }

    private void setEvents() {
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
                    editor.putString("SODIENTHOAI", user.getSoDienThoai());
                    editor.putString("EMAIL", user.getEmail());
                    editor.commit();
                    final ProgressDialog progressDialog = new ProgressDialog(UserLogin.this);
                    progressDialog.setMessage("Logging...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                Thread.sleep(3000);
                                progressDialog.dismiss();
                                startActivity(new Intent(UserLogin.this, UserInfo.class));
                            }
                            catch (Exception ex){
                            }
                        }
                    }).start();
                } else Toast.makeText(UserLogin.this, "Ten dang nhap hoac mat khau khong dung", Toast.LENGTH_SHORT).show();
            }
        });
        btnSignOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLogin.this, Register.class));
            }
        });
        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserLogin.this, "Just forget it and create a new account", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setControls() {
        data();
        setupToolbar();
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.toolBarReturnHome));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon_png);
        getSupportActionBar().setTitle("Đăng nhập");
    }

    private void data() {
        menuOnlineDatabase = new MenuOnlineDatabase(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
