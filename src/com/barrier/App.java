package com.barrier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {

	public static void main(String[] args) {

		Runnable barrierAction = () -> System.out.println("Barier opening!!!");
		CyclicBarrier barier = new CyclicBarrier(4, barrierAction);
		
		var service = Executors.newFixedThreadPool(4);
		List<Future<String>> futures = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			Future<String> future = service.submit(new Friend(barier));
			futures.add(future);

		}

		futures.forEach((future) -> {
			try {
				System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {

				e.printStackTrace();
			} finally {
				service.shutdown();
				//System.out.println("Executor service shut down");
			}
		});

	}

}
