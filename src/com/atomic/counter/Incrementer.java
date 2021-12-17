package com.atomic.counter;

public class Incrementer implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 1_000; i++) {	
			Data.increment();
		}
	}

}
