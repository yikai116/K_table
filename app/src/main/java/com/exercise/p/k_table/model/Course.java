package com.exercise.p.k_table.model;

import java.util.StringTokenizer;

/**
 * Created by p on 2016/10/24.
 */
public class Course {
    private String name;
    private int start;
    private int size;
    private String teacher;
    private int day;
    private int color;

    public Course(String name, int start, int size, String teacher, int day){
        this.name = name;
        this.start = start;
        this.size = size;
        this.teacher = teacher;
        this.day = day;
//        this.color = color;
    }

    public Course(String s){
        StringTokenizer stringTokenizer = new StringTokenizer(s,",");
        this.name = stringTokenizer.nextToken();
        this.start = Integer.valueOf(stringTokenizer.nextToken());
        this.size = Integer.valueOf(stringTokenizer.nextToken());
        this.teacher = stringTokenizer.nextToken();
        this.day = Integer.valueOf(stringTokenizer.nextToken());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String toString(){
        return name + "," + start + "," + size + "," + teacher + "," + day + "," + color;
    }

}
