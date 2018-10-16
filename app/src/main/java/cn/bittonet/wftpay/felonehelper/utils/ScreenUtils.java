package cn.bittonet.wftpay.felonehelper.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * 屏幕单位转化
 */
public class ScreenUtils {

    private ScreenUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * dp转为px
     *
     * @param dpVal
     * @return
     */
    public static float dp2px(float dpVal) {
        Resources r = Resources.getSystem();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, r.getDisplayMetrics());
    }

    /**
     * sp转为px
     *
     * @param spVal
     * @return
     */
    public static float sp2px(float spVal) {
        Resources r = Resources.getSystem();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, r.getDisplayMetrics());
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param pxValue
     */
    public static float px2dp(float pxValue) {
        Resources   r     = Resources.getSystem();
        final float scale = r.getDisplayMetrics().density;
        return (pxValue / scale + 0.5f);
    }

    /**
     * px转sp
     *
     * @param pxVal
     * @return
     */
    public static float px2sp(float pxVal) {
        Resources r = Resources.getSystem();
        return pxVal / r.getDisplayMetrics().scaledDensity + 0.5f;
    }

    /**
     * Gets the width of the display, in pixels.
     *
     * @param context
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int screenWidthPixel(Context context) {
        int sScreenWidthPixels;
        WindowManager windowManager = (WindowManager) context.getSystemService(
                Context.WINDOW_SERVICE);
        assert windowManager != null;
        Display display = windowManager.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Point outPoint = new Point();
            display.getRealSize(outPoint);
            sScreenWidthPixels = outPoint.x;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point outPoint = new Point();
            display.getSize(outPoint);
            sScreenWidthPixels = outPoint.x;
        } else {
            sScreenWidthPixels = display.getWidth();
        }
        return sScreenWidthPixels;
    }

    /**
     * Gets the height of the display, in pixels.
     *
     * @param context
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int screenHeightPixel(Context context) {
        int sScreenHeightPixels;
        WindowManager windowManager = (WindowManager) context.getSystemService(
                Context.WINDOW_SERVICE);
        assert windowManager != null;
        Display display = windowManager.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Point outPoint = new Point();
            display.getRealSize(outPoint);
            sScreenHeightPixels = outPoint.y;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point outPoint = new Point();
            display.getSize(outPoint);
            sScreenHeightPixels = outPoint.y;
        } else {
            sScreenHeightPixels = display.getHeight();
        }
        return sScreenHeightPixels;
    }

    /**
     * 测量 View
     *
     * @param measureSpec
     * @param defaultSize View 的默认大小
     * @return
     */
    public static int measure(int measureSpec, int defaultSize) {
        int result   = defaultSize;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);

        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == View.MeasureSpec.AT_MOST) {
            result = Math.min(result, specSize);
        }
        return result;
    }

    /**
     * 测量文字高度
     *
     * @param paint
     * @return
     */
    public static float measureTextHeight(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (Math.abs(fontMetrics.ascent) - fontMetrics.descent);
    }

    /**
     * 计算文字的高度
     *
     * @param paint
     * @return
     */
    public static int getTextHeight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.ascent);
    }

    public static int getTextFullHeight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.bottom - fm.top);
    }

    public static int getTextBaseLine(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) Math.abs(fm.bottom);
    }

    public static int getScreenWidth(Context c) {
        return c.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 计算圆弧长度
     *
     * @param radius 圆半径
     * @param angle  夹角度数（非弧度）
     * @return
     */
    public static float getCirclePathLength(float radius, float angle) {
        angle = changeAngleToSingle(angle);
        return (float) (Math.PI * radius * angle / 180);
    }

    /**
     * 将度数转换成0～360之间的值
     *
     * @param angle
     * @return
     */
    public static float changeAngleToSingle(float angle) {
        while (angle >= 360) {
            angle -= 360;
        }
        while (angle < 0) {
            angle += 360;
        }
        return angle;
    }

    /**
     * 获取文字的宽度
     *
     * @param paint
     * @param str
     * @return
     */
    public static int getTextWidth(Paint paint, String str) {
        int iRet = 0;
        if (str != null && str.length() > 0) {
            int     len    = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) Math.ceil(widths[j]);
            }
        }
        return iRet;
    }
}
