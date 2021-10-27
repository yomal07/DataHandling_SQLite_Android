package com.example.datahandling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.datahandling.database.DBHelper;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button selectall, add, update, delete, signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
    }

    public void addUser(View view){
        String uName = username.getText().toString();
        String pwd = password.getText().toString();
        DBHelper dbHelper = new DBHelper(this);

        if(uName.isEmpty()){
            Toast.makeText(this,"Enter the Username",Toast.LENGTH_SHORT).show();
        }else if (pwd.isEmpty()){
            Toast.makeText(this,"Enter the Password",Toast.LENGTH_SHORT).show();
        }else{
            dbHelper.addUser(uName,pwd);
            Toast.makeText(this,"Data inserted successfully",Toast.LENGTH_SHORT).show();
        }
    }
}