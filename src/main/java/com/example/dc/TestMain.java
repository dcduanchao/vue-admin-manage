package com.example.dc;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 10:46 2020/7/1
 * @ Description：
 */
public class TestMain {

    public static void main(String[] args) {
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(now);
//        System.out.println(now.getYear());
//        System.out.println(now.getMonthValue());//直接获取月份的值
//        System.out.println(now.getDayOfMonth());
//        System.out.println(now.getHour());
//        System.out.println(now.getMinute());
//        System.out.println(now.getSecond());
//
//        DayOfWeek dayOfWeek = now.getDayOfWeek();
//        int value = dayOfWeek.getValue();
//        System.out.println(value);
//        DayOfWeek of = DayOfWeek.of(value);

//        int i = 1 / 0;

        TestVo testVo = new TestVo();
        testVo.setId("1");
        testVo.setRolesList("2");
        testVo.setShowRoles("3");

        System.out.println(JSON.toJSONString(testVo));





        List<String> betweenTime = getBetweenTime("2020-08-27", "2020-09-09");

        System.out.println(betweenTime);


    }


    public static List<String> getBetweenTime(String starttime, String endtime)
    {
        List<String> betweenTime = new ArrayList<>();
        try
        {
            Date sdate= new SimpleDateFormat("yyyy-MM-dd").parse(starttime);
            Date edate= new SimpleDateFormat("yyyy-MM-dd").parse(endtime);

            SimpleDateFormat outformat = new SimpleDateFormat("yyyy-MM-dd");

            Calendar sCalendar = Calendar.getInstance();
            sCalendar.setTime(sdate);
            int year = sCalendar.get(Calendar.YEAR);
            int month = sCalendar.get(Calendar.MONTH);
            int day = sCalendar.get(Calendar.DATE);
            sCalendar.set(year, month, day, 0, 0, 0);

            Calendar eCalendar = Calendar.getInstance();
            eCalendar.setTime(edate);
            year = eCalendar.get(Calendar.YEAR);
            month = eCalendar.get(Calendar.MONTH);
            day = eCalendar.get(Calendar.DATE);
            eCalendar.set(year, month, day, 0, 0, 0);

            while (sCalendar.before(eCalendar))
            {
                betweenTime.add(outformat.format(sCalendar.getTime()));
                sCalendar.add(Calendar.DAY_OF_YEAR, 1);
            }
            betweenTime.add(outformat.format(eCalendar.getTime()));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return betweenTime;
    }
}