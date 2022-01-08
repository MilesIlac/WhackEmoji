package com.milesilac.whackamolu;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;

import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.os.Handler;
import android.widget.TextView;

import java.util.Random;

public class MainUntimedActivity extends AppCompatActivity {

    private int score = 0;
    private int totalScore = 0;
    private int highScoreCheck;
    private int lastTimedHighScore;

    Random moleGen = new Random();
    Button btnMainMenu;
    TextView scoreboard, countdownDialog, totalScoreResult;
    Dialog countdown, scoreResultDialog;
    Button[] buttons = new Button[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_untimed);

        scoreboard = findViewById(R.id.gameScore);

        buttons[0] = findViewById(R.id.hit1);
        buttons[1] = findViewById(R.id.hit2);
        buttons[2] = findViewById(R.id.hit3);
        buttons[3] = findViewById(R.id.hit4);
        buttons[4] = findViewById(R.id.hit5);
        buttons[5] = findViewById(R.id.hit6);
        buttons[6] = findViewById(R.id.hit7);
        buttons[7] = findViewById(R.id.hit8);
        buttons[8] = findViewById(R.id.hit9);
        buttons[9] = findViewById(R.id.hit10);

        countdown = new Dialog(this);
        countdown.requestWindowFeature(Window.FEATURE_NO_TITLE);
        countdown.setContentView(R.layout.countdown);
        countdown.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        countdown.setCancelable(true); //closes dialog
        countdown.show();
        countdown.setOnCancelListener(dialog -> {
            setTheMole();
        }); // starts game on dialog close

        scoreResultDialog = new Dialog(this);
        scoreResultDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        scoreResultDialog.setContentView(R.layout.score_result_dialog);
        scoreResultDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        scoreResultDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        scoreResultDialog.setCancelable(false); //closes dialog


        totalScoreResult = scoreResultDialog.findViewById(R.id.totalScoreResult);
        btnMainMenu = scoreResultDialog.findViewById(R.id.btnMainMenu);


        btnMainMenu.setOnClickListener(v -> {
            highScoreCheck = getIntent().getIntExtra("highScoreUntimed",0);
            lastTimedHighScore = getIntent().getIntExtra("highScoreTimed",0);
            Intent intent = new Intent(MainUntimedActivity.this,MenuActivity.class);
            if (highScoreCheck > totalScore) {
                intent.putExtra("scoreUntimed",highScoreCheck);
            }
            else {
                intent.putExtra("scoreUntimed",totalScore);
            }
            intent.putExtra("scoreTimed",lastTimedHighScore);
            startActivity(intent);
            finish();
        });

    }

//    public void StartCountdown(View v) {
//        countdownDialogTimer = 3;
//        countdownDialog.setText(String.valueOf(countdownDialogTimer));
//
//        final Handler handler = new Handler();
//        handler.postDelayed(() -> {
//            // Do something after 1s = 1000ms
//
//            //for 1-9 secs, display is "00" to "09"
//            if (countdownDialogTimer != -1) {
//                countdownDialogTimer--;
//                countdownDialog.setText(String.valueOf(countdownDialogTimer));
//            }
//            else {
//                StartCountdown(v);
//            }
//
//        }, 1000);
//
//        countdown.dismiss();
//    }

    public void ScoreAnimation() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            // Do something after 0.1s = 100ms

            if (totalScore == score) {
                btnMainMenu.setEnabled(true);
            }
            else {
                if (score >= 0) {
                    totalScore++;
                }
                else {
                    totalScore--;
                }
                totalScoreResult.setText(String.valueOf(totalScore));
                ScoreAnimation();
            }

        }, 100);
    }


    //hit method for each button
    private void setTheMole() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            // Do something every 1.5s = 1500ms
            int nextMole = moleGen.nextInt(9) + 1;

            //btn1.setTextColor(0xFF76FF03);
            buttons[nextMole].setTextColor(0xFFFFD54F);
            buttons[nextMole].setText("(✦‿✦)");

            for (int i=0;i<10;i++) {
                if (i == nextMole) {
                    buttons[i].setOnClickListener(v ->  {
                        MediaPlayer playBonk = MediaPlayer.create(MainUntimedActivity.this,R.raw.bonk);
                        buttons[nextMole].setBackgroundColor(0xFFFF6F00);
                        buttons[nextMole].setText("(>‿<)");
                        AddScore();
                        playBonk.start();
                    });
                }
                else {
                    int notMole = i;
                    System.out.println(notMole);
                    buttons[i].setOnClickListener(v -> {
                        MediaPlayer playError = MediaPlayer.create(MainUntimedActivity.this,R.raw.errorbonk);
                        buttons[notMole].setBackgroundColor(0xFFB71C1C);
                        buttons[notMole].setText("(#_#)");
                        playError.start();
                        scoreResultDialog.show();
                        ScoreAnimation();
                    });
                }
            }

            setTheMole();
            NoMoleState();
        }, 1300);
    }


    //reverts button to default state a bit after button click
    public void NoMoleState() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            // Do something after 1.45s = 1450ms

            for (int i=0;i<10;i++) {
                buttons[i].setBackgroundColor(0xFF311B92);
                buttons[i].setTextColor(Color.WHITE);
                buttons[i].setText("(O‿O)");
            }

        }, 1250);
    }


    public void AddScore() {
        score++;
        scoreboard.setText(String.valueOf(score));
    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        if (countdownDialogTimer == -1) {
//            //initialize timer and the mole
//
//        }
//
//    }
}