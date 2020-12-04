package com.alin.cityselectorlib.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


import com.alin.cityselectorlib.util.DensityUtil;
import com.alin.cityselectorlib.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ailibin
 * @version 1.0
 * createTime 2020/12/3.
 */

public class SideBar extends View {

    private static final int SELECTED_COLOR = Color.parseColor("#B01F24");
    private static final int NORMAL_COLOR = Color.parseColor("#333333");
    private static final String TAG = "ailibin";
    /**
     * 触摸事件
     */
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    /**
     * 26个字母
     */
    public static String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};
    public static List<String> b = new ArrayList<>();
    /**
     * 选中
     */
    private int choose = -1;
    private TextView mTextDialog;
    private boolean isDraw = false;

    public void setTextView(TextView mTextDialog) {
        this.mTextDialog = mTextDialog;
//        setWillNotDraw(false);
    }

    public SideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SideBar(Context context) {
        super(context);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int hSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        // MeasureSpec.AT_MOST 针对布局文件配置的wrap_content
//        if (wSpecMode == MeasureSpec.AT_MOST && hSpecMode == MeasureSpec.AT_MOST) {
////            setMeasuredDimension(300, 300);
//        } else if (wSpecMode == MeasureSpec.AT_MOST) {
////            setMeasuredDimension(300, hSpecSize);
//        } else if (hSpecMode == MeasureSpec.AT_MOST) {
//            setMeasuredDimension(wSpecSize, 300);
//        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * 重写这个方法
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 获取焦点改变背景颜色.
        Paint paint = new Paint();
        int singleHeight = DensityUtil.dp2px( 20f);
        getLayoutParams().height = singleHeight * b.size();
        LogUtil.e(TAG, "onDraw--sideBar" + b);
        // 获取对应宽度
        int width = getWidth();
        LogUtil.e(TAG, "width: " + width + "  height:" + getHeight());
        if (b.size() > 0) {
            for (int i = 0; i < b.size(); i++) {
                paint.setColor(Color.rgb(33, 65, 98));
                paint.setTypeface(Typeface.DEFAULT);
                paint.setAntiAlias(true);
                paint.setTextSize(DensityUtil.dp2px( 12));
                // 选中的状态
                if (i == choose) {
                    paint.setColor(SELECTED_COLOR);
                    //粗体
                    //paint.setFakeBoldText(true);
                } else {
                    paint.setFakeBoldText(false);
                    paint.setColor(NORMAL_COLOR);
                }
                // x坐标等于中间-字符串宽度的一半.
                float xPos = width / 2 - paint.measureText(b.get(i)) / 2;
                //这里yPos要加i + 1,不然第一个字符显示不出来
                float yPos = singleHeight * (i + 1);
                canvas.drawText(b.get(i), xPos, yPos, paint);
                // 重置画笔
                paint.reset();
            }
            //一定要重写这个方法,不然第一次控件的高度绘制不出来导致显示异常
            if (!isDraw) {
                this.requestLayout();
                isDraw = true;
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        // 点击y坐标
        final float y = event.getY();
        final int oldChoose = choose;
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        // 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.
        final int c = (int) (y / getHeight() * b.size());

        switch (action) {
            case MotionEvent.ACTION_UP:
                setBackgroundColor(0x00000000);
                choose = -1;
                invalidate();
                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.INVISIBLE);
                }
                break;
            default:
                //设置按下的背景色
//                setBackgroundResource(R.drawable.sidebar_background);
                if (oldChoose != c) {
                    if (c >= 0 && c < b.size()) {
                        if (listener != null) {
                            listener.onTouchingLetterChanged(b.get(c));
                        }
                        if (mTextDialog != null) {
                            mTextDialog.setText(b.get(c));
                            mTextDialog.setVisibility(View.VISIBLE);
                        }
                        choose = c;
                        invalidate();
                    }
                }
                break;
        }
        return true;
    }

    /**
     * 向外公开的方法
     *
     * @param onTouchingLetterChangedListener
     */
    public void setOnTouchingLetterChangedListener(
            OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    /**
     * 回调接口
     *
     * @author lez
     */
    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String s);
    }
}
