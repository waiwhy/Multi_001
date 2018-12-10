package com.sync007;
/*
*  volatile 使变量在多个线程间可见   （总线加锁   缓存一致性协议 ）可见性
*/
public class RunThread extends Thread{

	private  boolean isRunning = true;
	private void setRunning(boolean isRunning){
		this.isRunning = isRunning;
	}

	public void run(){
		System.out.println("进入run方法..");
		int i = 0;
		while(isRunning == true){
			//..
	/*	try {
				Thread.sleep(100);
			}catch (Exception e){
				e.printStackTrace();
			}

			System.out.println("---------");*/
		}
		System.out.println("线程停止");
	}
	
	public static void main(String[] args) throws InterruptedException {
		RunThread rt = new RunThread();
		rt.start();
		Thread.sleep(1000);
		rt.setRunning(false);
		System.out.println("isRunning的值已经被设置了false");

//		Thread.sleep(1000);
//		System.out.println(rt.isRunning);
	}
	
	
}
