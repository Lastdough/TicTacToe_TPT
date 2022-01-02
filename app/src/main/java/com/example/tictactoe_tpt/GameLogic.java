package com.example.tictactoe_tpt;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameLogic {
    private final int [][] papanGame;

    private String[] playerName = {"Player 1", "Player 2"};

    private int[] winType ={-1, -1, -1};
    private Button ulangBtn;
    private Button homeBtn;
    private TextView playerTurn;

    private  int player = 1;

    GameLogic(){
        papanGame = new  int[3][3];
        for(int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                papanGame[r][c] = 0;
            }
        }
    }

    public boolean updatePapanGame(int row, int col){
        if(papanGame[row - 1] [col - 1] == 0){
           papanGame[row - 1] [col - 1] = player;

           if (player == 1){
               playerTurn.setText((playerName[1]+"'s Turn"));
           }else{
               playerTurn.setText((playerName[0]+"'s Turn"));
           }
           return  true;
        }else{
            return  false;
        }
    }

    public boolean cekPemenang(){
        boolean apaMenang = false;
        //vertikal
        for (int row = 0; row< 3; row++){
            if(papanGame[row][0] == papanGame[row][1] && papanGame[row][0] == papanGame[row][2] && papanGame[row][0] != 0){
                apaMenang = true;
                winType = new int[] {row, 0, 1};
            }
        }
        //horizontal
        for (int col = 0; col <3; col++){
            if (papanGame[0][col] == papanGame [1][col] && papanGame[2][col] == papanGame [0][col] && papanGame[0][col] != 0){
                apaMenang =true;
                winType = new int[] {0, col, 2};
            }
        }
        //diagonal miring kanan
        if(papanGame[0][0] == papanGame [1][1] && papanGame [2][2] == papanGame [0][0] && papanGame[0][0] != 0){
            apaMenang = true;
            winType = new  int[] {0, 2, 3};
        }

        //diagonal miring kiri
        if(papanGame [2][0] == papanGame [1][1] && papanGame[0][2] == papanGame [2][0] && papanGame[2][0] != 0){
            apaMenang = true;
            winType = new int[] {2, 2, 4};
        }
        int isiPapan = 0;

        for(int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if(papanGame[r][c] != 0){
                    isiPapan+=1;
                }
            }
        }

        if (apaMenang){
            ulangBtn.setVisibility(View.VISIBLE);
            homeBtn.setVisibility(View.VISIBLE);
            playerTurn.setText((playerName[player-1] + " Won...!"));
            return true;
        }else if (isiPapan == 9){
            ulangBtn.setVisibility(View.VISIBLE);
            homeBtn.setVisibility(View.VISIBLE);
            playerTurn.setText(("Draw"));
            return true;
        }
            return false;
    }

    public void resetPapanGame(){
        for(int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                papanGame[r][c] = 0;
            }
        }

        winType[0] = -1;
        winType[1] = -1;
        winType[2] = -1;

        player = 1;
        ulangBtn.setVisibility(View.GONE);
        homeBtn.setVisibility(View.GONE);

        playerTurn.setText((playerName[0]+"'s Turn"));
    }

    public void setUlangBtn(Button ulangBtn) {
        this.ulangBtn = ulangBtn;
    }

    public void setHomeBtn(Button homeBtn) {
        this.homeBtn = homeBtn;
    }

    public void setPlayerTurn(TextView playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setPlayerName(String[] playerName) {
        this.playerName = playerName;
    }

    public int[][] getPapanGame() {
        return papanGame;
    }

    public int[] getWinType() {
        return winType;
    }

    public void setPlayer(int player){
        this.player = player;
    }

    public int getPlayer(){
        return player;
    }
}
