package com.xiong.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hui.xiong on 2016/3/31.
 */
public class SideBar extends View {
    public static String[] text = { "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z","#" };
    private int textSize,textColor,clickColor,background;
    private int choose =-1;//the position of click
    private OnTouchTextListener listener;

    interface OnTouchTextListener{
        void onTouchText(String text);
    }
    public SideBar(Context context) {
        super(context,null);
    }
    public SideBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SideBar, 0, defStyleAttr);
        textSize = a.getDimensionPixelSize(R.styleable.SideBar_textSize, getResources().getDimensionPixelSize(R.dimen.textSize));
        textColor = a.getColor(R.styleable.SideBar_textColor, ContextCompat.getColor(context, R.color.blue));
        clickColor = a.getColor(R.styleable.SideBar_clickColor, ContextCompat.getColor(context, R.color.red));
        background = a.getColor(R.styleable.SideBar_canvas, ContextCompat.getColor(context, R.color.white));
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawColor(background);
        Paint paint = new Paint();
        paint.setTextSize(textSize);

        int height =getHeight();
        int width  =getWidth();

        for (int i=0 ; i<text.length ; i++) {

            float x = ( width - paint.measureText(text[i]) ) / 2;
            float y = height / text.length *( i + 1 ) + 3;
            if(i == choose){
                paint.setColor(clickColor);
                canvas.drawText(text[i], x, y, paint);
            }else {
                paint.setColor(textColor);
                canvas.drawText(text[i], x, y, paint);
            }
        }
        paint.reset();

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float y = event.getY();
         choose = (int) (y / getHeight() * text.length);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (listener != null) listener.onTouchText(text[choose]);
                invalidate() ;
                break;
            case MotionEvent.ACTION_UP:
                choose = -1;
                invalidate();
                break;
        }
        return true;
    }



    public void setOnTouchTextListener(OnTouchTextListener l){
        listener = l ;
    }
}
