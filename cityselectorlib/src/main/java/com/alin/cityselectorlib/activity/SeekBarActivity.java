package com.alin.cityselectorlib.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alin.cityselectorlib.R;
import com.alin.cityselectorlib.router.RouterPath;
import com.alin.cityselectorlib.util.LogUtil;
import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;

@Route(path = RouterPath.TEST_SEEK_BAR_ACTIVITY)
public class SeekBarActivity extends AppCompatActivity {

    public static final String TAG = "ailibin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_seek_bar);
        initAgeSeekBar();
        initSalarySeekBar();
        initWorkYearSeekBar();

    }

    /**
     * 期望薪资
     */
    private void initSalarySeekBar() {
        final RangeSeekBar mSalarySeekBar = (RangeSeekBar) findViewById(R.id.sbSalary);
        //每五岁一个刻度
        int steps = (int) ((10000f - 2000f) / 1000);
        mSalarySeekBar.setSteps(steps);
        mSalarySeekBar.setRange(2000f, 10000f);
        mSalarySeekBar.setProgress(2000f, 10000f);
//        String[] markArrays = new String[]{"16", "20", "25", "30", "35", "40", "45", "50+"};
//        mSalarySeekBar.setTickMarkTextArray(markArrays);
        mSalarySeekBar.getLeftSeekBar().setIndicatorText("2000以下");
        mSalarySeekBar.getRightSeekBar().setIndicatorText("10000以上");

        mSalarySeekBar.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {

                int leftIntV = (int) (leftValue);
                int RightIntV = (int) (rightValue);
                String leftText = String.valueOf(leftIntV);
                String rightText = String.valueOf(RightIntV);
                LogUtil.e(TAG, "leftValue: " + (int) (leftValue) + "  rightValue:" + (int) (rightValue) + "  isFromUser:" + isFromUser);
                if (RightIntV >= 10000) {
                    view.getRightSeekBar().setIndicatorText("10000以上");
                } else {
                    view.getRightSeekBar().setIndicatorText(rightText);
                }

                if (leftIntV <= 2000) {
                    view.getLeftSeekBar().setIndicatorText("2000以下");
                }else{
                    view.getLeftSeekBar().setIndicatorText(leftText);
                }

            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });
    }

    /**
     * 年龄
     */
    private void initAgeSeekBar() {
        final RangeSeekBar mAgeSeekBar = (RangeSeekBar) findViewById(R.id.sbAge);
        //每五岁一个刻度
        int steps = (int) ((50f - 15f) / 5);
        mAgeSeekBar.setSteps(steps);
        mAgeSeekBar.setRange(16f, 50f);
        mAgeSeekBar.setProgress(16f, 50f);
        String[] markArrays = new String[]{"16", "20", "25", "30", "35", "40", "45", "50+"};
        mAgeSeekBar.setTickMarkTextArray(markArrays);
        mAgeSeekBar.getLeftSeekBar().setIndicatorText("16");
        mAgeSeekBar.getRightSeekBar().setIndicatorText("50+");

        mAgeSeekBar.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
//                if (rightValue < leftValue) {
//                    //右边的不能小于左边的值
//                    mAgeSeekBar.setEnabled(false);
//                } else {
//                    mAgeSeekBar.setEnabled(true);
//                }
                int leftIntV = (int) (leftValue);
                int RightIntV = (int) (rightValue);

                String leftText = String.valueOf(leftIntV);
                String rightText = String.valueOf(RightIntV);
                LogUtil.e(TAG, "leftValue: " + (int) (leftValue) + "  rightValue:" + (int) (rightValue) + "  isFromUser:" + isFromUser);
                if (RightIntV >= 50) {
                    view.getRightSeekBar().setIndicatorText("50+");
                } else {
                    view.getRightSeekBar().setIndicatorText(rightText);
                }
                view.getLeftSeekBar().setIndicatorText(leftText);
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });
    }

    /**
     * 工作年限
     */
    private void initWorkYearSeekBar() {
        final RangeSeekBar sbWorkYearSeekBar = (RangeSeekBar) findViewById(R.id.sbWorkYear);
        //2.5一个刻度,4个步骤
        int steps = (int) ((10 - 0f) / 2.5);
        sbWorkYearSeekBar.setSteps(steps);
        sbWorkYearSeekBar.setRange(0f, 10f);
        sbWorkYearSeekBar.setProgress(0f, 10f);
        sbWorkYearSeekBar.getLeftSeekBar().setIndicatorText("无经验");
        sbWorkYearSeekBar.getRightSeekBar().setIndicatorText("10年以上");

        sbWorkYearSeekBar.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                int leftIntV = (int) (leftValue);
                int RightIntV = (int) (rightValue);

                String leftText = String.valueOf(leftIntV);
                String rightText = String.valueOf(RightIntV);
                LogUtil.e(TAG, "leftValue: " + (int) (leftValue) + "  rightValue:" + (int) (rightValue) + "  isFromUser:" + isFromUser);
                if (RightIntV >= 50) {
                    view.getRightSeekBar().setIndicatorText("50+");
                } else {
                    view.getRightSeekBar().setIndicatorText(rightText);
                }
                view.getLeftSeekBar().setIndicatorText(leftText);
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
