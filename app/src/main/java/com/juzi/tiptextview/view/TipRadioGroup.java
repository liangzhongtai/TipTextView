package com.juzi.tiptextview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.juzi.tiptextview.R;
import com.juzi.tiptextview.global.Global;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangzhongtai on 2017/2/20.
 */
public class TipRadioGroup extends RadioGroup {
    public final static int TIP_VISIBLE = 1;
    public final static int TIP_GONE = 2;
    private ArrayList<View> views = new ArrayList<>();
    private float radioCount;
    private float totalWeight;
    private float[] weights;
    private float totalWidth;
    private float[] widths;
    private float totalHeight;
    private float[] heights;
    private Paint paint = new Paint();
    private int tipType;
    private int tipSurroundPadding;
    private int oriTipSurroundPadding;
    private int tipColor;
    private int[] tipColors;
    private int tipOuterColor;
    private int[] tipOuterColors;
    private int tipTextColor;
    private int[] tipTextColors;
    private int tipRadius;
    private int oriTipOuterStorke;
    private int[] tipRadiuses;
    private int tipOuterStroke;
    private int[] tipOuterStrokes;
    private int oriTipRadius;
    private int direction;
    private float tipSice;
    private float[] tipSices;
    private float oriTipSice;
    private ArrayList<String> tips = new ArrayList<>();
    private float tipRdius2TipSice;
    private float[] tipRdius2TipSices;
    private int max;
    private int[] maxes;
    private int shape;
    private int[] shapes;
    private float corner;
    private float[] corners;
    private float rHeight;
    private float[] rHeights;
    private float rWidth;
    private float[] rWidths;
    public final static int LEFT        = 0;
    public final static int TOP         = 1;
    public final static int RIGHT       = 2;
    public final static int BOTTOM      = 3;
    public final static int TOPRIGHT    = 4;
    public final static int BOTTOMRIGHT = 5;
    public final static int TOPLEFT     = 6;
    public final static int BOTTOMLEFT  = 7;

    public final static int FIXATION    = 0;
    public final static int SURROUND    = 1;
    public final static int SURROUND_V = 2;
    public final static int SURROUND_H = 3;

    public final static int CIRCLE          = 0;
    public final static int ROUND_RECTANGLE = 1;
    public final static int RECTANGLE       = 2;
    public final static int OVAL            = 3;

    public final static int ONLY_WWIGHT = 0;
    public final static int COMMON_MIX  = 1;
    public TipRadioGroup(Context context) {
        this(context, null);
    }
    public TipRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    public TipRadioGroup setViews(ArrayList<View> views,ArrayList<String> tips,int marginLeft,int marginTop,int
            marginRight,int
            marginBottom) {
        removeAllViews();
        this.views = views;
        this.tips = tips;
        LinearLayout.LayoutParams params;
        setWeightSum(views.size());
        int orientation = getOrientation();
        if(orientation==HORIZONTAL){
            params = new LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
        }else{
            params = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);
        }
        params.weight = 1;
        params.setMargins(Global.dp2px(marginLeft),Global.dp2px(marginTop),Global.dp2px(marginRight),Global
                .dp2px(marginBottom));
        params.gravity = Gravity.CENTER;
        radioCount = Float.valueOf(views.size());
        for(int i=0;i<radioCount;i++){
            addView(views.get(i),i, params);
        }
        return this;
    }

    public TipRadioGroup setTipColorLists(int[] tipColors,int[] tipTextColors){
        int tipLength = tipColors.length;
        int tipTextLength = tipTextColors.length;
        this.tipColors = new int[tipLength];
        this.tipTextColors = new int[tipTextLength];
        for(int i=0;i<tipLength;i++){
            this.tipColors[i]=getResources().getColor(tipColors[i]);
        }
        for(int i=0;i<tipTextLength;i++){
            this.tipTextColors[i]=getResources().getColor(tipTextColors[i]);
        }
        invalidate();
        return this;
    }

    public TipRadioGroup setTipAndTextColor(int tipColor,int tipTextColor){
        this.tipColor = getResources().getColor(tipColor);
        this.tipTextColor = getResources().getColor(tipTextColor);
        tipColors = null;
        tipTextColors = null;
        invalidate();
        return this;
    }

    public TipRadioGroup setTipSice(int tipSice){
        this.tipSice = Global.sp2px(tipSice);
        tipRdius2TipSice = this.tipSice==0?0f:this.tipRadius/this.tipSice;
        tipSices = null;
        tipRdius2TipSices = null;
        invalidate();
        return  this;
    }

    public float[] getTipSices() {
        return tipSices;
    }

    public TipRadioGroup setTipSices(float[] tipSices) {
        int length = tipSices.length;
        this.tipSices = new float[length];
        for (int i = 0; i < length; i++) {
            this.tipSices[i] = Global.sp2px(tipSices[i]);
        }
        if(tipRadiuses!=null&&tipRadiuses.length==length) {
            tipRdius2TipSices = new float[length];
            for (int i = 0; i < length; i++) {
                tipRdius2TipSices[i] =  this.tipSices[i]==0?0:(tipRadiuses[i] / this.tipSices[i]);
            }
        }else{
            tipRdius2TipSices = new float[length];
            for (int i = 0; i < length; i++) {
                tipRdius2TipSices[i] = 1f;
            }
        }
        invalidate();
        return this;
    }

    public TipRadioGroup setTipSices(int index,float tipSice) {
        tipSices[index] = Global.sp2px(tipSice);
        tipRdius2TipSices= tipRdius2TipSices==null?new float[tipSices.length]:tipRdius2TipSices;
        if(tipRadiuses==null){
            tipRdius2TipSices[index] = 1f;
        }else{
            tipRdius2TipSices[index] = tipSices[index]==0?0:tipRadiuses[index] / tipSices[index];
        }
        invalidate();
        return this;
    }

    public TipRadioGroup setTipRadiuses(int[] tipRadiuses) {
        int length = tipRadiuses.length;
        this.tipRadiuses = new int[length];
        for (int i = 0; i < length; i++) {
            this.tipRadiuses[i] = Global.dp2px(tipRadiuses[i]);
        }
        if(tipSices!=null&&tipSices.length==length) {
            tipRdius2TipSices = new float[length];
            for (int i = 0; i < length; i++) {
                tipRdius2TipSices[i] = tipSices[i]==0?0f:this.tipRadiuses[i] / tipSices[i];
            }
        }else{
            tipRdius2TipSices = new float[length];
            for (int i = 0; i < length; i++) {
                tipRdius2TipSices[i] =  1f;
            }
        }
        invalidate();
        return this;
    }

    public TipRadioGroup setTipRadiuses(int index,int tipRadius) {
        this.tipRadiuses[index]=Global.dp2px(tipRadius);
        tipRdius2TipSices= tipRdius2TipSices==null?new float[tipRadiuses.length]:tipRdius2TipSices;
        if(tipSices==null){
            tipRdius2TipSices[index]= 1f;
        }else{
            tipRdius2TipSices[index]= tipSices[index]==0?0f:this.tipRadiuses[index] / tipSices[index];
        }
        invalidate();
        return this;
    }

    public TipRadioGroup setTipOuterStrokes(int[] tipOuterStrokes) {
        int length = tipOuterStrokes.length;
        this.tipOuterStrokes = new int[length];
        for (int i = 0; i < length; i++) {
            this.tipOuterStrokes[i] = Global.dp2px(tipOuterStrokes[i]);
        }
        invalidate();
        return this;
    }

    public TipRadioGroup setTipOuterStrokes(int index,int tipOuterStroke) {
        this.tipOuterStrokes[index]=Global.dp2px(tipOuterStroke);
        invalidate();
        return this;
    }

    public TipRadioGroup setTipSurroundPaddings(int tipSurroundPadding){
        this.tipSurroundPadding = tipSurroundPadding;
        invalidate();
        return this;
    }

    public TipRadioGroup setTipCout(int index,int tip){
        if(tip<1){
            tips.set(index,"");
        }else {
            tips.set(index,tip + "");
        }
        invalidate();
        return this;
    }
    public TipRadioGroup setTipText(int index,String tip){
        if(TextUtils.isEmpty(tip)){
            tips.set(index,"");
        }else {
            tips.set(index,tip);
        }
        invalidate();
        return this;
    }

    public TipRadioGroup setTips(ArrayList<String> tips) {
        this.tips = tips;
        invalidate();
        return this;
    }

    public TipRadioGroup setDirection(int direction) {
        this.direction = direction;
        invalidate();
        return this;
    }

    public TipRadioGroup setTipColor(int tipColor) {
        this.tipColor = getResources().getColor(tipColor);
        tipColors = null;
        invalidate();
        return this;
    }

    public TipRadioGroup setTipTextColor(int tipTextColor) {
        this.tipTextColor = getResources().getColor(tipTextColor);
        tipTextColors = null;
        invalidate();
        return this;
    }

    public TipRadioGroup setTipColors(int[] tipColors) {
        int tipLength = tipColors.length;
        this.tipColors = new int[tipLength];
        for(int i=0;i<tipLength;i++){
            this.tipColors[i]=getResources().getColor(tipColors[i]);
        }
        invalidate();
        return this;
    }

    public TipRadioGroup setTipOuterColors(int[] tipOuterColors) {
        int tipOuterLength = tipOuterColors.length;
        this.tipOuterColors = new int[tipOuterLength];
        for(int i=0;i<tipOuterLength;i++){
            this.tipOuterColors[i]=getResources().getColor(tipOuterColors[i]);
        }
        invalidate();
        return this;
    }

    public TipRadioGroup setTipTextColors(int[] tipTextColors) {
        int tipTextLength = tipTextColors.length;
        this.tipTextColors = new int[tipTextLength];
        for(int i=0;i<tipTextLength;i++){
            this.tipTextColors[i] = getResources().getColor(tipTextColors[i]);
        }
        invalidate();
        return this;
    }

    public TipRadioGroup setTipColors(int index,int tipColor) {
        this.tipColors[index] = getResources().getColor(tipColor);
        invalidate();
        return this;
    }

    public TipRadioGroup setTipOuterColors(int index,int tipOuterColor) {
        this.tipOuterColors[index] = getResources().getColor(tipOuterColor);
        invalidate();
        return this;
    }

    public TipRadioGroup setTipTextColors(int index,int tipTextColor) {
        this.tipTextColors[index] = getResources().getColor(tipTextColor);
        invalidate();
        return this;
    }

    public TipRadioGroup setTipRadius(int tipRadius) {
        this.tipRadius = Global.dp2px(tipRadius);
        tipRdius2TipSice = tipSice==0?0f:this.tipRadius/tipSice;
        tipRadiuses = null;
        tipRdius2TipSices = null;
        invalidate();
        return this;
    }

    public TipRadioGroup setTipOuterRadius(int tipOuterStroke) {
        this.tipOuterStroke = Global.dp2px(tipOuterStroke);
        tipOuterStrokes = null;
        invalidate();
        return this;
    }

    public TipRadioGroup setTipSice(float tipSice) {
        this.tipSice = Global.sp2px(tipSice);
        tipRdius2TipSice = this.tipSice==0?0f:this.tipRadius/ this.tipSice;
        tipSices = null;
        tipRdius2TipSices = null;
        invalidate();
        return this;
    }

    public TipRadioGroup setTipSurroundPadding(int tipSurroundPadding) {
        this.tipSurroundPadding = tipSurroundPadding;
        invalidate();
        return this;
    }

    public TipRadioGroup setMax(int max) {
        this.max = max;
        maxes = null;
        invalidate();
        return this;
    }

    public TipRadioGroup setMaxes(int[] maxes) {
        this.maxes = maxes;
        invalidate();
        return this;
    }

    public TipRadioGroup setMaxes(int index, int max) {
        this.maxes[index] = max;
        invalidate();
        return this;
    }

    public TipRadioGroup setShape(int shape) {
        this.shape = shape;
        shapes = null;
        invalidate();
        return this;
    }

    public TipRadioGroup setShapes(int[] shapes) {
        this.shapes = shapes;
        invalidate();
        return this;
    }

    public TipRadioGroup setCorner(float corner) {
        this.corner = corner;
        corners = null;
        invalidate();
        return this;
    }

    public TipRadioGroup setCorners(float[] corners) {
        this.corners = corners;
        invalidate();
        return this;
    }

    public TipRadioGroup setRHeight(float rHeight) {
        this.rHeight = rHeight;
        rHeights = null;
        invalidate();
        return this;
    }

    public TipRadioGroup setRHeights(float[] rHeights) {
        this.rHeights = rHeights;
        invalidate();
        return this;
    }

    public TipRadioGroup setRWidth(float rWidth) {
        this.rWidth = rWidth;
        rWidths = null;
        invalidate();
        return this;
    }

    public TipRadioGroup setRWidths(float[] rWidths) {
        this.rWidths = rWidths;
        invalidate();
        return this;
    }

    public int getTipType() {
        return tipType;
    }

    public int getTipColor() {
        return tipColor;
    }

    public int[] getTipColors() {
        return tipColors;
    }

    public int getTipOuterColor() {
        return tipOuterColor;
    }

    public int[] getTipOuterColors() {
        return tipOuterColors;
    }

    public int getTipTextColor() {
        return tipTextColor;
    }

    public int[] getTipTextColors() {
        return tipTextColors;
    }

    public int getDirection() {
        return direction;
    }

    public ArrayList<String> getTips() {
        return tips;
    }

    public int getTipRadius() {
        return tipRadius;
    }

    public int getTipOuterStroke() {
        return tipOuterStroke;
    }

    public int[] getTipOuterStrokes() {
        return tipOuterStrokes;
    }

    public int[] getTipRadiuses() {
        return tipRadiuses;
    }

    public int getOriTipRadius() {
        return oriTipRadius;
    }

    public float getTipSice() {
        return tipSice;
    }

    public float getOriTipSice() {
        return oriTipSice;
    }

    public int getTipSurroundPadding() {
        return tipSurroundPadding;
    }

    public int getMax() {
        return max;
    }

    public int[] getMaxes() {
        return maxes;
    }

    public int getShape() {
        return shape;
    }

    public int[] getShapes() {
        return shapes;
    }

    public float getCorner() {
        return corner;
    }

    public float[] getCorners() {
        return corners;
    }

    public float getRHeight() {
        return rHeight;
    }

    public float[] getRHeights() {
        return rHeights;
    }

    public float getRWidth() {
        return rWidth;
    }

    public float[] getRWidths() {
        return rWidths;
    }

    public int getOriTipSurroundPadding() {
        return oriTipSurroundPadding;
    }

    public int getOriTipOuterStorke() {
        return oriTipOuterStorke;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @SuppressWarnings("ResourceType")
    public void init(AttributeSet attrs){
        paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setDither(true);
        paint.setAntiAlias(true);
        if(attrs != null) {
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.TipRadioGroup);
            tipType = array.getInt(R.styleable.TipRadioGroup_trg_type, FIXATION);
            tipSurroundPadding = Global.dp2px(array.getInt(R.styleable.TipRadioGroup_trg_surround_padding, 2));
            oriTipSurroundPadding = tipSurroundPadding;
            tipColor = array.getColor(R.styleable.TipRadioGroup_trg_color, getResources().getColor(R.color.colorecRed));
            tipOuterColor = array.getColor(R.styleable.TipRadioGroup_trg_outer_color, getResources().getColor(R.color
                    .colorffRed));
            tipTextColor = array.getColor(R.styleable.TipRadioGroup_trg_textcolor, getResources().getColor(R.color
                    .colorWhite));
            direction = array.getInt(R.styleable.TipRadioGroup_trg_direction, TOPRIGHT);
            tipRadius = Global.dp2px(array.getInt(R.styleable.TipRadioGroup_trg_radius, 6));
            oriTipRadius = tipRadius;
            tipOuterStroke = Global.dp2px(array.getInt(R.styleable.TipRadioGroup_trg_outer_stroke, 2));
            oriTipOuterStorke = tipOuterStroke;
            tipSice = Global.sp2px(array.getFloat(R.styleable.TipRadioGroup_trg_sice, 6));
            oriTipSice = tipSice;
            tipRdius2TipSice = array.getInt(R.styleable.TipRadioGroup_trg_radius, 6)/array.getFloat(R.styleable
                    .TipRadioGroup_trg_sice, 6);
            shape = array.getInt(R.styleable.TipRadioGroup_trg_shape, CIRCLE);
            corner = Global.sp2px(array.getFloat(R.styleable.TipRadioGroup_trg_corner, 2));
            rHeight = Global.sp2px(array.getFloat(R.styleable.TipRadioGroup_trg_rHeight, 14));
            rWidth = Global.sp2px(array.getFloat(R.styleable.TipRadioGroup_trg_rWidth, 20));
            array.recycle();
        }
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int length = getChildCount();
        if(length>0) {
            for(int i=0;i<length;i++) {
                radioCount++;
                views.add(getChildAt(i));
                tips.add("");
            }
        }
        invalidate();

    }



    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        float cx = 0;
        float cy = 0;
        int width = getWidth();
        int height = getHeight();
        int orientation = getOrientation();
        int length = getChildCount();
        totalWeight = 0;
        weights = new float[length];
        widths  = new float[length];
        heights = new float[length];
        for(int i=0;i<length;i++){
            weights[i] = ((LinearLayout.LayoutParams)getChildAt(i).getLayoutParams()).weight;
            totalWeight+= weights[i];
            widths[i] = getChildAt(i).getWidth();
            totalWidth+= widths[i];
            heights[i] = getChildAt(i).getWidth();
            totalHeight+= heights[i];
        }
        for(int i=0;i<radioCount;i++) {
            if (tipType != FIXATION) {
                float viewWidth = views.get(i) == null ? 0 : views.get(i).getRight();
                float viewHeight = views.get(i) == null ? 0 : views.get(i).getBottom();
                if(orientation==HORIZONTAL) {
                    float offsetWidth = formatOffSetWidthAndHeights(i, getWidth());
                    width = (int)(getWidth()*(weights[0]/totalWeight ));
                    if (direction == TOPRIGHT) {
                        cx = width / 2f + viewWidth / 2f + tipSurroundPadding + getPaddingLeft() - getPaddingRight()
                               +offsetWidth;
                        cy = height / 2f - viewHeight / 2f - tipSurroundPadding + getPaddingTop()
                                / 2f - getPaddingBottom() + Global.dp2px(3);
                    } else if (direction == BOTTOMRIGHT) {
                        cx = width / 2f + viewWidth / 2f + tipSurroundPadding + getPaddingLeft() - getPaddingRight()
                               +offsetWidth;
                        cy = height / 2f + viewHeight / 2f + tipSurroundPadding + getPaddingTop() / 2f - getPaddingBottom()
                                + Global.dp2px(3);
                    } else if (direction == TOPLEFT) {
                        cx = -tipSurroundPadding + getPaddingLeft() - getPaddingRight()+offsetWidth;
                        cy = height / 2f - viewHeight / 2f - tipSurroundPadding + getPaddingTop()
                                / 2f - getPaddingBottom() + Global.dp2px(3);
                    } else if (direction == BOTTOMLEFT) {
                        cx = -tipSurroundPadding + getPaddingLeft() - getPaddingRight()+offsetWidth;
                        cy = height / 2f + viewHeight / 2f + tipSurroundPadding + getPaddingTop() / 2f - getPaddingBottom()
                                + Global.dp2px(3);
                    } else if (direction == LEFT) {
                        cx = -tipSurroundPadding + getPaddingLeft() - getPaddingRight()+offsetWidth;
                        cy = height / 2f + getPaddingTop() / 2f - getPaddingBottom() + Global.dp2px(3);
                    } else if (direction == TOP) {
                        cx = width / 2f+offsetWidth;
                        cy = height / 2f - viewHeight / 2f - tipSurroundPadding + getPaddingTop()
                                / 2f - getPaddingBottom() + Global.dp2px(3);
                    } else if (direction == RIGHT) {
                        cx = width / 2f + viewWidth / 2f + tipSurroundPadding + getPaddingLeft() - getPaddingRight()
                               +offsetWidth;
                        cy = height / 2f + getPaddingTop() / 2f - getPaddingBottom() + Global.dp2px(3);
                    } else if (direction == BOTTOM) {
                        cx = width / 2f+offsetWidth;
                        cy = height / 2f + viewHeight / 2f + tipSurroundPadding + getPaddingTop() / 2f - getPaddingBottom()
                                + Global.dp2px(3);
                    }
                }else{
                    float offsetHeight = formatOffSetWidthAndHeights(i, getHeight());
                    height = (int)(getHeight()*weights[0]/totalWeight);
                    if (direction == TOPRIGHT) {
                        cx = width / 2f + viewWidth / 2f + tipSurroundPadding + getPaddingLeft() - getPaddingRight();
                        cy = height / 2f - viewHeight / 2f - tipSurroundPadding + getPaddingTop()
                                / 2f - getPaddingBottom() + Global.dp2px(3)+offsetHeight;
                    } else if (direction == BOTTOMRIGHT) {
                        cx = width / 2f + viewWidth / 2f + tipSurroundPadding + getPaddingLeft() - getPaddingRight();
                        cy = height / 2f + viewHeight / 2f + tipSurroundPadding + getPaddingTop() / 2f - getPaddingBottom()
                                + Global.dp2px(3)+offsetHeight;
                    } else if (direction == TOPLEFT) {
                        cx = -tipSurroundPadding + getPaddingLeft() - getPaddingRight();
                        cy = height / 2f - viewHeight / 2f - tipSurroundPadding + getPaddingTop()
                                / 2f - getPaddingBottom() + Global.dp2px(3)+offsetHeight;
                    } else if (direction == BOTTOMLEFT) {
                        cx = -tipSurroundPadding + getPaddingLeft() - getPaddingRight();
                        cy = height / 2f + viewHeight / 2f + tipSurroundPadding + getPaddingTop() / 2f - getPaddingBottom()
                                + Global.dp2px(3)+offsetHeight;
                    } else if (direction == LEFT) {
                        cx = -tipSurroundPadding + getPaddingLeft() - getPaddingRight();
                        cy = height / 2f + getPaddingTop() / 2f - getPaddingBottom() + Global.dp2px(3)+offsetHeight;
                    } else if (direction == TOP) {
                        cx = width / 2f;
                        cy = height / 2f - viewHeight / 2f - tipSurroundPadding + getPaddingTop()
                                / 2f - getPaddingBottom() + Global.dp2px(3)+offsetHeight;
                    } else if (direction == RIGHT) {
                        cx = width / 2f + viewWidth / 2f + tipSurroundPadding + getPaddingLeft() - getPaddingRight();
                        cy = height / 2f + getPaddingTop() / 2f - getPaddingBottom() + Global.dp2px(3)+offsetHeight;
                    } else if (direction == BOTTOM) {
                        cx = width / 2f;
                        cy = height / 2f + viewHeight / 2f + tipSurroundPadding + getPaddingTop() / 2f - getPaddingBottom()
                                + Global.dp2px(3)+offsetHeight;
                    }
                }
            } else {
                if(orientation==HORIZONTAL) {
                    float offsetWidth = formatOffSetWidthAndHeights(i, getWidth());
                    width = (int)(getWidth()*(weights[0] / totalWeight));
                    if (direction == TOPRIGHT) {
                        cx = width * 5 / 6f + tipSurroundPadding+offsetWidth;
                        cy = height * 1 / 4f - tipSurroundPadding;
                    } else if (direction == BOTTOMRIGHT) {
                        cx = width * 5 / 6f + tipSurroundPadding+offsetWidth;
                        cy = height * 3 / 4f + tipSurroundPadding;
                    } else if (direction == TOPLEFT) {
                        cx = width * 1 / 6f - tipSurroundPadding+offsetWidth;
                        cy = height * 1 / 4f - tipSurroundPadding;
                    } else if (direction == BOTTOMLEFT) {
                        cx = width * 1 / 6f - tipSurroundPadding+offsetWidth;
                        cy = height * 3 / 4f + tipSurroundPadding;
                    } else if (direction == LEFT) {
                        cx = width * 1 / 6f - tipSurroundPadding+offsetWidth;
                        cy = height / 2f;
                    } else if (direction == TOP) {
                        cx = width / 2f+offsetWidth;
                        cy = height * 1 / 6f - tipSurroundPadding;
                    } else if (direction == RIGHT) {
                        cx = width * 5 / 6f + tipSurroundPadding+offsetWidth;
                        cy = height / 2f;
                    } else if (direction == BOTTOM) {
                        cx = width / 2f+offsetWidth;
                        cy = height * 5 / 6f + tipSurroundPadding;
                    }
                }else{
                    float offsetHeight = formatOffSetWidthAndHeights(i, getHeight());
                    height =(int)(getHeight()*weights[0]/totalWeight);
                    //height = (int)(getHeight()*((i + 1f) / radioCount - 1f / (radioCount * 2f)));
                    if (direction == TOPRIGHT) {
                        cx = width * 5 / 6f + tipSurroundPadding;
                        cy = height * 1 / 4f - tipSurroundPadding+offsetHeight;
                    } else if (direction == BOTTOMRIGHT) {
                        cx = width * 5 / 6f + tipSurroundPadding;
                        cy = height * 3 / 4f + tipSurroundPadding+offsetHeight;
                    } else if (direction == TOPLEFT) {
                        cx = width * 1 / 6f - tipSurroundPadding;
                        cy = height * 1 / 4f - tipSurroundPadding+offsetHeight;
                    } else if (direction == BOTTOMLEFT) {
                        cx = width * 1 / 6f - tipSurroundPadding;
                        cy = height * 3 / 4f + tipSurroundPadding+offsetHeight;
                    } else if (direction == LEFT) {
                        cx = width * 1 / 6f - tipSurroundPadding;
                        cy = height / 2f+offsetHeight;
                    } else if (direction == TOP) {
                        cx = width / 2f+i/radioCount;
                        cy = height * 1 / 6f - tipSurroundPadding+offsetHeight;
                    } else if (direction == RIGHT) {
                        cx = width * 5 / 6f + tipSurroundPadding;
                        cy = height / 2f+offsetHeight;
                    } else if (direction == BOTTOM) {
                        cx = width / 2f+i/radioCount;
                        cy = height * 5 / 6f + tipSurroundPadding+offsetHeight;
                    }
                }
            }
            int tipR = tipRadiuses==null?tipRadius:tipRadiuses[i];
            int tipOuterR = tipOuterStrokes==null?(tipR+tipOuterStroke):(tipR+tipOuterStrokes[i]);
            if (TextUtils.isEmpty(tips.get(i))) {
                tipOuterR = 0;
                tipR = 0;
                tips.set(i, "");
            }


            float theWidth = (rWidths==null?rWidth:rWidths[i])/2f;
            float theHeight = (rHeights==null?rHeight:rHeights[i])/2f;
            float theOuterStroke = tipOuterStrokes==null?tipOuterStroke:tipOuterStrokes[i];
            float theCorner = corners==null?corner:corners[i];
            int theShape = shapes==null?shape:shapes[i];

            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setColor(tipOuterColors == null?tipOuterColor:tipOuterColors[i]);
            if(theShape==CIRCLE){
                canvas.drawCircle(cx, cy, tipOuterR, paint);
            }else if(theShape == ROUND_RECTANGLE){
                canvas.drawRoundRect(new RectF(
                        cx-theWidth-theOuterStroke
                        ,cy-theHeight-theOuterStroke
                        ,cx+theWidth+theOuterStroke
                        ,cy+theHeight+theOuterStroke)
                        ,theCorner,theCorner
                        ,paint);

            }else if(theShape == RECTANGLE){
                canvas.drawRect(cx - theWidth - theOuterStroke
                        ,cy-theHeight-theOuterStroke
                        ,cx+theWidth+theOuterStroke
                        ,cy+theHeight+theOuterStroke
                        ,paint);

            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    canvas.drawOval(cx-theWidth-theOuterStroke
                            ,cy-theHeight-theOuterStroke
                            ,cx+theWidth+theOuterStroke
                            ,cy+theHeight+theOuterStroke
                            ,paint);
                }
            }

            paint.setColor(tipColors == null ? tipColor : tipColors[i]);

            if(theShape==CIRCLE){
                canvas.drawCircle(cx, cy, tipR, paint);
            }else if(theShape == ROUND_RECTANGLE){
                canvas.drawRoundRect(new RectF(cx-theWidth,cy-theHeight,cx+theWidth,cy+theHeight)
                        ,theCorner, theCorner,paint);
            }else if(theShape == RECTANGLE){
                canvas.drawRect(cx-theWidth,cy-theHeight,cx+theWidth,cy+theHeight,paint);
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    canvas.drawOval(cx-theWidth,cy-theHeight,cx+theWidth,cy+theHeight,paint);
                }
            }

            paint.setStyle(Paint.Style.FILL);
            if(tipTextColors==null){
                paint.setColor(tipTextColor);
            }else{
                paint.setColor(tipTextColors[i]);
            }
            paint.setTextSize(tipSices == null ? tipSice : tipSices[i]);
            int theMax = maxes==null?max:maxes[i];
            if(theMax>0&&Integer.valueOf(tips.get(i))>theMax){
                canvas.drawText(tips.get(i) + "+", cx, cy + tipR / (3f * tipRdius2TipSice), paint);
            }else {
                canvas.drawText(tips.get(i), cx, cy + tipR / (3f * (tipRdius2TipSices == null ? tipRdius2TipSice :
                        tipRdius2TipSices[i])), paint);
            }

        }
    }

    private float formatOffSetWidthAndHeights(int index,float widthOrHeight) {
        if(index==0){
            return 0f;
        }else if(index==1){
            return widthOrHeight*(weights[0]/(totalWeight*2f)+weights[1]/(totalWeight*2f));
        }else{
            float offsetWidth = 0f;
            for (int i=0;i<=index;i++){
                if(i==0||i==index){
                    offsetWidth += widthOrHeight*weights[i]/(totalWeight*2f);
                }else{
                    offsetWidth += widthOrHeight*weights[i]/totalWeight;
                }
            }
            return offsetWidth;
        }
    }

}
