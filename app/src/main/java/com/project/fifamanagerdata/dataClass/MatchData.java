package com.project.fifamanagerdata.dataClass;
import java.util.List;

public class MatchData {
    private String matchId;
    private String matchDate;
    private int matchType;
    private List<MatchInfo> matchInfo;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public int getMatchType() {
        return matchType;
    }

    public void setMatchType(int matchType) {
        this.matchType = matchType;
    }

    public List<MatchInfo> getMatchInfo() {
        return matchInfo;
    }

    public void setMatchInfo(List<MatchInfo> matchInfo) {
        this.matchInfo = matchInfo;
    }

    public static class MatchInfo {
        private String ouid;
        private String nickname;
        private MatchDetail matchDetail;
        private Shoot shoot;

        // Getters and Setters
        public String getOuid() {
            return ouid;
        }

        public void setOuid(String ouid) {
            this.ouid = ouid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public MatchDetail getMatchDetail() {
            return matchDetail;
        }

        public void setMatchDetail(MatchDetail matchDetail) {
            this.matchDetail = matchDetail;
        }

        public Shoot getShoot() {
            return shoot;
        }

        public void setShoot(Shoot shoot) {
            this.shoot = shoot;
        }
    }

    public static class MatchDetail {
        private String matchResult;
        private int foul;
        private int redCards;
        private int yellowCards;
        private int dribble;
        private int possession;
        private int offsideCount;

        // Getters and Setters
        public String getMatchResult() {
            return matchResult;
        }

        public void setMatchResult(String matchResult) {
            this.matchResult = matchResult;
        }

        public int getFoul() {
            return foul;
        }

        public void setFoul(int foul) {
            this.foul = foul;
        }

        public int getRedCards() {
            return redCards;
        }

        public void setRedCards(int redCards) {
            this.redCards = redCards;
        }

        public int getYellowCards() {
            return yellowCards;
        }

        public void setYellowCards(int yellowCards) {
            this.yellowCards = yellowCards;
        }

        public int getDribble() {
            return dribble;
        }

        public void setDribble(int dribble) {
            this.dribble = dribble;
        }

        public int getPossession() {
            return possession;
        }

        public void setPossession(int possession) {
            this.possession = possession;
        }

        public int getOffsideCount() {
            return offsideCount;
        }

        public void setOffsideCount(int offsideCount) {
            this.offsideCount = offsideCount;
        }
    }

    public static class Shoot {
        private int shootTotal;
        private int effectiveShootTotal;
        private int goalTotalDisplay;

        // Getters and Setters
        public int getShootTotal() {
            return shootTotal;
        }

        public void setShootTotal(int shootTotal) {
            this.shootTotal = shootTotal;
        }

        public int getEffectiveShootTotal() {
            return effectiveShootTotal;
        }

        public void setEffectiveShootTotal(int effectiveShootTotal) {
            this.effectiveShootTotal = effectiveShootTotal;
        }

        public int getGoalTotalDisplay() {
            return goalTotalDisplay;
        }

        public void setGoalTotalDisplay(int goalTotalDisplay) {
            this.goalTotalDisplay = goalTotalDisplay;
        }
    }
}
