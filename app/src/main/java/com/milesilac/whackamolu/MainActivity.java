package com.milesilac.whackamolu;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.os.Handler;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int score;
    int isMole;
    int secondTimer;
    int minuteTimer;

    Random moleGen = new Random();
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button10;
    TextView scoreboard, timerSeconds, timerMinutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreboard = findViewById(R.id.gameScore);
        timerSeconds = findViewById(R.id.timer2);
        timerMinutes = findViewById(R.id.timer1);

        //manual set of timer
        secondTimer = 0;
        minuteTimer = 2;

        button1 = findViewById(R.id.hit1);
        button2 = findViewById(R.id.hit2);
        button3 = findViewById(R.id.hit3);
        button4 = findViewById(R.id.hit4);
        button5 = findViewById(R.id.hit5);
        button6 = findViewById(R.id.hit6);
        button7 = findViewById(R.id.hit7);
        button8 = findViewById(R.id.hit8);
        button9 = findViewById(R.id.hit9);
        button10 = findViewById(R.id.hit10);

        //initialize timer and the mole
        TimerDigital();
        setTheMole();

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

            if (minuteTimer >= 10) {
                timerMinutes.setText(String.valueOf(minuteTimer));
            }
            else {
                timerMinutes.setText("0" + minuteTimer);
            }

            if (secondTimer == 0 && minuteTimer == 0) {
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
            button1.setBackgroundColor(0xFF311B92);
            button2.setBackgroundColor(0xFF311B92);
            button3.setBackgroundColor(0xFF311B92);
            button4.setBackgroundColor(0xFF311B92);
            button5.setBackgroundColor(0xFF311B92);
            button6.setBackgroundColor(0xFF311B92);
            button7.setBackgroundColor(0xFF311B92);
            button8.setBackgroundColor(0xFF311B92);
            button9.setBackgroundColor(0xFF311B92);
            button10.setBackgroundColor(0xFF311B92);

        }, 500);
    }


    //hit method for each button
    public void hit1(View v) {
        MediaPlayer playBonk = MediaPlayer.create(this,R.raw.bonk);
        MediaPlayer playError = MediaPlayer.create(this,R.raw.errorbonk);
        if (isMole == 1) {
            button1.setBackgroundColor(0xFFFF6F00);
            button1.setText("(>‿<)");
            AddScore();
            playBonk.start();
        }
        else {
            button1.setBackgroundColor(0xFFB71C1C);
            button1.setText("(#_#)");
            MinusScore();
            playError.start();
        }
        InitialState();
    }

    public void hit2(View v) {
        MediaPlayer playBonk = MediaPlayer.create(this,R.raw.bonk);
        MediaPlayer playError = MediaPlayer.create(this,R.raw.errorbonk);
        if (isMole == 2) {
            button2.setBackgroundColor(0xFFFF6F00);
            button2.setText("(>‿<)");
            AddScore();
            playBonk.start();
        }
        else {
            button2.setBackgroundColor(0xFFB71C1C);
            button2.setText("(#_#)");
            MinusScore();
            playError.start();
        }
        InitialState();
    }

    public void hit3(View v) {
        MediaPlayer playBonk = MediaPlayer.create(this,R.raw.bonk);
        MediaPlayer playError = MediaPlayer.create(this,R.raw.errorbonk);
        if (isMole == 3) {
            button3.setBackgroundColor(0xFFFF6F00);
            button3.setText("(>‿<)");
            AddScore();
            playBonk.start();

        }
        else {
            button3.setBackgroundColor(0xFFB71C1C);
            button3.setText("(#_#)");
            MinusScore();
            playError.start();
        }
        InitialState();
    }

    public void hit4(View v) {
        MediaPlayer playBonk = MediaPlayer.create(this,R.raw.bonk);
        MediaPlayer playError = MediaPlayer.create(this,R.raw.errorbonk);
        if (isMole == 4) {
            button4.setBackgroundColor(0xFFFF6F00);
            button4.setText("(>‿<)");
            AddScore();
            playBonk.start();
        }
        else {
            button4.setBackgroundColor(0xFFB71C1C);
            button4.setText("(#_#)");
            MinusScore();
            playError.start();
        }
        InitialState();
    }

    public void hit5(View v) {
        MediaPlayer playBonk = MediaPlayer.create(this,R.raw.bonk);
        MediaPlayer playError = MediaPlayer.create(this,R.raw.errorbonk);
        if (isMole == 5) {
            button5.setBackgroundColor(0xFFFF6F00);
            button5.setText("(>‿<)");
            AddScore();
            playBonk.start();
        }
        else {
            button5.setBackgroundColor(0xFFB71C1C);
            button5.setText("(#_#)");
            MinusScore();
            playError.start();
        }
        InitialState();
    }

    public void hit6(View v) {
        MediaPlayer playBonk = MediaPlayer.create(this,R.raw.bonk);
        MediaPlayer playError = MediaPlayer.create(this,R.raw.errorbonk);
        if (isMole == 6) {
            button6.setBackgroundColor(0xFFFF6F00);
            button6.setText("(>‿<)");
            AddScore();
            playBonk.start();
        }
        else {
            button6.setBackgroundColor(0xFFB71C1C);
            button6.setText("(#_#)");
            MinusScore();
            playError.start();
        }
        InitialState();
    }

    public void hit7(View v) {
        MediaPlayer playBonk = MediaPlayer.create(this,R.raw.bonk);
        MediaPlayer playError = MediaPlayer.create(this,R.raw.errorbonk);
        if (isMole == 7) {
            button7.setBackgroundColor(0xFFFF6F00);
            button7.setText("(>‿<)");
            AddScore();
            playBonk.start();
        }
        else {
            button7.setBackgroundColor(0xFFB71C1C);
            button7.setText("(#_#)");
            MinusScore();
            playError.start();
        }
        InitialState();
    }

    public void hit8(View v) {
        MediaPlayer playBonk = MediaPlayer.create(this,R.raw.bonk);
        MediaPlayer playError = MediaPlayer.create(this,R.raw.errorbonk);
        if (isMole == 8) {
            button8.setBackgroundColor(0xFFFF6F00);
            button8.setText("(>‿<)");
            AddScore();
            playBonk.start();
        }
        else {
            button8.setBackgroundColor(0xFFB71C1C);
            button8.setText("(#_#)");
            MinusScore();
            playError.start();
        }
        InitialState();
    }

    public void hit9(View v) {
        MediaPlayer playBonk = MediaPlayer.create(this,R.raw.bonk);
        MediaPlayer playError = MediaPlayer.create(this,R.raw.errorbonk);
        if (isMole == 9) {
            button9.setBackgroundColor(0xFFFF6F00);
            button9.setText("(>‿<)");
            AddScore();
            playBonk.start();
        }
        else {
            button9.setBackgroundColor(0xFFB71C1C);
            button9.setText("(#_#)");
            MinusScore();
            playError.start();
        }
        InitialState();
    }

    public void hit10(View v) {
        MediaPlayer playBonk = MediaPlayer.create(this,R.raw.bonk);
        MediaPlayer playError = MediaPlayer.create(this,R.raw.errorbonk);
        if (isMole == 10) {
            button10.setBackgroundColor(0xFFFF6F00);
            button10.setText("(>‿<)");
            AddScore();
            playBonk.start();
        }
        else {
            button10.setBackgroundColor(0xFFB71C1C);
            button10.setText("(#_#)");
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
                    button1.setTextColor(0xFF76FF03);
                    break;
                case 2:
                    isMole = 2;
                    button2.setTextColor(0xFF76FF03);
                    break;
                case 3:
                    isMole = 3;
                    button3.setTextColor(0xFF76FF03);
                    break;
                case 4:
                    isMole = 4;
                    button4.setTextColor(0xFF76FF03);
                    break;
                case 5:
                    isMole = 5;
                    button5.setTextColor(0xFF76FF03);
                    break;
                case 6:
                    isMole = 6;
                    button6.setTextColor(0xFF76FF03);
                    break;
                case 7:
                    isMole = 7;
                    button7.setTextColor(0xFF76FF03);
                    break;
                case 8:
                    isMole = 8;
                    button8.setTextColor(0xFF76FF03);
                    break;
                case 9:
                    isMole = 9;
                    button9.setTextColor(0xFF76FF03);
                    break;
                case 10:
                    isMole = 10;
                    button10.setTextColor(0xFF76FF03);
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
            button1.setTextColor(Color.WHITE);
            button1.setText("(✦‿✦)");
            button2.setTextColor(Color.WHITE);
            button2.setText("(✦‿✦)");
            button3.setTextColor(Color.WHITE);
            button3.setText("(✦‿✦)");
            button4.setTextColor(Color.WHITE);
            button4.setText("(✦‿✦)");
            button5.setTextColor(Color.WHITE);
            button5.setText("(✦‿✦)");
            button6.setTextColor(Color.WHITE);
            button6.setText("(✦‿✦)");
            button7.setTextColor(Color.WHITE);
            button7.setText("(✦‿✦)");
            button8.setTextColor(Color.WHITE);
            button8.setText("(✦‿✦)");
            button9.setTextColor(Color.WHITE);
            button9.setText("(✦‿✦)");
            button10.setTextColor(Color.WHITE);
            button10.setText("(✦‿✦)");

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

}