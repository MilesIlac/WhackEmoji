package com.milesilac.whackamolu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private Button btnQuickPlay, btnCustomPlay, btn15sPlay, btn30sPlay, btn60sPlay, btnUntimedPlay, btnScore, btnCredits, btnQuit, btnCustomBack, btnGit;
    private Dialog menuCustomPlay, menuScore, menuCredits;
    private TextView highestScore;
    private int highestScoreValue = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnQuickPlay = findViewById(R.id.menubtn1);

        btnQuickPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
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
            Intent intent = new Intent(MenuActivity.this,MainActivity.class);
            intent.putExtra("secs",15);
            intent.putExtra("mins",0);
            intent.putExtra("highScore",highestScoreValue);
            startActivity(intent);
            finish();
        }); //choose 15 seconds of play

        btn30sPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this,MainActivity.class);
            intent.putExtra("secs",30);
            intent.putExtra("mins",0);
            intent.putExtra("highScore",highestScoreValue);
            startActivity(intent);
            finish();
        }); //choose 30 seconds of play

        btn60sPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this,MainActivity.class);
            intent.putExtra("mins",1);
            intent.putExtra("secs",0);
            intent.putExtra("highScore",highestScoreValue);
            startActivity(intent);
            finish();
        }); //choose 1 minute of play

        //TODO Do btnUntimedPlay logic + create separate Activity

        btnCustomBack.setOnClickListener(v -> menuCustomPlay.dismiss()); //close custom game menu


        menuScore = new Dialog(this);
        menuScore.requestWindowFeature(Window.FEATURE_NO_TITLE);
        menuScore.setContentView(R.layout.scoreboard_dialog);
        menuScore.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        menuScore.setCancelable(false);

        btnScore = findViewById(R.id.menubtn3);
        highestScore = menuScore.findViewById(R.id.highestScore);
        btnCustomBack = menuScore.findViewById(R.id.btnBack);

        highestScoreValue = getIntent().getIntExtra("score",0);
        highestScore.setText(String.valueOf(highestScoreValue));

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
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/MilesIlac/whack-a-molu"));
            startActivity(browserIntent);
        });

        btnCustomBack.setOnClickListener(v -> menuCredits.dismiss()); //close credits


        btnQuit = findViewById(R.id.menubtn5);
        btnQuit.setOnClickListener(v -> finish());
    }
}