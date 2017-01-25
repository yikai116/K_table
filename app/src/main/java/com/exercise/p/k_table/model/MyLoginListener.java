package com.exercise.p.k_table.model;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by p on 2017/1/24.
 */

public interface MyLoginListener {
    public void showDialog();
    public void closeDialog();
    public void showCourse(ArrayList<Course> arrayList, String id, String psw);
    public void showErrorDialog(String notice);
    public void toastError(String notice);
}
