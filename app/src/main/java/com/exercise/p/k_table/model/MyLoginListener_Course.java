package com.exercise.p.k_table.model;

import java.util.ArrayList;

/**
 * Created by p on 2017/1/24.
 */

public interface MyLoginListener_Course extends MyLoginListener{
    void showCourseData(ArrayList<Course> arrayList, String id, String psw);
}
