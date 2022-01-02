package com.example.tictactoe_tpt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void  playBtnClick(View view){
        Intent intent = new Intent(this, PlayerSetup.class);
        startActivity(intent);

    }

    public  void aboutBtnClick(View view){
        Intent intent = new Intent(this, AboutPage.class);
        startActivity(intent);
    }
}