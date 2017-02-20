package com.exercise.p.k_table.control;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.exercise.p.k_table.R;
import com.exercise.p.k_table.model.ColorPickerDialog;
import com.exercise.p.k_table.model.Global_Info;

public class SettingActivity extends AppCompatActivity {
    ActionBar actionBar;
    Button button_theme_color;
    Button button_bg_pic;
    Button button_default_setting;
    ImageView bg_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initActionBar();
        initView();
    }
    public void initView(){
        button_theme_color = (Button) findViewById(R.id.theme_color);
        button_bg_pic = (Button) findViewById(R.id.bg_pic);
        button_default_setting = (Button) findViewById(R.id.default_setting);
        button_theme_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {button_theme_color_click();
            }
        });
        button_bg_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_bg_pic_click();
            }
        });
        button_default_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global_Info.setTheme_color(getResources().getColor(R.color.actionbar_color_white));
                Resources r =getResources();
                Global_Info.setBG_pic(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                        + r.getResourcePackageName(R.mipmap.bg_pic2) + "/"
                        + r.getResourceTypeName(R.mipmap.bg_pic2) + "/"
                        + r.getResourceEntryName(R.mipmap.bg_pic2)));
                Global_Info.saveInfo(SettingActivity.this);
                actionBar.setBackgroundDrawable(new ColorDrawable(Global_Info.getTheme_color()));
                bg_view.setImageDrawable(getResources().getDrawable(R.mipmap.bg_pic3));
            }
        });
        bg_view = (ImageView) findViewById(R.id.setting_bg_view);
    }
    public void initActionBar(){
        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(Global_Info.getTheme_color()));
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.actionbar_custom_view);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        TextView textView = (TextView) actionBar.getCustomView().findViewById(R.id.actionbar_title);
        textView.setText("设置");
    }

    private void button_theme_color_click(){
        new ColorPickerDialog(SettingActivity.this, "选择主题颜色", new ColorPickerDialog.OnColorChangedListener() {
            @Override
            public void colorChanged(int color) {
                Log.i("Setting","color" + color);
                if (color < -16777216 / 1.5){
                    Toast.makeText(SettingActivity.this, "颜色太深会很难看哦~", Toast.LENGTH_SHORT).show();
                }
                int bg_color = Color.argb(120,Color.red(color),Color.green(color),Color.blue(color));
                int theme_color = Color.argb(220,Color.red(color),Color.green(color),Color.blue(color));
//                int color1 = Color.alpha(color);
//                Log.i("Setting","alpha" + color1);
//                Log.i("Setting","color" + color);
                Global_Info.setBG_color(bg_color);
                Global_Info.setTheme_color(theme_color);
                actionBar.setBackgroundDrawable(new ColorDrawable(theme_color));
                bg_view.setImageDrawable(new ColorDrawable(bg_color));
                Global_Info.saveInfo(SettingActivity.this);
            }
        }).show();
    }

    private void button_bg_pic_click(){
        Intent intent = new Intent();
        intent.setClass(SettingActivity.this, SelectPicActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            SettingActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        Log.i("Setting","Setting onResume");
        actionBar.setBackgroundDrawable(new ColorDrawable(Global_Info.getTheme_color()));
        if (Global_Info.getBGisColor()){
            bg_view.setImageDrawable(new ColorDrawable(Global_Info.getBG_color()));
        }
        else {
//            Glide.with(SettingActivity.this)
//                    .load(Global_Info.getBG_pic())
//                    .asBitmap()
//                    .into(bg_view);
        bg_view.setImageURI(Global_Info.getBG_pic());
        }
        super.onResume();
    }
}
