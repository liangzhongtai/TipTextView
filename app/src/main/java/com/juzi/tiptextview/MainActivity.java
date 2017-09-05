package com.juzi.tiptextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.juzi.tiptextview.view.TipLayout;
import com.juzi.tiptextview.view.TipLinearLayout;
import com.juzi.tiptextview.view.TipRadioGroup;
import com.juzi.tiptextview.view.TipTextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    private RadioGroup mDirection1RG;
    private RadioGroup mDirection2RG;
    private RadioGroup mTipColorRG;
    private RadioGroup mTipTextColorRG;
    private RadioGroup mShapeRG;
    private TipTextView mFixationTTV;
    private TipTextView mSurroundTTV;
    private TipTextView mSurroundDrawableTTV;
    private TipTextView mSurroundCVTTV;
    private TipTextView mSurroundCHTTV;
    private TipLayout mFixationBtnTL;
    private TipLayout mFixationIVTL;
    private TipLayout mFixationCBTL;
    private TipRadioGroup mTRG;
    private TipLinearLayout mTLL;

    private SeekBar mTipSurroundSB;
    private SeekBar mOuterRadiusSB;
    private SeekBar mTipRadiusSB;
    private SeekBar mTipTextSiceSB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        mDirection1RG       = (RadioGroup) findViewById(R.id.rg_direction1);
        mDirection2RG       = (RadioGroup) findViewById(R.id.rg_direction2);
        mTipColorRG         = (RadioGroup) findViewById(R.id.rg_tipcolor);
        mTipTextColorRG     = (RadioGroup) findViewById(R.id.rg_tiptextcolor);
        mShapeRG            = (RadioGroup) findViewById(R.id.rg_shape);
        mFixationTTV        = (TipTextView) findViewById(R.id.ttv_fixation);
        mSurroundTTV        = (TipTextView) findViewById(R.id.ttv_surround);
        mSurroundDrawableTTV= (TipTextView) findViewById(R.id.ttv_surround_with_drawable);
        mSurroundCVTTV      = (TipTextView) findViewById(R.id.ttv_surround_center_v);
        mSurroundCHTTV      = (TipTextView) findViewById(R.id.ttv_surround_center_h);
        mFixationBtnTL      = (TipLayout) findViewById(R.id.tl_btn);
        mFixationIVTL       = (TipLayout) findViewById(R.id.tl_img);
        mFixationCBTL       = (TipLayout) findViewById(R.id.tl_cb);
        mTRG                = (TipRadioGroup) findViewById(R.id.trg_test);
        mTLL                = (TipLinearLayout)findViewById(R.id.tll_test);

        mTipSurroundSB      = (SeekBar) findViewById(R.id.sb_ttv_surround);
        mTipRadiusSB        = (SeekBar) findViewById(R.id.sb_ttv_radius);
        mOuterRadiusSB      = (SeekBar) findViewById(R.id.sb_ttv_outer_radius);
        mTipTextSiceSB      = (SeekBar) findViewById(R.id.sb_ttv_textsice);
        //View类_设置角标文本
        mFixationTTV.setTipCout(8);
        mSurroundTTV.setTipCout(4);
        mSurroundDrawableTTV.setTipCout(9);
        mSurroundCVTTV.setTipCout(11);
        mSurroundCHTTV.setTipCout(7);

        mFixationBtnTL.setTipCout(10);
        mFixationIVTL.setTipCout(6);
        mFixationCBTL.setTipCout(8);
        //ViewGroup类_设置角标文本文本
        //参数1:child索引,不能包含类似分隔线这样宽度极小的View
        //参数2:文本
        mTRG.setTipCout(0, 2);
        mTRG.setTipCout(1, 4);
        mTRG.setTipCout(2, 13);
        mTLL.setTipCout(0, 4);
        //角标文本为0时不会显示角标
        mTLL.setTipCout(1, 0);
        mTLL.setTipCout(2, 8);

        //角标外环宽度
        //数组长度需要和getChildCount()长度一致,并且不能放置宽度太小的child,如分割线
        mTLL.setTipOuterStrokes(new int[]{4, 4, 4});
        //角标颜色
        mTRG.setTipColors(new int[]{R.color.color32Green, R.color.color3bBlue, R.color.colorb3Gray});
        //角标外环颜色
        mTRG.setTipOuterColors(new int[]{R.color.colorffRed, R.color.colorBlack, R.color.color3bBlue});
        //角标文本颜色
        mTRG.setTipTextColors(new int[]{R.color.colorWhite, R.color.colorecRed, R.color.colorBlack});
        //角标文字字号
        mTRG.setTipSices(new float[]{12f, 7f, 17f});
        //角标半径
        mTRG.setTipRadiuses(new int[]{12, 8, 16});
        //角标外环半径
        mTRG.setTipOuterStrokes(new int[]{4, 6, 2});
        //角标环绕间距
        mFixationTTV.setTipSurroundPadding(12);


        //圆角半径
        //统一圆角
        mTRG.setCorner(2);
        //单独圆角
        mTRG.setCorners(new float[]{2, 4, 6});

        //设置角标方向-共8个方向
        mFixationTTV.setDirection(TipTextView.LEFT);
        mFixationTTV.setDirection(TipTextView.TOP);
        mFixationTTV.setDirection(TipTextView.RIGHT);
        mFixationTTV.setDirection(TipTextView.BOTTOM);

        mFixationTTV.setDirection(TipTextView.TOPLEFT);
        mFixationTTV.setDirection(TipTextView.TOPRIGHT);
        mFixationTTV.setDirection(TipTextView.BOTTOMLEFT);
        mFixationTTV.setDirection(TipTextView.BOTTOMRIGHT);

        //设置角标形状
        //圆形
        mFixationTTV.setShape(TipTextView.CIRCLE);
        //矩形
        mFixationTTV.setShape(TipTextView.RECTANGLE);
        //圆角矩形
        mFixationTTV.setShape(TipTextView.ROUND_RECTANGLE);
        //椭圆形
        mFixationTTV.setShape(TipTextView.OVAL);

    }

    private void initListener() {
        //设置角标方向
        mDirection1RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb_top){
                    mFixationTTV.setDirection(TipTextView.TOP);
                    mSurroundTTV.setDirection(TipTextView.TOP);
                    mSurroundDrawableTTV.setDirection(TipTextView.TOP);
                    mSurroundCVTTV.setDirection(TipTextView.TOP);
                    mSurroundCHTTV.setDirection(TipTextView.TOP);
                    mFixationBtnTL.setDirection(TipTextView.TOP);
                    mFixationIVTL.setDirection(TipTextView.TOP);
                    mFixationCBTL.setDirection(TipTextView.TOP);
                    mTRG.setDirection(TipTextView.TOP);
                    mTLL.setDirection(TipTextView.TOP);
                }else if(checkedId==R.id.rb_right){
                    mFixationTTV.setDirection(TipTextView.RIGHT);
                    mSurroundTTV.setDirection(TipTextView.RIGHT);
                    mSurroundDrawableTTV.setDirection(TipTextView.RIGHT);
                    mSurroundCVTTV.setDirection(TipTextView.RIGHT);
                    mSurroundCHTTV.setDirection(TipTextView.RIGHT);
                    mFixationBtnTL.setDirection(TipTextView.RIGHT);
                    mFixationIVTL.setDirection(TipTextView.RIGHT);
                    mFixationCBTL.setDirection(TipTextView.RIGHT);
                    mTRG.setDirection(TipTextView.RIGHT);
                    mTLL.setDirection(TipTextView.RIGHT);
                }else if(checkedId==R.id.rb_bottom){
                    mFixationTTV.setDirection(TipTextView.BOTTOM);
                    mSurroundTTV.setDirection(TipTextView.BOTTOM);
                    mSurroundDrawableTTV.setDirection(TipTextView.BOTTOM);
                    mSurroundCVTTV.setDirection(TipTextView.BOTTOM);
                    mSurroundCHTTV.setDirection(TipTextView.BOTTOM);
                    mFixationBtnTL.setDirection(TipTextView.BOTTOM);
                    mFixationIVTL.setDirection(TipTextView.BOTTOM);
                    mFixationCBTL.setDirection(TipTextView.BOTTOM);
                    mTRG.setDirection(TipTextView.BOTTOM);
                    mTLL.setDirection(TipTextView.BOTTOM);
                }else if(checkedId==R.id.rb_left){
                    mFixationTTV.setDirection(TipTextView.LEFT);
                    mSurroundTTV.setDirection(TipTextView.LEFT);
                    mSurroundDrawableTTV.setDirection(TipTextView.LEFT);
                    mSurroundCVTTV.setDirection(TipTextView.LEFT);
                    mSurroundCHTTV.setDirection(TipTextView.LEFT);
                    mFixationBtnTL.setDirection(TipTextView.LEFT);
                    mFixationIVTL.setDirection(TipTextView.LEFT);
                    mFixationCBTL.setDirection(TipTextView.LEFT);
                    mTRG.setDirection(TipTextView.LEFT);
                    mTLL.setDirection(TipTextView.LEFT);
                }
            }
        });
        //设置角标方向
        mDirection2RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_topleft) {
                    mFixationTTV.setDirection(TipTextView.TOPLEFT);
                    mSurroundTTV.setDirection(TipTextView.TOPLEFT);
                    mSurroundDrawableTTV.setDirection(TipTextView.TOPLEFT);
                    mSurroundCVTTV.setDirection(TipTextView.TOPLEFT);
                    mSurroundCHTTV.setDirection(TipTextView.TOPLEFT);
                    mFixationBtnTL.setDirection(TipTextView.TOPLEFT);
                    mFixationIVTL.setDirection(TipTextView.TOPLEFT);
                    mFixationCBTL.setDirection(TipTextView.TOPLEFT);
                    mTRG.setDirection(TipTextView.TOPLEFT);
                    mTLL.setDirection(TipTextView.TOPLEFT);
                } else if (checkedId == R.id.rb_topright) {
                    mFixationTTV.setDirection(TipTextView.TOPRIGHT);
                    mSurroundTTV.setDirection(TipTextView.TOPRIGHT);
                    mSurroundDrawableTTV.setDirection(TipTextView.TOPRIGHT);
                    mSurroundCVTTV.setDirection(TipTextView.TOPRIGHT);
                    mSurroundCHTTV.setDirection(TipTextView.TOPRIGHT);
                    mFixationBtnTL.setDirection(TipTextView.TOPRIGHT);
                    mFixationIVTL.setDirection(TipTextView.TOPRIGHT);
                    mFixationCBTL.setDirection(TipTextView.TOPRIGHT);
                    mTRG.setDirection(TipTextView.TOPRIGHT);
                    mTLL.setDirection(TipTextView.TOPRIGHT);
                } else if (checkedId == R.id.rb_bottomleft) {
                    mFixationTTV.setDirection(TipTextView.BOTTOMLEFT);
                    mSurroundTTV.setDirection(TipTextView.BOTTOMLEFT);
                    mSurroundDrawableTTV.setDirection(TipTextView.BOTTOMLEFT);
                    mSurroundCVTTV.setDirection(TipTextView.BOTTOMLEFT);
                    mSurroundCHTTV.setDirection(TipTextView.BOTTOMLEFT);
                    mFixationBtnTL.setDirection(TipTextView.BOTTOMLEFT);
                    mFixationIVTL.setDirection(TipTextView.BOTTOMLEFT);
                    mFixationCBTL.setDirection(TipTextView.BOTTOMLEFT);
                    mTRG.setDirection(TipTextView.BOTTOMLEFT);
                    mTLL.setDirection(TipTextView.BOTTOMLEFT);
                } else if (checkedId == R.id.rb_bottomright) {
                    mFixationTTV.setDirection(TipTextView.BOTTOMRIGHT);
                    mSurroundTTV.setDirection(TipTextView.BOTTOMRIGHT);
                    mSurroundDrawableTTV.setDirection(TipTextView.BOTTOMRIGHT);
                    mSurroundCVTTV.setDirection(TipTextView.BOTTOMRIGHT);
                    mSurroundCHTTV.setDirection(TipTextView.BOTTOMRIGHT);
                    mFixationBtnTL.setDirection(TipTextView.BOTTOMRIGHT);
                    mFixationIVTL.setDirection(TipTextView.BOTTOMRIGHT);
                    mFixationCBTL.setDirection(TipTextView.BOTTOMRIGHT);
                    mTRG.setDirection(TipTextView.BOTTOMRIGHT);
                    mTLL.setDirection(TipTextView.BOTTOMRIGHT);
                }
            }
        });
        //设置角标颜色
        mTipColorRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_red) {
                    mFixationTTV.setTipColor(R.color.colorecRed);
                    mSurroundTTV.setTipColor(R.color.colorecRed);
                    mSurroundDrawableTTV.setTipColor(R.color.colorecRed);
                    mSurroundCVTTV.setTipColor(R.color.colorecRed);
                    mSurroundCHTTV.setTipColor(R.color.colorecRed);
                    mFixationBtnTL.setTipColor(R.color.colorecRed);
                    mFixationIVTL.setTipColor(R.color.colorecRed);
                    mFixationCBTL.setTipColor(R.color.colorecRed);
                    mTRG.setTipColor(R.color.colorecRed);
                    mTLL.setTipColor(R.color.colorecRed);
                } else if (checkedId == R.id.rb_green) {
                    mFixationTTV.setTipColor(R.color.color32Green);
                    mSurroundTTV.setTipColor(R.color.color32Green);
                    mSurroundDrawableTTV.setTipColor(R.color.color32Green);
                    mSurroundCVTTV.setTipColor(R.color.color32Green);
                    mSurroundCHTTV.setTipColor(R.color.color32Green);
                    mFixationBtnTL.setTipColor(R.color.color32Green);
                    mFixationIVTL.setTipColor(R.color.color32Green);
                    mFixationCBTL.setTipColor(R.color.color32Green);
                    mTRG.setTipColor(R.color.color32Green);
                    mTLL.setTipColor(R.color.color32Green);
                } else if (checkedId == R.id.rb_black) {
                    mFixationTTV.setTipColor(R.color.colorBlack);
                    mSurroundTTV.setTipColor(R.color.colorBlack);
                    mSurroundDrawableTTV.setTipColor(R.color.colorBlack);
                    mSurroundCVTTV.setTipColor(R.color.colorBlack);
                    mSurroundCHTTV.setTipColor(R.color.colorBlack);
                    mFixationBtnTL.setTipColor(R.color.colorBlack);
                    mFixationIVTL.setTipColor(R.color.colorBlack);
                    mFixationCBTL.setTipColor(R.color.colorBlack);
                    mTRG.setTipColor(R.color.colorBlack);
                    mTLL.setTipColor(R.color.colorBlack);
                } else if (checkedId == R.id.rb_blue) {
                    mFixationTTV.setTipColor(R.color.color3bBlue);
                    mSurroundTTV.setTipColor(R.color.color3bBlue);
                    mSurroundDrawableTTV.setTipColor(R.color.color3bBlue);
                    mSurroundCVTTV.setTipColor(R.color.color3bBlue);
                    mSurroundCHTTV.setTipColor(R.color.color3bBlue);
                    mFixationBtnTL.setTipColor(R.color.color3bBlue);
                    mFixationIVTL.setTipColor(R.color.color3bBlue);
                    mFixationCBTL.setTipColor(R.color.color3bBlue);
                    mTRG.setTipColor(R.color.color3bBlue);
                    mTLL.setTipColor(R.color.color3bBlue);
                }
            }
        });
        //设置角标文本颜色
        mTipTextColorRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_white_text) {
                    mFixationTTV.setTipTextColor(R.color.colorWhite);
                    mSurroundTTV.setTipTextColor(R.color.colorWhite);
                    mSurroundDrawableTTV.setTipTextColor(R.color.colorWhite);
                    mSurroundCVTTV.setTipTextColor(R.color.colorWhite);
                    mSurroundCHTTV.setTipTextColor(R.color.colorWhite);
                    mFixationBtnTL.setTipTextColor(R.color.colorWhite);
                    mFixationIVTL.setTipTextColor(R.color.colorWhite);
                    mFixationCBTL.setTipTextColor(R.color.colorWhite);
                    mTRG.setTipTextColor(R.color.colorWhite);
                    mTLL.setTipTextColor(R.color.colorWhite);
                } else if (checkedId == R.id.rb_gray_text) {
                    mFixationTTV.setTipTextColor(R.color.colorb3Gray);
                    mSurroundTTV.setTipTextColor(R.color.colorb3Gray);
                    mSurroundDrawableTTV.setTipTextColor(R.color.colorb3Gray);
                    mSurroundCVTTV.setTipTextColor(R.color.colorb3Gray);
                    mSurroundCHTTV.setTipTextColor(R.color.colorb3Gray);
                    mFixationBtnTL.setTipTextColor(R.color.colorb3Gray);
                    mFixationIVTL.setTipTextColor(R.color.colorb3Gray);
                    mFixationCBTL.setTipTextColor(R.color.colorb3Gray);
                    mTRG.setTipTextColor(R.color.colorb3Gray);
                    mTLL.setTipTextColor(R.color.colorb3Gray);
                } else if (checkedId == R.id.rb_black_text) {
                    mFixationTTV.setTipTextColor(R.color.colorBlack);
                    mSurroundTTV.setTipTextColor(R.color.colorBlack);
                    mSurroundDrawableTTV.setTipTextColor(R.color.colorBlack);
                    mSurroundCVTTV.setTipTextColor(R.color.colorBlack);
                    mSurroundCHTTV.setTipTextColor(R.color.colorBlack);
                    mFixationBtnTL.setTipTextColor(R.color.colorBlack);
                    mFixationIVTL.setTipTextColor(R.color.colorBlack);
                    mFixationCBTL.setTipTextColor(R.color.colorBlack);
                    mTRG.setTipTextColor(R.color.colorBlack);
                    mTLL.setTipTextColor(R.color.colorBlack);
                } else if (checkedId == R.id.rb_brown_text) {
                    mFixationTTV.setTipTextColor(R.color.colorb4Brown);
                    mSurroundTTV.setTipTextColor(R.color.colorb4Brown);
                    mSurroundDrawableTTV.setTipTextColor(R.color.colorb4Brown);
                    mSurroundCVTTV.setTipTextColor(R.color.colorb4Brown);
                    mSurroundCHTTV.setTipTextColor(R.color.colorb4Brown);
                    mFixationBtnTL.setTipTextColor(R.color.colorb4Brown);
                    mFixationIVTL.setTipTextColor(R.color.colorb4Brown);
                    mFixationCBTL.setTipTextColor(R.color.colorb4Brown);
                    mTRG.setTipTextColor(R.color.colorb4Brown);
                    mTLL.setTipTextColor(R.color.colorb4Brown);
                }
            }
        });
        //设置角标形状
        mShapeRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_circle) {
                    mFixationTTV.setShape(TipTextView.CIRCLE);
                    mSurroundTTV.setShape(TipTextView.CIRCLE);
                    mSurroundDrawableTTV.setShape(TipTextView.CIRCLE);
                    mSurroundCVTTV.setShape(TipTextView.CIRCLE);
                    mSurroundCHTTV.setShape(TipTextView.CIRCLE);
                    mFixationBtnTL.setShape(TipLayout.CIRCLE);
                    mFixationIVTL.setShape(TipLayout.CIRCLE);
                    mFixationCBTL.setShape(TipLayout.CIRCLE);
                    mTRG.setShape(TipRadioGroup.CIRCLE);
                    mTLL.setShape(TipRadioGroup.CIRCLE);
                }else if (checkedId == R.id.rb_rectangle) {
                    mFixationTTV.setShape(TipTextView.RECTANGLE);
                    mSurroundTTV.setShape(TipTextView.RECTANGLE);
                    mSurroundDrawableTTV.setShape(TipTextView.RECTANGLE);
                    mSurroundCVTTV.setShape(TipTextView.RECTANGLE);
                    mSurroundCHTTV.setShape(TipTextView.RECTANGLE);
                    mFixationBtnTL.setShape(TipLayout.RECTANGLE);
                    mFixationIVTL.setShape(TipLayout.RECTANGLE);
                    mFixationCBTL.setShape(TipLayout.RECTANGLE);
                    mTRG.setShape(TipRadioGroup.RECTANGLE);
                    mTLL.setShape(TipRadioGroup.RECTANGLE);
                } else if (checkedId == R.id.rb_round_rectangle) {
                    mFixationTTV.setShape(TipTextView.ROUND_RECTANGLE);
                    mSurroundTTV.setShape(TipTextView.ROUND_RECTANGLE);
                    mSurroundDrawableTTV.setShape(TipTextView.ROUND_RECTANGLE);
                    mSurroundCVTTV.setShape(TipTextView.ROUND_RECTANGLE);
                    mSurroundCHTTV.setShape(TipTextView.ROUND_RECTANGLE);
                    mFixationBtnTL.setShape(TipLayout.ROUND_RECTANGLE);
                    mFixationIVTL.setShape(TipLayout.ROUND_RECTANGLE);
                    mFixationCBTL.setShape(TipLayout.ROUND_RECTANGLE);
                    mTRG.setShape(TipRadioGroup.ROUND_RECTANGLE);
                    mTLL.setShape(TipRadioGroup.ROUND_RECTANGLE);
                } else {
                    mFixationTTV.setShape(TipTextView.OVAL);
                    mSurroundTTV.setShape(TipTextView.OVAL);
                    mSurroundDrawableTTV.setShape(TipTextView.OVAL);
                    mSurroundCVTTV.setShape(TipTextView.OVAL);
                    mSurroundCHTTV.setShape(TipTextView.OVAL);
                    mFixationBtnTL.setShape(TipLayout.OVAL);
                    mFixationIVTL.setShape(TipLayout.OVAL);
                    mFixationCBTL.setShape(TipLayout.OVAL);
                    mTRG.setShape(TipRadioGroup.OVAL);
                    mTLL.setShape(TipRadioGroup.OVAL);
                }
            }
        });
        mTipSurroundSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //设置角标的环绕间距
                float newSurroundPadding1 = progress / 100f * mFixationTTV.getOriTipSurroundPadding();
                mFixationTTV.setTipSurroundPadding((int) newSurroundPadding1);
                float newSurroundPadding2 = progress / 100f * mSurroundTTV.getOriTipSurroundPadding();
                mSurroundTTV.setTipSurroundPadding((int) newSurroundPadding2);
                float newSurroundPadding3 = progress / 100f * mSurroundDrawableTTV.getOriTipSurroundPadding();
                mSurroundDrawableTTV.setTipSurroundPadding((int) newSurroundPadding3);
                float newSurroundPadding4 = progress / 100f * mSurroundCVTTV.getOriTipSurroundPadding();
                mSurroundCVTTV.setTipSurroundPadding((int) newSurroundPadding4);
                float newSurroundPadding5 = progress / 100f * mSurroundCHTTV.getOriTipSurroundPadding();
                mSurroundCHTTV.setTipSurroundPadding((int) newSurroundPadding5);
                float newSurroundPadding6 = progress / 100f * mFixationBtnTL.getOriTipSurroundPadding();
                mFixationBtnTL.setTipSurroundPadding((int) newSurroundPadding6);
                float newSurroundPadding7 = progress / 100f * mFixationIVTL.getOriTipSurroundPadding();
                mFixationIVTL.setTipSurroundPadding((int) newSurroundPadding7);
                float newSurroundPadding8 = progress / 100f * mFixationCBTL.getOriTipSurroundPadding();
                mFixationCBTL.setTipSurroundPadding((int) newSurroundPadding8);
                float newSurroundPadding9 = progress / 100f * mTRG.getOriTipSurroundPadding();
                mTRG.setTipSurroundPadding((int) newSurroundPadding9);
                float newSurroundPadding10 = progress / 100f * mTLL.getOriTipSurroundPadding();
                mTLL.setTipSurroundPadding((int) newSurroundPadding10);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

            mOuterRadiusSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()

                  {
                      @Override
                      public void onProgressChanged(SeekBar seekBar, int
                              progress, boolean fromUser) {
                          //设置角标的外环半径
                          float newTipOuterRadius1 = progress / 100f *
                                  mFixationTTV.getOriTipOuterStorke();
                          mFixationTTV.setTipOuterRadius((int) newTipOuterRadius1);
                          float newTipOuterRadius2 = progress / 100f *
                                  mSurroundTTV.getOriTipOuterStorke();
                          mSurroundTTV.setTipOuterRadius((int) newTipOuterRadius2);
                          float newTipOuterRadius3 = progress / 100f *
                                  mSurroundDrawableTTV.getOriTipOuterStorke();
                          mSurroundDrawableTTV.setTipOuterRadius((int)
                                  newTipOuterRadius3);
                          float newTipOuterRadius4 = progress / 100f *
                                  mSurroundCVTTV.getOriTipOuterStorke();
                          mSurroundCVTTV.setTipOuterRadius((int)
                                  newTipOuterRadius4);
                          float newTipOuterRadius5 = progress / 100f *
                                  mSurroundCHTTV.getOriTipOuterStorke();
                          mSurroundCHTTV.setTipOuterRadius((int)
                                  newTipOuterRadius5);
                          float newTipOuterRadius6 = progress / 100f *
                                  mFixationBtnTL.getOriTipOuterStorke();
                          mFixationBtnTL.setTipOuterRadius((int)
                                  newTipOuterRadius6);
                          float newTipOuterRadius7 = progress / 100f *
                                  mFixationIVTL.getOriTipOuterStorke();
                          mFixationIVTL.setTipOuterRadius((int) newTipOuterRadius7);
                          float newTipOuterRadius8 = progress / 100f *
                                  mFixationCBTL.getOriTipOuterStorke();
                          mFixationCBTL.setTipOuterRadius((int) newTipOuterRadius8);
                          float newTipOuterRadius9 = progress / 100f * mTRG
                                  .getOriTipOuterStorke();
                          mTRG.setTipOuterRadius((int) newTipOuterRadius9);
                          float newTipOuterRadius10 = progress / 100f * mTLL
                                  .getOriTipOuterStorke();
                          mTLL.setTipOuterRadius((int) newTipOuterRadius10);
                      }

                      @Override
                      public void onStartTrackingTouch(SeekBar seekBar) {
                      }

                      @Override
                      public void onStopTrackingTouch(SeekBar seekBar) {
                      }
                  }

            );
            mTipRadiusSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()

                {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress,
                                                  boolean fromUser) {
                        //设置角标半径
                        float newTipRadius1 = progress / 100f * mFixationTTV
                                .getOriTipRadius();
                        mFixationTTV.setTipRadius((int) newTipRadius1);
                        float newTipRadius2 = progress / 100f * mSurroundTTV
                                .getOriTipRadius();
                        mSurroundTTV.setTipRadius((int) newTipRadius2);
                        float newTipRadius3 = progress / 100f *
                                mSurroundDrawableTTV.getOriTipRadius();
                        mSurroundDrawableTTV.setTipRadius((int) newTipRadius3);
                        float newTipRadius4 = progress / 100f * mSurroundCVTTV
                                .getOriTipRadius();
                        mSurroundCVTTV.setTipRadius((int) newTipRadius4);
                        float newTipRadius5 = progress / 100f * mSurroundCHTTV
                                .getOriTipRadius();
                        mSurroundCHTTV.setTipRadius((int) newTipRadius5);
                        float newTipRadius6 = progress / 100f * mFixationBtnTL.getOriTipRadius();
                        mFixationBtnTL.setTipRadius((int) newTipRadius6);
                        float newTipRadius7 = progress / 100f * mFixationIVTL.getOriTipRadius();
                        mFixationIVTL.setTipRadius((int) newTipRadius7);
                        float newTipRadius8 = progress / 100f * mFixationCBTL.getOriTipRadius();
                        mFixationCBTL.setTipRadius((int) newTipRadius8);
                        float newTipRadius9 = progress / 100f * mTRG.getOriTipRadius();
                        mTRG.setTipRadius((int) newTipRadius9);
                        float newTipRadius10 = progress / 100f * mTLL.getOriTipRadius();
                        mTLL.setTipRadius((int) newTipRadius10);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }

            );
            mTipTextSiceSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()

                  {
                      @Override
                      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                          //设置角标文本字号
                          float newTipTextSice1 = progress / 100f * mFixationTTV.getOriTipSice();
                          mFixationTTV.setTipSice(newTipTextSice1);
                          float newTipTextSice2 = progress / 100f * mSurroundTTV.getOriTipSice();
                          mSurroundTTV.setTipSice(newTipTextSice2);
                          float newTipTextSice3 = progress / 100f * mSurroundDrawableTTV.getOriTipSice();
                          mSurroundDrawableTTV.setTipSice(newTipTextSice3);
                          float newTipTextSice4 = progress / 100f * mSurroundCVTTV.getOriTipSice();
                          mSurroundCVTTV.setTipSice(newTipTextSice4);
                          float newTipTextSice5 = progress / 100f * mSurroundCHTTV.getOriTipSice();
                          mSurroundCHTTV.setTipSice(newTipTextSice5);
                          float newTipTextSice6 = progress / 100f * mFixationBtnTL.getOriTipSice();
                          mFixationBtnTL.setTipSice(newTipTextSice6);
                          float newTipTextSice7 = progress / 100f * mFixationIVTL.getOriTipSice();
                          mFixationIVTL.setTipSice(newTipTextSice7);
                          float newTipTextSice8 = progress / 100f * mFixationCBTL.getOriTipSice();
                          mFixationCBTL.setTipSice(newTipTextSice8);
                          float newTipTextSice9 = progress / 100f * mTRG.getOriTipSice();
                          mTRG.setTipSice(newTipTextSice9);
                          float newTipTextSice10 = progress / 100f * mTLL.getOriTipSice();
                          mTLL.setTipSice(newTipTextSice10);
                      }

                      @Override
                      public void onStartTrackingTouch(SeekBar seekBar) {
                      }

                      @Override
                      public void onStopTrackingTouch(SeekBar seekBar) {
                      }
                  }

            );
        }

    }
