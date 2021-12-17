package com.atomic.counter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {

	public static void main(String[] args) {

		var service = Executors.newFixedThreadPool(8);

		List<Future<?>> futures = new ArrayList<>();

		try {
			for (int i = 0; i < 4; i++) {
				futures.add(service.submit(new Incrementer()));
			}
			for (int i = 0; i < 4; i++) {
				futures.add(service.submit(new Decrementer()));
			}
			futures.forEach(future -> {
				try {
					future.get();
				} catch (InterruptedException | ExecutionException e) {
					System.out.println("Eror ocurs:" + e.getMessage());
				}
			});

			System.out.println("Count is:" + Data.getCount());
		} finally {
			service.shutdown();
		}

	}

}
