package cn.bittonet.wftpay.felonehelper.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import cn.bittonet.wftpay.felonehelper.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtils {

    public static final String TZ_FORMAT              = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String T_HOUR_FORMAT          = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_TIME_FORMAT       = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_HM_FORMAT    = "yyyy-MM-dd HH:mm";
    public static final String DATE_TIME_MONTH_FORMAT = "MM-dd HH:mm:ss";
    public static final String DATETIME_FORMAT        = "MM-dd HH:mm";
    public static final String DATE_FORMAT            = "yyyy-MM-dd";
    public static final String DATE_SHAORT_FORMAT     = "MM-dd";
    public static final String TIME_FORMAT            = "HH:mm:ss";
    public static final String TIME_SHAORT_FORMAT     = "HH:mm";
    public static final String TIME_MS_FORMAT         = "mm:ss";
    public static final String Y_M_FORMAT             = "yyyy-MM";
    public static final String YY_FORMAT              = "yy";
    public static final String Y_FORMAT               = "yyyy";
    public static final String M_FORMAT               = "MM";
    public static final String D_FORMAT               = "dd";
    public static final String H_24_FORMAT            = "HH";
    public static final String H_12_FORMAT            = "hh";
    public static final String MIN_FORMAT             = "mm";
    public static final String S_FORMAT               = "ss";
    public static final String MS_FORMAT              = "S";
    public static final String DATE_CALENDAR          = "yyyyMMdd";
    public static final String DATE_E_FORMAT          = "yyyy-MM-dd E";
    public static final String DATE_FORMAT_CN         = "yyyy年MM月dd日";
    public static final String DATE_SHAORT_FORMAT_CN  = "MM月dd日";
    public static final String DAY_FORMAT             = "MM/dd";
    public static final String DAY_YMD_FORMAT         = "yyyy/MM/dd";
    public static final String E_FORMAT               = "E"; // 示例：周一
    public static final String WEEKDAY_FORMAT         = "EEEE"; // 示例：星期一
    public static final String EE_FORMAT              = "EE"; // 示例：Sat
    public static final String TIME_E_FORMAT          = "MM.dd(E) HH:mm"; // 示例：02.23(周一) 14:23
    public static final String DAY_TIME_SHOART_FORMAT = "MM/dd HH:mm";

    private static final int WEEKS        = 0;
    private static final int WEEKS_CN     = 1;
    private static final int WEEKS_EN     = 2;
    private static final int WEEKS_EN_ABB = 3;
    private static final int WEEKS_EN_LET = 4;

    public static final  long ONE_SECOND = 1000;
    public static final  long ONE_MINUTE = 60 * ONE_SECOND;
    public static final  long ONE_HOUR   = 60 * ONE_MINUTE;
    private static final long ONE_DAY    = 24 * ONE_HOUR;

    private static final String[] CHINESE_ZODIAC = new String[] {"猴", "鸡", "狗", "猪", "鼠", "牛", "虎",
            "兔", "龙", "蛇", "马", "羊"};
    private static final String[] ZODIAC         = new String[] {"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座",
            "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"};

    private static final int[] ZODIAC_FLAGS = new int[] {20, 19, 21, 21, 21, 22, 23, 23, 23, 24, 23,
            22};

    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat getFormat(String format) {
        return new SimpleDateFormat(format);
    }

    /**
     * 格式化时间
     *
     * @param strDateTime yyyy-MM-dd HH:mm:ss
     * @param format
     * @return
     */
    public static String formatDate(String strDateTime, String format) {
        Date date = strToDate(strDateTime);
        return getFormat(format).format(date);
    }

    /**
     * 格式化时间
     *
     * @param strDateTime yyyy-MM-dd HH:mm:ss
     * @param format
     * @return
     */
    public static String formatDateTime(String strDateTime, String format) {
        Date date = strToDateTime(strDateTime);
        return getFormat(format).format(date);
    }

    /**
     * 格式化时间
     *
     * @param dateTime
     * @param format
     * @return
     */
    public static String formatDateTime(Date dateTime, String format) {
        return getFormat(format).format(dateTime);
    }

    /**
     * 格式化时间
     *
     * @param dateTime
     * @param format
     * @return
     */
    public static String formatDateTime(long dateTime, String format) {
        Date date = new Date(dateTime);
        return getFormat(format).format(date);
    }

    /**
     * 将日期字符串转换为date日期
     *
     * @param strDate yyyy-MM-dd
     * @return date型
     */
    public static Date strToDate(String strDate) {
        Date date = null;
        try {
            date = getFormat(DATE_FORMAT).parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将日期时间字符串转换为DateTime类型
     *
     * @param strDateTime
     * @return date yyyy-MM-dd HH:mm:ss
     */
    public static Date strToDateTime(String strDateTime) {
        if (StringUtils.isEmpty(strDateTime)) {
            return null;
        }
        Date datetime = null;
        try {
            datetime = getFormat(DATE_TIME_FORMAT).parse(strDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datetime;
    }

    /**
     * 将Date类型转化为String类型
     *
     * @param date
     * @param formatType
     * @return
     */
    public static String dateToStr(Date date, String formatType) {
        if (date == null) {
            return "Unknown";
        }
        return getFormat(formatType).format(date);
    }

    /**
     * 将String类型转化为Date类型
     *
     * @param strDate    YYYY-MM-dd
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String strDate, String formatType) throws ParseException {
        if (StringUtils.isEmpty(strDate)) {
            return null;
        }
        return getFormat(formatType).parse(strDate);
    }

    /**
     * 将String类型的日期 时间转换成long型
     *
     * @param strDateTime YYYY-MM-dd HH:mm:ss
     * @return
     */
    public static long datetimeToLong(String strDateTime) {
        if (StringUtils.isEmpty(strDateTime)) {
            return 0L;
        }
        Date date = strToDateTime(strDateTime);
        assert date != null;
        return date.getTime();
    }

    /**
     * 将String类型的日期转换成long型
     *
     * @param strDate YYYY-MM-dd
     * @return long型日期
     */
    public static long dateToLong(String strDate) {
        if (StringUtils.isEmpty(strDate)) {
            return 0L;
        }
        Date date = strToDate(strDate);
        return date.getTime();
    }

    /**
     * 获取当前日期 时间
     *
     * @param formatType 格式
     * @return 当前日期 时间
     */
    public static String getNowDateTime(String formatType) {
        return dateToStr(new Date(), formatType);
    }

    /**
     * 获取当前日期 时间
     *
     * @return 当前日期 时间
     */
    public static long getNowDateTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前日期 YYYY-MM-dd
     *
     * @return YYYY-MM-dd
     */
    public static String getToday() {
        return dateToStr(new Date(), DATE_FORMAT);
    }

    /**
     * 获取昨天日期 YYYY-MM-DD
     *
     * @return YYYY-MM-dd
     */
    public static String getYesterday() {
        return afterNDay(-1);
    }

    /**
     * 获取明天日期 YYYY-MM-DD
     *
     * @return YYYY-MM-dd
     */
    public static String getTomorrow() {
        return afterNDay(1);
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public static String getCurrentYear() {
        Date date = new Date();
        return dateToStr(date, Y_FORMAT);
    }

    /**
     * 获取当前系统年份
     *
     * @return
     */
    public static int getSysYear() {
        Calendar date = Calendar.getInstance();
        return date.get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static String getCurrentMonth() {
        Date date = new Date();
        return dateToStr(date, M_FORMAT);
    }

    /**
     * 获取当前系统月份
     *
     * @return
     */
    public static int getSysMonth() {
        Calendar date = Calendar.getInstance();
        return date.get(Calendar.MONTH);
    }

    /**
     * 根据日期取得星期几
     *
     * @param context 上下文
     * @param strDate
     * @param type
     * @return
     */
    public static String getWeek(Context context, String strDate, int type) {
        if (StringUtils.isEmpty(strDate)) {
            return "Unknown";
        }

        String[] weeks;
        switch (type) {
            case WEEKS:
                weeks = context.getResources().getStringArray(R.array.weekdays);
                break;
            case WEEKS_CN:
                weeks = context.getResources().getStringArray(R.array.weekdays_cn);
                break;
            case WEEKS_EN_ABB:
                weeks = context.getResources().getStringArray(R.array.weekdays_en_abb);
                break;
            case WEEKS_EN:
                weeks = context.getResources().getStringArray(R.array.weekdays_en);
                break;
            case WEEKS_EN_LET:
                weeks = context.getResources().getStringArray(R.array.weekdays_en_let);
                break;
            default:
                weeks = context.getResources().getStringArray(R.array.weekdays);
                break;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(strToDate(strDate));
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }

    /**
     * 根据指定日期计算所在周的周一到周日的日期
     *
     * @param strDate 指定日期 yyyy-MM-dd
     * @param week    周几
     * @return 日期
     * @throws ParseException
     */
    public static String getWeekDay(String strDate, int week) {
        if (StringUtils.isEmpty(strDate)) {
            return "Unknown";
        }

        String weekday;

        if (week < 0 || week > 6) {
            weekday = "Unknown";
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(strToDate(strDate));

            //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
            int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
            if (1 == dayWeek) {
                cal.add(Calendar.DAY_OF_MONTH, -1);
            }

            cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一

            int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
            cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
            cal.add(Calendar.DATE, week);
            weekday = getFormat(DATE_FORMAT).format(cal.getTime());
        }
        return weekday;
    }

    /**
     * 由出生日期获得年龄
     *
     * @param birthDay 出生日期 yyyy-MM-dd
     * @return 年龄
     */
    public static int getAge(String birthDay) {
        if (StringUtils.isEmpty(birthDay)) {
            return -1;
        }

        Date birthday = strToDate(birthDay);

        Calendar cal = Calendar.getInstance();

        if (cal.before(birthday)) {
            throw new IllegalArgumentException("The birthDay is before Now. It's unbelievable!");
        }
        int yearNow       = cal.get(Calendar.YEAR);
        int monthNow      = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthday);

        int yearBirth       = cal.get(Calendar.YEAR);
        int monthBirth      = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                age--;
            }
        }
        return age;
    }

    /**
     * 以友好的方式显示日期
     *
     * @param sdate 原始日期字符串 yyyy-MM-dd
     * @return
     */
    public static String friendlyDate(String sdate) {
        if (StringUtils.isEmpty(sdate)) {
            return "Unknown";
        }

        Date date = strToDate(sdate);

        if (null == date) {
            return "Unknown";
        }

        int days = differentDays(date, new Date());

        // 判断是否是同一天
        switch (days) {
            case 0:
                return "今天";
            case 1:
                return "昨天";
            case 2:
                return "前天";
        }

        // 当前系统月份
        String curMonth = getCurrentMonth();
        // 服务器返回日期月份
        String paramMonth = formatDate(sdate, M_FORMAT);
        // 判断是否是同一月
        if (!curMonth.equals(paramMonth)) {
            return formatDate(sdate, DAY_FORMAT);// yyyy/MM
        }

        // 当前系统年份
        String curYear = getCurrentYear();
        // 服务器返回日期年份
        String paramYear = formatDate(sdate, Y_FORMAT);
        // 判断是否是同一年
        if (!curYear.equals(paramYear)) {
            return formatDate(sdate, DAY_YMD_FORMAT); // yyyy/MM/dd
        }

        return days + "天前";
    }

    /**
     * 以友好的方式显示时间
     *
     * @param sdatetime 原始时间字符串 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String friendlyTime2(String sdatetime) {
        if (StringUtils.isEmpty(sdatetime)) {
            return "Unknown";
        }

        Date time = strToDateTime(sdatetime);

        if (null == time) {
            return "Unknown";
        }

        int days = differentDays(time, new Date());

        // 判断是否是同一天
        switch (days) {
            case 0:
                return formatDateTime(sdatetime, TIME_SHAORT_FORMAT);// HH:mm

            case 1:
                return "昨天";// 昨天

            case 2:
                return "前天";// 今天
        }

        // 当前系统月份
        String curMonth = getCurrentMonth();
        // 服务器返回日期月份
        String paramMonth = formatDate(sdatetime, M_FORMAT);
        // 判断是否是同一月
        if (!curMonth.equals(paramMonth)) {
            return formatDate(sdatetime, DAY_FORMAT);// yyyy/MM
        }

        // 当前系统年份
        String curYear = getCurrentYear();
        // 服务器返回日期年份
        String paramYear = formatDate(sdatetime, Y_FORMAT);
        // 判断是否是同一年
        if (!curYear.equals(paramYear)) {
            return formatDate(sdatetime, DAY_YMD_FORMAT); // yyyy/MM/dd
        }

        return days + "天前";
    }

    /**
     * 以友好的方式显示时间
     *
     * @param sdatetime 原始时间字符串 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String friendlyTime(String sdatetime) {
        if (StringUtils.isEmpty(sdatetime)) {
            return "Unknown";
        }

        Date time = strToDateTime(sdatetime);

        if (time == null) {
            return "Unknown";
        }

        String   ftime;
        Calendar cal = Calendar.getInstance();

        // 今天日期
        String curDate = getFormat(DATE_FORMAT_CN).format(cal.getTime());
        // 服务器返回日期
        String paramDate = getFormat(DATE_FORMAT_CN).format(time);

        // 两个时间间隔（单位秒）
        long timeInterval = (cal.getTimeInMillis() - time.getTime()) / 1000;

        // 判断是否是同一天
        if (curDate.equals(paramDate)) {
            int hour = (int) (timeInterval / 3600);
            if (hour == 0) {
                ftime = Math.max(timeInterval / 60, 1) + "分钟前";
            } else {
                ftime = hour + "小时前";
            }
            return ftime;
        }

        int days = (int) (timeInterval / 86400);
        if (days == 0) {
            int hour = (int) (timeInterval / 3600);
            if (hour == 0) {
                ftime = Math.max(timeInterval / 60, 1) + "分钟前";
            } else {
                ftime = hour + "小时前";
            }
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天 ";
        } else if (days > 2 && days < 31) {
            ftime = days + "天前";
        } else if (days >= 31 && days <= 2 * 31) {
            ftime = "一个月前";
        } else if (days > 2 * 31 && days <= 3 * 31) {
            ftime = "两个月前";
        } else if (days > 3 * 31 && days <= 4 * 31) {
            ftime = "三个月前";
        } else {
            ftime = getFormat(DATE_FORMAT_CN).format(time);
        }
        return ftime;
    }

    /**
     * 比较两个日期相差天数
     *
     * @param fDate yyyy-MM-dd
     * @param oDate yyyy-MM-dd
     * @return
     */
    public static int differentDaysByMillisecond(String fDate, String oDate) {

        if (StringUtils.isEmpty(oDate) || StringUtils.isEmpty(fDate)) {
            return -1;
        }

        Date date1 = strToDate(fDate);
        Date date2 = strToDate(oDate);

        if (null == date1 || null == date2) {
            return -1;
        }

        long intervalMilli = Math.abs(date1.getTime() - date2.getTime());

        return (int) (intervalMilli / (24 * 60 * 60 * 1000));
    }

    /**
     * 比较两个日期相差天数。通过计算两个日期相差的毫秒数来计算两个日期的天数差的。但是存在一个小问题，就是当两个时间相差是23个小时的时候，它就不算一天了。
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        if (null == date1 || null == date2) {
            return -1;
        }

        long intervalMilli = Math.abs(date1.getTime() - date2.getTime());

        return (int) (intervalMilli / (24 * 60 * 60 * 1000));
    }

    /**
     * 判断两个日期之间相差的天数。只是通过日期来进行比较两个日期的相差天数的比较，没有精确到相差到一天的时间。
     *
     * @param date1
     * @param date2
     * @return 相差天数
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        // 获取日期在所在年的天数
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);

        if (year1 != year2) {// 不同年
            int timeDistance = 0;// 整数年相差的天数
            for (int i = year1; i < year2; i++) {
                if (isLeapYear(i)) {//闰年
                    timeDistance += 366;
                } else {//不是闰年
                    timeDistance += 365;
                }
            }

            return timeDistance + Math.abs(day2 - day1);
        } else {// 同一年
            return Math.abs(day2 - day1);
        }
    }

    /**
     * 比较两个日期大小
     *
     * @param strDate1 yyyy-MM-dd
     * @param strDate2 yyyy-MM-dd
     * @return
     */
    public static boolean dateCompare(String strDate1, String strDate2) {
        if (StringUtils.isEmpty(strDate1) || StringUtils.isEmpty(strDate2)) {
            return false;
        }
        Date d1 = strToDate(strDate1);
        Date d2 = strToDate(strDate2);

        //比较
        return d1.getTime() - d2.getTime() >= 0;
    }

    /**
     * 比较两个时间大小
     *
     * @param strDateTime1 yyyy-MM-dd HH:mm:ss
     * @param strDateTime2 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static boolean dateTimeCompare(String strDateTime1, String strDateTime2) {
        if (StringUtils.isEmpty(strDateTime1) || StringUtils.isEmpty(strDateTime2)) {
            return false;
        }
        Date d1 = strToDateTime(strDateTime1);
        Date d2 = strToDateTime(strDateTime2);

        //比较
        assert d1 != null;
        assert d2 != null;
        return d1.getTime() - d2.getTime() >= 0;
    }

    /**
     * 根据指定时间得到前后N毫秒的时间
     *
     * @param strDateTime
     * @param mSeconds
     * @param format
     * @return
     */
    public static String afterNMSecond(String strDateTime, long mSeconds, String format) {
        if (StringUtils.isEmpty(strDateTime)) {
            return "Unknown";
        }
        long l = datetimeToLong(strDateTime);
        return formatDateTime(l + mSeconds, format);
    }

    /**
     * 当前日期前几天或者后几天的日期
     *
     * @param days
     * @return
     */
    public static String afterNDay(int days) {
        return afterNDay("", days, "");
    }

    /**
     * 当前日期前几天或者后几天的日期
     *
     * @param days
     * @param format
     * @return
     */
    public static String afterNDay(int days, String format) {
        return afterNDay("", days, format);
    }

    /**
     * 某日期之后days天的日期
     *
     * @param days
     * @param strDate
     * @return
     */
    public static String afterNDay(String strDate, int days) {
        return afterNDay(strDate, days, "");
    }

    /**
     * 某日期之后days天的日期
     *
     * @param days
     * @param strDate
     * @param format
     * @return
     */
    public static String afterNDay(String strDate, int days, String format) {
        Date date;
        if (StringUtils.isEmpty(strDate)) {
            date = new Date();
        } else {
            date = strToDate(strDate);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        Date afterDate = calendar.getTime();
        if (StringUtils.isEmpty(format)) {
            return getFormat(DATE_FORMAT).format(afterDate);
        } else {
            return getFormat(format).format(afterDate);
        }
    }

    /**
     * 判断某日期所在年是否润年
     *
     * 1.被400整除是闰年
     *
     * 2.不能被4整除则不是闰年
     *
     * 3.能被4整除同时不能被100整除则是闰年
     *
     * 4.能被4整除同时能被100整除则不是闰年
     *
     * @param strDate 给定日期
     * @return
     */
    public static boolean isLeapYear(String strDate) {
        if (StringUtils.isEmpty(strDate)) {
            return false;
        }
        Date date = strToDate(strDate);

        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();

        gc.setTime(date);
        int year = gc.get(Calendar.YEAR);
        return (year % 400) == 0 || (year % 4) == 0 && (year % 100) != 0;
    }

    /**
     * 判断某年是否润年
     *
     * 1.被400整除是闰年
     *
     * 2.不能被4整除则不是闰年
     *
     * 3.能被4整除同时不能被100整除则是闰年
     *
     * 4.能被4整除同时能被100整除则不是闰年
     *
     * @param year 年份
     * @return
     */
    public static boolean isLeapYear(int year) {
        return (year % 400) == 0 || (year % 4) == 0 && (year % 100) != 0;
    }

    /**
     * 计算某月的天数
     *
     * @param strDate
     * @return
     */
    public static int getDaysOfMonth(String strDate) {
        if (StringUtils.isEmpty(strDate)) {
            return -1;
        }
        Date date = strToDate(strDate);

        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);

        int month = gc.get(Calendar.MONTH) + 1;
        if (month < 1 || month > 12) {
            return 0;
        }
        boolean isLeapYear  = isLeapYear(strDate);
        int     daysOfMonth = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                daysOfMonth = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                daysOfMonth = 30;
                break;
            case 2:
                if (isLeapYear) {
                    daysOfMonth = 29;
                } else {
                    daysOfMonth = 28;
                }
            default:
                break;
        }
        return daysOfMonth;
    }

    /**
     * 计算给定日期所在月的第一天的日期
     *
     * @param strDate
     * @return
     */
    public static String getFirstDayOfMonth(String strDate) {
        if (StringUtils.isEmpty(strDate)) {
            return "Unknown";
        }
        Date date = strToDate(strDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        return formatDateTime(calendar.getTime(), DATE_FORMAT);
    }

    /**
     * 计算给定日期所在月的最后一天的日期
     *
     * @param strDate
     * @return
     */
    public static String getLastDayOfMonth(String strDate) {
        if (StringUtils.isEmpty(strDate)) {
            return "Unknown";
        }
        Date date = strToDate(strDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        return formatDateTime(calendar.getTime(), DATE_FORMAT);
    }

    /**
     * 获取给定日期的前一个月的月份
     *
     * @param strDate
     * @return
     */
    public static String getBeforeMonth(String strDate) {
        if (StringUtils.isEmpty(strDate)) {
            return "Unknown";
        }
        Date date = strToDate(strDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.MONTH, -1);

        return formatDateTime(calendar.getTime(), Y_M_FORMAT);
    }

    /**
     * 获取给定日期的后一个月的月份
     *
     * @param strDate
     * @return
     */
    public static String getAfterMonth(String strDate) {
        if (StringUtils.isEmpty(strDate)) {
            return "Unknown";
        }
        Date date = strToDate(strDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.MONTH, 1);

        return formatDateTime(calendar.getTime(), Y_M_FORMAT);
    }

    /**
     * 比较两个日期相差天数
     *
     * @param fDate
     * @param oDate
     * @return
     */
    public static int intervalDays(String fDate, String oDate) {
        if (StringUtils.isEmpty(fDate) || StringUtils.isEmpty(oDate)) {
            return -1;
        }
        Date date1 = strToDate(fDate);
        Date date2 = strToDate(oDate);

        long intervalMilli = Math.abs(date1.getTime() - date2.getTime());

        return (int) (intervalMilli / ONE_DAY);
    }

    /**
     * 获取某一年的生肖
     *
     * @param year
     * @return
     */
    public static String getChineseZodiac(int year) {
        return CHINESE_ZODIAC[year % 12];
    }

    /**
     * 获取某天是什么星座
     *
     * @param strDate
     * @return
     */
    public static String getZodiac(String strDate) {
        if (StringUtils.isEmpty(strDate)) {
            return "Unknown";
        }
        Date     date     = strToDate(strDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day   = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);

        return ZODIAC[day >= ZODIAC_FLAGS[month - 1] ? month - 1 : (month + 10) % 12];
    }
}
