package com.exercise.p.k_table.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by p on 2017/1/16.
 */

public class Global_Info {
    private static String user_id = "";
    private static String user_psw = "";
    private static String user_name = "";

    public static String getUser_id() {
        return user_id;
    }

    public static String getUser_psw() {
        return user_psw;
    }

    public static String getUser_name() {
        return user_name;
    }

    public static void setUser_id(String user_id) {
        Global_Info.user_id = user_id;
        if (user_id.length()>3)
            user_name = "某同学#" + user_id.substring(user_id.length()-3);
        else
            user_name = "某同学#" + user_id;
    }

    public static void setUser_psw(String user_psw) {
        Global_Info.user_psw = user_psw;
    }

    public static void setUser_name(String user_name) {
        Global_Info.user_name = user_name;
    }

    public static void getInfo(Activity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        user_id = sharedPreferences.getString("user_id","");
        user_psw = sharedPreferences.getString("user_psw","");
        if (user_id.length()>3)
            user_name = sharedPreferences.getString("user_name","某同学#" + user_id.substring(user_id.length()-3));
        else
            user_name = sharedPreferences.getString("user_name","某同学#" + user_id);
    }

    public static void savaInfo(Activity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_id",user_id);
        editor.putString("user_psw",user_psw);
        editor.putString("user_name",user_name);
        editor.apply();
    }
}
