package com.lossfinder.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.lossfinder.app.constant.Constant;

public class LoginActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    private TextInputLayout emailLayout;
    private TextInputLayout passwordLayout;
    private EditText etEmail;
    private EditText etPassword;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Constant.LOGIN_LAYOUT);

        emailLayout = (TextInputLayout) findViewById(Constant.LOGIN_EMAIL_LAYOUT);
        passwordLayout = (TextInputLayout) findViewById(Constant.LOGIN_PASSWORD_LAYOUT);

        etEmail = (EditText)  findViewById(Constant.LOGIN_EMAIL);
        etPassword = (EditText) findViewById(Constant.LOGIN_PASSWORD);

        emailLayout.setOnFocusChangeListener(this);
        passwordLayout.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        boolean emailIsEmpty = etEmail.getText().toString().isEmpty();
        boolean passIsEmpty = etPassword.getText().toString().isEmpty();

        if (v != etEmail && emailIsEmpty) {
            emailLayout.setErrorEnabled(true);
            emailLayout.setError(getResources().getString(Constant.LOGIN_ERROR));
        } else if (v != etPassword && passIsEmpty){
            passwordLayout.setErrorEnabled(true);
            passwordLayout.setError(getResources().getString(Constant.LOGIN_ERROR));
        }
        else {
            emailLayout.setErrorEnabled(false);
            passwordLayout.setErrorEnabled(false);
        }
    }
}
