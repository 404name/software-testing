package com.juelunn.class04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CostUtil {
	public static  double  getTelCost(long time) {
		double telCost;
		//计算话费
		if(time <= 20){
			telCost = 0.05*time;
		}else{
			telCost = 1.00 + (time - 20)*0.1;
		}
		BigDecimal b = new BigDecimal(telCost);
		telCost = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return telCost;
	}



	public static Date StrToDate(String str) {
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   Date date = null;
		   try {
		    date = format.parse(str);
		   } catch (ParseException e) {
		    e.printStackTrace();
		   }
		   return date;
		}
	public static String DateFormate(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		String sdate = format.format(date);
		return sdate;
	}
	public static long calculateTimeSpan(Date startTime, Boolean inDayLightSavingOfStartTime, Date endTime, boolean inDayLightSavingOfEndTime){
		long between,time;

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(startTime);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(endTime);

		between = cal2.getTime().getTime()-cal1.getTime().getTime();
		if(between<0) {
			time =( (cal2.getTime().getTime()-cal1.getTime().getTime())/1000)/60;//得到通话时长
		} else {
			time = (between/1000+59)/60;
		}
		//判断接通时间是否在发生转换的日期
		//判断结束时间是否在转换的日期
		if(inDayLightSavingOfStartTime){
			time -=60;//时间从2点转换到3点减去1h
		}

		//将位于转换的时间段内的时间都看作已经转换之后的
		if(inDayLightSavingOfEndTime){
			time += 60;
		}
		if(time>1800 || time <0){
			System.out.println("时间错误，时限范围不对！");
			return 0;
		}else{
			//System.out.println("间隔正确");
			return time;
		}
	}

}
