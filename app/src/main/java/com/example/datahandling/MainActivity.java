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

    EditText ET_username, ET_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ET_username = findViewById(R.id.et_username);
        ET_password = findViewById(R.id.et_password);
    }

    //onclick event for ADD
    public void addUser(View view){
        String uName = ET_username.getText().toString();
        String pwd = ET_password.getText().toString();
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

    //onClick event for SELECTALL
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
                String username = userInfo[i].split(":")[0];

                ET_username.setText(username);
                ET_password.setText("*******");

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

    //onclick event for DELETE
    public void deleteUser(View view){
        DBHelper dbHelper = new DBHelper(this);

        String userName = ET_username.getText().toString();

        if(userName.isEmpty()){
            Toast.makeText(this, "Please select user to Delete", Toast.LENGTH_SHORT).show();
        }else{
            dbHelper.deleteInfo(userName);
            Toast.makeText(this, "User deleted!", Toast.LENGTH_SHORT).show();

            ET_username.setText("");
            ET_password.setText("");
        }

    }


    //onclick event for UPDATE
    public void update(View view){
        DBHelper dbHelper = new DBHelper(this);

        String username = ET_username.getText().toString();
        String password = ET_password.getText().toString();

        if(username.isEmpty()){
            Toast.makeText(this,"Username is empty",Toast.LENGTH_SHORT).show();
        }else if(password.isEmpty()){
            Toast.makeText(this,"Password is required",Toast.LENGTH_SHORT).show();
        }else{
            dbHelper.editUser(view,username,password);
        }
    }
}