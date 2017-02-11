package com.exercise.p.k_table.model;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Checkable;
import android.widget.LinearLayout;

import com.exercise.p.k_table.R;

/**
 * Created by p on 2017/2/10.
 */

public class CheckableLinearLayout extends LinearLayout implements Checkable {
    private boolean mChecked;
    private int color;

    public CheckableLinearLayout(Context context) {
        super(context);
    }

    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckableLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setChecked(boolean checked) {
        mChecked = checked;
        if (checked)
            this.setBackgroundColor(getResources().getColor(R.color.courseBg2));
        else {
//            this.color = 2131427347;
//            Log.i("Res","unchecked" + this.color);
            this.setBackgroundColor(this.color);
        }
    }

    public void setColor(int color) {
        this.color = color;
//        Log.i("Res","color" + color + "");
//        Log.i("Res","this.color" + this.color + "");
        setChecked(false);
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }
}
