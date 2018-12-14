package cn.bittonet.wftpay.felonehelper.adapter;

import android.support.annotation.Nullable;
import cn.bittonet.wftpay.felonehelper.R;
import cn.bittonet.wftpay.felonehelper.holder.BaseViewHolderHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.autolayout.utils.AutoUtils;
import java.util.List;

/**
 * Created by Felone on 2018/12/13 0013 下午 5:23
 * description：
 * 50257836@qq.com
 */
public class MvAdapter extends BaseQuickAdapter<String, BaseViewHolderHelper> {

    public MvAdapter(@Nullable List<String> data) {
        super(R.layout.item_mv_play, data);
    }

    @Override
    protected void convert(BaseViewHolderHelper helper, String item) {
        AutoUtils.autoSize(helper.itemView);
        helper.setMvUrl(R.id.jz_video,item);
//        helper.setText(R.id.tv_bo_value, item.getBoValue() + "%")
//              .setText(R.id.tv_temp_value, item.getTempValue() + "℃")
//              .setText(R.id.tv_pulse_value, item.getPulseValue() + "bpm")
//              .setText(R.id.tv_check_time, item.getRecordTime());
    }
}
