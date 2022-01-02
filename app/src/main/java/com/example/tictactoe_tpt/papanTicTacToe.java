package com.example.tictactoe_tpt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class papanTicTacToe extends View {
    private  final GameLogic game;

    private final int papanColor;
    private  final  int buletColor;
    private  final int silangColor;
    private  final  int garisKemenangan;

    private boolean garisMenang = false;

    private final Paint paint = new Paint();

    private  int cellSize = getWidth()/3;



    public papanTicTacToe(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        game = new GameLogic();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.papanTicTacToe, 0, 0);
        try {
            papanColor = a.getInteger(R.styleable.papanTicTacToe_papanColor, 0);
            buletColor = a.getInteger(R.styleable.papanTicTacToe_buletColor,0);
            silangColor = a.getColor(R.styleable.papanTicTacToe_silangColor,0);
            garisKemenangan = a.getColor(R.styleable.papanTicTacToe_garisKemenangan, 0);

        }finally {
            a.recycle();
        }
    }

    @Override
    protected  void onMeasure(int width, int height){
        super.onMeasure(width, height);

        int  dimensi = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellSize = dimensi/3;


        setMeasuredDimension(dimensi, dimensi);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public  boolean onTouchEvent(MotionEvent event){
        float x =  event.getX();
        float y = event.getY();

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN){
            int row = (int) Math.ceil(y/cellSize);
            int col = (int) Math.ceil(x/cellSize);

            if (!garisMenang){
                 if(game.updatePapanGame(row, col)){
                    invalidate();

                    if (game.cekPemenang()){
                        garisMenang = true;
                        invalidate();
                    }

                    //mengupdate giliran pemain
                    if (game.getPlayer() % 2 == 0){
                    game.setPlayer(game.getPlayer() - 1);
                    }else {
                    game.setPlayer(game.getPlayer() + 1);
                    }
                 }
            }
            return true;
        }

        return  false;
    }

    @Override
    protected void onDraw(Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        drawGameBoard(canvas);
        gambarMarker(canvas);

        if (garisMenang){
            paint.setColor(garisKemenangan);
            gambarGarisKemenangan(canvas);
        }
        //gambarSilang(canvas, 0 ,0);

    }
    private void  drawGameBoard(Canvas canvas){
        paint.setColor(papanColor);
        paint.setStrokeWidth(16);

        for (int col = 1; col < 3; col++){
            canvas.drawLine(cellSize*col, 0, cellSize*col, canvas.getWidth(), paint);
        }
        for (int row = 1; row < 3; row++){
            canvas.drawLine(0, cellSize*row, canvas.getWidth(),cellSize*row, paint);
        }
    }

    private void gambarMarker(Canvas canvas){
        //untuk menentukan apa X atau O yg di gambar
        for(int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (game.getPapanGame() [r] [c] != 0){
                    if (game.getPapanGame() [r] [c] == 1){
                        gambarSilang(canvas, r, c);
                    }else {
                        gambarBulet(canvas, r, c);
                    }
                }
            }
        }

    }


    //Method untuk menggambar bulat
    private void gambarBulet(Canvas canvas, int row, int col){
        paint.setColor(buletColor);

        canvas.drawOval((float)(col*cellSize + cellSize*0.2),
                        (float)(row*cellSize + cellSize*0.2),
                        (float)((col*cellSize + cellSize) - cellSize*0.2),
                        (float)((row*cellSize + cellSize) - cellSize*0.2),
                        paint);

    }
    //methon untuk menggambar silang
    private void gambarSilang(Canvas canvas, int row, int col){
        paint.setColor(silangColor);

        canvas.drawLine((col+1)*cellSize - cellSize*0.2f,
                        row*cellSize + cellSize*0.2f,
                        col*cellSize + cellSize*0.2f,
                        (row+1)*cellSize - cellSize*0.2f,
                        paint);
        canvas.drawLine(col*cellSize + cellSize*0.2f,
                        row*cellSize + cellSize*0.2f,
                        (col+1)*cellSize - cellSize*0.2f,
                        (row+1)*cellSize - cellSize*0.2f,
                        paint);
    }

    public void gambarGarisHorizontal(Canvas canvas, int row, int col){
        canvas.drawLine(col*1,
                        row*cellSize + cellSize/2f,
                        cellSize*3,
                        row*cellSize + cellSize/2f, paint);
    }

    public void  gambarGarisVertikal(Canvas canvas, int row, int col){
        canvas.drawLine(col*cellSize + cellSize/2f,
                        row*1,
                        col*cellSize+cellSize/2f,
                        cellSize*3, paint);
    }

    private void gambarDiagonalMiringKiri(Canvas canvas){
        canvas.drawLine(0,
                        cellSize*3,
                        cellSize*3,
                        0, paint);
    }
    private void gambarDiagonalMiringKanan(Canvas canvas){
        canvas.drawLine(0,
                        0,
                        cellSize*3,
                        cellSize*3, paint);
    }

    private void gambarGarisKemenangan(Canvas canvas){
        int row = game.getWinType()[0];
        int col = game.getWinType()[1];

        switch (game.getWinType()[2]){
            case 1 :
                gambarGarisHorizontal(canvas, row, col);
                break;
            case 2:
                gambarGarisVertikal(canvas, row, col);
                break;
            case 3:
                gambarDiagonalMiringKanan(canvas);
                break;
            case 4:
                gambarDiagonalMiringKiri(canvas);
                break;
        }

    }

    public void setUpGame(Button ulang, Button home, TextView playerDisplay, String[] name){
        game.setUlangBtn(ulang);
        game.setHomeBtn(home);
        game.setPlayerTurn(playerDisplay);
        game.setPlayerName(name);
    }



    public void resetGame(){
        game.resetPapanGame();
        garisMenang = false;
    }
}

