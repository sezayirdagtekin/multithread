package com.producerconsumer;

import java.util.ArrayList;
import java.util.List;

public class BufferedData {

	private static List<Integer> buffer = new ArrayList<Integer>();

	public static List<Integer> getBuffer() {
		return buffer;
	}

	public static boolean isEmpty(List<Integer> buffer) {
		return buffer.size() == 0;
	}

	public static boolean isFull(List<Integer> buffer) {
		return buffer.size() == 10;
	}
}
