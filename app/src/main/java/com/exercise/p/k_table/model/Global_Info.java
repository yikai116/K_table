package com.exercise.p.k_table.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;

import com.exercise.p.k_table.R;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by p on 2017/1/16.
 */

public class Global_Info {
    private static String user_id = "";
    private static String user_psw = "";
    private static String user_name = "";
    private static int BG_color;
    private static int theme_color;
    private static Uri BG_pic;
    private static boolean BGisColor;

    public static String getUser_id() {
        return user_id;
    }

    public static String getUser_psw() {
        return user_psw;
    }

    public static String getUser_name() {
        return user_name;
    }

    public static Uri getBG_pic() {
        return BG_pic;
    }

    public static int getTheme_color() {
        return theme_color;
    }

    public static int getBG_color() {
        return BG_color;
    }

    public static boolean getBGisColor(){
        return BGisColor;
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

    public static void setBG_color(int color) {
        BGisColor = true;
        BG_color = color;
    }

    public static void setBG_pic(Uri BG_pic) {
        BGisColor = false;
        Global_Info.BG_pic = BG_pic;
    }

    public static void setTheme_color(int color) {
        theme_color = color;
    }

    public static void getInfo(Activity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        user_id = sharedPreferences.getString("user_id","");
        user_psw = sharedPreferences.getString("user_psw","");
        if (user_id.length()>3)
            user_name = sharedPreferences.getString("user_name","某同学#" + user_id.substring(user_id.length()-3));
        else
            user_name = sharedPreferences.getString("user_name","某同学#" + user_id);
        BGisColor = sharedPreferences.getBoolean("BGisColor",true);
        Log.i("Info","read BGisColor  " + BGisColor);
        if (BGisColor)
            BG_color = sharedPreferences.getInt("BG_color",activity.getResources().getColor(R.color.actionbar_color_white));
        else{
            BG_pic = Uri.parse(sharedPreferences.getString("BG_pic",""));
            Log.i("Info","read BG_pic  path  " + BG_pic.toString());
        }
        theme_color = sharedPreferences.getInt("theme_color",activity.getResources().getColor(R.color.actionbar_color_white));
    }

    public static void saveInfo(Activity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_id",user_id);
        editor.putString("user_psw",user_psw);
        editor.putString("user_name",user_name);
        editor.putBoolean("BGisColor",BGisColor);
        Log.i("Info","sava BGisColor  " + BGisColor);
        if (BGisColor)
            editor.putInt("BG_color", BG_color);
        else {
            editor.putString("BG_pic", BG_pic.toString());
            Log.i("Info","save BG_pic  path  " + BG_pic.toString());
        }
        editor.putInt("theme_color",theme_color);
        editor.apply();
    }
}
