package com.example.summoner2.app.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by alex on 30.04.14.
 */
public class League {
    private int id;
    private String queueType;
    private int leaguePoints;
    private String tier;
    private String rank;
    private int wins;
    private String leagueName;
    private boolean isVeteran;
    private boolean isFreshBlood;
    private boolean isHotStreak;

    public League(){}

    public League(JSONObject league_stats, int id){
        try {
            this.setId(id);
            this.setQueueType(league_stats.getString("queueType"));
            this.setLeaguePoints(league_stats.getInt("leaguePoints"));
            this.setTier(league_stats.getString("tier"));
            this.setRank(league_stats.getString("rank"));
            this.setWins(league_stats.getInt("wins"));
            this.setLeagueName(league_stats.getString("leagueName"));
            this.setVeteran(league_stats.getBoolean("isVeteran"));
            this.setFreshBlood(league_stats.getBoolean("isFreshBlood"));
            this.setHotStreak(league_stats.getBoolean("isHotStreak"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public boolean isVeteran() {
        return isVeteran;
    }

    public void setVeteran(boolean isVeteran) {
        this.isVeteran = isVeteran;
    }

    public boolean isFreshBlood() {
        return isFreshBlood;
    }

    public void setFreshBlood(boolean isFreshBlood) {
        this.isFreshBlood = isFreshBlood;
    }

    public boolean isHotStreak() {
        return isHotStreak;
    }

    public void setHotStreak(boolean isHotStreak) {
        this.isHotStreak = isHotStreak;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
