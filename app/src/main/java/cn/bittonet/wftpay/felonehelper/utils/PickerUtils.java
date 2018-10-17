package cn.bittonet.wftpay.felonehelper.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;
import cn.bittonet.wftpay.felonehelper.R;
import cn.bittonet.wftpay.felonehelper.widget.FloatPicker;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.DoublePicker;
import cn.qqtheme.framework.picker.LinkagePicker;
import cn.qqtheme.framework.picker.NumberPicker;
import cn.qqtheme.framework.picker.OptionPicker;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PickerUtils {

    private static final int TEXT_SIZE          = 18;
    private static final int TITLE_TEXT_COLOR   = R.color.text_color_deep;
    private static final int TEXT_COLOR         = R.color.text_color_deep;
    private static final int SUBMIT_TEXT_COLOR  = R.color.colorPrimary;
    private static final int PRESSED_TEXT_COLOR = R.color.colorPrimaryDark;

    public static void showPicker(Activity activity, String title, List<String> list,
            final OptionPicker.OnOptionPickListener listener) {
        showPicker(activity, title, null, list, 0, "", listener);
    }

    public static void showPicker(Activity activity, String title, String[] array,
            final OptionPicker.OnOptionPickListener listener) {
        showPicker(activity, title, array, null, 0, "", listener);
    }

    public static void showPicker(Activity activity, String title, String[] array, int index,
            final OptionPicker.OnOptionPickListener listener) {
        showPicker(activity, title, array, null, index, "", listener);
    }

    public static void showPicker(Activity activity, String title, List<String> list, int index,
            final OptionPicker.OnOptionPickListener listener) {
        showPicker(activity, title, null, list, index, "", listener);
    }

    public static void showPicker(Activity activity, String title, String[] array, String item,
            final OptionPicker.OnOptionPickListener listener) {
        showPicker(activity, title, array, null, 0, item, listener);
    }

    public static void showPicker(Activity activity, String title, List<String> list, String item,
            final OptionPicker.OnOptionPickListener listener) {
        showPicker(activity, title, null, list, 0, item, listener);
    }

    private static void showPicker(Activity activity, String title, String[] array,
            List<String> list, int index, String item,
            final OptionPicker.OnOptionPickListener listener) {
        OptionPicker picker = null;
        if (null != list && list.size() > 0) {
            picker = new OptionPicker(activity, list);
        } else if (null != array && array.length > 0) {
            picker = new OptionPicker(activity, array);
        }
        assert picker != null;
        picker.setDividerVisible(false);
        if (StringUtils.isEmpty(item)) {
            picker.setSelectedIndex(index);
        } else {
            picker.setSelectedItem(item);
        }
        picker.setTitleText(title);
        picker.setCancelTextSize(TEXT_SIZE);
        picker.setSubmitTextSize(TEXT_SIZE);
        picker.setTitleTextSize(TEXT_SIZE);
        picker.setTextSize(TEXT_SIZE);
        picker.setAnimationStyle(R.style.BottomDialogAnimation);
        picker.setTitleTextColor(ContextCompat.getColor(activity, TITLE_TEXT_COLOR));
        picker.setTextColor(ContextCompat.getColor(activity, TEXT_COLOR));
        picker.setTopLineColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));
        picker.setCancelTextColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));
        picker.setPressedTextColor(ContextCompat.getColor(activity, PRESSED_TEXT_COLOR));
        picker.setSubmitTextColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                if (listener != null) {
                    listener.onOptionPicked(index, item);
                }
            }
        });
        picker.show();
    }

    public static void showDatePicker(Activity activity, String title,
            final DatePicker.OnYearMonthDayPickListener listener) {
        showDatePicker(activity, title, null, null, null, listener);
    }

    /**
     * 日期选择器
     *
     * @param activity    Activity
     * @param title       标题
     * @param startDate   起始日期
     * @param endDate     结束日期
     * @param currentDate 当前日期
     * @param listener    事件监听
     */
    public static void showDatePicker(Activity activity, String title, String startDate,
            String endDate, String currentDate,
            final DatePicker.OnYearMonthDayPickListener listener) {
        Calendar   now        = Calendar.getInstance();
        DatePicker datePicker = new DatePicker(activity);
        datePicker.setDividerVisible(false);
        datePicker.setTitleText(title);
        datePicker.setCancelTextSize(TEXT_SIZE);
        datePicker.setSubmitTextSize(TEXT_SIZE);
        datePicker.setTitleTextSize(TEXT_SIZE);
        datePicker.setTextSize(TEXT_SIZE);
        datePicker.setTitleText(title);
        datePicker.setAnimationStyle(R.style.BottomDialogAnimation);
        datePicker.setTitleTextColor(ContextCompat.getColor(activity, TITLE_TEXT_COLOR));
        datePicker.setTextColor(ContextCompat.getColor(activity, TEXT_COLOR));
        datePicker.setTopLineColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));
        datePicker.setCancelTextColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));
        datePicker.setPressedTextColor(ContextCompat.getColor(activity, PRESSED_TEXT_COLOR));
        datePicker.setSubmitTextColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));
        datePicker.setUseWeight(true);
        int start_y, start_m, start_d;
        if (StringUtils.isEmpty(startDate)) {
            start_y = 1900;
            start_m = 1;
            start_d = 1;
        } else {
            start_y = Integer.valueOf(TimeUtils.formatDate(startDate, TimeUtils.Y_FORMAT));
            start_m = Integer.valueOf(TimeUtils.formatDate(startDate, TimeUtils.M_FORMAT));
            start_d = Integer.valueOf(TimeUtils.formatDate(startDate, TimeUtils.D_FORMAT));
        }
        datePicker.setRangeStart(start_y, start_m, start_d);
        if (StringUtils.isNotEmpty(endDate)) {
            int end_y, end_m, end_d;
            end_y = Integer.valueOf(TimeUtils.formatDate(endDate, TimeUtils.Y_FORMAT));
            end_m = Integer.valueOf(TimeUtils.formatDate(endDate, TimeUtils.M_FORMAT));
            end_d = Integer.valueOf(TimeUtils.formatDate(endDate, TimeUtils.D_FORMAT));
            datePicker.setRangeEnd(end_y, end_m, end_d);
        }
        int y, m, d;
        if (StringUtils.isEmpty(currentDate)) {
            y = now.get(Calendar.YEAR);
            m = now.get(Calendar.MONTH) + 1;
            d = now.get(Calendar.DAY_OF_MONTH);
        } else {
            y = Integer.valueOf(TimeUtils.formatDate(currentDate, TimeUtils.Y_FORMAT));
            m = Integer.valueOf(TimeUtils.formatDate(currentDate, TimeUtils.M_FORMAT));
            d = Integer.valueOf(TimeUtils.formatDate(currentDate, TimeUtils.D_FORMAT));
        }
        datePicker.setSelectedItem(y, m, d);
        datePicker.setResetWhileWheel(false);
        datePicker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                if (listener != null) {
                    listener.onDatePicked(year, month, day);
                }
            }
        });
        datePicker.show();
    }

    public static void showNumberPicker(Activity activity, String title, String label,
            int startNumber, int endNumber, double step, double selectdItem,
            final NumberPicker.OnNumberPickListener listener) {

        NumberPicker numberPicker = new NumberPicker(activity);
        numberPicker.setCancelTextSize(TEXT_SIZE);
        numberPicker.setSubmitTextSize(TEXT_SIZE);
        numberPicker.setTitleTextSize(TEXT_SIZE);
        numberPicker.setTextSize(TEXT_SIZE);
        numberPicker.setTitleText(title);
        numberPicker.setLabel(label);
        numberPicker.setRange(startNumber, endNumber, step);
        numberPicker.setSelectedItem(selectdItem);
        numberPicker.setCycleDisable(true);
        numberPicker.setAnimationStyle(R.style.BottomDialogAnimation);
        numberPicker.setTitleTextColor(ContextCompat.getColor(activity, TITLE_TEXT_COLOR));
        numberPicker.setTextColor(ContextCompat.getColor(activity, TEXT_COLOR));
        numberPicker.setTopLineColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));
        numberPicker.setCancelTextColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));
        numberPicker.setPressedTextColor(ContextCompat.getColor(activity, PRESSED_TEXT_COLOR));
        numberPicker.setSubmitTextColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));

        numberPicker.setOnNumberPickListener(new NumberPicker.OnNumberPickListener() {
            @Override
            public void onNumberPicked(int index, Number item) {
                if (listener != null) {
                    listener.onNumberPicked(index, item);
                }
            }
        });
        numberPicker.show();
    }

    public static void showNumberPicker(Activity activity, String title, String label,
            int startNumber, int endNumber, int step, int selectdItem,
            final NumberPicker.OnNumberPickListener listener) {

        NumberPicker numberPicker = new NumberPicker(activity);
        numberPicker.setCancelTextSize(TEXT_SIZE);
        numberPicker.setSubmitTextSize(TEXT_SIZE);
        numberPicker.setTitleTextSize(TEXT_SIZE);
        numberPicker.setTextSize(TEXT_SIZE);
        numberPicker.setTitleText(title);
        numberPicker.setLabel(label);
        numberPicker.setRange(startNumber, endNumber, step);
        numberPicker.setSelectedItem(selectdItem);
        numberPicker.setCycleDisable(true);
        numberPicker.setAnimationStyle(R.style.BottomDialogAnimation);
        numberPicker.setTitleTextColor(ContextCompat.getColor(activity, TITLE_TEXT_COLOR));
        numberPicker.setTextColor(ContextCompat.getColor(activity, TEXT_COLOR));
        numberPicker.setTopLineColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));
        numberPicker.setCancelTextColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));
        numberPicker.setPressedTextColor(ContextCompat.getColor(activity, PRESSED_TEXT_COLOR));
        numberPicker.setSubmitTextColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));

        numberPicker.setOnNumberPickListener(new NumberPicker.OnNumberPickListener() {
            @Override
            public void onNumberPicked(int index, Number item) {
                if (listener != null) {
                    listener.onNumberPicked(index, item);
                }
            }
        });
        numberPicker.show();
    }

    /**
     * 双滚轮选择器
     *
     * @param activity     上下文
     * @param title        标题
     * @param firstData    第一个列表数据
     * @param secondData   第二个列表数据
     * @param firstLable1
     * @param firstLable2
     * @param secondLable1
     * @param secondLable2
     * @param listener     回调
     */
    public static void showDoublePicker(Activity activity, String title,
            ArrayList<String> firstData, ArrayList<String> secondData, String firstLable1,
            String firstLable2, String secondLable1, String secondLable2,
            final DoublePicker.OnPickListener listener) {
        final DoublePicker picker = new DoublePicker(activity, firstData, secondData);
        picker.setCancelTextSize(TEXT_SIZE);
        picker.setSubmitTextSize(TEXT_SIZE);
        picker.setTitleTextSize(TEXT_SIZE);
        picker.setTextSize(TEXT_SIZE);
        picker.setTitleText(title);
        picker.setAnimationStyle(R.style.BottomDialogAnimation);
        picker.setDividerVisible(true);
        picker.setCycleDisable(true);
        picker.setSelectedIndex(0, 0);
        picker.setFirstLabel(firstLable1, firstLable2);
        picker.setSecondLabel(secondLable1, secondLable2);
        picker.setTitleTextColor(ContextCompat.getColor(activity, TITLE_TEXT_COLOR));
        picker.setTextColor(ContextCompat.getColor(activity, TEXT_COLOR));
        picker.setTopLineColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));
        picker.setCancelTextColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));
        picker.setPressedTextColor(ContextCompat.getColor(activity, PRESSED_TEXT_COLOR));
        picker.setSubmitTextColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));
        picker.setOnPickListener(new DoublePicker.OnPickListener() {
            @Override
            public void onPicked(int selectedFirstIndex, int selectedSecondIndex) {
                if (null != listener) {
                    listener.onPicked(selectedFirstIndex, selectedSecondIndex);
                }
            }
        });
        picker.show();
    }

    /**
     * 双滚轮选择器
     *
     * @param activity     上下文
     * @param title        标题
     * @param unit         单位
     * @param startNumber  起始值
     * @param endNumber    结束值
     * @param defaultValue 默认值
     * @param listener     回调
     */
    public static void showFloatPicker(Activity activity, String title, String unit,
            int startNumber, int endNumber, float defaultValue,
            final FloatPicker.OnPickListener listener) {
        final FloatPicker picker = new FloatPicker(activity, startNumber > endNumber ? endNumber :
                startNumber, startNumber > endNumber ? startNumber : endNumber);
        picker.setCancelTextSize(TEXT_SIZE);
        picker.setSubmitTextSize(TEXT_SIZE);
        picker.setTitleTextSize(TEXT_SIZE);
        picker.setTextSize(TEXT_SIZE);
        picker.setTitleText(title);
        picker.setAnimationStyle(R.style.BottomDialogAnimation);
        picker.setDividerVisible(true);
        picker.setCycleDisable(true);
        picker.setSelected(defaultValue);
        picker.setUnit(unit);
        picker.setTitleTextColor(ContextCompat.getColor(activity, TITLE_TEXT_COLOR));
        picker.setTextColor(ContextCompat.getColor(activity, TEXT_COLOR));
        picker.setTopLineColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));
        picker.setCancelTextColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));
        picker.setPressedTextColor(ContextCompat.getColor(activity, PRESSED_TEXT_COLOR));
        picker.setSubmitTextColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));
        picker.setOnPickListener(new FloatPicker.OnPickListener() {
            @Override
            public void onPicked(String item) {
                if (null != listener) {
                    listener.onPicked(item);
                }
            }
        });
        picker.show();
    }

    /**
     * 二级级联联动
     *
     * @param activity   上下文
     * @param title      标题
     * @param firstList  第一级列表
     * @param secondList 第二级列表
     * @param listener   回调
     */
    public static void showLinkagePicker(Activity activity, String title,
            final ArrayList<String> firstList, final ArrayList<ArrayList<String>> secondList,
            final LinkagePicker.OnStringPickListener listener) {

        if (firstList.size() > 0) {
            LinkagePicker picker = new LinkagePicker(activity, new LinkagePicker.DataProvider() {

                @Override
                public boolean isOnlyTwo() {
                    return true;
                }

                @NonNull
                @Override
                public List<String> provideFirstData() {
                    return firstList;
                }

                @NonNull
                @Override
                public List<String> provideSecondData(int firstIndex) {
                    return secondList.get(firstIndex);
                }

                @Nullable
                @Override
                public List<String> provideThirdData(int firstIndex, int secondIndex) {
                    return null;
                }
            });
            picker.setCancelTextSize(TEXT_SIZE);
            picker.setSubmitTextSize(TEXT_SIZE);
            picker.setTitleTextSize(TEXT_SIZE);
            picker.setTextSize(TEXT_SIZE);
            picker.setTitleText(title);
            picker.setAnimationStyle(R.style.BottomDialogAnimation);
            picker.setDividerVisible(true);
            picker.setCycleDisable(true);
            picker.setUseWeight(true);
            picker.setSelectedIndex(0, 0);
            picker.setContentPadding(10, 0);
            picker.setTitleTextColor(ContextCompat.getColor(activity, TITLE_TEXT_COLOR));
            picker.setTextColor(ContextCompat.getColor(activity, TEXT_COLOR));
            picker.setTopLineColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));
            picker.setCancelTextColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));
            picker.setPressedTextColor(ContextCompat.getColor(activity, PRESSED_TEXT_COLOR));
            picker.setSubmitTextColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));
            picker.setOnStringPickListener(new LinkagePicker.OnStringPickListener() {
                @Override
                public void onPicked(String first, String second, String third) {
                    if (null != listener) {
                        listener.onPicked(first, second, third);
                    }
                }
            });
            picker.show();
        } else {
            Toast.makeText(activity, "没有数据", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 三级级联联动
     *
     * @param activity   上下文
     * @param title      标题
     * @param firstList  第一级列表
     * @param secondList 第二级列表
     * @param thirdList  第三级列表
     * @param listener   回调
     */
    public static void showLinkagePicker(Activity activity, String title,
            final ArrayList<String> firstList, final ArrayList<ArrayList<String>> secondList,
            final ArrayList<ArrayList<ArrayList<String>>> thirdList,
            final LinkagePicker.OnStringPickListener listener) {

        LinkagePicker picker = new LinkagePicker(activity, new LinkagePicker.DataProvider() {

            @Override
            public boolean isOnlyTwo() {
                return false;
            }

            @NonNull
            @Override
            public List<String> provideFirstData() {
                return firstList;
            }

            @NonNull
            @Override
            public List<String> provideSecondData(int firstIndex) {
                return secondList.get(firstIndex);
            }

            @Nullable
            @Override
            public List<String> provideThirdData(int firstIndex, int secondIndex) {
                return thirdList.get(firstIndex).get(secondIndex);
            }
        });
        picker.setCancelTextSize(TEXT_SIZE);
        picker.setSubmitTextSize(TEXT_SIZE);
        picker.setTitleTextSize(TEXT_SIZE);
        picker.setTextSize(TEXT_SIZE);
        picker.setTitleText(title);
        picker.setAnimationStyle(R.style.BottomDialogAnimation);
        picker.setDividerVisible(true);
        picker.setCycleDisable(true);
        picker.setUseWeight(true);
        picker.setSelectedIndex(0, 0, 0);
        picker.setContentPadding(10, 0);
        picker.setTitleTextColor(ContextCompat.getColor(activity, TITLE_TEXT_COLOR));
        picker.setTextColor(ContextCompat.getColor(activity, TEXT_COLOR));
        picker.setTopLineColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));
        picker.setCancelTextColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));
        picker.setPressedTextColor(ContextCompat.getColor(activity, PRESSED_TEXT_COLOR));
        picker.setSubmitTextColor(ContextCompat.getColor(activity, SUBMIT_TEXT_COLOR));
        picker.setOnStringPickListener(new LinkagePicker.OnStringPickListener() {
            @Override
            public void onPicked(String first, String second, String third) {
                if (null != listener) {
                    listener.onPicked(first, second, third);
                }
            }
        });
        picker.show();
    }
}
