package com.exercise.p.k_table.model;

import java.io.Serializable;

/**
 * Created by p on 2017/2/9.
 */

public class Exam implements Serializable {
    private String exam_name;
    private String course_name;
    private String place;
    private String time;
    private String seat;

    public Exam( String exam_name,String course_name,String place,String time,String seat){
        this.exam_name = exam_name;
        this.course_name = course_name;
        this.place = place;
        this.time = time;
        this.seat = seat;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return exam_name + "_" + course_name + "_" + place + "_" + time + "_" + seat;
    }
}
