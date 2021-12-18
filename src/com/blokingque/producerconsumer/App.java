package com.blokingque.producerconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {

	public static void main(String[] args) throws InterruptedException {

		List<Producer> producers = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			producers.add(new Producer());
		}

		List<Consumer> consumers = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			consumers.add(new Consumer());
		}

		System.out.println("Producers and Consumers launched");

		List<Callable<String>> producersAndConsumers = new ArrayList<Callable<String>>();

		producersAndConsumers.addAll(producers);
		producersAndConsumers.addAll(consumers);

		ExecutorService executerService = Executors.newFixedThreadPool(8);

		try {

			List<Future<String>> features = executerService.invokeAll(producersAndConsumers);

			features.forEach((feature) -> {
				try {
					System.out.println(feature.get());

				} catch (InterruptedException | ExecutionException e) {
					System.out.println("Exception: " + e.getMessage());
				}
			});

		} finally {
			executerService.shutdown();
			System.out.println("Executor service shut down");
		}

	}
}
