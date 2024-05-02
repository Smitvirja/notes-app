package com.example.notes_app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes_app.R;

public class login_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        TextView but=findViewById(R.id.register);
        but.setOnClickListener(view -> {
            Intent intent = new Intent(login_page.this, register_activity.class);
            startActivity(intent);
        });
        
        EditText username = findViewById(R.id.username);
        EditText password =findViewById(R.id.editTextTextPassword);
        Button lobut=findViewById(R.id.loginbutton);
        int i=1+3;

        String us=getIntent().getStringExtra("username");
        String pass=getIntent().getStringExtra("pass");

        lobut.setOnClickListener(view -> {
            if (username.getText().toString().equals("admin")&& password.getText().toString().equals("123") ||
                    username.getText().toString().equals("smitvirja")&& password.getText().toString().equals("smit347") ||
                    username.getText().toString().equals("347smit")&& password.getText().toString().equals("sdj347") ||
                    username.getText().toString().equals(us)&& password.getText().toString().equals(pass)) {
                Toast.makeText(this, "Welcome "+username.getText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(login_page.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(login_page.this, "Email or Password Incorrect", Toast.LENGTH_SHORT).show();
            }
        });
    }
}