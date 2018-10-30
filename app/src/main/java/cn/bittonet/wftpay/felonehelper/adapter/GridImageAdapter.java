package cn.bittonet.wftpay.felonehelper.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import cn.bittonet.wftpay.felonehelper.R;
import cn.bittonet.wftpay.felonehelper.holder.RvHolder;
import com.luck.picture.lib.entity.LocalMedia;
import java.util.ArrayList;
import java.util.List;

public class GridImageAdapter extends RecyclerView.Adapter<RvHolder> {

    private static final int TYPE_CAMERA  = 1;
    private static final int TYPE_PICTURE = 2;

    private List<LocalMedia> list = new ArrayList<>();

    private int selectMax = 9;

    /**
     * 点击添加图片跳转
     */
    private onAddPicClickListener mOnAddPicClickListener;

    public interface onAddPicClickListener {
        void onAddPicClick();
    }

    public GridImageAdapter(onAddPicClickListener mOnAddPicClickListener) {
        this.mOnAddPicClickListener = mOnAddPicClickListener;
    }

    public void setSelectMax(int selectMax) {
        this.selectMax = selectMax;
    }

    public void setList(List<LocalMedia> list) {
        this.list = list;
    }

    @Override
    public int getItemCount() {
        if (list.size() < selectMax) {
            return list.size() + 1;
        } else {
            return list.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowAddItem(position)) {
            return TYPE_CAMERA;
        } else {
            return TYPE_PICTURE;
        }
    }

    @NonNull
    @Override
    public RvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return RvHolder.get(parent, R.layout.item_filter_image);
    }

    private boolean isShowAddItem(int position) {
        int size = list.size() > 0 ? list.size() : 0;
        return position == size;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(@NonNull final RvHolder viewHolder, final int position) {
        viewHolder.setViewHeight(R.id.layout_image, 1 / 3f, 30);
        //少于8张，显示继续添加的图标
        if (getItemViewType(position) == TYPE_CAMERA) {
            viewHolder.setImageRes(R.id.fiv, R.drawable.add_image)
                      .setOnClickListener(R.id.fiv, new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              mOnAddPicClickListener.onAddPicClick();
                          }
                      })
                      .setVisibility(R.id.iv_del, View.INVISIBLE);
        } else {
            viewHolder.setVisibility(R.id.iv_del, View.VISIBLE)
                      .setOnClickListener(R.id.iv_del, new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              int index = viewHolder.getAdapterPosition();
                              // 这里有时会返回-1造成数据下标越界,具体可参考getAdapterPosition()源码，
                              // 通过源码分析应该是bindViewHolder()暂未绘制完成导致，知道原因的也可联系我~感谢
                              if (index != RecyclerView.NO_POSITION) {
                                  list.remove(index);
                                  notifyItemRemoved(index);
                                  notifyItemRangeChanged(index, list.size());
                              }
                          }
                      });
            LocalMedia media = list.get(position);
            String     path;
            if (media.isCut() && !media.isCompressed())

            {
                // 裁剪过
                path = media.getCutPath();
            } else if (media.isCompressed() || (media.isCut() && media.isCompressed()))

            {
                // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                path = media.getCompressPath();
            } else

            {
                // 原图
                path = media.getPath();
            }

            viewHolder.setImageUrl(R.id.fiv, path);

            //itemView 的点击事件
            if (mItemClickListener != null)

            {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                                           @Override
                                                           public void onClick(View v) {
                                                               int adapterPosition
                                                                       = viewHolder.getAdapterPosition();
                                                                           mItemClickListener.onItemClick(adapterPosition, v);
                                                           }
                                                       }
                );
            }
        }
    }

    private OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
