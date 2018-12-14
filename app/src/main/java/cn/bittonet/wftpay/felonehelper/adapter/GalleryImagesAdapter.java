package cn.bittonet.wftpay.felonehelper.adapter;

import android.support.annotation.Nullable;
import cn.bittonet.wftpay.felonehelper.R;
import cn.bittonet.wftpay.felonehelper.holder.BaseViewHolderHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.autolayout.utils.AutoUtils;
import java.util.List;

/**
 * Created by Felone on 2018/12/11 0011 下午 1:48
 * description：
 * 50257836@qq.com
 */
public class GalleryImagesAdapter extends BaseQuickAdapter<String, BaseViewHolderHelper> {

    public GalleryImagesAdapter(@Nullable List<String> data) {
        super(R.layout.item_mv, data);
    }

    @Override
    protected void convert(BaseViewHolderHelper helper, String item) {
        AutoUtils.autoSize(helper.itemView);
        helper.setImageUrl2(R.id.iv, item)
        .setText(R.id.tv,item);
    }
}
