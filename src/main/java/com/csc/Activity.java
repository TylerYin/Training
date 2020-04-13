package com.csc;

public class Activity implements Comparable<Activity> {
    private String activityName;
    private Integer remainNum;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(Integer remainNum) {
        this.remainNum = remainNum;
    }

    @Override
    public int compareTo(Activity o) {
        return this.getActivityName().compareTo(o.getActivityName());
    }

    @Override
    public String toString() {
        return activityName + " " + remainNum;
    }
}