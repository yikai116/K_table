package com.exercise.p.k_table.model;

import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by p on 2017/1/25.
 */

public class MemoryAccess {
    public static void saveCourseToSD(ArrayList<Course> courses) throws IOException {
        File sdDir = Environment.getExternalStorageDirectory();
        String myPath = sdDir.getPath() + "/K_table";
        File myDir = new File(myPath);
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        myPath = myDir.getPath() + "/info.txt";
        myDir = new File(myPath);
        if (!myDir.exists())
            myDir.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(myDir.getPath()));

        for (Course c : courses) {
//            Log.i("Save",c.toString());
            writer.write(c.toString() + "\n");
        }
        writer.close();
    }

    public static ArrayList<String> readCourseFromSD() throws IOException {
        ArrayList<String> courses = new ArrayList<>();
        File sdDir = Environment.getExternalStorageDirectory();
        String myPath = sdDir.getPath() + "/K_table/info.txt";
        File myDir = new File(myPath);
        BufferedReader reader = new BufferedReader(new FileReader(myDir.getPath()));

        for (String line = reader.readLine(); line != null && (!line.equals("")); ) {
//            Log.i("Read",line);
            courses.add(line);
            line = reader.readLine();
        }
        reader.close();
        return courses;
    }
}
