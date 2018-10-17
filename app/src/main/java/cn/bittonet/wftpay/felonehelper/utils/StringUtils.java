package cn.bittonet.wftpay.felonehelper.utils;

import android.support.annotation.NonNull;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils {

    private StringUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 是否不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    /**
     * 是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(CharSequence str) {
        if (str == null || "".contentEquals(str) || str.length() == 0) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * Judge whether a list is null.
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> boolean isNotEmpty(List<T> list) {
        return !isEmpty(list);
    }

    /**
     * Judge whether a list is not null.
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }

    /**
     * Judge whether a array is null.
     *
     * @param array
     * @return
     */
    public static <T> boolean isEmpty(T[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * Judge whether a array is not null.
     *
     * @param array
     * @return
     */
    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }

    /**
     * 验证两个值是否相同
     *
     * @param value1
     * @param value2
     * @return
     */
    public static boolean checkValSame(String value1, String value2) {
        return value1.equals(value2);
    }

    /**
     * 精确的小数位四舍五入处理
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static String round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }

        BigDecimal b   = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 精确的小数位四舍五入处理
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static String round(String v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 将double型转换成小数的String型
     *
     * @param value
     * @return
     */
    public static String doubleToString(double value) {
        return round(value, 2);
    }

    /**
     * 将小数转换成百分数
     *
     * @param decimal
     * @return
     */
    public static String doubleToPercentage(double decimal) {
        DecimalFormat df = new DecimalFormat("#0.0%");
        return df.format(decimal);
    }

    /**
     * 分钟数转小时：分钟
     *
     * @param mins 总分钟
     * @return
     */
    public static String formatTime(int mins) {

        int hour = mins / 60;
        int min  = mins % 60;

        return hour + "小时" + min + "分钟";
    }

    /**
     * 分钟数转小时：分钟
     *
     * @param mins 总分钟
     * @return -h-min
     */
    public static String formatTimeEN(int mins) {

        int hour = mins / 60;
        int min  = mins % 60;

        return hour == 0 ? min + "min" : hour + "h" + min + "min";
    }

    /**
     * 判断一个数字是奇数还是偶数
     *
     * @param number
     * @return
     */
    public static boolean isOdd(int number) {
        return number % 2 == 1;
    }

    /**
     * 检测一个String类型的数字是否为整数
     *
     * @param number
     * @return
     */
    public static boolean checkNumInt(String number) {
        String regexInteger = "-?\\d*";
        return number.matches(regexInteger);
    }

    /**
     * 月日时分秒，0-9前补0
     */
    @NonNull
    public static String fillZero(int number) {
        return number < 10 ? "0" + number : "" + number;
    }

    /**
     * 截取掉前缀0以便转换为整数
     *
     * @see #fillZero(int)
     */
    public static int trimZero(@NonNull String text) {
        try {
            if (text.startsWith("0")) {
                text = text.substring(1);
            }
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * 比较两个MAC地址
     *
     * @param originalMac
     * @param compareMac
     * @return
     */
    public static Boolean compareMac(String originalMac, String compareMac) {
        if (isNotEmpty(originalMac) && isNotEmpty(compareMac)) {
            // 将“:”去掉
            String oMac = originalMac.replace(":", "");
            String cMac = compareMac.replace(":", "");

            return oMac.toLowerCase().equals(cMac.toLowerCase());
        } else {
            return false;
        }
    }

    /**
     * 校验一个字符串是否为mac地址
     *
     * @param macAddr
     * @return
     */
    public static Boolean stringIsMac(String macAddr) {
        return !isEmpty(macAddr) && (macAddr.matches("^[0-9a-fA-F]{2}([0-9a-fA-F]{2}){5}$")
                || macAddr.matches("^[0-9a-fA-F]{2}([~_;:.,|-][0-9a-fA-F]{2}){5}$"));
    }

    /**
     * 格式化MAC地址
     *
     * @param macAddr
     * @return
     */
    public static String formatMac(String macAddr) {
        String macStr;
        if (macAddr.matches("^[0-9a-fA-F]{2}([0-9a-fA-F]{2}){5}$")) {
            StringBuilder sb = new StringBuilder(macAddr.toUpperCase());
            sb.insert(10, ":");
            sb.insert(8, ":");
            sb.insert(6, ":");
            sb.insert(4, ":");
            sb.insert(2, ":");
            macStr = sb.toString();
        } else if (macAddr.matches("^[0-9a-fA-F]{2}([^a-zA-F0-9][0-9a-fA-F]{2}){5}$")) {
            macStr = macAddr.toUpperCase().replaceAll("[^a-fA-F0-9]", ":");
        } else {
            macStr = "";
        }
        return macStr;
    }

    private static final String DELIMITER = ",";

    /**
     * 遍历数组
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> String traverseArray(T[] array) {
        return traverseArray(array, DELIMITER);
    }

    /**
     * 遍历数组
     *
     * @param array
     * @param delimiter
     * @param <T>
     * @return
     */
    public static <T> String traverseArray(T[] array, String delimiter) {
        if (isNotEmpty(array)) {
            int           len     = array.length;
            StringBuilder builder = new StringBuilder(len);
            int           i       = 0;
            for (T t : array) {
                if (t == null) {
                    continue;
                }
                builder.append(t.toString().trim());
                i++;
                if (i < len) {
                    builder.append(delimiter);
                }
            }
            return builder.toString();
        }
        return null;
    }

    /**
     * 遍历List
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> String traverseList(List<T> list) {
        return traverseList(list, DELIMITER);
    }

    /**
     * 遍历List
     *
     * @param list
     * @param delimiter
     * @param <T>
     * @return
     */
    public static <T> String traverseList(List<T> list, String delimiter) {
        if (isNotEmpty(list)) {
            int           len     = list.size();
            StringBuilder builder = new StringBuilder(len);
            int           i       = 0;
            for (T t : list) {
                if (t == null) {
                    continue;
                }
                builder.append(t.toString().trim());
                i++;
                if (i < len) {
                    builder.append(delimiter);
                }
            }
            return builder.toString();
        }
        return null;
    }

    /**
     * 数组转List
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> List<T> arrayToList(T[] array) {
        if (isNotEmpty(array)) {
            return new ArrayList<>(Arrays.asList(array));
        } else {
            return null;
        }
    }

    /**
     * 获取数值精度格式化字符串
     *
     * @param precision
     * @return
     */
    public static String getPrecisionFormat(int precision) {
        return "%." + precision + "f";
    }

    /**
     * 格式化姓名（姓名首个字显示，其他用*代替）
     *
     * @param value
     * @return
     */
    public static String formString(String value) {
        if (isNotEmpty(value)) {
            return value.replaceAll("([\\u4e00-\\u9fa5])(.*)",
                                    "$1" + createAsterisk(value.length() - 1));
        } else {
            return "";
        }
    }

    /**
     * 格式化身份证号码（中间10位用*代替）
     *
     * @param id_number 身份证号码
     * @return
     */
    public static String formCardNumber(String id_number) {
        if (id_number.length() == 18) {
            return id_number.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1**********$2");
        } else {
            return createAsterisk(id_number.length());
        }
    }

    /**
     * 格式化手机号码（中间四位用*代替）
     *
     * @param mobile 手机号码
     * @return
     */
    public static String formMobile(String mobile) {
        if (mobile.length() == 11) {
            return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        } else {
            return createAsterisk(mobile.length());
        }
    }

    /**
     * 生成N个*
     *
     * @param length
     * @return
     */
    public static String createAsterisk(int length) {
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuffer.append("*");
        }
        return stringBuffer.toString();
    }

    /**
     * 把字符串按照某分隔符分隔开，保存到一个数组里
     *
     * @param string
     * @return
     */
    public static String[] split(String string) {
        return split(string, DELIMITER);
    }

    /**
     * 把字符串按照某分隔符分隔开，保存到一个数组里
     *
     * @param string
     * @param separator
     * @return
     */
    public static String[] split(String string, String separator) {
        return string.split(separator);
    }
}
