package com.exercise.p.k_table.model;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by p on 2017/1/24.
 */

public interface MyLoginListener {
    void showDialog();
    void closeDialog();
    void showCourseData(ArrayList<Course> arrayList, String id, String psw);
    void showErrorDialog(String notice);
    void toastError(String notice);
}
