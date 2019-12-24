package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button ok,add,cancel,restart,exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ok=findViewById(R.id.ok);
        cancel=findViewById(R.id.cancel);
        restart=findViewById(R.id.restart);
        add=findViewById(R.id.add);
        exit=findViewById(R.id.exit);
        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);
        restart.setOnClickListener(this);
        add.setOnClickListener(this);
        exit.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ok:
                Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancel:
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
                break;
            case R.id.restart:
                Toast.makeText(this, "Restart", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add:
                Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
