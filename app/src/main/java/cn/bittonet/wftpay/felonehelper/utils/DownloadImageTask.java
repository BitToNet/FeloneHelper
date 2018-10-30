package cn.bittonet.wftpay.felonehelper.utils;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.ViewGroup;
import java.io.IOException;
import java.net.URL;


public class DownloadImageTask extends AsyncTask<String, Void, Drawable> {

    @SuppressLint("StaticFieldLeak")
    private final ViewGroup viewGroup;

    public DownloadImageTask(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }

    protected Drawable doInBackground(String... urls) {
        return loadImageFromNetwork(urls[0]);
    }

    protected void onPostExecute(Drawable result) {
        if (null != result) {
            viewGroup.setBackground(result);
        }
    }

    private Drawable loadImageFromNetwork(String imageUrl) {
        Drawable drawable = null;
        try {
            // 可以在这里通过第二个参数(文件名)来判断，是否本地有此图片
            drawable = Drawable.createFromStream(new URL(imageUrl).openStream(), null);
        } catch (IOException e) {
            e.getMessage();
        }
        return drawable;
    }
}
