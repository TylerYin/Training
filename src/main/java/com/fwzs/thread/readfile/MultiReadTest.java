package com.fwzs.thread.readfile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

public class MultiReadTest {
	/**
	 * 多线程读取文件测试
	 *
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		test("src/495_500001_20180530092025.txt", 3);
	}

	private static void test(String fileName, int threadNumber) throws IOException{
		long start = System.currentTimeMillis();

		CountDownLatch doneSignal = new CountDownLatch(threadNumber);
		RandomAccessFile[] outArr = new RandomAccessFile[threadNumber];

		String out_file_name = fileName.substring(0, fileName.indexOf(".")) + "_copy.txt";
		BufferedWriter bfw = new BufferedWriter(new FileWriter(new File(out_file_name), true));
		try {
			File file = new File(fileName);
			long length = file.length();
			System.out.println("文件总长度：" + length/(1024*1024) + "MB");

			// 每线程应该读取的字节数
			long numPerThred = length / threadNumber;
			System.out.println("每个线程读取的字节数：" + numPerThred/(1024*1024) + "MB");

			// 整个文件整除后剩下的余数
			long left = length % threadNumber;
			for (int i = 0; i < threadNumber; i++) {
				outArr[i] = new RandomAccessFile(file, "r");
				if (i == threadNumber - 1) {
					new ReadThread(i * numPerThred, (i + 1) * numPerThred + left, outArr[i], bfw, doneSignal).start();
				} else {
					new ReadThread(i * numPerThred, (i + 1) * numPerThred, outArr[i], bfw, doneSignal).start();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			doneSignal.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("花费时间" + (end - start) / 1000 + "秒");
	}
}

class ReadThread extends Thread {
	private long end;
	private long start;
	private int curCount = 0;
	private int BUFFER_LENGTH = 256;

	private BufferedWriter bfw;
	private RandomAccessFile raf;
	private CountDownLatch doneSignal;

	public ReadThread(long start, long end, RandomAccessFile raf, BufferedWriter bfw, CountDownLatch doneSignal) {
		this.start = start;
		this.end = end;
		this.raf = raf;
		this.bfw = bfw;
		this.doneSignal = doneSignal;
	}

	public void run() {
		try {
			raf.seek(start);
			int hasRead = 0;
			long contentLen = end - start;
			long times = contentLen / BUFFER_LENGTH + 1;
			System.out.println(this.toString() + " 需要读的次数：" + times);

			String result = null;
			byte[] buff = new byte[BUFFER_LENGTH];
			for (int i = 0; i < times; i++) {
				hasRead = raf.read(buff);
				if (hasRead < 0) {
					break;
				}

				result = new String(buff, "gb2312");
				bfw.newLine();
				bfw.write(result);
			}
			doneSignal.countDown();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public RandomAccessFile getRaf() {
		return raf;
	}

	public void setRaf(RandomAccessFile raf) {
		this.raf = raf;
	}

	public int getCountByKeywords(String statement, String key) {
		return statement.split(key).length - 1;
	}

	public int getCurCount() {
		return curCount;
	}

	public void setCurCount(int curCount) {
		this.curCount = curCount;
	}

	public CountDownLatch getDoneSignal() {
		return doneSignal;
	}

	public void setDoneSignal(CountDownLatch doneSignal) {
		this.doneSignal = doneSignal;
	}
}
