package cn.bittonet.wftpay.felonehelper.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Outline;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.TextView;
import cn.bittonet.wftpay.felonehelper.R;
import cn.jzvd.JZDataSource;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * 这里可以监听到视频播放的生命周期和播放状态
 * 所有关于视频的逻辑都应该写在这里
 */
public class MyJzvdStd extends JzvdStd {

    private float     mPosX;
    private float     mPosY;
    private float     mCurPosX;
    private float     mCurPosY;
    public  Callbacks mCallbacks;
    private ImageView iv_love;
    private ImageView iv_start;
    private ImageView iv_share;
    private TextView tv_love;
    private TextView tv_start;
    private TextView tv_share;
    private boolean isLove1= false;
    private boolean isStart1= false;


    public MyJzvdStd(Context context) {
        super(context);
    }

    public MyJzvdStd(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context) {
        super.init(context);
        initView();
        Jzvd.SAVE_PROGRESS = false;
    }

    private void initView() {
        iv_love = findViewById(R.id.iv_love);
        iv_start = findViewById(R.id.iv_start);
        iv_share = findViewById(R.id.iv_share);
        iv_share.setOnClickListener(this);
        findViewById(R.id.ll_share).setOnClickListener(this);
        findViewById(R.id.ll_start).setOnClickListener(this);
        findViewById(R.id.ll_love).setOnClickListener(this);
        tv_love = findViewById(R.id.tv_love);
        tv_start = findViewById(R.id.tv_start);
        tv_share = findViewById(R.id.tv_share);
    }

    public void setCallbacks(Callbacks callbacks) {
        this.mCallbacks = callbacks;
    }

    //定义接口用于回调
    public interface Callbacks {
        void onBack();
        void onLoveClicked();
        void onStartClicked();
        void onShareClicked();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i1 = v.getId();
        if (i1 == R.id.back) {
            mCallbacks.onBack();

        } else if (i1 == R.id.ll_love) {
            isLove1 = !isLove1;
            iv_love.setSelected(isLove1);
            mCallbacks.onLoveClicked();

        } else if (i1 == R.id.ll_start) {
            isStart1 = !isStart1;
            iv_start.setSelected(isStart1);
            mCallbacks.onStartClicked();

        } else if (i1 == R.id.iv_share) {
            mCallbacks.onShareClicked();

        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.jz_layout_std_my;
    }

    @Override
    public void setUp(JZDataSource jzDataSource, int screen) {
        super.setUp(jzDataSource, screen);
        backButton.setVisibility(VISIBLE);

    }

    public void setView(boolean isLove, boolean isStart,String tvlove,
            String tvStart, String tvShare) {
        isLove1 = isLove;
        isStart1 = isStart;
        iv_love.setSelected(isLove);
        iv_start.setSelected(isStart);

        tv_love.setText(tvlove);
        tv_start.setText(tvStart);
        tv_share.setText(tvShare);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return super.onTouch(v, event);
    }

    @Override
    public void startVideo() {
        super.startVideo();
    }

    @Override
    public void onAutoCompletion() {
        super.onAutoCompletion();
        startVideo();
    }

    //onState 代表了播放器引擎的回调，播放视频各个过程的状态的回调
    @Override
    public void onStateNormal() {
        super.onStateNormal();
    }

    @Override
    public void onStatePreparing() {
        super.onStatePreparing();
    }

    @Override
    public void onStatePlaying() {
        super.onStatePlaying();
    }

    @Override
    public void onStatePause() {
        super.onStatePause();
    }

    @Override
    public void onStateError() {
        super.onStateError();
    }

    @Override
    public void onStateAutoComplete() {
        super.onStateAutoComplete();
    }

    //changeUiTo 真能能修改ui的方法
    @Override
    public void changeUiToNormal() {
        super.changeUiToNormal();
    }

    @Override
    public void changeUiToPreparing() {
        super.changeUiToPreparing();
    }

    @Override
    public void changeUiToPlayingShow() {
        super.changeUiToPlayingShow();
    }

    @Override
    public void changeUiToPlayingClear() {
        super.changeUiToPlayingClear();
    }

    @Override
    public void changeUiToPauseShow() {
        super.changeUiToPauseShow();
    }

    @Override
    public void changeUiToPauseClear() {
        super.changeUiToPauseClear();
    }

    @Override
    public void changeUiToComplete() {
        super.changeUiToComplete();
    }

    @Override
    public void changeUiToError() {
        super.changeUiToError();
    }

    @Override
    public void onInfo(int what, int extra) {
        super.onInfo(what, extra);
    }

    @Override
    public void onError(int what, int extra) {
        super.onError(what, extra);
    }

    @Override
    public void startWindowFullscreen() {
        super.startWindowFullscreen();
    }

    @Override
    public void startWindowTiny() {
        super.startWindowTiny();
    }

    @SuppressLint("NewApi")
    public static class JzViewOutlineProvider extends ViewOutlineProvider {
        private float mRadius;

        public JzViewOutlineProvider(float radius) {
            this.mRadius = radius;
        }

        @Override
        public void getOutline(View view, Outline outline) {
            Rect rect = new Rect();
            view.getGlobalVisibleRect(rect);
            Rect selfRect = new Rect(0, 0, view.getWidth(), view.getHeight());
            outline.setRoundRect(selfRect, mRadius);
        }
    }


}
