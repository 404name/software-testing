package com.juelunn.class03;

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
	private long time;//通话时长
	public Date startT;
	public Date endT;
	private String startTime;
	private String endTime;

	public double getTelCost() {
		double telCost;
		long time = getHoldingTime();
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

	public static void main(String[] args) {

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));//字符输入流
		try {
			System.out.println("起始时间（如2022-04-01 03:04:05）:");
			String str1=bufferedReader.readLine();//按行读入
			System.out.println("截止时间（如2022-04-02 03:04:05）:");
			String str2=bufferedReader.readLine();
			CostUtil costUtil = new CostUtil(str1,str2);
			System.out.println("电话接通时间："+costUtil.DateFormate(costUtil.startT));
			System.out.println("电话结束时间："+costUtil.DateFormate(costUtil.endT));
			DecimalFormat df   = new DecimalFormat("######0.00");
			long time = costUtil.getHoldingTime();
			System.out.println("通话时间为："+time+"分钟");
			System.out.println("本次通话话费为："+df.format(costUtil.getTelCost())+"美元");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public CostUtil(String startTime, String endTime){
		this.startTime = startTime;
		this.endTime = endTime;
		this.startT = StrToDate(this.startTime);
		this.endT = StrToDate(this.endTime);
	}

	public Date StrToDate(String str) {
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   Date date = null;
		   try {
		    date = format.parse(str);
		   } catch (ParseException e) {
		    e.printStackTrace();
		   }
		   return date;
		}
	public String DateFormate(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		String sdate = format.format(date);
		return sdate;
	}
	public long getHoldingTime(){
		long between;
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(startT);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(endT);

		between = cal2.getTime().getTime()-cal1.getTime().getTime();
		if(between<0) {
			time =( (cal2.getTime().getTime()-cal1.getTime().getTime())/1000)/60;//得到通话时长
		} else {
			time = (between/1000+59)/60;
		}
		//判断接通时间是否在发生转换的日期
		if((cal1.get(Calendar.MONTH) ==Calendar.MARCH && cal1.get(Calendar.DATE) >= 29 && cal1.get(Calendar.DATE) <= 31 && cal1.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
			|| ((cal1.get(Calendar.MONTH) ==Calendar.APRIL && cal1.get(Calendar.DATE) >= 1 && cal1.get(Calendar.DATE) <= 4 && cal1.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)))
		{
			System.out.println("需要转换1");
			if(cal1.get(Calendar.HOUR_OF_DAY)<2 && cal2.get(Calendar.HOUR_OF_DAY)>=3) {
				time -=60;//时间从2点转换到3点减去1h
			}
		}else{
			if((cal2.get(Calendar.MONTH) ==3 && cal2.get(Calendar.DATE) >= 29 && cal2.get(Calendar.DATE) <= 31 && cal2.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
					|| ((cal2.get(Calendar.MONTH) ==4 && cal2.get(Calendar.DATE) >= 1 && cal2.get(Calendar.DATE) <= 4 && cal2.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)))
				//判断结束时间是否在转换的日期
			{
				if(cal2.get(Calendar.HOUR_OF_DAY)>3 ) {
					time -=60;
				}
			}
		}
		//将位于转换的时间段内的时间都看作已经转换之后的
		if(cal1.get(Calendar.MONTH) ==Calendar.OCTOBER && cal1.get(Calendar.DATE) >= 25 && cal1.get(Calendar.DATE) <= 31 && cal1.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
		{
			if(cal1.get(Calendar.HOUR_OF_DAY) ==2){
						time +=60;
			}
		}else{
			if(cal2.get(Calendar.MONTH) ==Calendar.OCTOBER && cal2.get(Calendar.DATE) >= 25 && cal2.get(Calendar.DATE) <= 31 && cal2.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
			{
				if(cal2.get(Calendar.HOUR_OF_DAY)==2)
				{
						time +=60;
				}
			}
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
