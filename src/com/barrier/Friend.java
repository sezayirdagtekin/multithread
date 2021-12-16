package com.barrier;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Friend implements Callable<String> {

	private CyclicBarrier barier;

	public Friend(CyclicBarrier barier) {
		this.barier = barier;
	}
	
	Random  random= new Random();

	@Override
	public String call() throws Exception {
	    //Arrive time
		Thread.sleep((random.nextInt(20)*100)+100);
		System.out.println("I just arrived,wating  for the others!");
		barier.await(5, TimeUnit.SECONDS);
		System.out.println("lets go to the cineama!");
		return "OK";
	}

}
