package com.sync005;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 */
public class DateUtils {
    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    /**
     * 统一返回日期格式
     *
     * @return 2017-09-23 00:00:00
     */
    @SuppressWarnings("finally")
    public static String formatDate(String dateStr) {

        try {
            if (new Integer(dateStr) < 20011010) {
                return "";
            }
        } catch (Exception e) {
        }

        HashMap<String, String> dateRegFormat = new HashMap<String, String>();
        dateRegFormat.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$", "yyyy-MM-dd-HH-mm-ss");//2014年3月12日 13时5分34秒，2014-03-12 12:05:34，2014/3/12 12:5:34
        dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd-HH-mm");//2014-03-12 12:05
        dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd-HH");//2014-03-12 12
        dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd");//2014-03-12
        dateRegFormat.put("^\\d{4}\\D+\\d{2}$", "yyyy-MM");//2014-03
        dateRegFormat.put("^\\d{4}$", "yyyy");//2014
        dateRegFormat.put("^\\d{14}$", "yyyyMMddHHmmss");//20140312120534
        dateRegFormat.put("^\\d{12}$", "yyyyMMddHHmm");//201403121205
        dateRegFormat.put("^\\d{10}$", "yyyyMMddHH");//2014031212
        dateRegFormat.put("^\\d{8}$", "yyyyMMdd");//20140312
        dateRegFormat.put("^\\d{6}$", "yyyyMM");//201403
        dateRegFormat.put("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$", "yyyy-MM-dd-HH-mm-ss");//13:05:34  拼接当前日期
        dateRegFormat.put("^\\d{2}\\s*:\\s*\\d{2}$", "yyyy-MM-dd-HH-mm");//13:05  拼接当前日期
        dateRegFormat.put("^\\d{2}\\D+\\d{1,2}\\D+\\d{1,2}$", "yy-MM-dd");//14.10.18(年.月.日)
        dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}$", "yyyy-dd-MM");//30.12(日.月) 拼接当前年份
        dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}\\D+\\d{4}$", "dd-MM-yyyy");//12.21.2013(日.月.年)

        String curDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat formatter2;
        String dateReplace;
        String strSuccess = "";
        try {
            for (String key : dateRegFormat.keySet()) {
                if (Pattern.compile(key).matcher(dateStr).matches()) {
                    formatter2 = new SimpleDateFormat(dateRegFormat.get(key));
                    if (key.equals("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$")
                            || key.equals("^\\d{2}\\s*:\\s*\\d{2}$")) {//13:05:34 或 13:05 拼接当前日期
                        dateStr = curDate + "-" + dateStr;
                    } else if (key.equals("^\\d{1,2}\\D+\\d{1,2}$")) {//21.1 (日.月) 拼接当前年份
                        dateStr = curDate.substring(0, 4) + "-" + dateStr;
                    }
                    dateReplace = dateStr.replaceAll("\\D+", "-");
                    // System.out.println(dateRegExpArr[i]);
                    strSuccess = formatter1.format(formatter2.parse(dateReplace));
                    break;
                }
            }
        } catch (Exception e) {
            return "";
        }
        return strSuccess;
    }

//    public static void main(String[] a) {
//
//        System.out.println(formatDate("1009"));
//
//    }


    //字符转日期 yyyy-MM-dd HH:mm:ss
    public static Date StrToDate(String str) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
    }

    public static Date StrToDate2(String str) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(str);
    }

    public static Date strToDate(String str)  {
        Date parse = null;
        try {
            parse = new SimpleDateFormat("yyyy-MM-dd").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    public static Date StrToDate3(String str) throws ParseException {
        return new SimpleDateFormat("yyyy/MM/dd").parse(str);
    }

    //日期转字符 yyyy-MM-dd HH:mm:ss
    public static String DateToStr(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    //日期转字符 yyyy-MM-dd HH:mm:ss
    public static String DateToStr2(Date date) throws ParseException {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
    }

    public static Integer DateToInt(Date date) throws ParseException {
        return Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(date));
    }

    /**
     * 计算两个日期之间相差的天数 负数转正数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        Integer i = Integer.parseInt(String.valueOf(between_days));
        return i < 0 ? i * -1 : i;
    }


    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween2(Date smdate, Date bdate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    private final static Integer dayMinute = 1440;



    /**
     * 将时间戳转换为时间
     */
    public static Date stampToDate(String s) {
        return new Date(new Long(s) * 1000);
    }


    /***
     * 日期加减
     *
     * @param date  时间
     * @param s     判断加减 天（Calendar.DAY_OF_MONTH）， 分钟（Calendar.MINUTE） 秒（Calendar.SECOND）
     * @param t     数量
     * @return
     */
    public static Date checkOption(Date date, Integer s, Integer t) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(s, t);
        date = cl.getTime();
        return date;
    }


    public static Date getday1() throws ParseException {
        Date date2 = new Date(new Date().getTime());
        SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
        String time = matter1.format(date2);
        Date date = matter1.parse(time);
        return date;
    }

    public static Date getday2() throws ParseException {
        Date date2 = new Date(new Date().getTime());
        SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
        String time = matter1.format(date2);
        Date date = matter1.parse(time);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        return date;
    }

    public static String Day(Date date) {
        SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String strDate = sdfDateFormat.format(date);
        return strDate;
    }

    public static String Day2(Date date) {
        SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyyMMdd");
        String strDate = sdfDateFormat.format(date);
        return strDate;
    }

    public static String Day3(Date date) {
        SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdfDateFormat.format(date);
        return strDate;
    }

    /**
     * 判读当天是不是本月第一天
     *
     * @return
     */
    public static Boolean monthDay() {
        Calendar c = Calendar.getInstance();
        int today = c.get(Calendar.DAY_OF_MONTH);
        return today == 1;
    }

    /**
     * 获取当前月
     *
     * @return
     */
    public static Integer month() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前月小时
     *
     * @return
     */
    public static int getHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }



    /**
     * 获取n天前0点时刻
     */
    public static Date getNDaysAgo0(Integer n) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date());
        cl.add(Calendar.DAY_OF_MONTH, n * (-1));
        cl.set(Calendar.HOUR_OF_DAY, 0);
        cl.set(Calendar.MINUTE, 0);
        cl.set(Calendar.SECOND, 0);
        return cl.getTime();
    }

    /**
     * 获取n天前最后时刻
     */
    public static Date getNDaysAgo24(Integer n) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date());
        cl.add(Calendar.DAY_OF_MONTH, n * (-1));
        cl.set(Calendar.HOUR_OF_DAY, 23);
        cl.set(Calendar.MINUTE, 59);
        cl.set(Calendar.SECOND, 59);
        return cl.getTime();
    }

    public static Integer hoursBetween(Date smdate, Date bdate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (60 * 60 * 1000);

            return Integer.parseInt(String.valueOf(between_days));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date strToDateTime2(String dateStr) {
        Date date = null;
        SimpleDateFormat strToDate = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            date = strToDate.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return date;
    }

    //日期转字符 yyyy-MM-dd HH:mm:ss
    public static String DateToStr4(Date date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static String DateToMonth(Date date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM").format(date);
    }

    public static String DateToStr5(Date date) throws Exception {
        return new SimpleDateFormat("yyyy/MM/dd").format(date);
    }
}
