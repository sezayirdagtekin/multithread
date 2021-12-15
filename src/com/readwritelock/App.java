package com.readwritelock;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService executerService = Executors.newFixedThreadPool(4);
		
		System.out.println("Adding value to Map...");
		try {
			for (int i = 0; i < 4; i++) {
		   Future<String> future = executerService.submit(new Producer());
		   System.out.println(future.get());
			}
		} finally {
			executerService.shutdown();
		}
		

	}

}
