package com.kkbnart.animalshougi.controller;

public class FpsManager {
	private double fps;
	private long elapsedTime;
	private long prevTime;
	private long nowTime;

	public FpsManager(){
		fps			 = 0.0;
		prevTime 	 = System.currentTimeMillis();
		nowTime  	 = prevTime;
		elapsedTime = 0l;
	}
	
	public long getElapsedTime(){
		nowTime  = System.currentTimeMillis();
		return elapsedTime = nowTime - prevTime;
	}
	
	public long getElapsedTimeAndMeasure(){
		nowTime  = System.currentTimeMillis();
		elapsedTime = nowTime - prevTime;
		fps			 = elapsedTime>0 ? 1000.0 / (double)elapsedTime : 1000.0;
		prevTime	 = nowTime;
		return elapsedTime;
	}
	
	public double measure(){
		nowTime  	 = System.currentTimeMillis();
		elapsedTime = nowTime - prevTime;
		fps			 = elapsedTime>0 ? 1000.0 / (double)elapsedTime : 1000.0;
		prevTime	 = nowTime;
		return fps;
	}
	
	public double getNowFPS(){
		return fps;
	}
}