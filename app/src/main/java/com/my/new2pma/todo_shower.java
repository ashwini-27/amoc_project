package com.my.new2pma;

/**
 * Created by ashwini on 2/8/2018.
 */

public class todo_shower {
    String deadline;
    String description;
    int alarm;
    int id;

    public todo_shower(String deadline, String description, int alarm, int id) {
        this.deadline = deadline;
        this.description = description;
        this.alarm = alarm;
        this.id = id;
    }


    public String getDeadline() {
        return deadline;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getAlarm() {
        return alarm;
    }
}
