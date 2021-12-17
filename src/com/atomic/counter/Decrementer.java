package com.atomic.counter;


public class Decrementer implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 1_000; i++) {
			Data.decrement();
		}
	}

}

