package cn.bittonet.wftpay.felonehelper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import cn.bittonet.wftpay.felonehelper.R;
import cn.bittonet.wftpay.felonehelper.adapter.GridImageAdapter;
import cn.bittonet.wftpay.felonehelper.bean.ImageBean;
import cn.bittonet.wftpay.felonehelper.utils.FullyGridLayoutManager;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageView    mLlAddImg;
    private List<LocalMedia> selectList = new ArrayList<>();
    private List<ImageBean>  imageList  = new ArrayList<>();
    private GridImageAdapter adapter;
    private int maxSelectNum = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        recyclerView = findViewById(R.id.recyclerview);
        findViewById(R.id.ll_add_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhoto();
            }
        });
        mLlAddImg = findViewById(R.id.iv_medic);
        initview();
    }

    private void initview() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    imageList.clear();
                    for (int i = 0; i < selectList.size(); i++) {
                        imageList.add(new ImageBean(selectList.get(i).getPath()));
                    }

                    if (imageList.size() > 0) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("index", position);
                        bundle.putSerializable("images", (Serializable) imageList);

                        Intent intent = new Intent(AActivity.this, PhotoViewActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            choosePhoto();
        }
    };

    private void choosePhoto() {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(this)
                       .openGallery(
                               PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                       .theme(R.style.picture_white_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                       .maxSelectNum(maxSelectNum)// 最大图片选择数量
                       .minSelectNum(1)// 最小选择数量
                       .imageSpanCount(4)// 每行显示个数
                       .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                       .previewImage(true)// 是否可预览图片
                       .isCamera(true)// 是否显示拍照按钮
                       .enableCrop(false)// 是否裁剪
                       .compress(true)// 是否压缩 true or false
                       .minimumCompressSize(100)// 小于100kb的图片不压缩
                       .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                       .selectionMedia(selectList)// 是否传入已选图片 List<LocalMedia> list
                       .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    if (selectList.size() > 0) {
                        mLlAddImg.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                        // 例如 LocalMedia 里面返回三种path
                        // 1.media.getPath(); 为原图path
                        // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                        // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                        // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                        adapter.setList(selectList);
                        adapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }
}
