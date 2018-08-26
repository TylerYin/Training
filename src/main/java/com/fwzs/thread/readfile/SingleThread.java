package com.fwzs.thread.readfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SingleThread {
	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();

		File file = new File("src/495_500001_20180530092025.txt");
		try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
			String line = null;
			while ((line = bf.readLine()) != null) {
				System.out.println(line);
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("花费时间" + (end - start) / 1000 + "秒");
	}
}
