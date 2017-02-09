package com.exercise.p.k_table.model;

import java.io.Serializable;

/**
 * Created by p on 2017/2/9.
 */

public class Grade implements Serializable {
    private String name;
    private String credit;
    private String type;
    private String mGrade;

    public Grade(String name,String credit,String type,String mGrade){
        this.name = name;
        this.credit = credit;
        this.type = type;
        this.mGrade = mGrade;
    }

    public String getmGrade() {
        return mGrade;
    }

    public String getCredit() {
        return credit;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public void setmGrade(String mGrade) {
        this.mGrade = mGrade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name + "_" + credit + "_" + type + "_" + mGrade;
    }
}
