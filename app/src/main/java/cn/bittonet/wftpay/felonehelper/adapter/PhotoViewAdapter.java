package cn.bittonet.wftpay.felonehelper.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.bittonet.wftpay.felonehelper.bean.ImageBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.photoview.PhotoView;
import java.util.List;

public class PhotoViewAdapter extends PagerAdapter {

    private Context         context;
    private List<ImageBean> imageUrl;

    public PhotoViewAdapter(Context context, List<ImageBean> imageUrl) {
        this.context = context;
        this.imageUrl = imageUrl;
    }

    @Override
    public int getCount() {
        return imageUrl.size() > 0 ? imageUrl.size() : 0;
    }

    @NonNull
    @Override
    public View instantiateItem(@NonNull final ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(container.getContext());

        RequestOptions options = new RequestOptions().priority(Priority.HIGH)
                                                     .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
             .load(imageUrl.get(position).getImageUrl())
             .apply(options)
             .into(photoView);

        // Now just add PhotoView to ViewPager and return it
        container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT,
                          ViewGroup.LayoutParams.MATCH_PARENT);

        return photoView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ImageView imageView = (PhotoView) object;
        container.removeView(imageView);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
