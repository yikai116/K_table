package com.exercise.p.k_table.model;

import android.util.Log;

import java.io.Serializable;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by p on 2016/10/24.
 */
public class Course implements Serializable{
    private static int[] color_using = {0,0,0,0,0,0,0,0,0};
    private String name;
    private int start;
    private int size;
    private String teacher;
    private int day;
    private int color;
    private String type;
    private String week_of_class;
    private float credit;
    private String place;

    public Course(String name, int start, int size, String teacher, int day, String place, String type, String week_of_class, float credit){
        this.name = name;
        this.start = start;
        this.size = size;
        this.teacher = teacher;
        this.day = day;
        this.place = place;
        this.type = type;
        this.week_of_class = week_of_class;
        this.credit = credit;
        int sum = 0;
        for (int i = 0; i < color_using.length; i++){
            sum += color_using[i];
        }
        while (true) {
            int x = new Random().nextInt(9);
            if (color_using[x] <= sum / 9) {
                this.color = x;
                color_using[x] ++;
                break;
            }
        }
    }

    public Course(String s){
        StringTokenizer stringTokenizer = new StringTokenizer(s,",");
        this.name = stringTokenizer.nextToken();
        this.start = Integer.valueOf(stringTokenizer.nextToken());
        this.size = Integer.valueOf(stringTokenizer.nextToken());
        this.teacher = stringTokenizer.nextToken();
        this.day = Integer.valueOf(stringTokenizer.nextToken());
        this.place = stringTokenizer.nextToken();
        this.type = stringTokenizer.nextToken();
        this.credit = Float.valueOf(stringTokenizer.nextToken());
        this.week_of_class = stringTokenizer.nextToken();
        this.color = Integer.valueOf(stringTokenizer.nextToken());
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

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeek_of_class() {
        return week_of_class;
    }

    public void setWeek_of_class(String week_of_class) {
        this.week_of_class = week_of_class;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String toString(){
        return name + "," + start + "," + size + "," + teacher + "," + day + "," + place + "," + type + "," + credit + "," + week_of_class + "," + color;
    }

}
