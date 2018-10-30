package cn.bittonet.wftpay.felonehelper.utils;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.DrawableRes;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

/**
 * MyLibrary
 * Package chingtech.library.utils
 * Description:
 * Created by 师春雷
 * Created at 2017/4/11 11:32
 */
public class ViewUtils {

    private ViewUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 设置EditText输入金钱时小数点动态监测
     *
     * @param editText
     */
    public static void setPricePoint(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0, s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (".".equals(s.toString().trim())) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }
                if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
                    if (!".".equals(s.toString().substring(1, 2))) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });
    }

    /**
     * 设置View高度(高度占屏幕宽度的比例)
     *
     * @param context
     * @param view
     * @param type
     * @param screen
     */
    public static void setViewHeight(Context context, View view, float type, float screen) {
        int width = ScreenUtils.screenWidthPixel(context);

        int height = (int) ((width * 1.0f - screen) * type);

        /** 取控件View当前的布局参数 */
        ViewGroup.LayoutParams params = view.getLayoutParams();
        /** 控件的高强制设成height */
        params.height = height;
        /** 使设置好的布局参数应用到控件 */
        view.setLayoutParams(params);
    }

    /**
     * 设置View高度(高度占屏幕宽度的比例)
     *
     * @param context
     * @param view
     * @param type
     */
    public static void setViewHeight(Context context, View view, float type) {
        setViewHeight(context, view, type, 0f);
    }

    /**
     * 设置View宽度(占屏幕宽度比例)
     *
     * @param context
     * @param view
     * @param type
     * @param screen
     */
    public static void setViewWidth(Context context, View view, float type, float screen) {
        int width = ScreenUtils.screenWidthPixel(context);

        int _width = (int) ((width * 1.0f - screen) * type);

        /** 取控件View当前的布局参数 */
        ViewGroup.LayoutParams params = view.getLayoutParams();
        /** 控件的高强制设成_width */
        params.width = _width;
        /** 使设置好的布局参数应用到控件 */
        view.setLayoutParams(params);
    }

    /**
     * 设置View宽度(占屏幕宽度比例)
     *
     * @param context
     * @param view
     * @param type
     */
    public static void setViewWidth(Context context, View view, float type) {
        setViewWidth(context, view, type, 0f);
    }

    /**
     * 设置View大小(宽高相同，占屏幕宽度的比例)
     *
     * @param context
     * @param view
     * @param type
     * @param screen
     */
    public static void setViewSize(Context context, View view, float type, float screen) {
        int width = ScreenUtils.screenWidthPixel(context);

        int size = (int) ((width * 1.0f - screen) * type);

        /** 取控件View当前的布局参数 */
        ViewGroup.LayoutParams params = view.getLayoutParams();
        /** 控件的高强制设成 size */
        params.height = size;
        /** 控件的高强制设成 size */
        params.width = size;
        /** 使设置好的布局参数应用到控件 */
        view.setLayoutParams(params);
    }

    /**
     * 设置View大小(宽高相同，占屏幕宽度的比例)
     *
     * @param context
     * @param view
     * @param type
     */
    public static void setViewSize(Context context, View view, float type) {
        setViewSize(context, view, type, 0f);
    }

    /**
     * 设置View宽度
     *
     * @param view
     * @param width
     */
    public static void setViewWidthPx(View view, int width) {
        setViewSizePx(view, width, -1);
    }

    /**
     * 设置View高度
     *
     * @param view
     * @param height
     */
    public static void setViewHeightPx(View view, int height) {
        setViewSizePx(view, -1, height);
    }

    /**
     * 设置View尺寸
     *
     * @param view
     * @param width
     * @param height
     */
    public static void setViewSizePx(View view, int width, int height) {
        /** 取控件View当前的布局参数 */
        ViewGroup.LayoutParams params = view.getLayoutParams();
        /** 控件的高强制设成 height */
        if (height != -1) {
            params.height = height;
        }
        /** 控件的高强制设成 width */
        if (width != -1) {
            params.width = width;
        }
        /** 使设置好的布局参数应用到控件 */
        view.setLayoutParams(params);
    }

    /**
     * 给TextView设置下划线
     *
     * @param textView
     */
    public static void setUnderLine(TextView textView) {
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        textView.getPaint().setAntiAlias(true);
    }

    /**
     * 给TextView设置删除线
     *
     * @param textView
     */
    public static void setDeleteLine(TextView textView) {
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        textView.getPaint().setAntiAlias(true);
    }

    /**
     * 监听EditText
     *
     * @param editText
     */
    public static void setTextChanged(final EditText editText, final View view) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (editText.getText().length() > 0) {
                    view.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editText.getText().length() > 0) {
                    view.setVisibility(View.VISIBLE);
                } else {
                    view.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * 设置图片
     *
     * @param context             上下文
     * @param drawableView        控件
     * @param imageUrl            图片url路径
     * @param placeholderDrawable 加载占位图
     * @param errorDrawable       失败占位图
     */
    public static void initImage(Context context, ImageView drawableView, String imageUrl,
            @DrawableRes int placeholderDrawable, @DrawableRes int errorDrawable) {
        RequestOptions options = new RequestOptions().centerCrop()
                                                     .placeholder(placeholderDrawable)
                                                     .error(errorDrawable)
                                                     .priority(Priority.HIGH)
                                                     .diskCacheStrategy(DiskCacheStrategy.NONE);
        DrawableTransitionOptions mTransitionOptions = new DrawableTransitionOptions().crossFade();
        Glide.with(context)
             .load(imageUrl)
             .apply(options)
             .transition(mTransitionOptions)
             .into(drawableView);
    }

    public static void initImage(Context context, ImageView drawableView, String imageUrl,
            @DrawableRes int defaultDrawable) {
        initImage(context, drawableView, imageUrl, defaultDrawable, defaultDrawable);
    }

    public static void initImage(Context context, ImageView drawableView, String imageUrl) {
        initImage(context, drawableView, imageUrl, 0, 0);
    }

    /**
     * 设置头像
     *
     * @param context         上下文
     * @param drawableView    控件
     * @param imageUrl        头像url路径
     * @param defaultDrawable 占位图片
     */
    public static void setHeader(Context context, ImageView drawableView, String imageUrl,
            @DrawableRes int defaultDrawable) {
        RequestOptions options = new RequestOptions().placeholder(defaultDrawable)
                                                     .error(defaultDrawable)
                                                     .priority(Priority.HIGH)
                                                     .dontAnimate()
                                                     .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(context).load(imageUrl).apply(options).into(drawableView);
    }
}
