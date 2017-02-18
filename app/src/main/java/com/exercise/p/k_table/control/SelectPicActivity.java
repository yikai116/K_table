package com.exercise.p.k_table.control;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.exercise.p.k_table.R;
import com.exercise.p.k_table.model.Global_Info;

public class SelectPicActivity extends AppCompatActivity {
    ActionBar actionBar;
    int res[] = {R.mipmap.bg_pic1,R.mipmap.bg_pic2,R.mipmap.bg_pic3,R.mipmap.bg_pic4};
    Button button_local_album;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pic);
        initActionBar();
        GridView gridView = (GridView) findViewById(R.id.pic_preview);
        gridView.setAdapter(new MyAdapter());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Global_Info.setBg_drawable(getResources().getDrawable(res[position]));
                Toast.makeText(SelectPicActivity.this, "设置成功~", Toast.LENGTH_SHORT).show();
            }
        });
        button_local_album = (Button) findViewById(R.id.local_album);
        button_local_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectPicActivity.this, "本地选择", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void initActionBar(){
        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable(Global_Info.getTheme_color_drawable());
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.actionbar_custom_view);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        TextView textView = (TextView) actionBar.getCustomView().findViewById(R.id.actionbar_title);
        textView.setText("本地选择");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            SelectPicActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return res.length;
        }

        @Override
        public Object getItem(int position) {
            return res[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(SelectPicActivity.this);
            int x = getWindowManager().getDefaultDisplay().getWidth();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(x/4,x/4);
            int margin = (int) getResources().getDimension(R.dimen.grad_item_margin);
            params.setMargins(margin,margin,margin,margin);
            imageView.setLayoutParams(params);
            imageView.setImageResource(res[position]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }
    }
}
