package com.example.crxc.a360safe.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.crxc.a360safe.R;

/**
 * Created by crxc on 2016/12/11.
 */

public class SettingItemView extends LinearLayout {

    private ImageView iv;
    private int visibility;

    public boolean isSwitchState() {
        return switchState;
    }

    public void setSwitchState(boolean switchState) {
        this.switchState = switchState;
    }

    private boolean switchState=false;

    public SettingItemView(Context context) {
        super(context);
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_setting_item, this,false);
//        View view = View.inflate(context, R.layout.fragment_setting_item, null);
        TextView text = (TextView) view.findViewById(R.id.text);
        iv = (ImageView) view.findViewById(R.id.iv);
        addView(view);
        updateSwitchState();
        TypedArray typeArray=context.obtainStyledAttributes(attrs,R.styleable.SettingItemView);
        text.setText(typeArray.getString(R.styleable.SettingItemView_text));
        visibility = typeArray.getInt(R.styleable.SettingItemView_visibility,0);
        int background=typeArray.getInt(R.styleable.SettingItemView_backImage,1);
        switch (visibility){
            case 0:
                iv.setVisibility(VISIBLE);
                break;
            case 1:
                iv.setVisibility(INVISIBLE);
                break;
        }

        switch (background){
            case 0:
                view.setBackground(getResources().getDrawable(R.drawable.iv_setting_item_first_selector));
                break;
            case 1:
                view.setBackground(getResources().getDrawable(R.drawable.iv_setting_item_middle_selector));
                break;
            case 2:
                view.setBackground(getResources().getDrawable(R.drawable.iv_setting_item_last_selector));
                break;
            default:
                view.setBackground(getResources().getDrawable(R.drawable.iv_setting_item_middle_selector));
                break;
        }
        typeArray.recycle();
    }



    public void updateSwitchState() {
        if(switchState){
            iv.setImageResource(R.mipmap.on);
        }else {
            iv.setImageResource(R.mipmap.off);
        }
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

}
