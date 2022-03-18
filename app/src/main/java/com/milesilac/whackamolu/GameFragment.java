package com.milesilac.whackamolu;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String IS_UP = "isUp";
    private static final String IS_TIMED = "isTimed";
    private static final String SET_TIMER = "setTimer";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int score;
    private int getTimer;
    private int putTimer;
    private int highScoreCheck;
    private int putScoreTimed;
    private int putScoreUntimed;

    private final Random moleGen = new Random();
    private final Random delayGen = new Random();
    private Button btnMainMenu;
    private TextView scoreboard, timerView, countdownDialog, totalScoreResult;
    private Dialog scoreResultDialog;
    private final Button[] buttons = new Button[10];

    boolean isUp;
    boolean isTimed;


    public GameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameFragment newInstance(String param1, String param2) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            isTimed = getArguments().getBoolean(IS_TIMED);
            getTimer = getArguments().getInt(SET_TIMER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentManager fragmentManager = getParentFragmentManager();

        scoreboard = requireView().findViewById(R.id.gameScore);
        timerView = requireView().findViewById(R.id.timerView);
        buttons[0] = requireView().findViewById(R.id.hit1);
        buttons[1] = requireView().findViewById(R.id.hit2);
        buttons[2] = requireView().findViewById(R.id.hit3);
        buttons[3] = requireView().findViewById(R.id.hit4);
        buttons[4] = requireView().findViewById(R.id.hit5);
        buttons[5] = requireView().findViewById(R.id.hit6);
        buttons[6] = requireView().findViewById(R.id.hit7);
        buttons[7] = requireView().findViewById(R.id.hit8);
        buttons[8] = requireView().findViewById(R.id.hit9);
        buttons[9] = requireView().findViewById(R.id.hit10);


        isUp = true;
        timerView.setText(getTimerText());


        //-- code for upper-right hand timer visibility
        if (isTimed) {
            timerView.setEnabled(true);
            timerView.setVisibility(View.VISIBLE);
        }
        else {
            timerView.setEnabled(false);
            timerView.setVisibility(View.GONE);
        }

        moleClick();

        //-- show scoreResult after play
        scoreResultDialog = new Dialog(getContext());
        scoreResultDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        scoreResultDialog.setContentView(R.layout.score_result_dialog);
        scoreResultDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        scoreResultDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scoreResultDialog.setCancelable(false); //closes dialog

        totalScoreResult = scoreResultDialog.findViewById(R.id.totalScoreResult);
        btnMainMenu = scoreResultDialog.findViewById(R.id.btnMainMenu);

        //writes score to sharedprefs on click
        btnMainMenu.setOnClickListener(v1 -> {
            if (isTimed) {
                highScoreCheck = Integer.parseInt(SharedPrefs.read(SharedPrefs.HIGHSCORETIMED, "0"));
                putScoreTimed = Math.max(highScoreCheck, score);
                SharedPrefs.write(SharedPrefs.HIGHSCORETIMED, String.valueOf(putScoreTimed));//save string in shared preference.
            } else {
                highScoreCheck = Integer.parseInt(SharedPrefs.read(SharedPrefs.HIGHSCOREUNTIMED, "0"));
                putScoreUntimed = Math.max(highScoreCheck, score);
                SharedPrefs.write(SharedPrefs.HIGHSCOREUNTIMED, String.valueOf(putScoreUntimed));//save string in shared preference.
            }

            scoreResultDialog.dismiss();

            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, MenuFragment.class, null)
                    .setReorderingAllowed(true)
                    .commit();

        });
    } //onViewCreated

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("remainder", getSeconds());
        savedInstanceState.putInt("score",score);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState == null) {
            putTimer = getTimer;
        }
        else {
            putTimer = savedInstanceState.getInt("remainder");
            score = savedInstanceState.getInt("score");
            scoreboard.setText(String.valueOf(score));
        }

        timerView.setText(getTimerText());
    }

    @Override
    public void onPause() {
        super.onPause();
        isUp = false;
    }

    @Override
    public void onResume() {
        super.onResume();

        Dialog countdown = new Dialog(getContext());
        countdown.requestWindowFeature(Window.FEATURE_NO_TITLE);
        countdown.setContentView(R.layout.countdown);
        countdown.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        countdown.setCancelable(true); //closes dialog
        countdown.show();

        countdown.setOnDismissListener(dialog -> {

            Dialog countdownAnimationDialog = new Dialog(getContext());
            countdownAnimationDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            countdownAnimationDialog.setContentView(R.layout.countdown);
            countdownAnimationDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            countdownAnimationDialog.setCancelable(false);
            countdownAnimationDialog.setCanceledOnTouchOutside(false);
            countdownAnimationDialog.show();

            countdownDialog = countdownAnimationDialog.findViewById(R.id.readyCountdown);

            ValueAnimator scoreAnimator = new ValueAnimator();
            scoreAnimator.setIntValues(3,0);
            scoreAnimator.addUpdateListener(animation -> {
                countdownDialog.setText(String.valueOf(animation.getAnimatedValue()));
                if (countdownDialog.getText().toString().equals("0")) {
                    countdownAnimationDialog.dismiss();
                    isUp = true;
                    if (isTimed) {
                        TimerDigital();
                        setTheMoleTimed();
                    } else {
                        setTheMoleUntimed();
                    }
                }
            });
            scoreAnimator.setDuration(3300);
            scoreAnimator.start();

        }); // starts game on dialog close
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isUp = false;
    }


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
                    isUp = false;
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
    private int getSeconds() {
        String getTimerText = timerView.getText().toString();
        int getIndex = getTimerText.indexOf(":");
        return Integer.parseInt(getTimerText.substring(getIndex+1));
    }


    //hit method for each button
    private void setTheMoleTimed() {
        if (isUp) {
            int nextDelay = 500 + delayGen.nextInt(1000);
            System.out.println("Current delay: " + nextDelay);
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                // Do something every 1.5s = 1500ms
                int nextMole = moleGen.nextInt(9);

                //btn1.setTextColor(0xFF76FF03);
                buttons[nextMole].setTextColor(0xFFFFD54F);
                buttons[nextMole].setText("(✦‿✦)");

                setTheMoleTimed();
                NoMoleState();
            }, nextDelay);
        }
        else {
            instantMoleClear();
        }
    }

    private void setTheMoleUntimed() {
        if (isUp) {
            int nextDelay = 500 + delayGen.nextInt(1000);
            System.out.println("Current delay: " + nextDelay);
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                // Do something every 1.5s = 1500ms
                int nextMole = moleGen.nextInt(9);

                //btn1.setTextColor(0xFF76FF03);
                buttons[nextMole].setTextColor(0xFFFFD54F);
                buttons[nextMole].setText("(✦‿✦)");

                setTheMoleUntimed();
                NoMoleState();
            }, nextDelay);
        }
        else {
            instantMoleClear();
        }
    }

    public void instantMoleClear() {
        for (int i=0;i<10;i++) {
            if (!buttons[i].getText().equals("(O‿O)")) {
                buttons[i].setBackgroundColor(0xFF311B92);
                buttons[i].setTextColor(Color.WHITE);
                buttons[i].setText("(O‿O)");
            }
        } //for loop
    }


    public void moleClick() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            for (int i=0;i<10;i++) {
                int checkMole = i;
                buttons[i].setOnClickListener(v -> {
                    if (buttons[checkMole].getText().equals("(✦‿✦)")) {
                        MediaPlayer playBonk = MediaPlayer.create(getContext(),R.raw.bonk);
                        buttons[checkMole].setBackgroundColor(0xFFFF6F00);
                        buttons[checkMole].setText("(>‿<)");
                        AddScore();
                        playBonk.start();
                    }
                    else {
                        if (isTimed) {
                            MediaPlayer playError = MediaPlayer.create(getContext(),R.raw.errorbonk);
                            buttons[checkMole].setBackgroundColor(0xFFB71C1C);
                            buttons[checkMole].setText("(#_#)");
                            MinusScore();
                            playError.start();
                        }
                        else {
                            MediaPlayer playError = MediaPlayer.create(getContext(),R.raw.errorbonk);
                            buttons[checkMole].setBackgroundColor(0xFFB71C1C);
                            buttons[checkMole].setText("(#_#)");
                            playError.start();
                            scoreResultDialog.show();
                            ScoreAnimation();
                            isUp = false;
                        }
                    }
                });
            }

        },0);

    }


    public void NoMoleState() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            for (int i=0;i<10;i++) {
                if (!buttons[i].getText().equals("(O‿O)")) {
                    int isClicked = i;
                    final Handler handler1 = new Handler();
                    handler1.postDelayed(() -> {
                        buttons[isClicked].setBackgroundColor(0xFF311B92);
                        buttons[isClicked].setTextColor(Color.WHITE);
                        buttons[isClicked].setText("(O‿O)");
                    },1000);
                }
            } //for loop
        }, 0);
    }


    public void ScoreAnimation() {
        ValueAnimator scoreAnimator = new ValueAnimator();
        scoreAnimator.setIntValues(0,score);
        scoreAnimator.addUpdateListener(animation -> {
            totalScoreResult.setText(String.valueOf(animation.getAnimatedValue()));
            if (totalScoreResult.getText().equals(String.valueOf(score))) {
                btnMainMenu.setEnabled(true);
            }
        });
        scoreAnimator.setDuration(500);
        scoreAnimator.start();

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