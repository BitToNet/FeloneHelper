package cn.bittonet.wftpay.felonehelper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.bittonet.wftpay.felonehelper.R;
import cn.bittonet.wftpay.felonehelper.utils.CustomDialog;
import cn.bittonet.wftpay.felonehelper.utils.PickerUtils;
import cn.qqtheme.framework.picker.OptionPicker;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    CustomDialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //选择器演示
        findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> kindList = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    kindList.add(i+"");
                }
                PickerUtils.showPicker(MainActivity.this, "请选择", kindList,
                                       new OptionPicker.OnOptionPickListener() {
                                           @Override
                                           public void onOptionPicked(int index, String item) {

                                           }
                                       });
            }
        });
        //照片选择器
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AActivity.class);
                startActivity(intent);
            }
        });
        //通讯录
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
