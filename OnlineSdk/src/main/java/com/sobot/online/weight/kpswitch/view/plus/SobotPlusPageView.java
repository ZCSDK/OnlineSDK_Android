package com.sobot.online.weight.kpswitch.view.plus;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.sobot.onlinecommon.utils.SobotResourceUtils;

public class SobotPlusPageView extends RelativeLayout {

    private GridView mGvView;

    public GridView getGridView() {
        return mGvView;
    }

    public SobotPlusPageView(Context context) {
        this(context, null);
    }

    public SobotPlusPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater == null) {
            return;
        }
        View view = inflater.inflate(SobotResourceUtils.getIdByName(context, "layout", "sobot_item_pluspage"), this);
        mGvView = (GridView) view.findViewById(SobotResourceUtils.getIdByName(context, "id", "sobot_gv"));

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            mGvView.setMotionEventSplittingEnabled(false);
        }
        mGvView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        mGvView.setCacheColorHint(0);
        mGvView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mGvView.setVerticalScrollBarEnabled(false);
    }

    public void setNumColumns(int row) {
        mGvView.setNumColumns(row);
    }
}