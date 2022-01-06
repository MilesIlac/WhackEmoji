package com.milesilac.whackamolu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button btnQuickPlay, btnCustomPlay, btnScore, btnCredits, btnQuit, btnCustomConfirmPlay, btnCustomBack, btnGit;
    Dialog menuCustomPlay, menuScore, menuCredits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnQuickPlay = findViewById(R.id.menubtn1);

        btnQuickPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this,MainActivity.class);
            startActivity(intent);
        });


        //TODO create Custom dialog parameters logic (editText,radioGroup,etc)
        menuCustomPlay = new Dialog(this);
        menuCustomPlay.requestWindowFeature(Window.FEATURE_NO_TITLE);
        menuCustomPlay.setContentView(R.layout.custom_dialog);
        menuCustomPlay.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        menuCustomPlay.setCancelable(false);

        btnCustomPlay = findViewById(R.id.menubtn2);
        btnCustomConfirmPlay = menuCustomPlay.findViewById(R.id.btnPlay);
        btnCustomBack = menuCustomPlay.findViewById(R.id.btnBack);

        //show custom game menu
        btnCustomPlay.setOnClickListener(v -> menuCustomPlay.show());

        //TODO edit intent.putExtra();
        btnCustomConfirmPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this,MainActivity.class);
            //intent.putExtra();
            startActivity(intent);
        });

        //close custom game menu
        btnCustomBack.setOnClickListener(v -> menuCustomPlay.dismiss());


        //TODO create Scoreboard dialog, edit .setContentView
        menuScore = new Dialog(this);
        menuScore.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //menuCustomPlay.setContentView(R.layout.output);
        //menuCustomPlay.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_background));
        menuScore.setCancelable(false);

        btnScore = findViewById(R.id.menubtn3);


        menuCredits = new Dialog(this);
        menuCredits.requestWindowFeature(Window.FEATURE_NO_TITLE);
        menuCredits.setContentView(R.layout.credits_dialog);
        menuCredits.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        menuCredits.setCancelable(false);

        btnCredits = findViewById(R.id.menubtn4);
        btnGit = menuCredits.findViewById(R.id.btnGit);
        btnCustomBack = menuCredits.findViewById(R.id.btnBack);

        //show credits
        btnCredits.setOnClickListener(v -> menuCredits.show());

        //view Github in local browser
        btnGit.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/MilesIlac/whack-a-molu"));
            startActivity(browserIntent);
        });

        //close credits
        btnCustomBack.setOnClickListener(v -> menuCredits.dismiss());


        btnQuit = findViewById(R.id.menubtn5);
        btnQuit.setOnClickListener(v -> finish());
    }
}