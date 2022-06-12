package com.milesilac.whackemoji;


import java.util.Locale;

public class DigitalTimer implements GameContract.DigitalTimer {

    GameContract.GameFragment gameFragment;

    public DigitalTimer(GameContract.GameFragment gameFragment) {
        this.gameFragment = gameFragment;
    }

    @Override
    public String getTimerText(int putTimer) {
        int rounded = Math.round(putTimer);

        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;

        return formatTime(seconds, minutes);
    }

    @Override
    public String formatTime(int seconds, int minutes) {
        return String.format(Locale.getDefault(),"%02d",minutes) + ":" + String.format(Locale.getDefault(),"%02d",seconds);
    }

}
