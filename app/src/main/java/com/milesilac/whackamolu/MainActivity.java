package com.milesilac.whackamolu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static String savedTimedScore;
    public static String savedUntimedScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        SharedPrefs.init(getApplicationContext());


        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, MenuFragment.class, null)
                    .setReorderingAllowed(true)
                    .commit();
        }


        fragmentManager.addFragmentOnAttachListener((fragmentManager1, fragment) -> {
            savedTimedScore = SharedPrefs.read(SharedPrefs.HIGHSCORETIMED, "0");//read string in shared preference.
            savedUntimedScore = SharedPrefs.read(SharedPrefs.HIGHSCOREUNTIMED, "0");//read string in shared preference.
        }); //fragmentManager.addFragmentOnAttachListener

    } //onCreate
}