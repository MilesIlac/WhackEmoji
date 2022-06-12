package com.milesilac.whackemoji;

public interface GameContract {

    interface GameFragment {

    }

    interface DigitalTimer {
        String getTimerText(int timer);
        String formatTime(int seconds, int minutes);
    }
}
