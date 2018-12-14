package cn.bittonet.wftpay.felonehelper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import cn.bittonet.wftpay.felonehelper.R;
import cn.bittonet.wftpay.felonehelper.adapter.GalleryImagesAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;

public class OOMActivity extends AppCompatActivity implements OnRefreshListener,
        OnLoadmoreListener {
    private RecyclerView         recyclerview;
    private SmartRefreshLayout   refreshLayout;
    private GalleryImagesAdapter imagesAdapter;
    ArrayList<String> imagesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oom);

        initView();
    }

    private void initView() {
        recyclerview = findViewById(R.id.recyclerview);
        refreshLayout = findViewById(R.id.smartrefresh);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);

        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2,
                                                                                   GridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(
                StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        recyclerview.setLayoutManager(mLayoutManager);
        imagesAdapter = new GalleryImagesAdapter(imagesList);
        recyclerview.setAdapter(imagesAdapter);
        addData();
        imagesAdapter.openLoadAnimation();//开启动画
        imagesAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);//默认提供5种方法（渐显、缩放、从下到上，从左到右、从右到左）
        imagesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(OOMActivity.this, MvActivity.class);
                startActivity(intent);
            }
        });
    }
    private void addData() {
        for (int i = 1; i < 8; i++) {
            imagesList.add(
                    "https://file.juzimi.com/weibopic/jozimd"+i+".jpg");
        }
        imagesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        addData();
        refreshlayout.finishLoadmore();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh();
    }
}
