package com.example.lastmileconnectivity.lastmileconnectivity.util;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * <h1><font color="orange">BaseLineTextView</font></h1>
 * Custom ImageView with Aspect Ratio
 *
 * @author Faisal Khan
 */
public class AspectRatioImageView extends ImageView {

    private final String TAG=AspectRatioImageView.class.getSimpleName();

    public AspectRatioImageView(Context context) {
        super(context);
        Log.v(TAG, "AspectRatioImageView");
    }

    public AspectRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.v(TAG, "AspectRatioImageView");
    }

    public AspectRatioImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Log.v(TAG, "AspectRatioImageView");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.v(TAG, "onMeasure");

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width * getDrawable().getIntrinsicHeight() / getDrawable().getIntrinsicWidth();
        setMeasuredDimension(width, height);
    }
}