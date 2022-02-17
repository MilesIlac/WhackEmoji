package com.milesilac.whackamolu;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;


public class GameView {

    private int score;
    private int totalScore;
    private int countdownDialogTimer;
    private int getTimer;
    private int putTimer;
    private int highScoreCheck;
    private int putScoreTimed;
    private int putScoreUntimed;

    private Random moleGen = new Random();
    private Button btnMainMenu;
    private TextView scoreboard, timerView, countdownDialog, totalScoreResult;
    private Dialog countdown, scoreResultDialog;
    private Button[] buttons = new Button[10];

    private RelativeLayout menuLayout;
    boolean isUp;
    boolean isTimed;

    Context context;

    public GameView(int score, int totalScore, RelativeLayout menuLayout, boolean isUp, boolean isTimed, Context context, int getTimer) {
        this.score = score;
        this.totalScore = totalScore;
        this.menuLayout = menuLayout;
        this.isUp = isUp;
        this.isTimed = isTimed;
        this.context = context;
        this.getTimer = getTimer;
    }

    public void setGameView() {

        SharedPrefs.init(context.getApplicationContext());

        LayoutInflater gamePlay = LayoutInflater.from(context);
        View gameLayout = gamePlay.inflate(R.layout.activity_main,null);

        scoreboard = gameLayout.findViewById(R.id.gameScore);
        timerView = gameLayout.findViewById(R.id.timerView);
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

        //-- code for upper-right hand timer visibility
        if (isTimed) {
            timerView.setEnabled(true);
            timerView.setVisibility(View.VISIBLE);

            // manual set of timer
            putTimer = getTimer;
        }
        else {
            timerView.setEnabled(false);
            timerView.setVisibility(View.GONE);
        }
        //--

        menuLayout.addView(gameLayout, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);

        countdown = new Dialog(context);
        countdown.requestWindowFeature(Window.FEATURE_NO_TITLE);
        countdown.setContentView(R.layout.countdown);
        countdown.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        countdown.setCancelable(true); //closes dialog
        timerView.setText(getTimerText());
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


        //-- show scoreResult after play
        scoreResultDialog = new Dialog(context);
        scoreResultDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        scoreResultDialog.setContentView(R.layout.score_result_dialog);
        scoreResultDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        scoreResultDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        scoreResultDialog.setCancelable(false); //closes dialog

        totalScoreResult = scoreResultDialog.findViewById(R.id.totalScoreResult);
        btnMainMenu = scoreResultDialog.findViewById(R.id.btnMainMenu);

        //writes score to sharedprefs on click
        btnMainMenu.setOnClickListener(v1 -> {
            if (isTimed) {
                highScoreCheck = Integer.parseInt(SharedPrefs.read(SharedPrefs.HIGHSCORETIMED, "0"));
                putScoreTimed = Math.max(highScoreCheck, totalScore);
                SharedPrefs.write(SharedPrefs.HIGHSCORETIMED, String.valueOf(putScoreTimed));//save string in shared preference.
            }
            else {
                highScoreCheck = Integer.parseInt(SharedPrefs.read(SharedPrefs.HIGHSCOREUNTIMED, "0"));
                putScoreUntimed = Math.max(highScoreCheck, totalScore);
                SharedPrefs.write(SharedPrefs.HIGHSCOREUNTIMED, String.valueOf(putScoreUntimed));//save string in shared preference.
            }

            scoreResultDialog.dismiss();
            menuLayout.removeView(gameLayout);
            MenuActivity.btnQuickPlay.setEnabled(true);
            MenuActivity.btnCustomPlay.setEnabled(true);
            MenuActivity.btnScore.setEnabled(true);
            MenuActivity.btnCredits.setEnabled(true);
            MenuActivity.btnQuit.setEnabled(true);
            isUp = false;

            MenuActivity.highestScoreTimed.setText(SharedPrefs.read(SharedPrefs.HIGHSCORETIMED, "0"));
            MenuActivity.highestScoreUntimed.setText(SharedPrefs.read(SharedPrefs.HIGHSCOREUNTIMED, "0"));
            //--

        });

    } //GameView()


    //code for the countdown timer located at the upper right hand corner of the game
    public void TimerDigital() {
        if (isUp) {
            final Handler handler = new Handler();
            timerView.setText(getTimerText());
            handler.postDelayed(() -> {
                // Do something after 1s = 1000ms

                timerView.setText(getTimerText());
                putTimer--;

                //if seconds and minutes timers are both 0, timer will stop
                if (putTimer == -1) {
                    scoreResultDialog.show();
                    ScoreAnimation();
                }
                else {
                    TimerDigital();
                }

            }, 1000);
        }

    } //TimerDigital()
    private String getTimerText() {
        int rounded = Math.round(putTimer);

        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;

        return formatTime(seconds, minutes);
    }
    private String formatTime(int seconds, int minutes) {
        return String.format(Locale.getDefault(),"%02d",minutes) + ":" + String.format(Locale.getDefault(),"%02d",seconds);
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
                            MediaPlayer playBonk = MediaPlayer.create(context,R.raw.bonk);
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
                            MediaPlayer playError = MediaPlayer.create(context,R.raw.errorbonk);
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
                            MediaPlayer playBonk = MediaPlayer.create(context,R.raw.bonk);
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
                            MediaPlayer playError = MediaPlayer.create(context,R.raw.errorbonk);
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

    public void MinusScore() {
        score--;
        scoreboard.setText(String.valueOf(score));
    }


}
