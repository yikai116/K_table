package com.exercise.p.k_table.model;

import java.util.ArrayList;

/**
 * Created by p on 2017/1/24.
 */

public interface MyLoginListener_Query extends MyLoginListener{
    void showGradeData(ArrayList<Grade> grades, String name);
    void showExamData(ArrayList<Exam> grades, String name);
    void showCETData(String grade_all,String grade_listen,String grade_read, String grade_write);
}
