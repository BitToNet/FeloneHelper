package cn.bittonet.wftpay.felonehelper.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import cn.bittonet.wftpay.felonehelper.R;
import cn.bittonet.wftpay.felonehelper.adapter.GalleryImagesAdapter;
import cn.bittonet.wftpay.felonehelper.adapter.MvAdapter;
import cn.bittonet.wftpay.felonehelper.widget.MyJzvdStd;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.util.ArrayList;
import java.util.Arrays;

public class MvActivity extends AppCompatActivity {
    String[] urls = {
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=v0200fa10000bg7ok2b3ft9o1hmrc610&line=0",
            "https://api.amemv.com/aweme/v1/playwm/?video_id=v0200fff0000bemdeu9pjc2ugfpqqrjg&line=0&ratio=720p&media_type=4&vr_type=0&test_cdn=None&improve_bitrate=0",
            "https://api.amemv.com/aweme/v1/playwm/?video_id=v0200fc00000bfl7e78697ankqfdj8c0&line=0&ratio=720p&media_type=4&vr_type=0&test_cdn=None&improve_bitrate=0",
            "https://api.amemv.com/aweme/v1/play/?video_id=v0200f1b0000be6baucd1dr3qdhlo9f0&line=0&ratio=720p&media_type=4&vr_type=0&test_cdn=None&improve_bitrate=0",
            "http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"};

    private RecyclerView         mRecyclerView;
    private SmartRefreshLayout   refreshLayout;
    private GalleryImagesAdapter imagesAdapter;
    private MvAdapter            mvAdapter;
    private ArrayList<String> strings = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oom);

        initView();
    }

    private void initView() {
        fullScreen(this);
        mRecyclerView = findViewById(R.id.recyclerview);
        refreshLayout = findViewById(R.id.smartrefresh);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setEnableRefresh(false);

        mRecyclerView.setNestedScrollingEnabled(false);
        PagerSnapHelper snapHelper = new PagerSnapHelper() {
            // 在 Adapter的 onBindViewHolder 之后执行
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager,
                    int velocityX, int velocityY) {
                // TODO 找到对应的Index
                int targetPos = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
                setMvItemClick(linearLayoutManager.findViewByPosition(targetPos));
                if (targetPos >= (strings.size() - 1)) {
                    strings.addAll(Arrays.asList(urls));
                    mvAdapter.notifyDataSetChanged();
                }
                return targetPos;
            }

            // 在 Adapter的 onBindViewHolder 之后执行
            @Nullable
            @Override
            public View findSnapView(RecyclerView.LayoutManager layoutManager) {
                // TODO 找到对应的View
                View view = super.findSnapView(layoutManager);
                //                setMvItemClick(view);
                return view;
            }
        };
        snapHelper.attachToRecyclerView(mRecyclerView);
        // ---布局管理器---
        linearLayoutManager = new LinearLayoutManager(this);
        // 默认是Vertical (HORIZONTAL则为横向列表)
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.getViewTreeObserver()
                     .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                         @Override
                         public void onGlobalLayout() {
                             if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                                 mRecyclerView.getViewTreeObserver()
                                              .removeGlobalOnLayoutListener(this);
                             } else {
                                 mRecyclerView.getViewTreeObserver()
                                              .removeOnGlobalLayoutListener(this);
                             }
                             strings.addAll(Arrays.asList(urls));

                             /**
                              *  这么写是为了获取RecycleView的宽高
                              */
                             // 创建Adapter，并指定数据集
                             mvAdapter = new MvAdapter(strings);
                             // 设置Adapter
                             mRecyclerView.setAdapter(mvAdapter);
                             mRecyclerView.scrollBy(0, 0);
                             //                             if(strings!=null){
                             //                                 while (true){
                             //                                     if(mRecyclerView.getChildAt(0)==null){
                             //                                     }else {
                             //                                         setMvItemClick(mRecyclerView.getChildAt(0));
                             //                                         break;
                             //                                     }
                             //
                             //                                 }
                             //
                             //                             }
                         }
                     });
    }

    private void setMvItemClick(View view) {
        if (view == null) {
            return;
        }
        MyJzvdStd myJzvdStd = view.findViewById(R.id.jz_video);
        myJzvdStd.startVideo();
        myJzvdStd.setCallbacks(new MyJzvdStd.Callbacks() {
            @Override
            public void onBack() {
                onBackPressed();
            }

            @Override
            public void onLoveClicked() {

            }

            @Override
            public void onStartClicked() {

            }

            @Override
            public void onShareClicked() {

            }
        });
    }

    @Override
    public void onBackPressed() {
        JzvdStd.goOnPlayOnPause();
        Jzvd.backPress();
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //home back
        JzvdStd.goOnPlayOnResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //                setMvItemClick(mRecyclerView.getChildAt(0));
                setMvItemClick(linearLayoutManager.findViewByPosition(0));
            }
        }, 300);//2为recyclerView中item位置，

    }

    @Override
    protected void onPause() {
        super.onPause();
        //     Jzvd.clearSavedProgress(this, null);
        //home back
        JzvdStd.goOnPlayOnPause();
    }

    /**
     * 通过设置全屏，设置状态栏透明
     *
     * @param activity
     */
    private void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window    = activity.getWindow();
                View   decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以正常设置
                //                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window                     window     = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation
                        = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
                //                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }

}
