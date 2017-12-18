package com.sxp8h.redspotify;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.sxp8h.redspotify.action.ActionMain;
import com.sxp8h.redspotify.beans.User;

//
//  ::::::::  :::    ::: :::::::::   ::::::::  :::    :::
// :+:    :+: :+:    :+: :+:    :+: :+:    :+: :+:    :+:
// +:+         +:+  +:+  +:+    +:+ +:+    +:+ +:+    +:+
// +#++:++#++   +#++:+   +#++:++#+   +#++:++#  +#++:++#++
//        +#+  +#+  +#+  +#+        +#+    +#+ +#+    +#+
// #+#    #+# #+#    #+# #+#        #+#    #+# #+#    #+#
//  ########  ###    ### ###         ########  ###    ###
//
//          --------Created by SXP8H--------


public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button buttonLogin;
    private CheckBox checkBoxSave;

    private static MainActivity mainActivity = null;
    public static MainActivity getInstance(){
        return mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;

        email = (EditText) findViewById(R.id.edtEmail);
        password = (EditText) findViewById(R.id.edtPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        checkBoxSave = (CheckBox) findViewById(R.id.checkboxSave);

        final SharedPreferences settings = getSharedPreferences("LOGIN", MODE_PRIVATE);
        if(settings.contains("EMAIL")){
            email.setText(settings.getString("EMAIL", ""));
            Intent listActivity = new Intent(MainActivity.getInstance().getBaseContext(), ListActivity.class);
            startActivity(listActivity);
        }
        if(settings.contains("PASSWORD")){
            password.setText(settings.getString("PASSWORD", ""));
        }
        if(settings.contains("LOGIN")){
            checkBoxSave.setChecked(settings.getBoolean("LOGIN", false));
        }
        checkBoxSave.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBoxSave.isChecked()){
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("LOGIN", true);
                    editor.putString("EMAIL", email.getText().toString());
                    editor.putString("PASSWORD", password.getText().toString());
                    editor.commit();
                }else{
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("LOGIN", false);
                    editor.putString("EMAIL", "");
                    editor.putString("PASSWORD", "");
                    editor.commit();
                }

            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();

                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());
                ActionMain.validateLogin(user);
            }
        });
    }
}
