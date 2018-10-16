package cn.bittonet.wftpay.felonehelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.bittonet.wftpay.felonehelper.utils.CustomDialog;

public class MainActivity extends AppCompatActivity {
    CustomDialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        showBackDialog();
    }

    /**
     * 自定义对话框
     */
    private void showBackDialog() {
        View                 customView = LayoutInflater.from(this).inflate(R.layout.dialog_pay, null);
        CustomDialog.Builder builder    = new CustomDialog.Builder(this);
        customDialog = builder.cancelTouchout(true)
                              .cancelTouchout(true)
                              .view(customView)
                              .widthpx(ViewGroup.LayoutParams.MATCH_PARENT)
                              .heightpx(ViewGroup.LayoutParams.WRAP_CONTENT)
                              .style(R.style.AlertDialogStyle)
                              .addViewOnclick(R.id.tv_cancel,
                                              new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View v) {
                                                      customDialog.dismiss();
                                                  }
                                              })
                              .addViewOnclick(R.id.tv_ok, new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      finish();
                                      customDialog.dismiss();
                                  }
                              })
                              .build();
        customDialog.show();

    }
}
