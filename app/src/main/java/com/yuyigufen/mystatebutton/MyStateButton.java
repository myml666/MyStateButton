package com.yuyigufen.mystatebutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/6/13 0013.
 */

public class MyStateButton extends RelativeLayout {

    private View view;
    private TextView textView;
    private ImageView imageView;
    private int defaultImage =0;
    private int pressImage =0;
    private int textcolorDefault=Color.parseColor("#567DBF");
    private int textcolorPress=Color.parseColor("#BFBFBF");
    private RelativeLayout contentlayout;
    private MyStateButtonClickListener myStateButtonClickListener;
    private GradientDrawable defaultDrawable;
    private GradientDrawable pressDrawable;
    private int borderColorDefault;
    private int borderColorPress;
    private int borderWidth=2;
    private float cornerRadius=8;
    public MyStateButtonClickListener getMyStateButtonClickListener() {
        return myStateButtonClickListener;
    }

    public void setMyStateButtonClickListener(MyStateButtonClickListener myStateButtonClickListener) {
        this.myStateButtonClickListener = myStateButtonClickListener;
    }

    public MyStateButton(Context context) {
        this(context,null);
    }

    public MyStateButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyStateButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = View.inflate(context, R.layout.buttonlayout, this);
        textView=view.findViewById(R.id.text);
        imageView=view.findViewById(R.id.icon);
//        初始化GradientDrawable用于显示边框
        defaultDrawable = new GradientDrawable();
        pressDrawable = new GradientDrawable();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyStateButton);
        if(typedArray!=null){
//            默认图片
            defaultImage = typedArray.getResourceId(R.styleable.MyStateButton_defaultImage, R.mipmap.one);
//            点击图片
            pressImage = typedArray.getResourceId(R.styleable.MyStateButton_pressImage, R.mipmap.one_press);
//            设置文字
            String text = typedArray.getString(R.styleable.MyStateButton_text);
//            默认文字颜色
            textcolorDefault = typedArray.getColor(R.styleable.MyStateButton_defaultTextColor, Color.parseColor("#567DBF"));
//            点击文字颜色
            textcolorPress = typedArray.getColor(R.styleable.MyStateButton_pressTextColor, Color.parseColor("#BFBFBF"));
            imageView.setImageResource(defaultImage);
            textView.setTextColor(textcolorDefault);
            textView.setText(text);
//            默认边框颜色
            borderColorDefault = typedArray.getColor(R.styleable.MyStateButton_defaultBorderColor, Color.parseColor("#567DBF"));
//            点击边框颜色
            borderColorPress = typedArray.getColor(R.styleable.MyStateButton_pressBorderColor, Color.parseColor("#BFBFBF"));
//            边框宽度
            borderWidth = typedArray.getInteger(R.styleable.MyStateButton_borderWidth, 2);
//            边框圆角大小
            cornerRadius = typedArray.getFloat(R.styleable.MyStateButton_cornerRadius, 8);
            defaultDrawable.setStroke(borderWidth,borderColorDefault);
            pressDrawable.setStroke(borderWidth,borderColorPress);
            defaultDrawable.setCornerRadius(cornerRadius);
            pressDrawable.setCornerRadius(cornerRadius);
            setBackground(defaultDrawable);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int width = getWidth();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.width= (int) (width*0.5);
        layoutParams.height=(int) (width*0.5);
        layoutParams.topMargin=(int) (width*0.05);
        textView.setTextSize((float) (width*0.09));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:;
                setBackground(pressDrawable);
                textView.setTextColor(textcolorPress);
                if(pressImage!=0){
                    imageView.setImageResource(pressImage);
                }
                break;
            case MotionEvent.ACTION_UP:
                setBackground(defaultDrawable);
                textView.setTextColor(textcolorDefault);
                if(defaultImage!=0){
                    imageView.setImageResource(defaultImage);
                }
                if(myStateButtonClickListener!=null){
                    myStateButtonClickListener.onClick(view);
                }
                break;
        }
        return true;
    }
}
