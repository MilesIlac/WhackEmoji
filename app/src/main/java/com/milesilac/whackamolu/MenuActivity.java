package com.milesilac.whackamolu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MenuActivity extends AppCompatActivity {

    private Button btn15sPlay, btn30sPlay, btn60sPlay, btnUntimedPlay, btnCustomBack, btnGit;
    public static Button btnQuickPlay, btnCustomPlay, btnScore, btnCredits, btnQuit;
    private Dialog menuCustomPlay, menuScore, menuCredits;
    public static TextView highestScoreTimed, highestScoreUntimed;
    private int getTimer;

    private int score;
    private int totalScore;
    private int countdownDialogTimer;



    private RelativeLayout menuLayout;
    boolean isUp = false;
    boolean isTimed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        SharedPrefs.init(getApplicationContext());

        menuLayout = findViewById(R.id.menuLayout);

        btnQuickPlay = findViewById(R.id.menubtn1);

        btnQuickPlay.setOnClickListener(v -> {
            isUp = true;
            score = 0;
            totalScore = 0;
            getTimer = 15;
            btnQuickPlay.setEnabled(false);
            btnCustomPlay.setEnabled(false);
            btnScore.setEnabled(false);
            btnCredits.setEnabled(false);
            btnQuit.setEnabled(false);
            isTimed = true;

            GameView gameView = new GameView(score,totalScore,menuLayout,isUp,isTimed,MenuActivity.this,getTimer);
            gameView.setGameView();
        });

        //menuCustomPlay Dialog
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
            getTimer = 15;
            btnQuickPlay.setEnabled(false);
            btnCustomPlay.setEnabled(false);
            btnScore.setEnabled(false);
            btnCredits.setEnabled(false);
            btnQuit.setEnabled(false);
            isTimed = true;

            GameView gameView = new GameView(score,totalScore,menuLayout,isUp,isTimed,MenuActivity.this,getTimer);
            gameView.setGameView();
        }); //choose 15 seconds of play

        btn30sPlay.setOnClickListener(v -> {
            menuCustomPlay.dismiss();
            isUp = true;
            score = 0;
            totalScore = 0;
            getTimer = 30;
            btnQuickPlay.setEnabled(false);
            btnCustomPlay.setEnabled(false);
            btnScore.setEnabled(false);
            btnCredits.setEnabled(false);
            btnQuit.setEnabled(false);
            isTimed = true;

            GameView gameView = new GameView(score,totalScore,menuLayout,isUp,isTimed,MenuActivity.this,getTimer);
            gameView.setGameView();
        }); //choose 30 seconds of play

        btn60sPlay.setOnClickListener(v -> {
            menuCustomPlay.dismiss();
            isUp = true;
            score = 0;
            totalScore = 0;
            getTimer = 60;
            btnQuickPlay.setEnabled(false);
            btnCustomPlay.setEnabled(false);
            btnScore.setEnabled(false);
            btnCredits.setEnabled(false);
            btnQuit.setEnabled(false);
            isTimed = true;

            GameView gameView = new GameView(score,totalScore,menuLayout,isUp,isTimed,MenuActivity.this,getTimer);
            gameView.setGameView();
        }); //choose 1 minute of play

        btnUntimedPlay.setOnClickListener(v -> {
            menuCustomPlay.dismiss();
            isUp = true;
            score = 0;
            totalScore = 0;
            getTimer = 0;
            btnQuickPlay.setEnabled(false);
            btnCustomPlay.setEnabled(false);
            btnScore.setEnabled(false);
            btnCredits.setEnabled(false);
            btnQuit.setEnabled(false);
            isTimed = false;

            GameView gameView = new GameView(score,totalScore,menuLayout,isUp,isTimed,MenuActivity.this,getTimer);
            gameView.setGameView();
        }); //choose untimed play

        btnCustomBack.setOnClickListener(v -> menuCustomPlay.dismiss()); //close custom game menu

        isTimed = false;

        //menuScore Dialog
        menuScore = new Dialog(this);
        menuScore.requestWindowFeature(Window.FEATURE_NO_TITLE);
        menuScore.setContentView(R.layout.scoreboard_dialog);
        menuScore.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        menuScore.setCancelable(false);

        btnScore = findViewById(R.id.menubtn3);
        highestScoreTimed = menuScore.findViewById(R.id.highestScoreTimed);
        highestScoreUntimed = menuScore.findViewById(R.id.highestScoreUntimed);
        btnCustomBack = menuScore.findViewById(R.id.btnBack);

        //-- sharedPrefs
        String savedTimedScore = SharedPrefs.read(SharedPrefs.HIGHSCORETIMED, "0");//read string in shared preference.
        String savedUntimedScore = SharedPrefs.read(SharedPrefs.HIGHSCOREUNTIMED, "0");//read string in shared preference.
        highestScoreTimed.setText(savedTimedScore);
        highestScoreUntimed.setText(savedUntimedScore);
        //--

        btnScore.setOnClickListener(v -> menuScore.show()); //show scoreboard
        btnCustomBack.setOnClickListener(v -> menuScore.dismiss()); //close scoreboard

        //menuCredits Dialog
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

    } //OnCreate
}