package com.milesilac.whackamolu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.Random;

public class MenuActivity extends AppCompatActivity {

    private Button btnQuickPlay, btnCustomPlay, btn15sPlay, btn30sPlay, btn60sPlay, btnUntimedPlay, btnScore, btnCredits, btnQuit, btnCustomBack, btnGit;
    private Dialog menuCustomPlay, menuScore, menuCredits;
    private TextView highestScoreTimed, highestScoreUntimed;
    private String savedTimedScore, savedUntimedScore;

    public static String SHARED_PREFS = "sharedPrefs";
    public static String HIGHSCORETIMED = "highscoreTimed";
    public static String HIGHSCOREUNTIMED = "highscoreUntimed";

    private int score;
    private int totalScore;
    private int secondTimer;
    private int minuteTimer;
    private int countdownDialogTimer;
    private int getSecTimer;
    private int getMinTimer;
    private int highScoreCheck;
    private int putScoreTimed;
    private int putScoreUntimed;


    private Random moleGen = new Random();
    private Button btnMainMenu;
    private TextView scoreboard, timerSeconds, timerMinutes, timerDivider, countdownDialog, totalScoreResult;
    private Dialog countdown, scoreResultDialog;
    private Button[] buttons = new Button[10];

    private RelativeLayout menuLayout;
    boolean isUp = false;
    boolean isTimed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menuLayout = findViewById(R.id.menuLayout);

        btnQuickPlay = findViewById(R.id.menubtn1);

        btnQuickPlay.setOnClickListener(v -> {
            isUp = true;
            score = 0;
            totalScore = 0;
            getSecTimer = 15;
            getMinTimer = 0;
            btnQuickPlay.setEnabled(false);
            btnCustomPlay.setEnabled(false);
            btnScore.setEnabled(false);
            btnCredits.setEnabled(false);
            btnQuit.setEnabled(false);

            isTimed = true;

            GameView();
        });

        menuCustomPlay = new Dialog(this);
        menuCustomPlay.requestWindowFeature(Window.FEATURE_NO_TITLE);
        menuCustomPlay.setContentView(R.layout.custom_dialog);
        menuCustomPlay.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        menuCustomPlay.setCancelable(false);

        btnCustomPlay = findViewById(R.id.menubtn2);
        btn15sPlay = menuCustomPlay.findViewById(R.id.secs15);
        btn30sPlay = menuCustomPlay.findViewById(R.id.secs30);
        btn60sPlay = menuCustomPlay.findViewById(R.id.secs60);
        btnUntimedPlay = menuCustomPlay.findViewById(R.id.secsNull);
        btnCustomBack = menuCustomPlay.findViewById(R.id.btnBack);

        btnCustomPlay.setOnClickListener(v -> menuCustomPlay.show()); //show custom game menu

        btn15sPlay.setOnClickListener(v -> {
            menuCustomPlay.dismiss();
            isUp = true;
            score = 0;
            totalScore = 0;
            getSecTimer = 15;
            getMinTimer = 0;
            btnQuickPlay.setEnabled(false);
            btnCustomPlay.setEnabled(false);
            btnScore.setEnabled(false);
            btnCredits.setEnabled(false);
            btnQuit.setEnabled(false);

            isTimed = true;

            GameView();
        }); //choose 15 seconds of play

        btn30sPlay.setOnClickListener(v -> {
            menuCustomPlay.dismiss();
            isUp = true;
            score = 0;
            totalScore = 0;
            getSecTimer = 30;
            getMinTimer = 0;
            btnQuickPlay.setEnabled(false);
            btnCustomPlay.setEnabled(false);
            btnScore.setEnabled(false);
            btnCredits.setEnabled(false);
            btnQuit.setEnabled(false);

            isTimed = true;

            GameView();
        }); //choose 30 seconds of play

        btn60sPlay.setOnClickListener(v -> {
            menuCustomPlay.dismiss();
            isUp = true;
            score = 0;
            totalScore = 0;
            getSecTimer = 0;
            getMinTimer = 1;
            btnQuickPlay.setEnabled(false);
            btnCustomPlay.setEnabled(false);
            btnScore.setEnabled(false);
            btnCredits.setEnabled(false);
            btnQuit.setEnabled(false);

            isTimed = true;

            GameView();
        }); //choose 1 minute of play


        btnUntimedPlay.setOnClickListener(v -> {
            menuCustomPlay.dismiss();
            isUp = true;
            score = 0;
            totalScore = 0;
            btnQuickPlay.setEnabled(false);
            btnCustomPlay.setEnabled(false);
            btnScore.setEnabled(false);
            btnCredits.setEnabled(false);
            btnQuit.setEnabled(false);

            isTimed = false;

            GameView();
        }); //choose untimed play

        btnCustomBack.setOnClickListener(v -> menuCustomPlay.dismiss()); //close custom game menu

        isTimed = false;

        menuScore = new Dialog(this);
        menuScore.requestWindowFeature(Window.FEATURE_NO_TITLE);
        menuScore.setContentView(R.layout.scoreboard_dialog);
        menuScore.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        menuScore.setCancelable(false);

        btnScore = findViewById(R.id.menubtn3);
        highestScoreTimed = menuScore.findViewById(R.id.highestScoreTimed);
        highestScoreUntimed = menuScore.findViewById(R.id.highestScoreUntimed);
        btnCustomBack = menuScore.findViewById(R.id.btnBack);

        loadData();

        btnScore.setOnClickListener(v -> menuScore.show()); //show scoreboard
        btnCustomBack.setOnClickListener(v -> menuScore.dismiss()); //close scoreboard

        menuCredits = new Dialog(this);
        menuCredits.requestWindowFeature(Window.FEATURE_NO_TITLE);
        menuCredits.setContentView(R.layout.credits_dialog);
        menuCredits.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        menuCredits.setCancelable(false);

        btnCredits = findViewById(R.id.menubtn4);
        btnGit = menuCredits.findViewById(R.id.btnGit);
        btnCustomBack = menuCredits.findViewById(R.id.btnBack);

        btnCredits.setOnClickListener(v -> menuCredits.show()); //show credits

        //view Github in local browser
        btnGit.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/MilesIlac/whack-a-molu/tree/basic"));
            startActivity(browserIntent);
        });

        btnCustomBack.setOnClickListener(v -> menuCredits.dismiss()); //close credits


        btnQuit = findViewById(R.id.menubtn5);
        btnQuit.setOnClickListener(v -> finish());

    }


    public void GameView() {

        LayoutInflater gamePlay = getLayoutInflater();
        View gameLayout = gamePlay.inflate(R.layout.activity_main,null);

        scoreboard = gameLayout.findViewById(R.id.gameScore);
        timerSeconds = gameLayout.findViewById(R.id.timer2);
        timerMinutes = gameLayout.findViewById(R.id.timer1);
        timerDivider = gameLayout.findViewById(R.id.timerDivider);
        buttons[0] = gameLayout.findViewById(R.id.hit1);
        buttons[1] = gameLayout.findViewById(R.id.hit2);
        buttons[2] = gameLayout.findViewById(R.id.hit3);
        buttons[3] = gameLayout.findViewById(R.id.hit4);
        buttons[4] = gameLayout.findViewById(R.id.hit5);
        buttons[5] = gameLayout.findViewById(R.id.hit6);
        buttons[6] = gameLayout.findViewById(R.id.hit7);
        buttons[7] = gameLayout.findViewById(R.id.hit8);
        buttons[8] = gameLayout.findViewById(R.id.hit9);
        buttons[9] = gameLayout.findViewById(R.id.hit10);

        if (isTimed) {
            timerSeconds.setEnabled(true);
            timerSeconds.setVisibility(View.VISIBLE);
            timerDivider.setVisibility(View.VISIBLE);
            timerMinutes.setEnabled(true);
            timerMinutes.setVisibility(View.VISIBLE);
        }
        else {
            timerSeconds.setEnabled(false);
            timerSeconds.setVisibility(View.GONE);
            timerDivider.setVisibility(View.GONE);
            timerMinutes.setEnabled(false);
            timerMinutes.setVisibility(View.GONE);
        }

        if (isTimed) {
                //manual set of timer
                secondTimer = getSecTimer;
                minuteTimer = getMinTimer;

                //for 1-9 secs, display is "00" to "09"
                if (secondTimer >= 10) {
                    timerSeconds.setText(String.valueOf(secondTimer));
                }
                else {
                    String zeroSeconds = "0" + secondTimer;
                    timerSeconds.setText(zeroSeconds);
                }

                //for 1-9 mins, display is "00" to "09"
                if (minuteTimer >= 10) {
                    timerMinutes.setText(String.valueOf(minuteTimer));
                }
                else {
                    String zeroMinutes = "0" + minuteTimer;
                    timerMinutes.setText(zeroMinutes);
                }
            }


        menuLayout.addView(gameLayout,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);

        countdown = new Dialog(this);
        countdown.requestWindowFeature(Window.FEATURE_NO_TITLE);
        countdown.setContentView(R.layout.countdown);
        countdown.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        countdown.setCancelable(true); //closes dialog
        countdown.show();
        countdown.setOnCancelListener(dialog -> {
            if (isTimed) {
                TimerDigital();
                setTheMoleTimed();
            }
            else {
                setTheMoleUntimed();
            }
        }); // starts game on dialog close

        scoreResultDialog = new Dialog(this);
        scoreResultDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        scoreResultDialog.setContentView(R.layout.score_result_dialog);
        scoreResultDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        scoreResultDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        scoreResultDialog.setCancelable(false); //closes dialog

        totalScoreResult = scoreResultDialog.findViewById(R.id.totalScoreResult);
        btnMainMenu = scoreResultDialog.findViewById(R.id.btnMainMenu);

        btnMainMenu.setOnClickListener(v1 -> {
            if (isTimed) {
                highScoreCheck = Integer.parseInt(savedTimedScore);
                if (highScoreCheck > totalScore) {
                    putScoreTimed = highScoreCheck;
                    saveData();
                }
                else {
                    putScoreTimed = totalScore;
                    saveData();
                }
            }
            else {
                    highScoreCheck = Integer.parseInt(savedUntimedScore);
                    if (highScoreCheck > totalScore) {
                        putScoreUntimed = highScoreCheck;
                        saveData();
                    }
                    else {
                        putScoreUntimed = totalScore;
                        saveData();
                    }
                }


            scoreResultDialog.dismiss();
            menuLayout.removeView(gameLayout);
            btnQuickPlay.setEnabled(true);
            btnCustomPlay.setEnabled(true);
            btnScore.setEnabled(true);
            btnCredits.setEnabled(true);
            btnQuit.setEnabled(true);
            isUp = false;
            loadData();

        });

    }


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


    //code for the countdown timer located at the upper right hand corner of the game
    public void TimerDigital() {
        if (isUp) {
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
                    String zeroSeconds = "0" + secondTimer;
                    timerSeconds.setText(zeroSeconds);
                }

                //for 1-9 mins, display is "00" to "09"
                if (minuteTimer >= 10) {
                    timerMinutes.setText(String.valueOf(minuteTimer));
                }
                else {
                    String zeroMinutes = "0" + minuteTimer;
                    timerMinutes.setText(zeroMinutes);
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


    }


    //hit method for each button
    private void setTheMoleTimed() {
        if (isUp) {
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
                            MediaPlayer playBonk = MediaPlayer.create(MenuActivity.this,R.raw.bonk);
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
                            MediaPlayer playError = MediaPlayer.create(MenuActivity.this,R.raw.errorbonk);
                            buttons[notMole].setBackgroundColor(0xFFB71C1C);
                            buttons[notMole].setText("(#_#)");
                            MinusScore();
                            playError.start();
                        });
                    }
                }

                setTheMoleTimed();
                NoMoleState();
            }, 1300);
        }

    }

    private void setTheMoleUntimed() {
        if (isUp) {
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
                            MediaPlayer playBonk = MediaPlayer.create(MenuActivity.this,R.raw.bonk);
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
                            MediaPlayer playError = MediaPlayer.create(MenuActivity.this,R.raw.errorbonk);
                            buttons[notMole].setBackgroundColor(0xFFB71C1C);
                            buttons[notMole].setText("(#_#)");
                            playError.start();
                            scoreResultDialog.show();
                            ScoreAnimation();
                        });
                    }
                }

                setTheMoleUntimed();
                NoMoleState();
            }, 1300);
        }

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


    //scoring logic
    public void AddScore() {
        score++;
        scoreboard.setText(String.valueOf(score));
    }

    public void MinusScore() {
        score--;
        scoreboard.setText(String.valueOf(score));
    }

    //data persistence
    public void saveData() {
        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putString(HIGHSCORETIMED,String.valueOf(putScoreTimed));
        editor.putString(HIGHSCOREUNTIMED,String.valueOf(putScoreUntimed));

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);

        savedTimedScore = sharedPrefs.getString(HIGHSCORETIMED,"0");
        highestScoreTimed.setText(savedTimedScore);

        savedUntimedScore = sharedPrefs.getString(HIGHSCOREUNTIMED,"0");
        highestScoreUntimed.setText(savedUntimedScore);
    }

}