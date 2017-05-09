package com.example.admin.menu_online;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.menu_online.Database.MenuOnlineDatabase;

public class Register extends AppCompatActivity {

    MenuOnlineDatabase menuOnlineDatabase;

    TextView txtUsernameRegister, txtPasswordRegister, txtRepassword;
    Button btnRegister, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        menuOnlineDatabase = new MenuOnlineDatabase(this);
//        menuOnlineDatabase.insertUser("ahihi", "ahihi");

        txtUsernameRegister = (TextView) findViewById(R.id.txtUsernameRegister);
        txtPasswordRegister = (TextView) findViewById(R.id.txtPasswordRegister);
        txtRepassword = (TextView) findViewById(R.id.txtRePassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(menuOnlineDatabase.checkUsername(txtUsernameRegister.getText().toString())) {
                    menuOnlineDatabase.insertUser(txtUsernameRegister.getText().toString(), txtPasswordRegister.getText().toString());
                    Toast.makeText(Register.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(Register.this, "Username da ton tai", Toast.LENGTH_SHORT).show();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
