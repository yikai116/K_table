package com.exercise.p.k_table.control;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.exercise.p.k_table.R;
import com.exercise.p.k_table.model.Exam;
import com.exercise.p.k_table.model.Grade;

import java.util.ArrayList;

public class Query_ResActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Res","new");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_res);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(getColor(R.color.main_color)));


        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Bundle bundle = getIntent().getBundleExtra("info");
        String flag = bundle.getString("flag");
        assert flag != null;
        switch (flag){
            case "grade":Log.i("Res","Grade");
                fragmentTransaction.replace(R.id.layout_show_value,new InfoFragment());
                break;
            case "exam":Log.i("Res","Exam");
                break;
            case "cet":
                break;
        }
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        Log.i("Res","des");
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            Query_ResActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
