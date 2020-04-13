package com.csc;

import java.util.ArrayList;
import java.util.List;

public class Client implements Comparable<Client> {
    private String firstName;
    private String lastName;

    List<Activity> activities = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public int compareTo(Client o) {
        int result = this.getLastName().compareTo(o.getLastName());
        if (0 == result) {
            return this.getFirstName().compareTo(o.getFirstName());
        }
        return result;
    }

    @Override
    public String toString() {
        String result = firstName + " " + lastName;
        if (activities.size() > 0) {
            result += ", the ticket belong to this client list as below:";
            for (Activity activity : activities) {
                result += " " + activity.getActivityName();
            }
        }
        return result;
    }
}