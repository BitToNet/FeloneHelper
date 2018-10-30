package cn.bittonet.wftpay.felonehelper.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 自定义对话框
 */
public class CustomDialog extends Dialog {

    private int height, width;
    private boolean cancelTouchout;
    private boolean cancelable;
    private View    view;

    private CustomDialog(Builder builder) {
        super(builder.context);
        height = builder.height;
        width = builder.width;
        cancelTouchout = builder.cancelTouchout;
        cancelable = builder.cancelable;
        view = builder.view;
    }

    private CustomDialog(Builder builder, int resStyle) {
        super(builder.context, resStyle);
        height = builder.height;
        width = builder.width;
        cancelTouchout = builder.cancelTouchout;
        cancelable = builder.cancelable;
        view = builder.view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(view);

        setCanceledOnTouchOutside(cancelTouchout);

        Window                     win = getWindow();
        WindowManager.LayoutParams lp  = win != null ? win.getAttributes() : null;
        assert lp != null;
        lp.gravity = Gravity.CENTER;
        lp.height = height;
        lp.width = width;
        win.setAttributes(lp);
    }

    public static final class Builder {

        private Context context;
        private int     height, width;
        private boolean cancelTouchout;
        private boolean cancelable;
        private View    view;
        private int resStyle = -1;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder view(View resView) {
            view = resView;
            return this;
        }

        public Builder heightpx(int val) {
            height = val;
            return this;
        }

        public Builder widthpx(int val) {
            width = val;
            return this;
        }

        public Builder heightdp(int val) {
            height = (int) ScreenUtils.dp2px(val);
            return this;
        }

        public Builder widthdp(int val) {
            width = (int) ScreenUtils.dp2px(val);
            return this;
        }

        public Builder heightDimenRes(int dimenRes) {
            height = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public Builder widthDimenRes(int dimenRes) {
            width = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public Builder style(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        public Builder cancelTouchout(boolean val) {
            // 调用这个方法时，按对话框以外的地方不起作用。按返回键还起作用
            cancelTouchout = val;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            // 调用这个方法时，按对话框以外的地方不起作用。按返回键也不起作用
            this.cancelable = cancelable;
            return this;
        }

        public Builder addViewOnclick(@IdRes int viewRes, View.OnClickListener listener) {
            view.findViewById(viewRes).setOnClickListener(listener);
            return this;
        }

        public CustomDialog build() {
            if (resStyle != -1) {
                return new CustomDialog(this, resStyle);
            } else {
                return new CustomDialog(this);
            }
        }
    }
}
