package com.exercise.p.k_table.control;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.exercise.p.k_table.model.Course;
import com.exercise.p.k_table.model.Global_Info;
import com.exercise.p.k_table.R;
import com.exercise.p.k_table.model.MemoryAccess;

import java.io.IOException;
import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {

    private boolean isFirst = true;
    private boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        WelAsyncTask task = new WelAsyncTask();
        task.execute();
    }

    protected class WelAsyncTask extends AsyncTask<Void,Void,Integer> {
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        @Override
        protected Integer doInBackground(Void... params) {
            sharedPreferences = getSharedPreferences("IsFirstAndIsLogin",MODE_PRIVATE);
            editor = sharedPreferences.edit();
            isFirst = sharedPreferences.getBoolean("isFirst",true);
            isLogin = sharedPreferences.getBoolean("isLogin",false);
            Global_Info.getInfo(WelcomeActivity.this);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            //第一次启动，进入引导页
            if (isFirst){
                //进入引导页
                Toast.makeText(WelcomeActivity.this, "进入引导页", Toast.LENGTH_SHORT).show();
                editor.putBoolean("isFirst",false);
                editor.apply();
            }
            else {
                ArrayList<String> courses = null;
                try {
                    courses = MemoryAccess.read();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(WelcomeActivity.this, "文件读取错误", Toast.LENGTH_SHORT).show();
                    isLogin = false;
                }
                if (isLogin){
                    //进入主界面
                    Global_Info.getInfo(WelcomeActivity.this);
                    Toast.makeText(WelcomeActivity.this, "进入主界面", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(WelcomeActivity.this,MainActivity.class);
                    if (courses!=null)
                        intent.putStringArrayListExtra("courses",courses);
                    WelcomeActivity.this.startActivity(intent);
                }
                else {
                    //进入登录界面
                    Toast.makeText(WelcomeActivity.this, "进入登录界面", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(WelcomeActivity.this,LoginActivity.class);
                    WelcomeActivity.this.startActivity(intent);
                }
            }
            WelcomeActivity.this.finish();
        }
    }
}
