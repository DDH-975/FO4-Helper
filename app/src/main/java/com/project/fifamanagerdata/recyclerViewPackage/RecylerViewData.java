package com.project.fifamanagerdata.recyclerViewPackage;

import com.project.fifamanagerdata.dataClass.MatchData;

import java.util.ArrayList;

public class RecylerViewData {
    private String matchDate;
    private String player1;
    private String player2;
    private String score;
    private ArrayList<MatchData> matchData = new ArrayList<>();


    public RecylerViewData(){}

    public RecylerViewData(String matchDate, String player1, String player2,
                           String score,  MatchData matchData) {

        this.matchDate = matchDate;
        this.player1 = player1;
        this.player2 = player2;
        this.score = score;
        this.matchData.add(matchData);

    }


    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public ArrayList<MatchData> getMatchData() {
        return matchData;
    }

    public void setMatchData(ArrayList<MatchData> matchData) {
        this.matchData = matchData;
    }
}
