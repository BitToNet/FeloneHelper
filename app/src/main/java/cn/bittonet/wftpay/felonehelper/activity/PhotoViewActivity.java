package cn.bittonet.wftpay.felonehelper.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import cn.bittonet.wftpay.felonehelper.R;
import cn.bittonet.wftpay.felonehelper.adapter.PhotoViewAdapter;
import cn.bittonet.wftpay.felonehelper.bean.ImageBean;
import cn.bittonet.wftpay.felonehelper.widget.RoundTextView;
import java.util.List;

public class PhotoViewActivity extends AppCompatActivity {
    ViewPager     viewPager;
    RoundTextView rtIndex;
    private int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        viewPager = findViewById(R.id.view_pager);
        rtIndex = findViewById(R.id.rt_number);
        initView();
    }

    private void initView() {

        int index = getIntent().getIntExtra("index",0);

        List<ImageBean> imageUrl = (List<ImageBean>) getIntent().getSerializableExtra("images");
        assert imageUrl != null;
        size = imageUrl.size() > 0 ? imageUrl.size() : 0;
        PhotoViewAdapter adapter = new PhotoViewAdapter(this, imageUrl);
        viewPager.setAdapter(adapter);

        rtIndex.setText((index + 1) + "/" + size);
        viewPager.setCurrentItem(index);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                    int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                rtIndex.setText((position + 1) + "/" + size);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
