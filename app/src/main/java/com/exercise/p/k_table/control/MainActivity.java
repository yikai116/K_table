package com.exercise.p.k_table.control;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.exercise.p.k_table.R;
import com.exercise.p.k_table.model.Course;
import com.exercise.p.k_table.model.Global_Info;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    MainFragment mainFragment;
    QueryFragment queryFragment;
    InfoFragment infoFragment;
    ActionBar actionBar;
    int currentFragment = -1;
    ArrayList<Course> courses;
    ImageView bg_view;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bg_view = (ImageView) findViewById(R.id.main_bg_view);
        fragmentManager = getSupportFragmentManager();
        courses = (ArrayList<Course>) getIntent().getBundleExtra("courses").get("courses");
        initActionBar();
        initTabView();
        Bundle bundle = new Bundle();
        bundle.putSerializable("courses",courses);
        setFragment(0,bundle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragment_main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        Log.i("Main","Main onResume");
        actionBar.setBackgroundDrawable(new ColorDrawable(Global_Info.getTheme_color()));
//        Log.i("Info","Main " + Global_Info.getBGisColor());
        if (Global_Info.getBGisColor()){
            bg_view.setImageDrawable(new ColorDrawable(Global_Info.getBG_color()));
        }
        else {
//            Glide.with(MainActivity.this)
//                    .load(Global_Info.getBG_pic())
//                    .into(bg_view);
            bg_view.setImageURI(Global_Info.getBG_pic());
        }
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.fragment_main_menu_exit){
            MainActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void initActionBar(){
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(Global_Info.getTheme_color()));
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.actionbar_custom_view);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(getResources().getDrawable(R.drawable.ic_logo_small));
    }

    public void initTabView() {
        LinearLayout tab1 = (LinearLayout) findViewById(R.id.tab1);
        ImageView imageView = (ImageView) tab1.findViewById(R.id.tab_image);
        TextView textView = (TextView) tab1.findViewById(R.id.tab_text);
        imageView.setImageResource(R.drawable.ic_tab1);
        textView.setText("课程表");
        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("courses",courses);
                setFragment(0,bundle);
            }
        });
        LinearLayout tab2= (LinearLayout) findViewById(R.id.tab2);
        imageView = (ImageView) tab2.findViewById(R.id.tab_image);
        textView = (TextView) tab2.findViewById(R.id.tab_text);
        imageView.setImageResource(R.drawable.ic_tab2);
        textView.setText("查询");
        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(1,null);
            }
        });
        LinearLayout tab3 = (LinearLayout) findViewById(R.id.tab3);
        imageView = (ImageView) tab3.findViewById(R.id.tab_image);
        textView = (TextView) tab3.findViewById(R.id.tab_text);
        imageView.setImageResource(R.drawable.ic_tab3);
        textView.setText("个人");
        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(2,null);
            }
        });
    }

    public void setFragment(int x,Bundle bundle){
//        Log.i("Main","set" + x);
        TextView textView = (TextView) actionBar.getCustomView().findViewById(R.id.actionbar_title);
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (x){
            case 0:
                if (currentFragment != 0) {
                    textView.setText("第X周");
                    if (mainFragment == null) {
                        mainFragment = new MainFragment();
//                        Log.i("Main", "main new");
                        mainFragment.setArguments(bundle);
                    }
                    hideFragment();
                    if (!mainFragment.isAdded())
                        fragmentTransaction.add(R.id.viewPager, mainFragment);
                    else
                        fragmentTransaction.show(mainFragment);
                    currentFragment = 0;
                }
                break;
            case 1:
                if (currentFragment != 1) {
                    textView.setText("查询");
                    if (queryFragment == null) {
                        queryFragment = new QueryFragment();
//                        Log.i("Main", "query new");
                        queryFragment.setArguments(bundle);
                    }
                    hideFragment();
                    if (!queryFragment.isAdded())
                        fragmentTransaction.add(R.id.viewPager, queryFragment);
                    else
                        fragmentTransaction.show(queryFragment);
                    currentFragment = 1;
                }
                break;
            case 2:
                if (currentFragment != 2) {
                    textView.setText("个人中心");
                    if (infoFragment == null) {
                        infoFragment = new InfoFragment();
//                        Log.i("Main", "info new");
                        infoFragment.setArguments(bundle);
                    }
                    hideFragment();
                    if (!infoFragment.isAdded())
                        fragmentTransaction.add(R.id.viewPager, infoFragment);
                    else
                        fragmentTransaction.show(infoFragment);
                    currentFragment = 2;
                }
                break;
            default:hideFragment();
        }
        fragmentTransaction.commit();
    }

    private void hideFragment(){
//        Log.i("Main","hide" + currentFragment);
        switch (currentFragment){
            case 0:
                fragmentTransaction.hide(mainFragment);
                break;
            case 1:
                fragmentTransaction.hide(queryFragment);
                break;
            case 2:
                fragmentTransaction.hide(infoFragment);
                break;
        }
    }
}
