package com.gadspracticeproject.Models;

import com.google.gson.annotations.SerializedName;

public class LeaderModel {
    @SerializedName("badgeUrl")
    String leaderImage;
    @SerializedName("name")
    String leaderName;
    @SerializedName("country")
    String leaderLocation;
    @SerializedName("hours")
    String learningHrs;

    public LeaderModel() {
    }

    public LeaderModel(String leaderImage, String leaderName, String leaderLocation, String learningHrs) {
        this.leaderImage = leaderImage;
        this.leaderName = leaderName;
        this.leaderLocation = leaderLocation;
        this.learningHrs = learningHrs;
    }

    public String getLeaderImage() {
        return leaderImage;
    }

    public void setLeaderImage(String leaderImage) {
        this.leaderImage = leaderImage;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getLeaderLocation() {
        return leaderLocation;
    }

    public void setLeaderLocation(String leaderLocation) {
        this.leaderLocation = leaderLocation;
    }

    public String getLearningHrs() {
        return learningHrs;
    }

    public void setLearningHrs(String learningHrs) {
        this.learningHrs = learningHrs;
    }
}
