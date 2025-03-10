package com.project.fifamanagerdata.recyclerViewPackage;

import com.project.fifamanagerdata.dataClass.MatchData;

import java.util.ArrayList;

public class RecylerViewData {
    private String matchDate;
    private String player1;
    private String player2;
    private String score;
    private String nickname;
    private int matchEndType;
    private String Ouid_p1;
    private String Ouid_p2;
    private ArrayList<MatchData> matchData = new ArrayList<>();


    public RecylerViewData(String matchDate, String player1, String player2,
                           String score,  MatchData matchData, int matchEndType, String nickname, String Ouid_p1, String Ouid_p2) {

        this.matchDate = matchDate;
        this.player1 = player1;
        this.player2 = player2;
        this.score = score;
        this.matchData.add(matchData);
        this.matchEndType = matchEndType;
        this.nickname = nickname;
        this.Ouid_p1 = Ouid_p1;
        this.Ouid_p2 = Ouid_p2;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getMatchEndType() {
        return matchEndType;
    }

    public void setMatchEndType(int matchEndType) {
        this.matchEndType = matchEndType;
    }

    public String getOuid_p2() {
        return Ouid_p2;
    }

    public void setOuid_p2(String ouid_p2) {
        Ouid_p2 = ouid_p2;
    }

    public String getOuid_p1() {
        return Ouid_p1;
    }

    public void setOuid_p1(String ouid_p1) {
        Ouid_p1 = ouid_p1;
    }
}
