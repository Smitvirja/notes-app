package com.example.notes_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.notes_app.R;

public class register_activity extends AppCompatActivity {

    private EditText inputEmail;
    private EditText inputUsername;
    private EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button confirmButton = findViewById(R.id.re_submit);
        TextView loginButton = findViewById(R.id.login);

        inputEmail = findViewById(R.id.Re_mail);
        inputPassword = findViewById(R.id.re_pass);
        inputUsername = findViewById(R.id.re_username);

        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(register_activity.this, login_page.class);
            startActivity(intent);
            finish();
        });

        confirmButton.setOnClickListener(v -> {
            confirmInput();
            Toast.makeText(this, "Register Successful", Toast.LENGTH_SHORT).show();
        });
    }

    private boolean validateEmail() {
        String emailInput = inputEmail.getText().toString().trim();

        if (emailInput.isEmpty()) {
            inputEmail.setError("Email field can't be empty");
            return false;
        } else {
            inputEmail.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String usernameInput = inputUsername.getText().toString().trim();
        if (usernameInput.isEmpty()) {
            inputUsername.setError("Username field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            inputUsername.setError("Username is too long");
            return false;
        } else {
            inputUsername.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = inputPassword.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            inputPassword.setError("Password field can't be empty");
            return false;
        } else {
            inputPassword.setError(null);
            return true;
        }
    }

    public void confirmInput() {
        if (!validateEmail() || !validatePassword() || !validateUsername()) {
            return;
        }

        String inputEmailText = inputEmail.getText().toString();
        String inputPasswordText = inputPassword.getText().toString();
        String inputUsernameText = inputUsername.getText().toString();

        Intent intent = new Intent(register_activity.this, login_page.class);
        intent.putExtra("email", inputEmailText);
        intent.putExtra("pass", inputPasswordText);
        intent.putExtra("username", inputUsernameText);
        startActivity(intent);
    }
}
