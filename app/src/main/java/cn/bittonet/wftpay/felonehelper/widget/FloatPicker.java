package cn.bittonet.wftpay.felonehelper.widget;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.qqtheme.framework.picker.WheelPicker;
import cn.qqtheme.framework.widget.WheelView;
import java.util.ArrayList;
import java.util.List;

public class FloatPicker extends WheelPicker {

    private List<String> number  = new ArrayList<>();// 整数位
    private List<String> decimal = new ArrayList<>();// 小数位

    private int numberIndex  = 0;
    private int decimalIndex = 0;

    private int startNumber;
    private int endNumber;

    private String unit = "";

    private OnPickListener onPickListener;

    public FloatPicker(Activity activity, int startNumber, int endNumber) {
        super(activity);
        this.startNumber = startNumber;
        this.endNumber = endNumber;

        for (int i = startNumber; i <= endNumber; i++) {
            number.add(String.valueOf(i));
        }
        for (int j = 0; j < 10; j++) {
            decimal.add(String.valueOf(j));
        }
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setSelectedIndex(int numberIndex, int decimalIndex) {
        if (numberIndex >= 0 && numberIndex < number.size()) {
            this.numberIndex = numberIndex;
        }
        if (decimalIndex >= 0 && decimalIndex < decimal.size()) {
            this.decimalIndex = decimalIndex;
        }
    }

    public void setSelected(float defaultValue) {
        int num = (int) Math.floor(defaultValue);// 取整数位
        for (int i = 0; i <= endNumber - startNumber; i++) {
            if (num == Integer.valueOf(number.get(i))) {
                this.numberIndex = i;
            }
        }

        int dNumber = (int) (defaultValue * 10 - num * 10);// 取小数部分数字,因为只有一位小数故此处乘以10
        for (int j = 0; j < 10; j++) {
            if (dNumber == j) {
                this.decimalIndex = j;
            }
        }
    }

    public String getNumberItem() {
        if (number.size() > numberIndex) {
            return number.get(numberIndex);
        }
        return "0";
    }

    public String getDecimalItem() {
        if (decimal.size() > decimalIndex) {
            return decimal.get(decimalIndex);
        }
        return "0";
    }

    @NonNull
    @Override
    protected View makeCenterView() {
        LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER);

        final WheelView firstView = createWheelView();
        firstView.setLayoutParams(new LinearLayout.LayoutParams(0, WRAP_CONTENT, 1.0f));
        layout.addView(firstView);

        TextView pointView = createLabelView();
        pointView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        pointView.setText(".");
        layout.addView(pointView);

        final WheelView secondView = createWheelView();
        secondView.setLayoutParams(new LinearLayout.LayoutParams(0, WRAP_CONTENT, 1.0f));
        layout.addView(secondView);

        TextView unitView = createLabelView();
        unitView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        unitView.setText(unit);
        layout.addView(unitView);

        firstView.setItems(number, numberIndex);
        firstView.setOnItemSelectListener(new WheelView.OnItemSelectListener() {
            @Override
            public void onSelected(int index) {
                numberIndex = index;
            }
        });

        secondView.setItems(decimal, decimalIndex);
        secondView.setOnItemSelectListener(new WheelView.OnItemSelectListener() {
            @Override
            public void onSelected(int index) {
                decimalIndex = index;
            }
        });

        return layout;
    }

    @Override
    public void onSubmit() {
        if (onPickListener != null) {
            onPickListener.onPicked(getNumberItem() + "." + getDecimalItem());
        }
    }

    public void setOnPickListener(OnPickListener onPickListener) {
        this.onPickListener = onPickListener;
    }

    /**
     * 数据选择完成监听器
     */
    public interface OnPickListener {
        void onPicked(String item);
    }
}
