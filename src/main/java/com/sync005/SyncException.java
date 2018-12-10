package com.sync005;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * synchronized异常
 *
 */
public class SyncException {

	private int i = 0;
	public synchronized void operation(){
		while(true){
			try {
				i++;
				Thread.sleep(100);
				System.out.println(Thread.currentThread().getName() + " , i = " + i);
				if(i == 20){
//					Integer.parseInt("a");
					throw new RuntimeException();
//					throw new InterruptedException();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
//				continue;
			}
		}
	}

	public static void main(String[] args) {
		
		/*final SyncException se = new SyncException();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				se.operation();
			}
		},"t1");
		t1.start();*/





	}


}
