package com.thfund.mdas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProcessTest {
	
	private static ExecutorService pool = Executors.newFixedThreadPool(2);

	public static void main(String[] args) {
		pool.submit(new Runnable(){

			public void run() {
			System.out.println(Thread.currentThread().getName()+" run...");
				try {
					Thread.sleep(5000);
					System.exit(0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		System.out.println("pool submited");
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			
			 public void run() {
				 System.out.println("process shutdown!");
			 }
		});
	}

}
