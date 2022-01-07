package com.milesilac.whackamolu;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;

import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.os.Handler;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int score = 0;
    private int totalScore = 0;
    private int isMole;
    private int secondTimer;
    private int minuteTimer;
    private int countdownDialogTimer;

    private RelativeLayout scoreResultDialogLayout;

    Random moleGen = new Random();
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btnMainMenu;
    TextView scoreboard, timerSeconds, timerMinutes, countdownDialog, totalScoreResult, thanks4Playing;
    EditText playerInputName;
    Dialog countdown, scoreResultDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreboard = findViewById(R.id.gameScore);
        timerSeconds = findViewById(R.id.timer2);
        timerMinutes = findViewById(R.id.timer1);

        //manual set of timer
        secondTimer = 15;
        minuteTimer = 0;

        //for 1-9 secs, display is "00" to "09"
        if (secondTimer >= 10) {
            timerSeconds.setText(String.valueOf(secondTimer));
        }
        else {
            timerSeconds.setText("0" + secondTimer);
        }

        //for 1-9 mins, display is "00" to "09"
        if (minuteTimer >= 10) {
            timerMinutes.setText(String.valueOf(minuteTimer));
        }
        else {
            timerMinutes.setText("0" + minuteTimer);
        }

        btn1 = findViewById(R.id.hit1);
        btn2 = findViewById(R.id.hit2);
        btn3 = findViewById(R.id.hit3);
        btn4 = findViewById(R.id.hit4);
        btn5 = findViewById(R.id.hit5);
        btn6 = findViewById(R.id.hit6);
        btn7 = findViewById(R.id.hit7);
        btn8 = findViewById(R.id.hit8);
        btn9 = findViewById(R.id.hit9);
        btn10 = findViewById(R.id.hit10);

        countdown = new Dialog(this);
        countdown.requestWindowFeature(Window.FEATURE_NO_TITLE);
        countdown.setContentView(R.layout.countdown);
        countdown.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        countdown.setCancelable(true); //closes dialog
        countdown.show();
        countdown.setOnCancelListener(dialog -> {
            TimerDigital();
            setTheMole();
        }); // starts game on dialog close

        scoreResultDialog = new Dialog(this);
        scoreResultDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        scoreResultDialog.setContentView(R.layout.score_result_dialog);
        scoreResultDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        scoreResultDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        scoreResultDialog.setCancelable(false); //closes dialog


        totalScoreResult = scoreResultDialog.findViewById(R.id.totalScoreResult);
        playerInputName = scoreResultDialog.findViewById(R.id.playerInputName);
        thanks4Playing = scoreResultDialog.findViewById(R.id.thanks4Playing);
        btnMainMenu = scoreResultDialog.findViewById(R.id.btnMainMenu);
        scoreResultDialogLayout = scoreResultDialog.findViewById(R.id.scoreResultDialogLayout);


        btnMainMenu.setOnClickListener(v -> {
            if (playerInputName.getText().toString().isEmpty()) {
                Snackbar.make(scoreResultDialogLayout,"Please input a name",Snackbar.LENGTH_INDEFINITE)
                        .setAction("BACK", v1 -> {

                        })
                        .show();
            }
            else {
                Intent intent = new Intent(MainActivity.this,MenuActivity.class);
                //intent.putExtra();
                startActivity(intent);
                finish();
            }
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
                playerInputName.setVisibility(View.VISIBLE);
                thanks4Playing.setVisibility(View.VISIBLE);
                btnMainMenu.setVisibility(View.VISIBLE);
            }
            else {
                totalScore++;
                totalScoreResult.setText(String.valueOf(totalScore));
                ScoreAnimation();
            }

        }, 100);
    }



    //code for the countdown timer located at the upper right hand corner of the game
    public void TimerDigital() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            // Do something after 1s = 1000ms

            //every 60 secs, subtract 1 from mins
            if (secondTimer == 0 && minuteTimer != 0) {
                secondTimer = 60;
                minuteTimer--;

            }
            secondTimer--;

            //for 1-9 secs, display is "00" to "09"
            if (secondTimer >= 10) {
                timerSeconds.setText(String.valueOf(secondTimer));
            }
            else {
                timerSeconds.setText("0" + secondTimer);
            }

            //for 1-9 mins, display is "00" to "09"
            if (minuteTimer >= 10) {
                timerMinutes.setText(String.valueOf(minuteTimer));
            }
            else {
                timerMinutes.setText("0" + minuteTimer);
            }

            //if seconds and minutes timers are both 0, timer will stop
            if (secondTimer == 0 && minuteTimer == 0) {
                scoreResultDialog.show();
                ScoreAnimation();
            }
            else {
                TimerDigital();
            }

        }, 1000);

    }


    //helps revert to default color state
    public void InitialState() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            // Do something after 0.5s = 500ms
            btn1.setBackgroundColor(0xFF311B92);
            btn2.setBackgroundColor(0xFF311B92);
            btn3.setBackgroundColor(0xFF311B92);
            btn4.setBackgroundColor(0xFF311B92);
            btn5.setBackgroundColor(0xFF311B92);
            btn6.setBackgroundColor(0xFF311B92);
            btn7.setBackgroundColor(0xFF311B92);
            btn8.setBackgroundColor(0xFF311B92);
            btn9.setBackgroundColor(0xFF311B92);
            btn10.setBackgroundColor(0xFF311B92);

        }, 500);
    }


    //hit method for each button
    public void hit1(View v) {
        MediaPlayer playBonk = MediaPlayer.create(this,R.raw.bonk);
        MediaPlayer playError = MediaPlayer.create(this,R.raw.errorbonk);
        if (isMole == 1) {
            btn1.setBackgroundColor(0xFFFF6F00);
            btn1.setText("(>‿<)");
            AddScore();
            playBonk.start();
        }
        else {
            btn1.setBackgroundColor(0xFFB71C1C);
            btn1.setText("(#_#)");
            MinusScore();
            playError.start();
        }
        InitialState();
    }

    public void hit2(View v) {
        MediaPlayer playBonk = MediaPlayer.create(this,R.raw.bonk);
        MediaPlayer playError = MediaPlayer.create(this,R.raw.errorbonk);
        if (isMole == 2) {
            btn2.setBackgroundColor(0xFFFF6F00);
            btn2.setText("(>‿<)");
            AddScore();
            playBonk.start();
        }
        else {
            btn2.setBackgroundColor(0xFFB71C1C);
            btn2.setText("(#_#)");
            MinusScore();
            playError.start();
        }
        InitialState();
    }

    public void hit3(View v) {
        MediaPlayer playBonk = MediaPlayer.create(this,R.raw.bonk);
        MediaPlayer playError = MediaPlayer.create(this,R.raw.errorbonk);
        if (isMole == 3) {
            btn3.setBackgroundColor(0xFFFF6F00);
            btn3.setText("(>‿<)");
            AddScore();
            playBonk.start();

        }
        else {
            btn3.setBackgroundColor(0xFFB71C1C);
            btn3.setText("(#_#)");
            MinusScore();
            playError.start();
        }
        InitialState();
    }

    public void hit4(View v) {
        MediaPlayer playBonk = MediaPlayer.create(this,R.raw.bonk);
        MediaPlayer playError = MediaPlayer.create(this,R.raw.errorbonk);
        if (isMole == 4) {
            btn4.setBackgroundColor(0xFFFF6F00);
            btn4.setText("(>‿<)");
            AddScore();
            playBonk.start();
        }
        else {
            btn4.setBackgroundColor(0xFFB71C1C);
            btn4.setText("(#_#)");
            MinusScore();
            playError.start();
        }
        InitialState();
    }

    public void hit5(View v) {
        MediaPlayer playBonk = MediaPlayer.create(this,R.raw.bonk);
        MediaPlayer playError = MediaPlayer.create(this,R.raw.errorbonk);
        if (isMole == 5) {
            btn5.setBackgroundColor(0xFFFF6F00);
            btn5.setText("(>‿<)");
            AddScore();
            playBonk.start();
        }
        else {
            btn5.setBackgroundColor(0xFFB71C1C);
            btn5.setText("(#_#)");
            MinusScore();
            playError.start();
        }
        InitialState();
    }

    public void hit6(View v) {
        MediaPlayer playBonk = MediaPlayer.create(this,R.raw.bonk);
        MediaPlayer playError = MediaPlayer.create(this,R.raw.errorbonk);
        if (isMole == 6) {
            btn6.setBackgroundColor(0xFFFF6F00);
            btn6.setText("(>‿<)");
            AddScore();
            playBonk.start();
        }
        else {
            btn6.setBackgroundColor(0xFFB71C1C);
            btn6.setText("(#_#)");
            MinusScore();
            playError.start();
        }
        InitialState();
    }

    public void hit7(View v) {
        MediaPlayer playBonk = MediaPlayer.create(this,R.raw.bonk);
        MediaPlayer playError = MediaPlayer.create(this,R.raw.errorbonk);
        if (isMole == 7) {
            btn7.setBackgroundColor(0xFFFF6F00);
            btn7.setText("(>‿<)");
            AddScore();
            playBonk.start();
        }
        else {
            btn7.setBackgroundColor(0xFFB71C1C);
            btn7.setText("(#_#)");
            MinusScore();
            playError.start();
        }
        InitialState();
    }

    public void hit8(View v) {
        MediaPlayer playBonk = MediaPlayer.create(this,R.raw.bonk);
        MediaPlayer playError = MediaPlayer.create(this,R.raw.errorbonk);
        if (isMole == 8) {
            btn8.setBackgroundColor(0xFFFF6F00);
            btn8.setText("(>‿<)");
            AddScore();
            playBonk.start();
        }
        else {
            btn8.setBackgroundColor(0xFFB71C1C);
            btn8.setText("(#_#)");
            MinusScore();
            playError.start();
        }
        InitialState();
    }

    public void hit9(View v) {
        MediaPlayer playBonk = MediaPlayer.create(this,R.raw.bonk);
        MediaPlayer playError = MediaPlayer.create(this,R.raw.errorbonk);
        if (isMole == 9) {
            btn9.setBackgroundColor(0xFFFF6F00);
            btn9.setText("(>‿<)");
            AddScore();
            playBonk.start();
        }
        else {
            btn9.setBackgroundColor(0xFFB71C1C);
            btn9.setText("(#_#)");
            MinusScore();
            playError.start();
        }
        InitialState();
    }

    public void hit10(View v) {
        MediaPlayer playBonk = MediaPlayer.create(this,R.raw.bonk);
        MediaPlayer playError = MediaPlayer.create(this,R.raw.errorbonk);
        if (isMole == 10) {
            btn10.setBackgroundColor(0xFFFF6F00);
            btn10.setText("(>‿<)");
            AddScore();
            playBonk.start();
        }
        else {
            btn10.setBackgroundColor(0xFFB71C1C);
            btn10.setText("(#_#)");
            MinusScore();
            playError.start();
        }
        InitialState();
    }


    private void setTheMole() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            // Do something every 1.5s = 1500ms
            int nextMole = moleGen.nextInt(10) + 1;
            switch (nextMole) {
                case 1:
                    isMole = 1;
                    //btn1.setTextColor(0xFF76FF03);
                    btn1.setTextColor(0xFFFFD54F);
                    btn1.setText("(✦‿✦)");
                    break;
                case 2:
                    isMole = 2;
                    //btn2.setTextColor(0xFF76FF03);
                    btn2.setTextColor(0xFFFFD54F);
                    btn2.setText("(✦‿✦)");
                    break;
                case 3:
                    isMole = 3;
                    //btn3.setTextColor(0xFF76FF03);
                    btn3.setTextColor(0xFFFFD54F);
                    btn3.setText("(✦‿✦)");
                    break;
                case 4:
                    isMole = 4;
                    //btn4.setTextColor(0xFF76FF03);
                    btn4.setTextColor(0xFFFFD54F);
                    btn4.setText("(✦‿✦)");
                    break;
                case 5:
                    isMole = 5;
                    //btn5.setTextColor(0xFF76FF03);
                    btn5.setTextColor(0xFFFFD54F);
                    btn5.setText("(✦‿✦)");
                    break;
                case 6:
                    isMole = 6;
                    //btn6.setTextColor(0xFF76FF03);
                    btn6.setTextColor(0xFFFFD54F);
                    btn6.setText("(✦‿✦)");
                    break;
                case 7:
                    isMole = 7;
                    //btn7.setTextColor(0xFF76FF03);
                    btn7.setTextColor(0xFFFFD54F);
                    btn7.setText("(✦‿✦)");
                    break;
                case 8:
                    isMole = 8;
                    //btn8.setTextColor(0xFF76FF03);
                    btn8.setTextColor(0xFFFFD54F);
                    btn8.setText("(✦‿✦)");
                    break;
                case 9:
                    isMole = 9;
                    //btn9.setTextColor(0xFF76FF03);
                    btn9.setTextColor(0xFFFFD54F);
                    btn9.setText("(✦‿✦)");
                    break;
                case 10:
                    isMole = 10;
                    //btn10.setTextColor(0xFF76FF03);
                    btn10.setTextColor(0xFFFFD54F);
                    btn10.setText("(✦‿✦)");
                    break;
            }

            setTheMole();
            NoMoleState();
        }, 1500);
    }


    //reverts button to default state a bit after button click
    public void NoMoleState() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            // Do something after 1.45s = 1450ms
            btn1.setTextColor(Color.WHITE);
            btn1.setText("(O‿O)");
            btn2.setTextColor(Color.WHITE);
            btn2.setText("(O‿O)");
            btn3.setTextColor(Color.WHITE);
            btn3.setText("(O‿O)");
            btn4.setTextColor(Color.WHITE);
            btn4.setText("(O‿O)");
            btn5.setTextColor(Color.WHITE);
            btn5.setText("(O‿O)");
            btn6.setTextColor(Color.WHITE);
            btn6.setText("(O‿O)");
            btn7.setTextColor(Color.WHITE);
            btn7.setText("(O‿O)");
            btn8.setTextColor(Color.WHITE);
            btn8.setText("(O‿O)");
            btn9.setTextColor(Color.WHITE);
            btn9.setText("(O‿O)");
            btn10.setTextColor(Color.WHITE);
            btn10.setText("(O‿O)");

        }, 1450);
    }


    public void AddScore() {
        score++;
        scoreboard.setText(String.valueOf(score));
    }

    public void MinusScore() {
        score--;
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