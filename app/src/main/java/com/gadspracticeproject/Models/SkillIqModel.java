package com.gadspracticeproject.Models;

import com.google.gson.annotations.SerializedName;

public class SkillIqModel {
    @SerializedName("badgeUrl")
    String leaderImage;
    @SerializedName("name")
    String leaderName;
    @SerializedName("country")
    String leaderLocation;
    @SerializedName("score")
    String iqScore;

    public SkillIqModel() {
    }

    public SkillIqModel(String leaderImage, String leaderName, String leaderLocation, String iqScore) {
        this.leaderImage = leaderImage;
        this.leaderName = leaderName;
        this.leaderLocation = leaderLocation;
        this.iqScore = iqScore;
    }

    public String getLeaderImage() {
        return leaderImage;
    }


    public String getLeaderName() {
        return leaderName;
    }


    public String getLeaderLocation() {
        return leaderLocation;
    }

    public String getIqScore() {
        return iqScore;
    }
}
