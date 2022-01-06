package com.milesilac.whackamolu;

public class Score {

    String name;
    int score;
    int playerid;

    public Score(String name, int score, int playerid) {
        this.name = name;
        this.score = score;
        this.playerid = playerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPlayerid() {
        return playerid;
    }

    public void setPlayerid(int playerid) {
        this.playerid = playerid;
    }

}
