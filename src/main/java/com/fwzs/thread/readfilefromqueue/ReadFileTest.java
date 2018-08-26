package com.fwzs.thread.readfilefromqueue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ReadFileTest {

	public static void main(String[] args) throws IOException {
		test("495_500001_20180530092025.txt", 3);
	}

	private static void test(String inFileName, int threadNum) throws IOException {
		long start = System.currentTimeMillis();

		File inFile = new File(inFileName);
		String outFileName = inFileName.substring(0, inFileName.indexOf(".")) + "_copy.txt";
		BufferedWriter bfw1 = new BufferedWriter(new FileWriter(new File(outFileName)));

		try {
			String line;
			ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
			try (BufferedReader bf = new BufferedReader(new FileReader(inFile))) {
				while ((line = bf.readLine()) != null) {
					queue.add(line);
				}
			}

			for (int i = 0; i < threadNum; i++) {
				new Thread(new ReadFileFromQueueThread(bfw1, queue)).start();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			long end = System.currentTimeMillis();
			System.out.println("花费时间" + (end - start) / 1000 + "秒");
		}
	}
}
