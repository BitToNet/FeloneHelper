package cn.bittonet.wftpay.felonehelper.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import cn.bittonet.wftpay.felonehelper.R;

import static cn.bittonet.wftpay.felonehelper.utils.ScreenUtils.dp2px;


public class RoundViewDelegate {

    private final View view;

    private GradientDrawable gd_background       = new GradientDrawable();
    private GradientDrawable gd_background_press = new GradientDrawable();

    private int     backgroundColor;
    private int     backgroundPressColor;
    private int     cornerRadius;
    private int     cornerRadius_TL;
    private int     cornerRadius_TR;
    private int     cornerRadius_BL;
    private int     cornerRadius_BR;
    private int     strokeWidth;
    private int     strokeColor;
    private float   strokeDashWidth;
    private float   strokeDashGap;
    private int     strokePressColor;
    private int     textPressColor;
    private boolean isRadiusHalfHeight;
    private boolean isWidthHeightEqual;

    private int     rippleColor;
    private boolean isRippleEnable;

    protected int mStatePressed = android.R.attr.state_pressed;

    private float[] radiusArr = new float[8];

    public RoundViewDelegate(View view, Context context, AttributeSet attrs) {
        this.view = view;
        obtainAttributes(context, attrs);
    }

    private void obtainAttributes(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundTextView);
        backgroundColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundColor,
                                      Color.TRANSPARENT);
        backgroundPressColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundPressColor,
                                           Integer.MAX_VALUE);
        cornerRadius = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius, 0);
        strokeWidth = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_strokeWidth, 0);
        strokeDashWidth = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_strokeDishWidth, 0);
        strokeDashGap = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_strokeDishGap, 0);
        strokeColor = ta.getColor(R.styleable.RoundTextView_rv_strokeColor, Color.TRANSPARENT);
        strokePressColor = ta.getColor(R.styleable.RoundTextView_rv_strokePressColor,
                                       Integer.MAX_VALUE);
        textPressColor = ta.getColor(R.styleable.RoundTextView_rv_textPressColor,
                                     Integer.MAX_VALUE);
        isRadiusHalfHeight = ta.getBoolean(R.styleable.RoundTextView_rv_isRadiusHalfHeight, false);
        isWidthHeightEqual = ta.getBoolean(R.styleable.RoundTextView_rv_isWidthHeightEqual, false);
        cornerRadius_TL = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_TL, 0);
        cornerRadius_TR = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_TR, 0);
        cornerRadius_BL = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_BL, 0);
        cornerRadius_BR = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_BR, 0);
        rippleColor = ta.getColor(R.styleable.RoundTextView_rv_rippleColor, Color.WHITE);
        isRippleEnable = ta.getBoolean(R.styleable.RoundTextView_rv_isRippleEnable, true);

        ta.recycle();
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        setBgSelector();
    }

    public void setBackgroundPressColor(int backgroundPressColor) {
        this.backgroundPressColor = backgroundPressColor;
        setBgSelector();
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = (int) dp2px(cornerRadius);
        setBgSelector();
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = (int) dp2px(strokeWidth);
        setBgSelector();
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        setBgSelector();
    }

    public void setStrokePressColor(int strokePressColor) {
        this.strokePressColor = strokePressColor;
        setBgSelector();
    }

    public void setTextPressColor(int textPressColor) {
        this.textPressColor = textPressColor;
        setBgSelector();
    }

    public void setIsRadiusHalfHeight(boolean isRadiusHalfHeight) {
        this.isRadiusHalfHeight = isRadiusHalfHeight;
        setBgSelector();
    }

    public void setIsWidthHeightEqual(boolean isWidthHeightEqual) {
        this.isWidthHeightEqual = isWidthHeightEqual;
        setBgSelector();
    }

    public void setCornerRadius_TL(int cornerRadius_TL) {
        this.cornerRadius_TL = cornerRadius_TL;
        setBgSelector();
    }

    public void setCornerRadius_TR(int cornerRadius_TR) {
        this.cornerRadius_TR = cornerRadius_TR;
        setBgSelector();
    }

    public void setCornerRadius_BL(int cornerRadius_BL) {
        this.cornerRadius_BL = cornerRadius_BL;
        setBgSelector();
    }

    public void setCornerRadius_BR(int cornerRadius_BR) {
        this.cornerRadius_BR = cornerRadius_BR;
        setBgSelector();
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getBackgroundPressColor() {
        return backgroundPressColor;
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public int getStrokePressColor() {
        return strokePressColor;
    }

    public int getTextPressColor() {
        return textPressColor;
    }

    public boolean isRadiusHalfHeight() {
        return isRadiusHalfHeight;
    }

    public boolean isWidthHeightEqual() {
        return isWidthHeightEqual;
    }

    public int getCornerRadius_TL() {
        return cornerRadius_TL;
    }

    public int getCornerRadius_TR() {
        return cornerRadius_TR;
    }

    public int getCornerRadius_BL() {
        return cornerRadius_BL;
    }

    public int getCornerRadius_BR() {
        return cornerRadius_BR;
    }

    private void setDrawable(GradientDrawable gd, int color, int strokeColor) {
        if (cornerRadius_TL > 0
                || cornerRadius_TR > 0
                || cornerRadius_BR > 0
                || cornerRadius_BL > 0) {
            /**The corners are ordered top-left, top-right, bottom-right, bottom-left*/
            radiusArr[0] = cornerRadius_TL;
            radiusArr[1] = cornerRadius_TL;
            radiusArr[2] = cornerRadius_TR;
            radiusArr[3] = cornerRadius_TR;
            radiusArr[4] = cornerRadius_BR;
            radiusArr[5] = cornerRadius_BR;
            radiusArr[6] = cornerRadius_BL;
            radiusArr[7] = cornerRadius_BL;
            gd.setCornerRadii(radiusArr);
        } else {
            gd.setCornerRadius(cornerRadius);
        }

        gd.setStroke(strokeWidth, strokeColor, strokeDashWidth, strokeDashGap);
        gd.setColor(color);
    }

    public void setBgSelector() {
        //获取view当前drawable--用于判断是否通过默认属性设置背景
        Drawable mDrawable = view.getBackground();
        //判断是否使用自定义颜色值
        boolean isSetBg = backgroundColor != Integer.MAX_VALUE
                || backgroundPressColor != Integer.MAX_VALUE
                || strokeWidth > 0
                || cornerRadius > 0
                || cornerRadius_TL > 0
                || cornerRadius_TR > 0
                || cornerRadius_BL > 0
                || cornerRadius_BR > 0;

        setDrawable(gd_background_press, backgroundPressColor, strokePressColor);
        setDrawable(gd_background, backgroundColor, strokeColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                && isRippleEnable
                && view.isEnabled()
                && !view.isSelected()) {//5.0以上且设置水波属性并且可操作
            RippleDrawable rippleDrawable = new RippleDrawable(
                    new ColorStateList(new int[][] {new int[] {mStatePressed}, new int[] {}},
                                       new int[] {rippleColor != Integer.MAX_VALUE ? rippleColor :
                                               backgroundPressColor, rippleColor}),
                    getContentDrawable(mDrawable, isSetBg), null);
            view.setBackground(rippleDrawable);
        } else {
            if (!isSetBg) {
                return;
            }
            StateListDrawable mStateDrawable = new StateListDrawable();
            if (backgroundPressColor != Integer.MAX_VALUE
                    || strokePressColor != Integer.MAX_VALUE) {
                setDrawable(gd_background_press, backgroundPressColor, strokePressColor);
                mStateDrawable.addState(new int[] {mStatePressed}, gd_background_press);
            }
            mStateDrawable.addState(new int[] {}, gd_background);//默认状态--放置在最后否则其它状态不生效
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                view.setBackground(mStateDrawable);
            } else {
                view.setBackgroundDrawable(mStateDrawable);
            }
        }
    }

    /**
     * 水波纹效果完成后最终展示的背景Drawable
     *
     * @param mDrawable
     * @param isSetBg
     * @return
     */
    private Drawable getContentDrawable(Drawable mDrawable, boolean isSetBg) {
        return !isSetBg ? mDrawable : view.isSelected() ? gd_background_press : gd_background;
    }
}
