package cn.bittonet.wftpay.felonehelper.utils.Sidebar;

import android.support.annotation.Nullable;
import cn.bittonet.wftpay.felonehelper.R;
import cn.bittonet.wftpay.felonehelper.holder.BaseViewHolderHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.autolayout.utils.AutoUtils;
import java.util.List;



public class SortAdapter extends BaseQuickAdapter<SortModel, BaseViewHolderHelper> {

    private List<SortModel> mData;

    public SortAdapter(@Nullable List<SortModel> data) {
        super(R.layout.item_name, data);
        mData = data;
    }

    @Override
    protected void convert(BaseViewHolderHelper helper, SortModel item) {
        AutoUtils.autoSize(helper.itemView);
        helper.setText(R.id.tvName, item.getName())
        ;
    }
    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr   = mData.get(i).getLetters();
            char   firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

}
