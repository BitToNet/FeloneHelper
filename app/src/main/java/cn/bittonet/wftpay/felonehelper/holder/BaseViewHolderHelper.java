package cn.bittonet.wftpay.felonehelper.holder;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.bittonet.wftpay.felonehelper.R;
import cn.bittonet.wftpay.felonehelper.utils.DownloadImageTask;
import cn.bittonet.wftpay.felonehelper.utils.ViewUtils;
import cn.bittonet.wftpay.felonehelper.widget.MyJzvdStd;
import cn.bittonet.wftpay.felonehelper.widget.RoundTextView;
import cn.bittonet.wftpay.felonehelper.widget.SmoothCheckBox;
import cn.jzvd.JZDataSource;
import cn.jzvd.JzvdStd;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;


public class BaseViewHolderHelper extends BaseViewHolder {

    private MyJzvdStd myJzvdStd;

    public BaseViewHolderHelper(View view) {
        super(view);
    }

    public BaseViewHolderHelper setHtmlText(@IdRes int viewId, @StringRes int strId, String value) {
        TextView view = getView(viewId);
        String htmlValue = String.format(view.getContext().getResources().getString(strId), value);
        view.setText(Html.fromHtml(htmlValue));
        return this;
    }

    /**
     * 给TextView添加删除线
     *
     * @param viewId
     * @return
     */
    public BaseViewHolderHelper setTextDeleteLine(@IdRes int viewId) {
        TextView view = getView(viewId);
        view.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        return this;
    }

    /**
     * 给TextView添加下划线
     *
     * @param viewId
     * @return
     */
    public BaseViewHolderHelper setTextUnderLine(@IdRes int viewId) {
        TextView view = getView(viewId);
        view.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        return this;
    }

    /**
     * Set a view visibility to VISIBLE or GONE or INVISIBLE.
     *
     * @param viewId  The view id.
     * @param visible VISIBLE, GONE, or INVISIBLE.
     * @return The BaseViewHolder for chaining.
     */
    public BaseViewHolder setVisible(@IdRes int viewId, int visible) {
        View view = getView(viewId);
        view.setVisibility(visible);
        return this;
    }

    /**
     * 加载网络图片
     *
     * @param viewId   控件ID
     * @param imageUrl 图片URL
     * @return
     */
    public BaseViewHolderHelper setImageUrl(@IdRes int viewId, String imageUrl) {
        ImageView imageview = getView(viewId);
        RequestOptions options = new RequestOptions().centerCrop()
                                                     .priority(Priority.HIGH)
                                                     .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(imageview.getContext()).load(imageUrl).apply(options).into(imageview);
        return this;
    }

    /**
     * 加载网络视频
     *
     * @param viewId   控件ID
     * @param imageUrl 图片URL
     * @return
     */
    @SuppressLint("NewApi")
    public BaseViewHolderHelper setMvUrl(@IdRes int viewId, String imageUrl) {
        JZDataSource jzDataSource = new JZDataSource(imageUrl, imageUrl);
        jzDataSource.looping = true;
        if(myJzvdStd==null){
            myJzvdStd = getView(viewId);
            //播放器圆角设置
            myJzvdStd.setOutlineProvider(new MyJzvdStd.JzViewOutlineProvider(new Float(15)));
            myJzvdStd.setClipToOutline(true);
            myJzvdStd
                    //                .setUp("http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"
                    //                .setUp("https://api.amemv.com/aweme/v1/play/?video_id=v0200f1b0000be6baucd1dr3qdhlo9f0&line=0&ratio=720p&media_type=4&vr_type=0&test_cdn=None&improve_bitrate=0"
                    .setUp(jzDataSource, JzvdStd.SCREEN_WINDOW_NORMAL);
            //            myJzvdStd.startVideo();

        }else {
            myJzvdStd.changeUrl(jzDataSource, 0);
        }



        //        myJzvdStd.setCallbacks();
        return this;
    }


    /**
     * 加载网络图片
     *
     * @param viewId   控件ID
     * @param imageUrl 图片URL
     * @return
     */
    public BaseViewHolderHelper setImageUrl2(@IdRes int viewId, String imageUrl) {
        ImageView imageview = getView(viewId);
        RequestOptions options = new RequestOptions().priority(Priority.HIGH)
                                                     .placeholder(R.drawable.image_processing)
                                                     .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(imageview.getContext()).load(imageUrl).apply(options).into(imageview);
        return this;
    }

    /**
     * 加载网络图片
     *
     * @param viewId          控件ID
     * @param imageUrl        图片URL
     * @param defaultDrawable 默认图片
     * @return
     */
    public BaseViewHolderHelper setImageUrl(@IdRes int viewId, String imageUrl,
            @DrawableRes int defaultDrawable) {
        ImageView imageview = getView(viewId);
        RequestOptions options = new RequestOptions().centerCrop()
                                                     .placeholder(defaultDrawable)
                                                     .error(defaultDrawable)
                                                     .priority(Priority.HIGH)
                                                     .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(imageview.getContext()).load(imageUrl).apply(options).into(imageview);
        return this;
    }

    /**
     * 加载网络图片
     *
     * @param viewId          控件ID
     * @param imageUrl        图片URL
     * @param defaultDrawable 默认图片
     * @param isGif           是否开启gif
     * @return
     */
    public BaseViewHolderHelper setImageUrl(@IdRes int viewId, String imageUrl, boolean isGif,
            @DrawableRes int defaultDrawable) {
        ImageView imageview = getView(viewId);
        RequestOptions options = new RequestOptions().centerCrop()
                                                     .placeholder(defaultDrawable)
                                                     .error(defaultDrawable)
                                                     .priority(Priority.HIGH)
                                                     .diskCacheStrategy(DiskCacheStrategy.NONE);

        if (isGif) {
            Glide.with(imageview.getContext())
                 .asGif()
                 .load(imageUrl)
                 .apply(options)
                 .into(imageview);
        } else {
            Glide.with(imageview.getContext()).load(imageUrl).apply(options).into(imageview);
        }
        return this;
    }

    public BaseViewHolderHelper setCircleImageUrl(@IdRes int viewId, String imageUrl,
            @DrawableRes int defaultDrawable) {
        ImageView imageview = getView(viewId);
        RequestOptions options = new RequestOptions().placeholder(defaultDrawable)
                                                     .error(defaultDrawable)
                                                     .priority(Priority.HIGH)
                                                     .dontAnimate()
                                                     .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(imageview.getContext()).load(imageUrl).apply(options).into(imageview);
        return this;
    }

    public BaseViewHolderHelper setAvatar(@IdRes int viewId, String imageUrl,
            @DrawableRes int defaultDrawable) {
        ImageView imageview = getView(viewId);
        RequestOptions options = new RequestOptions().placeholder(defaultDrawable)
                                                     .error(defaultDrawable)
                                                     .priority(Priority.HIGH)
                                                     .dontAnimate()
                                                     .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(imageview.getContext()).load(imageUrl).apply(options).into(imageview);
        return this;
    }

    /**
     * 加载网络图片作为一个布局的背景
     *
     * @param viewId        布局控件ID
     * @param backgroundUrl 背景图片URL
     * @return
     */
    public BaseViewHolderHelper setViewGroupBackgroundUrl(@IdRes int viewId, String backgroundUrl) {
        ViewGroup imageview = getView(viewId);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            new DownloadImageTask(imageview).execute(backgroundUrl);
        }
        return this;
    }

    /**
     * 设置图片资源的颜色
     *
     * @param viewId    控件ID
     * @param colorARGB 要设置的颜色
     * @return
     */
    public BaseViewHolderHelper setColorFilter(@IdRes int viewId, @ColorInt int colorARGB) {
        ImageView view = getView(viewId);
        view.setColorFilter(colorARGB);
        return this;
    }

    /**
     * 设置图片资源的颜色
     *
     * @param viewId 控件ID
     * @param r      红色
     * @param g      绿色
     * @param b      蓝色
     * @return
     */
    public BaseViewHolderHelper setColorFilter(@IdRes int viewId, int r, int g, int b) {
        ImageView view = getView(viewId);
        view.setColorFilter(Color.argb(255, r, g, b));
        return this;
    }

    /**
     * 设置图片资源的颜色
     *
     * @param viewId 控件ID
     * @param a      透明度
     * @param r      红色
     * @param g      绿色
     * @param b      蓝色
     * @return
     */
    public BaseViewHolderHelper setColorFilter(@IdRes int viewId, int a, int r, int g, int b) {
        ImageView view = getView(viewId);
        view.setColorFilter(Color.argb(a, r, g, b));
        return this;
    }

    /**
     * 设置控件的高度
     *
     * @param viewId The view id.
     * @param type   所占屏幕宽度比例
     * @return
     */
    public BaseViewHolderHelper setViewHeight(@IdRes int viewId, float type) {
        View view = getView(viewId);
        ViewUtils.setViewHeight(view.getContext(), view, type);
        return this;
    }

    /**
     * 设置控件的高度
     *
     * @param viewId The view id.
     * @param type   所占屏幕宽度比例
     * @param screen 间隔尺寸
     * @return
     */
    public BaseViewHolderHelper setViewHeight(@IdRes int viewId, float type, float screen) {
        View view = getView(viewId);
        ViewUtils.setViewHeight(view.getContext(), view, type, screen);
        return this;
    }

    /**
     * 设置控件的宽度
     *
     * @param viewId The view id.
     * @param type   所占屏幕宽度比例
     */
    public BaseViewHolderHelper setViewWidth(@IdRes int viewId, float type) {
        View view = getView(viewId);
        ViewUtils.setViewWidth(view.getContext(), view, type);
        return this;
    }

    /**
     * 设置控件的宽度
     *
     * @param viewId The view id.
     */
    public BaseViewHolderHelper setViewWidth(@IdRes int viewId, float type, float screen) {
        View view = getView(viewId);
        ViewUtils.setViewWidth(view.getContext(), view, type, screen);
        return this;
    }

    /**
     * 设置控件的大小
     *
     * @param viewId The view id.
     * @param type   所占屏幕宽度比例
     */
    public BaseViewHolderHelper setViewSize(@IdRes int viewId, float type) {
        View view = getView(viewId);
        ViewUtils.setViewSize(view.getContext(), view, type);
        return this;
    }

    /**
     * 设置控件的大小
     *
     * @param viewId The view id.
     * @param type   所占屏幕宽度比例
     */
    public BaseViewHolderHelper setViewSize(@IdRes int viewId, float type, float screen) {
        View view = getView(viewId);
        ViewUtils.setViewSize(view.getContext(), view, type, screen);
        return this;
    }

    /**
     * 设置控件的尺寸
     *
     * @param viewId The view id.
     * @param width  The view width.
     * @param height The view height.
     */
    public BaseViewHolderHelper setViewSize(@IdRes int viewId, int width, int height) {
        View view = getView(viewId);
        ViewUtils.setViewSize(view.getContext(), view, width, height);
        return this;
    }

    public BaseViewHolderHelper setEnabled(@IdRes int viewId, boolean enable) {
        View view = getView(viewId);
        view.setEnabled(enable);
        return this;
    }

    public BaseViewHolderHelper setRoundViewBgColor(@IdRes int viewId, @ColorRes int color) {
        RoundTextView view = getView(viewId);
        view.getDelegate().setBackgroundColor(ContextCompat.getColor(view.getContext(), color));
        return this;
    }

    public BaseViewHolderHelper setRoundViewBgPressColor(@IdRes int viewId, @ColorRes int color) {
        RoundTextView view = getView(viewId);
        view.getDelegate()
            .setBackgroundPressColor(ContextCompat.getColor(view.getContext(), color));
        return this;
    }

    public BaseViewHolderHelper setSmoothChecked(@IdRes int viewId, boolean checked,
            boolean animate) {
        SmoothCheckBox view = getView(viewId);
        view.setChecked(checked, animate);
        return this;
    }

    public BaseViewHolderHelper setSmoothChecked(@IdRes int viewId, boolean checked) {
        SmoothCheckBox view = getView(viewId);
        view.setChecked(checked);
        return this;
    }

    public BaseViewHolderHelper setOnCheckedChangeListener(@IdRes int viewId,
            SmoothCheckBox.OnCheckedChangeListener listener) {
        SmoothCheckBox view = getView(viewId);
        view.setOnCheckedChangeListener(listener);
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesWithIntrinsicBoundsLeft(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawablesWithIntrinsicBounds(
                ContextCompat.getDrawable(view.getContext(), drawableRes), null, null, null);
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesWithIntrinsicBoundsTop(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawablesWithIntrinsicBounds(null,
                                                     ContextCompat.getDrawable(view.getContext(),
                                                                               drawableRes), null,
                                                     null);
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesWithIntrinsicBoundsRight(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawablesWithIntrinsicBounds(null, null,
                                                     ContextCompat.getDrawable(view.getContext(),
                                                                               drawableRes), null);
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesWithIntrinsicBoundsBottom(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
                                                     ContextCompat.getDrawable(view.getContext(),
                                                                               drawableRes));
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesWithIntrinsicBounds(@IdRes int viewId,
            @DrawableRes int drawableResLeft, @DrawableRes int drawableResTop,
            @DrawableRes int drawableResRight, @DrawableRes int drawableResBottom) {
        TextView view = getView(viewId);
        view.setCompoundDrawablesWithIntrinsicBounds(
                ContextCompat.getDrawable(view.getContext(), drawableResLeft),
                ContextCompat.getDrawable(view.getContext(), drawableResTop),
                ContextCompat.getDrawable(view.getContext(), drawableResRight),
                ContextCompat.getDrawable(view.getContext(), drawableResBottom));
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesWithIntrinsicBounds(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawablesWithIntrinsicBounds(
                ContextCompat.getDrawable(view.getContext(), drawableRes),
                ContextCompat.getDrawable(view.getContext(), drawableRes),
                ContextCompat.getDrawable(view.getContext(), drawableRes),
                ContextCompat.getDrawable(view.getContext(), drawableRes));
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesLeft(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawables(ContextCompat.getDrawable(view.getContext(), drawableRes), null,
                                  null, null);
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesTop(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawables(null, ContextCompat.getDrawable(view.getContext(), drawableRes),
                                  null, null);
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesRight(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawables(null, null,
                                  ContextCompat.getDrawable(view.getContext(), drawableRes), null);
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesBottom(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawables(null, null, null,
                                  ContextCompat.getDrawable(view.getContext(), drawableRes));
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawables(@IdRes int viewId,
            @DrawableRes int drawableResLeft, @DrawableRes int drawableResTop,
            @DrawableRes int drawableResRight, @DrawableRes int drawableResBottom) {
        TextView view = getView(viewId);
        view.setCompoundDrawables(ContextCompat.getDrawable(view.getContext(), drawableResLeft),
                                  ContextCompat.getDrawable(view.getContext(), drawableResTop),
                                  ContextCompat.getDrawable(view.getContext(), drawableResRight),
                                  ContextCompat.getDrawable(view.getContext(), drawableResBottom));
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawables(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawables(ContextCompat.getDrawable(view.getContext(), drawableRes),
                                  ContextCompat.getDrawable(view.getContext(), drawableRes),
                                  ContextCompat.getDrawable(view.getContext(), drawableRes),
                                  ContextCompat.getDrawable(view.getContext(), drawableRes));
        return this;
    }

    public BaseViewHolderHelper setLayoutManager(@IdRes int viewId, LinearLayoutManager manager) {
        RecyclerView recyclerview = getView(viewId);
        recyclerview.setLayoutManager(manager);
        return this;
    }

    public BaseViewHolderHelper setLayoutManager(@IdRes int viewId, GridLayoutManager manager) {
        RecyclerView recyclerview = getView(viewId);
        recyclerview.setLayoutManager(manager);
        return this;
    }

    @Override
    public BaseViewHolderHelper setRating(@IdRes int viewId, float stars) {
        MaterialRatingBar ratingBar = getView(viewId);
        ratingBar.setRating(stars);
        return this;
    }

    public BaseViewHolderHelper setNumStars(@IdRes int viewId, int stars) {
        MaterialRatingBar ratingBar = getView(viewId);
        ratingBar.setNumStars(stars);
        return this;
    }

    public BaseViewHolderHelper setStepSize(@IdRes int viewId, float stars) {
        MaterialRatingBar ratingBar = getView(viewId);
        ratingBar.setStepSize(stars);
        return this;
    }

    @SuppressLint("ClickableViewAccessibility")
    public BaseViewHolderHelper setOnClick(@IdRes int viewId, final boolean isCheck) {
        MaterialRatingBar ratingBar = getView(viewId);
        ratingBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                return !isCheck;
            }
        });

        return this;
    }

    /**
     * 控件旋转
     *
     * @param viewId 控件ID
     * @param value  旋转角度
     * @return
     */
    public BaseViewHolderHelper rotation(@IdRes int viewId, float value) {
        ImageView view = getView(viewId);
        ViewCompat.animate(view).rotation(value).start();
        return this;
    }
}
