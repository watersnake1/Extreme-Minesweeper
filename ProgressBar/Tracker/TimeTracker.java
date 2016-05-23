package Tracker;

import java.util.TimerTask;

public class TimeTracker extends TimerTask
{
	private static int timeSecond = 0, timeMinute = 0;
	private static String time = "0:00";

	@Override
	public void run() 
	{
		timeSecond++;
		
		if(timeSecond == 60){timeSecond = 0;timeMinute ++;}
		if(timeSecond < 10){time = timeMinute + ":0" + timeSecond;}
		else{time = timeMinute + ":" + timeSecond;}
	}

	public static int getTimeSecond() 
	{
		return timeSecond;
	}

	public static int getTimeMinute() 
	{
		return timeMinute;
	}

	public static String getTime() 
	{
		return time;
	}
}
