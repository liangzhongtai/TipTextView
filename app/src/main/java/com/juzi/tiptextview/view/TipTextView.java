package com.juzi.tiptextview.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.juzi.tiptextview.R;
import com.juzi.tiptextview.global.Global;


/**
 * Created by liangzhongtai on 2016/9/21.
 */
public class TipTextView extends TextView {
    public final static int TIP_VISIBLE = 1;
    public final static int TIP_GONE = 2;
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
    public TipTextView(Context context) {
        this(context, null);
    }
    public TipTextView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }
    public TipTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public TipTextView setTipCout(int tip){
        if(tip<1){
            currentStatus = TIP_GONE;
        }else {
            currentStatus = TIP_VISIBLE;
            this.tip = tip + "";
        }
        invalidate();
        return this;
    }
    public TipTextView setTipText(String tip){
        if(TextUtils.isEmpty(tip)){
            currentStatus = TIP_GONE;
        }else {
            currentStatus = TIP_VISIBLE;
            this.tip = tip + "";
        }
        invalidate();
        return this;
    }

    public TipTextView setDirection(int direction) {
        this.direction = direction;
        invalidate();
        return this;
    }

    public TipTextView setTipColor(int tipColor) {
        this.tipColor = getResources().getColor(tipColor);
        invalidate();
        return this;
    }

    public TipTextView setTipOuterColor(int tipOuterColor) {
        this.tipOuterColor = getResources().getColor(tipOuterColor);
        invalidate();
        return this;
    }

    public TipTextView setTipTextColor(int tipTextColor) {
        this.tipTextColor = getResources().getColor(tipTextColor);
        invalidate();
        return this;
    }

    public TipTextView setTipRadius(int tipRadius) {
        this.tipRadius = Global.dp2px(tipRadius);
        tipRdius2TipSice = this.tipRadius/tipSice;
        invalidate();
        return this;
    }

    public TipTextView setTipOuterRadius(int tipOuterStroke) {
        this.tipOuterStroke = Global.dp2px(tipOuterStroke);
        invalidate();
        return this;
    }

    public TipTextView setTipSice(float tipSice) {
        this.tipSice = Global.sp2px(tipSice);
        tipRdius2TipSice = this.tipRadius/tipSice;
        invalidate();
        return this;
    }

    public TipTextView setTipSurroundPadding(int tipSurroundPadding) {
        this.tipSurroundPadding = Global.dp2px(tipSurroundPadding);
        invalidate();
        return this;
    }

    public TipTextView setMax(int max) {
        this.max = max;
        invalidate();
        return this;
    }

    public TipTextView setShape(int shape) {
        this.shape = shape;
        invalidate();
        return this;
    }

    public TipTextView setCorner(float corner) {
        this.corner = corner;
        invalidate();
        return this;
    }

    public TipTextView setRHeight(float rHeight) {
        this.rHeight = rHeight;
        invalidate();
        return this;
    }

    public TipTextView setRWidth(float rWidth) {
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

    public int getOriTipOuterStorke() {
        return oriTipOuterStorke;
    }

    public float getTipSice() {
        return tipSice;
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

    public float getOriTipSice() {
        return oriTipSice;
    }

    public int getOriTipSurroundPadding() {
        return oriTipSurroundPadding;
    }



    @SuppressWarnings("ResourceType")
    public void init(AttributeSet attrs){
        paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setDither(true);
        paint.setAntiAlias(true);
        if(attrs != null) {
             TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.TipTextView);
             tipType = array.getInt(R.styleable.TipTextView_ttv_type, FIXATION);
             tipSurroundPadding = Global.dp2px(array.getInt(R.styleable.TipTextView_ttv_surround_padding, 2));
             oriTipSurroundPadding = tipSurroundPadding;
             tipColor = array.getColor(R.styleable.TipTextView_ttv_color, getResources().getColor(R.color.colorecRed));
             tipOuterColor = array.getColor(R.styleable.TipTextView_ttv_outer_color,
                     getResources().getColor(R.color.colorffRed));
             tipTextColor = array.getColor(R.styleable.TipTextView_ttv_textcolor,
                     getResources().getColor(R.color.colorWhite));
             direction = array.getInt(R.styleable.TipTextView_ttv_direction, TOPRIGHT);
             tipRadius = Global.dp2px(array.getInt(R.styleable.TipTextView_ttv_radius, 6));
             tipOuterStroke = Global.dp2px(array.getInt(R.styleable.TipTextView_ttv_outer_stroke, 6));
             oriTipOuterStorke = tipOuterStroke;
             oriTipRadius = tipRadius;
             tipSice = Global.sp2px(array.getFloat(R.styleable.TipTextView_ttv_sice, 6));
             oriTipSice = tipSice;
             tipRdius2TipSice = array.getInt(R.styleable.TipTextView_ttv_radius, 6)/array.getFloat(R.styleable
                    .TipTextView_ttv_sice, 6);
             shape = array.getInt(R.styleable.TipTextView_ttv_shape, CIRCLE);
             corner = Global.sp2px(array.getFloat(R.styleable.TipTextView_ttv_corner, 2));
             rHeight = Global.sp2px(array.getFloat(R.styleable.TipTextView_ttv_rHeight, 14));
             rWidth = Global.sp2px(array.getFloat(R.styleable.TipTextView_ttv_rWidth, 20));
             array.recycle();
        }
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
        float cx = 0;
        float cy = 0;
        int width = getWidth();
        int height = getHeight();
        if(tipType!=FIXATION){
            int lineCount = getLineCount();
            Rect rect = new Rect();
            String text = getText()+"";
            int length = text.length();
            if(length==0)return;
            int maxLengthEatchLine = (int) Math.ceil(length/lineCount);
            Paint textPaint = new Paint();
            textPaint.setTextSize(getTextSize());
            textPaint.getTextBounds(text, 0, maxLengthEatchLine, rect);
            float textWidth = rect.width();
            float textHeight = getLineHeight()*lineCount+Global.dp2px(3);
            //0 LEFT 1 TOP 2 RIGHT 3 BOTTOM
            Drawable[] drawables = getCompoundDrawables();
            int[] drawableWidths = new int[4];
            int[] drawableHeights= new int[4];
            for(int i=0;i<4;i++){
                if(drawables[i]==null){
                    drawableWidths[i] = 0;
                    drawableHeights[i]= 0;
                }else{
                    drawableWidths[i] = drawables[i].getMinimumWidth()+getCompoundDrawablePadding();
                    drawableHeights[i]= drawables[i].getMinimumHeight()+getCompoundDrawablePadding();
                }
            }
            if(getGravity()== Gravity.CENTER) {
                if (direction == TOPRIGHT) {
                    cx = width / 2f+ textWidth/2f + tipSurroundPadding + getPaddingLeft()-getPaddingRight()
                            +getCompoundDrawablePadding();
                    cy = height / 2f- textHeight/2f-tipSurroundPadding + getPaddingTop()
                            /2f-getPaddingBottom()-getCompoundDrawablePadding()+Global.dp2px(3);
                } else if (direction == BOTTOMRIGHT) {
                    cx = width / 2f+ textWidth/2f + tipSurroundPadding + getPaddingLeft()-getPaddingRight()
                            +getCompoundDrawablePadding();
                    cy = height / 2f+ textHeight/2f+ tipSurroundPadding + getPaddingTop()/2f-getPaddingBottom()
                            +getCompoundDrawablePadding()+Global.dp2px(3);
                } else if (direction == TOPLEFT) {
                    cx = -tipSurroundPadding + getPaddingLeft()-getPaddingRight()-getCompoundDrawablePadding();
                    cy = height / 2f- textHeight/2f-tipSurroundPadding + getPaddingTop()
                            /2f-getPaddingBottom()-getCompoundDrawablePadding()+Global.dp2px(3);
                } else if (direction == BOTTOMLEFT) {
                    cx = -tipSurroundPadding + getPaddingLeft()-getPaddingRight()-getCompoundDrawablePadding();
                    cy = height / 2f+ textHeight/2f+ tipSurroundPadding + getPaddingTop()/2f-getPaddingBottom()
                            +getCompoundDrawablePadding()+Global.dp2px(3);
                } else if (direction == LEFT) {
                    cx = -tipSurroundPadding + getPaddingLeft()-getPaddingRight()-getCompoundDrawablePadding();
                    cy = height / 2f + getPaddingTop()/2f-getPaddingBottom()+Global.dp2px(3);
                } else if (direction == TOP) {
                    cx = width / 2f;
                    cy = height / 2f- textHeight/2f-tipSurroundPadding + getPaddingTop()
                            /2f-getPaddingBottom()-getCompoundDrawablePadding()+Global.dp2px(3);
                } else if (direction == RIGHT) {
                    cx = width / 2f+ textWidth/2f + tipSurroundPadding + getPaddingLeft()-getPaddingRight()
                            +getCompoundDrawablePadding();
                    cy = height / 2f + getPaddingTop()/2f-getPaddingBottom()+Global.dp2px(3);
                } else if (direction == BOTTOM) {
                    cx = width / 2f;
                    cy = height / 2f+ textHeight/2f+ tipSurroundPadding + getPaddingTop()/2f-getPaddingBottom()
                            +getCompoundDrawablePadding()+Global.dp2px(3);
                }
            }else if(tipType == SURROUND_V){
                if (direction == TOPRIGHT) {
                    cx = textWidth + tipSurroundPadding + getPaddingLeft()+drawableWidths[LEFT]+drawableWidths[RIGHT];
                    cy = height / 2f- textHeight/2f-tipSurroundPadding + getPaddingTop()
                            /2f-getPaddingBottom()-getCompoundDrawablePadding()+Global.dp2px(3);
                } else if (direction == BOTTOMRIGHT) {
                    cx = textWidth + tipSurroundPadding + getPaddingLeft()+drawableWidths[LEFT]+drawableWidths[RIGHT];
                    cy = height / 2f+ textHeight/2f+ tipSurroundPadding + getPaddingTop()/2f-getPaddingBottom()
                            +getCompoundDrawablePadding()+Global.dp2px(3);
                } else if (direction == TOPLEFT) {
                    cx = -tipSurroundPadding + getPaddingLeft();
                    cy = height / 2f- textHeight/2f-tipSurroundPadding + getPaddingTop()
                            /2f-getPaddingBottom()-getCompoundDrawablePadding()+Global.dp2px(3);
                } else if (direction == BOTTOMLEFT) {
                    cx = -tipSurroundPadding + getPaddingLeft();
                    cy = height / 2f+ textHeight/2f+ tipSurroundPadding + getPaddingTop()/2f-getPaddingBottom()
                            +getCompoundDrawablePadding()+Global.dp2px(3);
                } else if (direction == LEFT) {
                    cx = -tipSurroundPadding + getPaddingLeft();
                    cy = height / 2f + getPaddingTop()/2f-getPaddingBottom()+Global.dp2px(3);
                } else if (direction == TOP) {
                    cx = textWidth / 2f + getPaddingLeft();
                    cy = height / 2f- textHeight/2f-tipSurroundPadding + getPaddingTop()/2f
                            -getPaddingBottom()-getCompoundDrawablePadding()+Global.dp2px(3);
                } else if (direction == RIGHT) {
                    cx = textWidth + tipSurroundPadding + getPaddingLeft()+ drawableWidths[LEFT]+drawableWidths[RIGHT];
                    cy = height / 2f + getPaddingTop()/2f-getPaddingBottom()+Global.dp2px(3);
                } else if (direction == BOTTOM) {
                    cx = textWidth / 2f + getPaddingLeft();
                    cy = height / 2f+ textHeight/2f+ tipSurroundPadding + getPaddingTop()/2f-getPaddingBottom()
                            +getCompoundDrawablePadding()+Global.dp2px(3);
                }
            }else if(tipType == SURROUND_H){
                if (direction == TOPRIGHT) {
                    cx = width / 2f+ textWidth/2f + tipSurroundPadding + getPaddingLeft()/2-getPaddingRight()
                            +getCompoundDrawablePadding()+Global.dp2px(3);
                    cy = -tipSurroundPadding + getPaddingTop();
                } else if (direction == BOTTOMRIGHT) {
                    cx = width / 2f+ textWidth/2f + tipSurroundPadding + getPaddingLeft()/2-getPaddingRight()
                            +getCompoundDrawablePadding()+Global.dp2px(3);
                    cy = textHeight + tipSurroundPadding + getPaddingTop()+drawableHeights[TOP]+drawableHeights[BOTTOM];
                } else if (direction == TOPLEFT) {
                    cx = width/2f-textWidth/2f-tipSurroundPadding + getPaddingLeft()/2-getPaddingRight()
                            -getCompoundDrawablePadding()+Global.dp2px(3);
                    cy = -tipSurroundPadding + getPaddingTop();
                } else if (direction == BOTTOMLEFT) {
                    cx = width/2f-textWidth/2f-tipSurroundPadding + getPaddingLeft()/2-getPaddingRight()
                            -getCompoundDrawablePadding()+Global.dp2px(3);
                    cy = textHeight + tipSurroundPadding + getPaddingTop()+drawableHeights[TOP]+drawableHeights[BOTTOM];
                } else if (direction == LEFT) {
                    cx = width/2f-textWidth/2f-tipSurroundPadding + getPaddingLeft()/2-getPaddingRight()
                            -getCompoundDrawablePadding()+Global.dp2px(3);
                    cy = textHeight / 2f + getPaddingTop();
                } else if (direction == TOP) {
                    cx = width / 2f;
                    cy = -tipSurroundPadding + getPaddingTop();
                } else if (direction == RIGHT) {
                    cx = width / 2f+ textWidth/2f + tipSurroundPadding + getPaddingLeft()/2-getPaddingRight()
                            +getCompoundDrawablePadding()+Global.dp2px(3);
                    cy = textHeight / 2f + getPaddingTop();
                } else if (direction == BOTTOM) {
                    cx = width / 2f;
                    cy = textHeight + tipSurroundPadding + getPaddingTop()+drawableHeights[TOP]+drawableHeights[BOTTOM];
                }
            }else {
                if (direction == TOPRIGHT) {
                    cx = textWidth + tipSurroundPadding + getPaddingLeft()+drawableWidths[LEFT]+drawableWidths[RIGHT];
                    cy = -tipSurroundPadding + getPaddingTop();
                } else if (direction == BOTTOMRIGHT) {
                    cx = textWidth + tipSurroundPadding + getPaddingLeft()+drawableWidths[LEFT]+drawableWidths[RIGHT];
                    cy = textHeight + tipSurroundPadding + getPaddingTop()+drawableHeights[TOP]+drawableHeights[BOTTOM];
                } else if (direction == TOPLEFT) {
                    cx = -tipSurroundPadding + getPaddingLeft();
                    cy = -tipSurroundPadding + getPaddingTop();
                } else if (direction == BOTTOMLEFT) {
                    cx = -tipSurroundPadding + getPaddingLeft();
                    cy = textHeight + tipSurroundPadding + getPaddingTop()+drawableHeights[TOP]+drawableHeights[BOTTOM];
                } else if (direction == LEFT) {
                    cx = -tipSurroundPadding + getPaddingLeft();
                    cy = textHeight / 2f + getPaddingTop();
                } else if (direction == TOP) {
                    cx = textWidth / 2f + getPaddingLeft();
                    cy = -tipSurroundPadding + getPaddingTop();
                } else if (direction == RIGHT) {
                    cx = textWidth + tipSurroundPadding + getPaddingLeft()+ drawableWidths[LEFT]+drawableWidths[RIGHT];
                    cy = textHeight / 2f + getPaddingTop();
                } else if (direction == BOTTOM) {
                    cx = textWidth / 2f + getPaddingLeft();
                    cy = textHeight + tipSurroundPadding + getPaddingTop()+drawableHeights[TOP]+drawableHeights[BOTTOM];
                }
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
        int tipOuterR = tipRadius+tipOuterStroke;
        int tipR = tipRadius;
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
            canvas.drawRect(cx - rWidth/2f - tipOuterStroke, cy - rHeight/2f - tipOuterStroke, cx + rWidth/2f
                    + tipOuterStroke,cy + rHeight/2f + tipOuterStroke, paint);
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
            canvas.drawRect(cx-rWidth/2f,cy-rHeight/2f,cx+rWidth/2f,cy+rHeight/2f,paint);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                canvas.drawOval(cx - rWidth/2f, cy - rHeight/2f, cx + rWidth/2f, cy + rHeight/2f, paint);
            }
        }
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(tipTextColor);
        paint.setTextSize(tipSice);
        if(max>0&&Integer.valueOf(tip)>max){
            canvas.drawText(tip+"+", cx, cy + tipR / (3f*tipRdius2TipSice), paint);
        }else{
            canvas.drawText(tip, cx, cy + tipR / (3f*tipRdius2TipSice), paint);
        }


    }

}
