package com.juzi.tiptextview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.juzi.tiptextview.R;
import com.juzi.tiptextview.global.Global;

/**
 * Created by liangzhongtai on 2017/2/20.
 */
public class TipLayout extends FrameLayout {
    public final static int TIP_VISIBLE = 1;
    public final static int TIP_GONE = 2;
    private View view;
    private Paint paint = new Paint();
    private int tipType;
    private int tipSurroundPadding;
    private int oriTipSurroundPadding;
    private int tipColor;
    private int tipOuterColor;
    private int tipTextColor;
    private int tipRadius;
    private int oriTipOuterStorke;
    private int tipOuterStroke;
    private int oriTipRadius;
    private int direction;
    private float tipSice;
    private float oriTipSice;
    private String tip;
    private int currentStatus = TIP_GONE;
    private float tipRdius2TipSice;
    private int max;
    private int shape;
    private float corner;
    private float rHeight;
    private float rWidth;
    public final static int LEFT = 0;
    public final static int TOP = 1;
    public final static int RIGHT = 2;
    public final static int BOTTOM = 3;
    public final static int TOPRIGHT = 4;
    public final static int BOTTOMRIGHT = 5;
    public final static int TOPLEFT = 6;
    public final static int BOTTOMLEFT = 7;

    public final static int FIXATION = 0;
    public final static int SURROUND = 1;
    public final static int SURROUND_V = 2;
    public final static int SURROUND_H = 3;

    public final static int CIRCLE = 0;
    public final static int ROUND_RECTANGLE = 1;
    public final static int RECTANGLE = 2;
    public final static int OVAL = 3;
    public TipLayout(Context context) {
        this(context, null);
    }
    public TipLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }
    public TipLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
        if(getChildCount()!=0) view = getChildAt(0);
    }

    public TipLayout setView(View view,int marginLeft,int marginTop,int marginRight,int marginBottom) {
        removeAllViews();
        this.view = view;
        FrameLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup
                .LayoutParams.WRAP_CONTENT);
        params.setMargins(Global.dp2px(marginLeft),Global.dp2px(marginTop),Global.dp2px(marginRight),Global
                .dp2px(marginBottom));
        params.gravity = Gravity.CENTER;
        addView(view, params);
        return this;
    }

    public TipLayout setTipColors(int tipColor,int tipTextColor){
        this.tipColor = getResources().getColor(tipColor);
        this.tipTextColor = getResources().getColor(tipTextColor);
        invalidate();
        return this;
    }
    public TipLayout setTipRadiuss(int tipRadius){
        this.tipRadius = tipRadius;
        invalidate();
        return this;
    }
    public TipLayout setTipSices(int tipSice){
        this.tipSice = tipSice;
        invalidate();
        return  this;
    }
    public TipLayout setTipSurroundPaddings(int tipSurroundPadding){
        this.tipSurroundPadding = tipSurroundPadding;
        invalidate();
        return this;
    }

    public TipLayout setTipCout(int tip){
        if(tip<1){
            currentStatus = TIP_GONE;
        }else {
            currentStatus = TIP_VISIBLE;
            this.tip = tip + "";
        }
        invalidate();
        return this;
    }
    public TipLayout setTipText(String tip){
        if(TextUtils.isEmpty(tip)){
            currentStatus = TIP_GONE;
        }else {
            currentStatus = TIP_VISIBLE;
            this.tip = tip + "";
        }
        invalidate();
        return this;
    }

    public TipLayout setDirection(int direction) {
        this.direction = direction;
        invalidate();
        return this;
    }

    public TipLayout setTipColor(int tipColor) {
        this.tipColor = getResources().getColor(tipColor);
        invalidate();
        return this;
    }

    public TipLayout setTipOuterColor(int tipOuterColor) {
        this.tipOuterColor = getResources().getColor(tipOuterColor);
        invalidate();
        return this;
    }

    public TipLayout setTipTextColor(int tipTextColor) {
        this.tipTextColor = getResources().getColor(tipTextColor);
        invalidate();
        return this;
    }

    public TipLayout setTipRadius(int tipRadius) {
        this.tipRadius = Global.dp2px(tipRadius);
        tipRdius2TipSice = this.tipRadius/tipSice;
        invalidate();
        return this;
    }

    public TipLayout setTipOuterRadius(int tipOuterStroke) {
        this.tipOuterStroke = Global.dp2px(tipOuterStroke);
        invalidate();
        return this;
    }


    public TipLayout setTipSice(float tipSice) {
        this.tipSice = Global.sp2px(tipSice);
        tipRdius2TipSice = this.tipRadius/tipSice;
        invalidate();
        return this;
    }


    public TipLayout setTipSurroundPadding(int tipSurroundPadding) {
        this.tipSurroundPadding = tipSurroundPadding;
        invalidate();
        return this;
    }

    public TipLayout setMax(int max) {
        this.max = max;
        invalidate();
        return this;
    }

    public TipLayout setShape(int shape) {
        this.shape = shape;
        invalidate();
        return this;
    }

    public TipLayout setCorner(float corner) {
        this.corner = corner;
        invalidate();
        return this;
    }

    public TipLayout setRHeight(float rHeight) {
        this.rHeight = rHeight;
        invalidate();
        return this;
    }

    public TipLayout setRWidth(float rWidth) {
        this.rWidth = rWidth;
        invalidate();
        return this;
    }

    public int getTipType() {
        return tipType;
    }

    public int getTipColor() {
        return tipColor;
    }

    public int getTipOuterColor() {
        return tipOuterColor;
    }

    public int getTipTextColor() {
        return tipTextColor;
    }

    public int getDirection() {
        return direction;
    }

    public String getTip() {
        return tip;
    }

    public int getTipRadius() {
        return tipRadius;
    }

    public int getTipOuterStroke() {
        return tipOuterStroke;
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

    public int getShape() {
        return shape;
    }

    public float getCorner() {
        return corner;
    }

    public float getRHeight() {
        return rHeight;
    }

    public float getRWidth() {
        return rWidth;
    }

    public int getOriTipSurroundPadding() {
        return oriTipSurroundPadding;
    }

    public int getOriTipOuterStorke() {
        return oriTipOuterStorke;
    }

    @SuppressWarnings("ResourceType")
    public void init(AttributeSet attrs){
        paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setDither(true);
        paint.setAntiAlias(true);
        if(attrs != null) {
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.TipLayout);
            tipType = array.getInt(R.styleable.TipLayout_tl_type, FIXATION);
            tipSurroundPadding = Global.dp2px(array.getInt(R.styleable.TipLayout_tl_surround_padding, 2));
            oriTipSurroundPadding = tipSurroundPadding;
            tipColor = array.getColor(R.styleable.TipLayout_tl_color, getResources().getColor(R.color.colorecRed));
            tipOuterColor = array.getColor(R.styleable.TipLayout_tl_outer_color, getResources().getColor(R.color
                    .colorffRed));
            tipTextColor = array.getColor(R.styleable.TipLayout_tl_textcolor, getResources().getColor(R.color
                    .colorWhite));
            direction = array.getInt(R.styleable.TipLayout_tl_direction, TOPRIGHT);
            tipRadius = Global.dp2px(array.getInt(R.styleable.TipLayout_tl_radius, 6));
            oriTipRadius = tipRadius;
            tipOuterStroke = Global.dp2px(array.getInt(R.styleable.TipLayout_tl_outer_stroke, 2));
            oriTipOuterStorke = tipOuterStroke;
            tipSice = Global.sp2px(array.getFloat(R.styleable.TipLayout_tl_sice, 6));
            oriTipSice = tipSice;
            tipRdius2TipSice = array.getInt(R.styleable.TipLayout_tl_radius, 6)/array.getFloat(R.styleable
                    .TipLayout_tl_sice, 6);
            shape = array.getInt(R.styleable.TipLayout_tl_shape, CIRCLE);
            corner = Global.sp2px(array.getFloat(R.styleable.TipLayout_tl_corner, 2));
            rHeight = Global.sp2px(array.getFloat(R.styleable.TipLayout_tl_rHeight, 14));
            rWidth = Global.sp2px(array.getFloat(R.styleable.TipLayout_tl_rWidth, 20));
            array.recycle();
        }
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        invalidate();

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        float cx = 0;
        float cy = 0;
        int width = getWidth();
        int height = getHeight();
        if(tipType!=FIXATION){
            float viewWidth = view==null?0:view.getMeasuredWidth();
            float viewHeight = view==null?0:view.getMeasuredHeight();
            if (direction == TOPRIGHT) {
                cx = width / 2f+ viewWidth/2f + tipSurroundPadding + getPaddingLeft()-getPaddingRight()
                ;
                cy = height / 2f- viewHeight/2f-tipSurroundPadding + getPaddingTop()
                        /2f-getPaddingBottom()+Global.dp2px(3);
            } else if (direction == BOTTOMRIGHT) {
                cx = width / 2f+ viewWidth/2f + tipSurroundPadding + getPaddingLeft()-getPaddingRight()
                ;
                cy = height / 2f+ viewHeight/2f+ tipSurroundPadding + getPaddingTop()/2f-getPaddingBottom()
                        +Global.dp2px(3);
            } else if (direction == TOPLEFT) {
                cx = -tipSurroundPadding + getPaddingLeft()-getPaddingRight();
                cy = height / 2f- viewHeight/2f-tipSurroundPadding + getPaddingTop()
                        /2f-getPaddingBottom()+Global.dp2px(3);
            } else if (direction == BOTTOMLEFT) {
                cx = -tipSurroundPadding + getPaddingLeft()-getPaddingRight();
                cy = height / 2f+ viewHeight/2f+ tipSurroundPadding + getPaddingTop()/2f-getPaddingBottom()
                        +Global.dp2px(3);
            } else if (direction == LEFT) {
                cx = -tipSurroundPadding + getPaddingLeft()-getPaddingRight();
                cy = height / 2f + getPaddingTop()/2f-getPaddingBottom()+Global.dp2px(3);
            } else if (direction == TOP) {
                cx = width / 2f;
                cy = height / 2f- viewHeight/2f-tipSurroundPadding + getPaddingTop()
                        /2f-getPaddingBottom()+Global.dp2px(3);
            } else if (direction == RIGHT) {
                cx = width / 2f+ viewWidth/2f + tipSurroundPadding + getPaddingLeft()-getPaddingRight()
                ;
                cy = height / 2f + getPaddingTop()/2f-getPaddingBottom()+Global.dp2px(3);
            } else if (direction == BOTTOM) {
                cx = width / 2f;
                cy = height / 2f+ viewHeight/2f+ tipSurroundPadding + getPaddingTop()/2f-getPaddingBottom()
                        +Global.dp2px(3);
            }

        }else {
            if (direction == TOPRIGHT) {
                cx = width * 5 / 6f+tipSurroundPadding;
                cy = height * 1 / 4f-tipSurroundPadding;
            } else if (direction == BOTTOMRIGHT) {
                cx = width * 5 / 6f+tipSurroundPadding;
                cy = height * 3 / 4f+tipSurroundPadding;
            } else if (direction == TOPLEFT) {
                cx = width * 1 / 6f-tipSurroundPadding;
                cy = height * 1 / 4f-tipSurroundPadding;
            } else if (direction == BOTTOMLEFT) {
                cx = width * 1 / 6f-tipSurroundPadding;
                cy = height * 3 / 4f+tipSurroundPadding;
            } else if (direction == LEFT) {
                cx = width * 1 / 6f-tipSurroundPadding;
                cy = height / 2f;
            } else if (direction == TOP) {
                cx = width / 2f;
                cy = height * 1 / 6f-tipSurroundPadding;
            } else if (direction == RIGHT) {
                cx = width * 5 / 6f+tipSurroundPadding;
                cy = height / 2f;
            } else if (direction == BOTTOM) {
                cx = width / 2f;
                cy = height * 5 / 6f+tipSurroundPadding;
            }
        }
        int tipOuterR = tipRadius+tipOuterStroke;        int tipR = tipRadius;
        if (currentStatus == TIP_GONE) {
            tipOuterR = 0;
            tipR = 0;
            tip = "";
        }

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(tipOuterColor);
        if(shape==CIRCLE){
            canvas.drawCircle(cx, cy, tipOuterR, paint);
        }else if(shape == ROUND_RECTANGLE){
            canvas.drawRoundRect(new RectF(cx-rWidth/2f-tipOuterStroke,cy-rHeight/2f-tipOuterStroke,
                    cx+rWidth/2f+tipOuterStroke,cy+rHeight/2f+tipOuterStroke),corner,corner,paint);
        }else if(shape == RECTANGLE){
            canvas.drawRect(cx - rWidth/2f - tipOuterStroke, cy - rHeight/2f - tipOuterStroke, cx + rWidth /
                    2f + tipOuterStroke,cy + rHeight/2f + tipOuterStroke, paint);

        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                canvas.drawOval(cx-rWidth/2f-tipOuterStroke,cy-rHeight/2f-tipOuterStroke,cx+rWidth/2f+tipOuterStroke,
                        cy+rHeight/2f+tipOuterStroke,paint);
            }
        }
        paint.setColor(tipColor);
        if(shape==CIRCLE){
            canvas.drawCircle(cx, cy, tipR, paint);
        }else if(shape == ROUND_RECTANGLE){
            canvas.drawRoundRect(new RectF(cx-rWidth/2f,cy-rHeight/2f,cx+rWidth/2,cy+rHeight/2),corner,corner,paint);
        }else if(shape == RECTANGLE){
            canvas.drawRect(cx-rWidth/2f,cy-rHeight/2f,cx+rWidth/2,cy+rHeight/2,paint);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                canvas.drawOval(cx - rWidth/2f, cy - rHeight/2f, cx + rWidth/2, cy + rHeight/2, paint);
            }
        }
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(tipTextColor);
        paint.setTextSize(tipSice);
        if(max>0&&Integer.valueOf(tip)>max){
            canvas.drawText(tip+"+", cx, cy + tipR / (3f*tipRdius2TipSice), paint);
        }else {
            canvas.drawText(tip, cx, cy + tipR / (3f * tipRdius2TipSice), paint);
        }
    }


}
