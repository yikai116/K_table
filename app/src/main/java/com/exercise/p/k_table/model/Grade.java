package com.exercise.p.k_table.model;

import java.io.Serializable;

/**
 * Created by p on 2017/2/9.
 */

public class Grade implements Serializable {
    private String name;
    private int credit;
    private String type;
    private int mGrade;
    private double GPA;

    public Grade(String name,int credit,String type,int mGrade){
        this.name = name;
        this.credit = credit;
        this.type = type;
        this.mGrade = mGrade;
        if (mGrade >= 95){
            GPA = 4.0;
        }
        else if (mGrade >= 90){
            GPA = 3.8;
        }
        else if (mGrade >= 85){
            GPA = 3.6;
        }
        else if (mGrade >= 80){
            GPA = 3.2;
        }
        else if (mGrade >= 75){
            GPA = 2.7;
        }
        else if (mGrade >= 70){
            GPA = 2.2;
        }
        else if (mGrade >= 65){
            GPA = 1.7;
        }
        else if (mGrade >=680){
            GPA = 1;
        }
        else {
            GPA = 0;
        }
    }

    public int getmGrade() {
        return mGrade;
    }

    public int getCredit() {
        return credit;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getGPA() {
        return GPA;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setmGrade(int mGrade) {
        this.mGrade = mGrade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    @Override
    public String toString() {
        return name + "_" + credit + "_" + type + "_" + mGrade + "_" + GPA;
    }
}
