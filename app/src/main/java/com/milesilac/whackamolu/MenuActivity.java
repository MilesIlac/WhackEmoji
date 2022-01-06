package com.milesilac.whackamolu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button btnQuickPlay, btnCustomPlay, btnScore, btnCredits, btnQuit, btnCustomConfirmPlay, btnCustomBack;
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

        btnCustomPlay.setOnClickListener(v -> menuCustomPlay.show());

        //TODO edit intent.putExtra();
        btnCustomConfirmPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this,MainActivity.class);
            //intent.putExtra();
            startActivity(intent);
        });

        btnCustomBack.setOnClickListener(v -> menuCustomPlay.dismiss());


        //TODO create Scoreboard dialog, edit .setContentView
        menuScore = new Dialog(this);
        menuScore.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //menuCustomPlay.setContentView(R.layout.output);
        //menuCustomPlay.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_background));
        menuScore.setCancelable(false);

        btnScore = menuScore.findViewById(R.id.menubtn3);

        //TODO create Credits dialog, edit .setContentView
        menuCredits = new Dialog(this);
        menuCredits.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //menuCustomPlay.setContentView(R.layout.output);
        //menuCustomPlay.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_background));
        menuCredits.setCancelable(false);

        btnCredits = menuCredits.findViewById(R.id.menubtn4);

        btnQuit = findViewById(R.id.menubtn5);
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}