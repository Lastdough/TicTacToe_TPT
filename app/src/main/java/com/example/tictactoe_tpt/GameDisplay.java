package com.example.tictactoe_tpt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.FillEventHistory;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameDisplay extends AppCompatActivity {

    private papanTicTacToe PapanTicTacToe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_display);

        Button ulangBtn = findViewById(R.id.ulangBtn);
        Button homeBtn = findViewById(R.id.homeBtn);
        TextView playerTurn = findViewById(R.id.playerNameDisplay);

        PapanTicTacToe = findViewById(R.id.papanTicTacToe);

        String [] playerNames = getIntent().getStringArrayExtra("PLAYER_NAME");

        ulangBtn.setVisibility(View.GONE);
        homeBtn.setVisibility(View.GONE);

        if(playerNames != null){
            playerTurn.setText((playerNames[0]+"'s Turn"));
        }

        PapanTicTacToe.setUpGame(ulangBtn, homeBtn, playerTurn, playerNames);
    }

    public void ulangBtnClick(View view){
        PapanTicTacToe.resetGame();
        PapanTicTacToe.invalidate();
    }

    public  void homeBtnClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}