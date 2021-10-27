package com.example.datahandling;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.datahandling.database.DBHelper;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

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

    public void selectAll(View view){
        DBHelper dbHelper =  new DBHelper(this);

        //get the details from the database
        List userDetails = dbHelper.selectAll();

        //display the userdeatils
        String[] userInfo = (String[]) userDetails.toArray(new String[0]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("User Details");

        builder.setItems(userInfo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}